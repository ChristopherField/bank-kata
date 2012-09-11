package com.theladders.bankkata.report;

import java.io.PrintStream;

import com.theladders.bankkata.transaction.Transaction;
import com.theladders.bankkata.transaction.TransactionsFunction;

// go through list of transactions to display for report
public class DailyDisplayer implements TransactionsFunction {
	private PrintStream stream;
	private TransactionDisplayer displayer;

	public DailyDisplayer(PrintStream stream, TransactionDisplayer displayer) {
		this.stream = stream;
		this.displayer = displayer;
	}

	// display each transaction for report
	public Object apply(Transaction transaction) {
		stream.println(displayer.display(transaction));
		return null;
	}

}
