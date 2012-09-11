package com.theladders.bankkata.report;

import com.theladders.bankkata.transaction.Transaction;
import com.theladders.bankkata.transaction.TransactionsFunction;

public class TransactionAdder implements TransactionsFunction {
	private StatementDisplayer displayer;

	public TransactionAdder(StatementDisplayer displayer) {
		this.displayer = displayer;
	}

	public Object apply(Transaction transaction) {
		// TODO Auto-generated method stub
		displayer.add(transaction);
		return null;
	}

}
