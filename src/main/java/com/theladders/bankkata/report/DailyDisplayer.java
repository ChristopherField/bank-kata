package com.theladders.bankkata.report;

import java.io.PrintStream;

import com.theladders.bankkata.transaction.Transaction;
import com.theladders.bankkata.transaction.TransactionsFunction;

public class DailyDisplayer implements TransactionsFunction {

	private PrintStream stream;
	private TransactionDisplayer displayer;
	public DailyDisplayer(PrintStream stream, TransactionDisplayer displayer)
	{
		this.stream = stream;
		this.displayer = displayer;
	}
	public Object apply(Transaction transaction) {
		displayer.display(transaction);
		return null;
	}

}
