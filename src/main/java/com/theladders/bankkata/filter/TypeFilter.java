package com.theladders.bankkata.filter;
import static com.theladders.bankkata.filter.FilterType.*;

import com.theladders.bankkata.transaction.Transaction;

public class TypeFilter {	
  private FilterType type;
  public TypeFilter(FilterType type)
  {
	  this.type = type;
  }
  public TypeFilter()
  {
	  this.type = null;
  }
  public boolean meetsCriteria(Transaction transaction)
  {
	  boolean isDeposit = transaction.isDeposit();
	  boolean isWithdrawal = transaction.isWithdrawal();
	  return (type == null || (type == DEPOSIT && isDeposit) || (type == WITHDRAWAL && isWithdrawal));
  }
}
