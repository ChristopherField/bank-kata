package com.theladders.bankkata.report;

import com.theladders.bankkata.Account;
import com.theladders.bankkata.filter.TransactionFilter;
import com.theladders.bankkata.money.Money;
import com.theladders.bankkata.time.Time;
import com.theladders.bankkata.transaction.Transaction;
import com.theladders.bankkata.transaction.Transactions;
import com.theladders.bankkata.transaction.TransactionsFunction;

public class Statement {
  private Account account;
  private Transactions transactions;
  

  public Statement(Account account)
  {
	  this.account = account;
	  transactions = account.filter(TransactionFilter.getNoFilter());
  }
  
  private Statement(Account account, Transactions transactions)
  {
	  this.account = account;
	  this.transactions = transactions;
  }
  
  // filters are closed, should enable better filtering in steps
  public Statement filter(TransactionFilter filter)
  {
	  return new Statement(account, transactions.filter(filter));
  }
  
  /**
   *  encapsulation should be violated here to allow plugable displayers in full implementation
   *  Instead of just stripping it out, recommend turning StatementDisplayer into a 3rd instance variable using the strategy pattern
   * @return StatementDisplayer to display statement
   */
  public StatementDisplayer display()
  {
	  StatementDisplayer displayer = new StatementDisplayer(account);
	  transactions.map(new TransactionAdder(displayer));
	  return displayer;
  }
  
  public Money statementBalance()
  {
	  return transactions.computeBalance(Time.MAX_TIME);
  }
  
}
