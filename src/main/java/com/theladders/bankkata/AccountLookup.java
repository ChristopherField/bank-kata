package com.theladders.bankkata;

import java.util.Map;
import java.util.HashMap;

public class AccountLookup
{
  Map <AccountNumber, Account> accountMap = new HashMap<AccountNumber, Account>();
  public Account getAccount(AccountNumber number)
  {
    return accountMap.get(number);
  }
  public boolean hasAccount(AccountNumber number)
  {
    return accountMap.containsKey(number);
  }
  
  public void addAccount(AccountNumber number, Account account)
  {
    accountMap.put(number, account);
  }
  
}
