package com.theladders.bankkata;

import com.theladders.bankkata.money.Money;
import com.theladders.bankkata.transaction.Transactions;

public class Account
{
  AccountNumber number;
  Transactions transactions;
  Money computeBalance()
  {
    return transactions.computeBalance();
  }     
  
  
  
  public void deposit(Time time, Amount amount)
  {
    transactions.add(time, amount);
  }
  
  public void withdraw(Time time, Money amount) throws InsufficientFunds
  {
    
  }
}
