package com.theladders.bankkata.report;

import com.theladders.bankkata.transaction.Transaction;

public class TransactionDisplayer {

	private String displayColumn(Transaction transaction)
	{
		if (transaction.isDeposit())
			return getAmount(transaction) + "||||";
		return "||" + getAmount(transaction) + "||";
	}
	public String display(Transaction transaction)
	{
		
	}
	
	public String getAmount(Transaction transaction)
	{
		if transaction.violate(new TransactionAmount());
	}

}
