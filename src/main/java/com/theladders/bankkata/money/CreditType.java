package com.theladders.bankkata.money;

public class CreditType extends Type
{
  @Override
  protected String getIdentity()
  {
    return "CREDIT";
  }
  
  @Override
  public Type flip()
  {
    return super.debit();
  }
}
