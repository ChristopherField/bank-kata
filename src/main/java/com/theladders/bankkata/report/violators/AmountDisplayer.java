package com.theladders.bankkata.report.violators;

public class AmountDisplayer implements
		com.theladders.bankkata.money.AmountViolator {

	public Object violate(int dollars, int cents) {
		String leadingZero = "";
		if (cents < 10)
			leadingZero = "0";
		return "" + dollars + "." + leadingZero + cents;
	}

}
