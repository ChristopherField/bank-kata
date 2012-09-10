package com.theladders.bankkata;

import org.junit.Test;
import static org.junit.Assert.*;

import com.theladders.bankkata.money.Mint;
import com.theladders.bankkata.money.Money;
import com.theladders.bankkata.time.Time;
import com.theladders.bankkata.exception.InsufficientFunds;

public class AccountTest
{
  private Account givenAccount(String number)
  {
	  AccountNumber accountNumber = new AccountNumber(number);
	  return new Account(accountNumber);
  }
  private Mint givenMint()
  {
	  return new Mint();
  }
  @Test
  public void testZeroBalance()
  {
     Money zero = givenMint().printNothing();
     Account account = givenAccount("12345");
     zero.equals(account.computeBalance(Time.MAX_TIME));    
  }
  
  private int normalizeDollars(int dollars, int cents)
  {
	  return dollars + cents / 100;
  }
  private int normalizeCents (int cents)
  {
	  return cents % 100;
  }
  
  @Test(expected=InsufficientFunds.class)
  public void testInsufficientFunds() throws InsufficientFunds
  {
	  Account account = givenAccount("1245");
	  account.withdraw(Time.MIN_TIME, 0, 1);
  }
  
  @Test
  public void testWithdrawal() throws Exception
  {
	  Account account = givenAccount("1234");
	  account.deposit(Time.MIN_TIME, 10, 50);
	  account.withdraw(Time.MAX_TIME, 10, 50);
	  Money balance = account.computeBalance();
	  assertEquals(givenMint().printNothing(), balance);
  }
  
  @Test
  public void testSuccessfulDeposit()
  {
	  int DOLLARS = 35;
	  int CENTS = 50;
	  Money amount = givenMint().printDebit(DOLLARS, CENTS);
	  Account account = givenAccount("12345");
	  account.deposit(Time.MIN_TIME, DOLLARS, CENTS);
	  Money balance = account.computeBalance();
	  assertEquals(amount, balance);
	  account.deposit(Time.MIN_TIME, DOLLARS, CENTS);
	  amount = givenMint().printDebit(normalizeDollars(DOLLARS*2, CENTS*2), normalizeCents(CENTS*2));
	  balance = account.computeBalance();
	  assertEquals(amount, balance);
	  
  }

}
