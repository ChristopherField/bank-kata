package com.theladders.bankkata;

import static org.junit.Assert.*;

import org.junit.Test;

public class BankKataTest
{
  @Test
  public void bankShouldAcceptDeposits()
  {
    // Violated strictly TDD here....did not need to pass in Account#
    // Bank should accept deposits just needed to accept some deposits....
    // not necessarily worry about balances, account numbers, etc...
    // but oh well.....
    AccountLookup = new AccountLookup();
    Bank bank = new Bank(AccountLookup);
    bank.deposit("63221", 3);
    
    
  }


  @Test
  public void bankShouldAcceptDepositsForDifferentCustomers()
  {
    fail("unimplemented");
  }


  @Test
  public void bankShouldAllowMultipleDeposits()
  {
    fail("unimplemented");
  }


  @Test
  public void bankShouldAcceptWithdrawalsForDifferentCustomers()
  {
    fail("unimplemented");
  }


  @Test
  public void bankShouldRejectWithdrawalsWithoutEnoughFunds()
  {
    fail("unimplemented");
  }


  @Test
  public void bankShouldAllowTransfersBetweenCustomers()
  {
    fail("unimplemented");
  }


  @Test
  public void bankShouldRejectTransfersWithoutEnoughFunds()
  {
    fail("unimplemented");
  }


  @Test
  public void bankShouldPrintTransactions()
  {
    fail("unimplemented");
  }


  @Test
  public void bankShouldBeAbleToPrintOnlyDeposits()
  {
    fail("unimplemented");
  }


  @Test
  public void bankShouldBeAbleToPrintOnlyWithdrawals()
  {
    fail("unimplemented");
  }


  @Test
  public void bankShouldBeAbleToPrintOnlyTransactionTimes()
  {
    fail("unimplemented");
  }
}
