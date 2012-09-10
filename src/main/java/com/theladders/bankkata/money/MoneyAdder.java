package com.theladders.bankkata.money;

public class MoneyAdder {
  private static Money adjustMoney(Amount amount, Type type)
  {
	 if (amount.isNegative())
	 {
	   amount = amount.negate();
	   type = type.flip();
	 }
	 if (amount.isZero())
	 {
	   type = Type.zeroType();
	 }
	 return new Money(amount, type);
  }

  private static boolean differentSigns(Type type1, Type type2)
  {
    return !type1.equals(type2);
  }

  public static Money add(Amount amount, Type type, Amount otherAmount, Type otherType)
  {
	if (differentSigns(type, otherType))
	  otherAmount = otherAmount.negate();
	Amount answer = amount.add(otherAmount);
	return adjustMoney(answer, type);
  }
  
}
