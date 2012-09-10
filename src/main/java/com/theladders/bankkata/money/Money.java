package com.theladders.bankkata.money;

// Needs a new name..... no such thing as negative money...doh gonna mess up Mint....
public class Money {
	Amount amount;
	Type type;

	Money(int dollars, int cents, Type type) {
		amount = new Amount(dollars, cents);
		this.type = type;
	}

	Money(Amount amount, Type type) {
		this.amount = amount;
		this.type = type;
	}

	@Override
	public boolean equals(Object o) {
		return (o instanceof Money) && amount.compareTo(((Money) o).amount) == 0
				&& type.equals(((Money) o).type);
	}

	public Money add(Money other) {
		Amount otherAmount = other.amount;
		Type otherType = other.type;
		return MoneyAdder.add(amount, type, otherAmount, otherType);
	}

	@Override
	public String toString() {
		return type.toString() + amount.toString();
	}

	public boolean isType(Type type) {
		Type me = this.type;
		return me.equals(type);
	}

	public Object violate(MoneyViolator violator) {
		return violator.violate(amount, type);
	}
	// only used for balance
    public int compareAmounts(Money other)
    {
    	Amount otherAmount = other.amount;
    	Type otherType = other.type;
    	return amount.compareTo(otherAmount);
    }
}
