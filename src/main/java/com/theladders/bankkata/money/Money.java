package com.theladders.bankkata.money;

// Needs a new name..... no such thing as negative money...doh gonna mess up Mint....
public class Money
{
  Amount amount;
  Type type;
  Money(int dollars, int cents, Type type)
  {
    amount = new Amount(dollars, cents);
    this.type = type;
  }
  private Money(Amount amount, Type type)
  {
    if (amount.isNegative())
    {
      amount = amount.negate();
      type = type.flip();
    }
    if (amount.isZero())
    {
      type = type.zeroType();
    }
    this.amount = amount;
    this.type = type;
  }
  private boolean differentSigns(Money other)
  {
    return type.equals(other.type);
  }
  public Money add(Money other)
  {
    Amount otherAmount = other.amount;
    if (differentSigns(other))
     otherAmount = otherAmount.negate();
    Amount answer = amount.add(otherAmount);
    return new Money(answer, type);
  }
  @Override
  public boolean equals(Object o)
  {
    return o instanceof Money && this.amount.equals(((Money)o).amount) && this.type == ((Money)o).type;
  }
  @Override
  public String toString()
  {
    return type.toString() + amount.toString();
  }
}
