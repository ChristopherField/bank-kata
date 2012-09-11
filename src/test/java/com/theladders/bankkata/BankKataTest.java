package com.theladders.bankkata;

import static org.junit.Assert.*;

import java.io.PrintStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Test;

import com.theladders.bankkata.exception.InsufficientFunds;
import com.theladders.bankkata.filter.DateRange;
import com.theladders.bankkata.filter.FilterType;
import com.theladders.bankkata.filter.TransactionFilter;
import com.theladders.bankkata.filter.TypeFilter;
import com.theladders.bankkata.money.Mint;
import com.theladders.bankkata.money.Money;
import com.theladders.bankkata.time.Time;
import com.theladders.bankkata.time.TimeKeeper;
import com.theladders.bankkata.report.Statement;
import com.theladders.bankkata.report.StatementDisplayer;
import com.theladders.bankkata.report.TransactionDisplayer;

public class BankKataTest {
	private static DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
	private Mint mint = new Mint();
	private TimeKeeper defaultTimer = new TimeKeeper();

	private Date givenDate(String dateString) throws Exception {
		return dateFormat.parse(dateString);
	}

	@Test
	public void bankShouldAcceptDeposits() throws Exception {
		String accountNumber = "4570123";
		Bank bank = new Bank(defaultTimer);
		bank.openAccount(accountNumber);
		bank.deposit(accountNumber, 32, 68);
		assertBalance(accountNumber, 32, 68, bank.balance(accountNumber));

	}

	@Test
	public void bankShouldAcceptDepositsForDifferentCustomers()
			throws Exception {
		String customer = "64732123";
		String anotherCustomer = "123245";
		Bank bank = new Bank(defaultTimer);
		bank.openAccount(customer);
		bank.openAccount(anotherCustomer);
		bank.deposit(customer, 32, 68);
		bank.deposit(anotherCustomer, 76, 32);
		assertBalance(customer, 32, 68, bank.balance(customer));
		assertBalance(anotherCustomer, 76, 32, bank.balance(anotherCustomer));
	}

	@Test
	public void bankShouldAllowMultipleDeposits() throws Exception {
		String accountNumber = "12345";
		Bank bank = new Bank(defaultTimer);
		bank.openAccount(accountNumber);
		bank.deposit(accountNumber, 3, 50);
		assertBalance(accountNumber, 3, 50, bank.balance(accountNumber));

		bank.deposit(accountNumber, 10, 50);
		assertBalance(accountNumber, 14, 0, bank.balance(accountNumber));
	}

	private String givenCustomerWithBalance(Bank bank, String accountNumber,
			int dollars, int cents) throws Exception {
		bank.openAccount(accountNumber);
		bank.deposit(accountNumber, dollars, cents);
		return accountNumber;
	}

	private void assertBalance(String account, int expectedDollars,
			int expectedCents, Money balance) {
		assertEquals("Wrong balance for: " + account,
				mint.printDebit(expectedDollars, expectedCents), balance);
	}

	private void assertNegativeBalance(String account, int expectedDollars,
			int expectedCents, Money balance) {
		assertEquals("Wrong balance for: " + account,
				mint.printCredit(expectedDollars, expectedCents), balance);
	}

	@Test
	public void bankShouldAcceptWithdrawalsForDifferentCustomers()
			throws Exception {
		Bank bank = new Bank(defaultTimer);
		String customer1 = givenCustomerWithBalance(bank, "1", 350, 10);
		String customer2 = givenCustomerWithBalance(bank, "2", 20, 10);
		bank.withdraw(customer1, 3, 25);
		bank.withdraw(customer2, 7, 5);
		assertBalance(customer1, 346, 85, bank.balance(customer1));
		assertBalance(customer2, 13, 5, bank.balance(customer2));
	}

	@Test(expected = InsufficientFunds.class)
	public void bankShouldRejectWithdrawalsWithoutEnoughFunds()
			throws Exception {
		Bank bank = new Bank(defaultTimer);
		String account = givenCustomerWithBalance(bank, "1", 356, 10);
		bank.withdraw(account, 357, 11);
	}

