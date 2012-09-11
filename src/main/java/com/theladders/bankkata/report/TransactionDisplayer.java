package com.theladders.bankkata.report;

import com.theladders.bankkata.money.Mint;
import com.theladders.bankkata.money.Money;
import com.theladders.bankkata.report.violators.MoneyDisplayer;
import com.theladders.bankkata.report.violators.TransactionAmount;
import com.theladders.bankkata.report.violators.TransactionDate;
import com.theladders.bankkata.transaction.Transaction;

// display individual transaction for report
public class TransactionDisplayer {
    Money balance = (new Mint()).printNothing();
    
	// display individual transaction for report
	private String displaySign(Transaction transaction) {
		if (transaction.isWithdrawal())
			return "-";
		return "";
	}

	private String displayDate(Transaction transaction) {
		return (String) transaction.violate(new TransactionDate());
	}

	private String displayMoney(Money balance) {
		return (String) balance.violate(new MoneyDisplayer());
	}

	public String display(Transaction transaction) {
		balance = transaction.accumulateBalance(balance);
		return displayDate(transaction) + " : " + displaySign(transaction) + getAmount(transaction)
				+ " : " + displayMoney(balance);

	}

	public String getAmount(Transaction transaction) {
		return (String) transaction.violate(new TransactionAmount());
	}

}
