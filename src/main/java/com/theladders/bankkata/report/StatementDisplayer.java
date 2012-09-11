package com.theladders.bankkata.report;
import java.io.PrintStream;
import java.util.Map;
import java.util.List;
import java.util.TreeMap;
import java.util.LinkedList;

import com.theladders.bankkata.Account;
import com.theladders.bankkata.report.violators.TransactionAmount;
import com.theladders.bankkata.report.violators.TransactionDate;


import com.theladders.bankkata.transaction.Transaction;
import com.theladders.bankkata.transaction.Transactions;
public class StatementDisplayer {
	private Account account;
	private Map<String, Transactions> dailyMap = new TreeMap<String, Transactions>();
	
	public StatementDisplayer(Account account)
	{
		this.account = account;
	}
	public void add(Transaction transaction)
	{
		TransactionDate dateGetter = new TransactionDate();
		String date = (String) transaction.violate(dateGetter);
		addToMap(date, transaction);
	}
	private void addToMap(String date, Transaction transaction)
	{
		if (!dailyMap.containsKey(date))
		{
			dailyMap.put(date, new Transactions());
		}
		Transactions list = dailyMap.get(date);
		list.add(transaction);
	}
	
	private void displayDaily(PrintStream stream, TransactionDisplayer displayer, Transactions transactions)
	{
		transactions.map(new DailyDisplayer(stream, displayer));
		
	}

	public void display(TransactionDisplayer displayer, PrintStream stream)
	{
		stream.println("******** STATEMENT FOR " + account + "***************************");
		stream.println("Trans Date : Amount : Balance");
		stream.println("-----------------------------");
		for (Transactions transactions: dailyMap.values())
		{
			displayDaily(stream, displayer, transactions);
		}
		stream.println("**************** END STATEMENT FOR " + account + "***************");
	}
}
