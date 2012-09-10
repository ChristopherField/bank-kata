package com.theladders.bankkata.transaction;

import java.util.LinkedList;
import java.util.List;

import com.theladders.bankkata.filter.TransactionFilter;
import com.theladders.bankkata.money.Mint;
import com.theladders.bankkata.money.Money;
import com.theladders.bankkata.time.Time;

public class Transactions {
	private List<Transaction> transactions = new LinkedList<Transaction>();

	public void add(Transaction transaction) {
		transactions.add(transaction);
	}

	private void addFiltered(Transactions filteredList,
			TransactionFilter filter, Transaction transaction) {
		if (filter.meetsCriteria(transaction)) {
			filteredList.add(transaction);
		}

	}

	// worth extra for type safe filter that satisfies closure property
	public Transactions filter(TransactionFilter filter)
	{
		Transactions filteredList = new Transactions();
		for (Transaction transaction: transactions)
		{
			addFiltered(filteredList, filter, transaction);
		}
		return filteredList;
	}

	public List<Object> map(TransactionsFunction function) {
		List<Object> output = new LinkedList<Object>();
		for (Transaction transaction : transactions) {
			Object value = function.apply(transaction);
			output.add(value);
		}
		return output;
	}

	public Money computeBalance(Time time)
	{
		Money balance = (new Mint()).printNothing();
		for (Transaction transaction: transactions)
		{
			balance = transaction.accumulateBalance(balance);
		}
		return balance;
	}
}
