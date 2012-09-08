package com.theladders.bankkata.transaction;

public class InsufficientFunds extends Exception
{
  public InsufficientFunds()
  {
    super("Insufficient Funds");
  }
}