	@Test
	public void bankShouldAllowTransfersBetweenCustomers() throws Exception {
		Bank bank = new Bank(defaultTimer);
		String customer1 = givenCustomerWithBalance(bank, "1", 356, 10);
		String customer2 = givenCustomerWithBalance(bank, "2", 10, 0);
		bank.transfer(customer1, customer2, 356, 10);
		assertBalance(customer1, 0, 0, bank.balance(customer1));
		assertBalance(customer2, 366, 10, bank.balance(customer2));
	}

	@Test(expected = InsufficientFunds.class)
	public void bankShouldRejectTransfersWithoutEnoughFunds() throws Exception {
		Bank bank = new Bank(defaultTimer);
		String customerOne = givenCustomerWithBalance(bank, "1", 356, 10);
		String customerTwo = givenCustomerWithBalance(bank, "2", 10, 0);
		bank.transfer(customerOne, customerTwo, 357, 11);
	}

	// Statement Test dataset
	private Bank givenBankWithTransactionsForCustomer(String accountNumber)
			throws Exception {
		TimeKeeper time = new TimeKeeper(givenDate("1/1/2011"),
				givenDate("1/2/2011"), givenDate("1/3/2011"),
				givenDate("12/14/2011"));
		Bank b = new Bank(time);
		b.openAccount(accountNumber);
		b.deposit(accountNumber, 3, 50);
		b.withdraw(accountNumber, 1, 30);
		b.withdraw(accountNumber, 2, 0);
		b.deposit(accountNumber, 100, 1);

		return b;

	}

	private void printStatement(String testName, Statement statement,
			PrintStream stream) {
		stream.println("----------------------BEGIN TEST STATEMENT ("
				+ testName + ")------------------------");
		StatementDisplayer displayer = statement.display();
		displayer.display(new TransactionDisplayer(), stream);
		stream.println("----------------------END TEST----------------------------------------------------------");
		stream.flush();
	}

	/**
	 * Not sure how to test, but works okay in test runner window for me.
	 * Depending upon system being run on, may need to check reportFile for
	 * report....
	 * 
	 * @throws Exception
	 */
	@Test
	public void bankShouldPrintTransactions() throws Exception {
		String account = "42";
		Bank b = givenBankWithTransactionsForCustomer(account);
		Statement statement = b.accountStatement(account);
		printStatement("bankShouldPrintTransactions", statement, System.out);
	}

	/**
	 * For filtering tests, performed tests on Statement list but not really
	 * sure how to test report printing it appears
	 * 
	 * @throws Exception
	 */
	@Test
	public void bankShouldBeAbleToPrintOnlyDeposits() throws Exception {
		String account = "42";
		Bank b = givenBankWithTransactionsForCustomer(account);
		Statement statement = b.accountStatement(account);
		statement = statement.filter(new TransactionFilter(
				DateRange.allDates(), new TypeFilter(FilterType.DEPOSIT)));
		assertBalance(account, 103, 51, statement.statementBalance());
		printStatement("bankShouldBeAbleToPrintOnlyDeposits", statement,
				System.out);

	}

	@Test
	public void bankShouldBeAbleToPrintOnlyWithdrawals() throws Exception {
		String account = "42";
		Bank b = givenBankWithTransactionsForCustomer(account);
		Statement statement = b.accountStatement(account);
		statement = statement.filter(new TransactionFilter(
				DateRange.allDates(), new TypeFilter(FilterType.WITHDRAWAL)));
		assertNegativeBalance(account, 3, 30, statement.statementBalance());
		printStatement("bankShouldBeAbleToPrintOnlyWithdrawals", statement,
				System.out);

	}

	private Time givenTime(String time) throws Exception {
		return new Time(givenDate(time));
	}

	@Test
	public void bankShouldBeAbleToPrintOnlyTransactionTimes() throws Exception {
		String account = "42";
		Bank b = givenBankWithTransactionsForCustomer(account);
		Statement statement = b.accountStatement(account);
		statement = statement.filter(new TransactionFilter(new DateRange(
				givenTime("1/2/2011"), givenTime("1/2/2011")), TypeFilter
				.allTypes()));
		assertNegativeBalance(account, 1, 30, statement.statementBalance());
		printStatement("bankShouldBeAbleToPrintOnlyTransactionTimes",
				statement, System.out);
	}
}
