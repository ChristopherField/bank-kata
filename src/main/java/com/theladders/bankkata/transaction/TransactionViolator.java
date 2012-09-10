package com.theladders.bankkata.transaction;

import com.theladders.bankkata.money.Money;
import com.theladders.bankkata.time.Time;

public interface TransactionViolator {
  public Object violate(Time time, Money money);
}
