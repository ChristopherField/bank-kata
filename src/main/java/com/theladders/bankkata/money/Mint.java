package com.theladders.bankkata.money;


// AWESOME!!!!!
public class Mint
{
  public Money printDebit(int dollars, int cents)
  {
    return new Money(dollars, cents, Type.debit());
  }
  public Money printCredit(int dollars, int cents)
  {
    return new Money(dollars, cents, Type.credit());
  }
  
  public Money printNothing()
  {
    return new Money(0, 0, Type.credit());
  }

}
