package com.theladders.bankkata.report.violators;

import com.theladders.bankkata.money.Amount;
import com.theladders.bankkata.money.Money;
import com.theladders.bankkata.money.Type;
import com.theladders.bankkata.time.Time;

public class TransactionAmount implements com.theladders.bankkata.transaction.TransactionViolator {

	public Object violate(Time time, Money money) {
		
		return money.violate(new AmountRetriever());
	}
	public static class AmountRetriever implements com.theladders.bankkata.money.MoneyViolator {

		public Object violate( Amount amount, Type type) {
			return amount.violate(new AmountDisplayer());		
		}
	}


}
