package com.theladders.bankkata.money;

public class DebitType extends Type
{
  @Override
  protected String getIdentity()
  {
    return "DEBIT";
  }
  
  @Override
  public Type flip()
  {
    return super.credit();
  }

}
