package com.theladders.bankkata;

import com.theladders.bankkata.AccountNumber;
import com.theladders.bankkata.exception.InsufficientFunds;
import com.theladders.bankkata.filter.TransactionFilter;
import com.theladders.bankkata.money.Amount;
import com.theladders.bankkata.money.Mint;
import com.theladders.bankkata.money.Money;
import com.theladders.bankkata.money.Type;
import com.theladders.bankkata.time.Time;
import com.theladders.bankkata.transaction.Transaction;
import com.theladders.bankkata.transaction.Transactions;

public class Account {
	AccountNumber number;
	Transactions transactions;

	public Account(AccountNumber accountNumber) {
		this.number = accountNumber;
		transactions = new Transactions();
	}
	
	public Money computeBalance()
	{
		return transactions.computeBalance(Time.MAX_TIME);
	}

	public Money computeBalance(Time time) {
		return transactions.computeBalance(time);
	}

	public Transactions filter(TransactionFilter filter) {
		return transactions.filter(filter);
	}

	public void deposit(Time time, int dollars, int cents) {
		Mint mint = new Mint();
		Transaction transaction = new Transaction(time, mint.printDebit(
				dollars, cents));
		transactions.add(transaction);
	}

	public void withdraw(Time time, int dollars, int cents)
			throws InsufficientFunds {
		Mint mint = new Mint();
		Money amount = mint.printCredit(dollars, cents);
		Money balance = computeBalance(Time.MAX_TIME);
		if (balance.isType(Type.credit()) || balance.compareAmounts(amount) < 0)
			throw new InsufficientFunds();
		Transaction transaction = new Transaction(time, mint.printCredit(
				dollars, cents));
		transactions.add(transaction);
	}
	
	public String toString()
	{
		return number.toString();
	}
	
}