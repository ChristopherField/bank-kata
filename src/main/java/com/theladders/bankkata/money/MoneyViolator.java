package com.theladders.bankkata.money;

// Sometimes violation of encapsulation is required to keep logic outside a class.....sorry
public interface MoneyViolator {
	public Object violate(Amount amount, Type type);
}
