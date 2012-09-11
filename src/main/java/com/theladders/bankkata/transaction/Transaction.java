package com.theladders.bankkata.transaction;

import com.theladders.bankkata.time.Time;

import com.theladders.bankkata.filter.DateRange;
import com.theladders.bankkata.filter.TypeFilter;
import com.theladders.bankkata.money.Money;
import com.theladders.bankkata.money.Type;

public class Transaction {
	private Time time;
	private Money money;

	public Transaction(Time time, Money money) {
		this.time = time;
		this.money = money;
	}

	public Money accumulateBalance(Money currentBalance) {
		return currentBalance.add(money);
	}

	public boolean isDeposit() {
		return money.isType(Type.debit());
	}

	public boolean isWithdrawal() {
		return money.isType(Type.credit());
	}

	public Object violate(TransactionViolator violator) {
		return violator.violate(time, money);
	}

	public boolean withinRange(DateRange range) {
		return range.meetsCriteria(time);
	}

}
