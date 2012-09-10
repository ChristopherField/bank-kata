package com.theladders.bankkata.report.violators;

import com.theladders.bankkata.money.Money;
import com.theladders.bankkata.time.Time;

public class TransactionDate implements com.theladders.bankkata.transaction.TransactionViolator {

	public Object violate(Time time, Money money) {
		return time.toString();
	}

}
