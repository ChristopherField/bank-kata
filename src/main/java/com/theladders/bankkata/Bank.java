package com.theladders.bankkata;

import com.theladders.bankkata.exception.AccountExists;
import com.theladders.bankkata.exception.InsufficientFunds;
import com.theladders.bankkata.exception.NoSuchAccount;
import com.theladders.bankkata.filter.TransactionFilter;
import com.theladders.bankkata.money.Money;
import com.theladders.bankkata.report.Statement;
import com.theladders.bankkata.time.Time;
import com.theladders.bankkata.time.TimeKeeper;
import com.theladders.bankkata.transaction.Transactions;

public class Bank {
	private AccountLookup accounts = new AccountLookup();
	private TimeKeeper timer;

	public Bank(TimeKeeper timer) {
		this.timer = timer;
	}

	public void openAccount(String number) throws AccountExists {
		AccountNumber accountNumber = new AccountNumber(number);
		if (accounts.hasAccount(accountNumber))
			throw new AccountExists();
		accounts.addAccount(accountNumber, new Account(accountNumber));

	}

	private Account getAccount(String accountNumber) throws NoSuchAccount {
		AccountNumber number = new AccountNumber(accountNumber);
		if (!accounts.hasAccount(number))
			throw new NoSuchAccount(number.toString());
		return accounts.getAccount(number);
	}

	public void deposit(String accountNumber, int dollars, int cents)
			throws NoSuchAccount {
		Account account = getAccount(accountNumber);
		account.deposit(timer.getTime(), dollars, cents);
	}

	public void withdraw(String accountNumber, int dollars, int cents)
			throws NoSuchAccount, InsufficientFunds {
		Account account = getAccount(accountNumber);
		account.withdraw(timer.getTime(), dollars, cents);
	}

	public void transfer(String accountFrom, String accountTo, int dollars,
			int cents) throws NoSuchAccount, InsufficientFunds {
		Time time = timer.getTime();
		Account from = getAccount(accountFrom);
		Account to = getAccount(accountTo);
		from.withdraw(time, dollars, cents);
		to.deposit(time, dollars, cents);
	}

	public Money balance(String accountNumber) throws NoSuchAccount {
		Account account = getAccount(accountNumber);
		return account.computeBalance();
	}
	public Statement accountStatement(String accountNumber) throws NoSuchAccount
	{
		return new Statement(getAccount(accountNumber));
	}
	

}
