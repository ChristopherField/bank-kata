package com.theladders.bankkata.report.violators;

import com.theladders.bankkata.money.Amount;
import com.theladders.bankkata.money.MoneyViolator;
import com.theladders.bankkata.money.Type;

public class MoneyDisplayer implements MoneyViolator {

	public String getSign(Type type)
	{
		Type negative = Type.credit();
		if (type.equals(negative))
		{
			return "-";
		}
		return "";
	}
	public Object violate(Amount amount, Type type) {
		return getSign(type) + amount.violate(new AmountDisplayer());
	}
	
}
