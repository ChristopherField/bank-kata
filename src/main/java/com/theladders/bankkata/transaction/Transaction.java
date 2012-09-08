package com.theladders.bankkata.transaction;

import com.theladders.bankkata.time.Time;

import com.theladders.bankkata.money.Money;

public class Transaction
{
  private Time time;
  private Money money;

  public Transaction(Time time, Money money)
  {
    this.time = time;
    this.money = money;
  }
  
  Money accumulateBalance(Money currentBalance)
  {
    return currentBalance.add(money);
  }

}
