package com.theladders.bankkata.money;

import static org.junit.Assert.*;

import org.junit.Test;

public class MoneyTest {
  private Mint mint = new Mint();
  @Test
  public void testIsZero()
  {
	  Money money = mint.printNothing();
	  assertTrue(money.isType(Type.zeroType()));
	  assertTrue(money.isType(Type.debit()));
  }
  
  @Test
  public void testEquals()
  {
	  Money moneyZero = mint.printNothing();
	  Money moneyOne = mint.printDebit(1, 0);
	  Money moneyOneCredit = mint.printCredit(1, 0);
	  assertEquals(moneyZero, moneyZero);
	  assertNotSame(moneyZero, moneyOne);
	  assertNotSame(moneyOne, moneyOneCredit);
	  assertSame(moneyOne, moneyOne);
	  assertSame(moneyOneCredit, moneyOneCredit);
	  assertEquals(new Money(2, 0, Type.debit()), mint.printDebit(2, 0));
  }
  
  @Test
  public void testCompareTo()
  {
	  Money moneyZero = mint.printNothing();
	  Money moneyThreeDebit = mint.printDebit(3, 0);
	  Money moneyThreeCredit = mint.printCredit(3, 0);
	  assertTrue(moneyZero.compareAmounts(moneyThreeDebit) < 0);
	  // only comparing amount, below expected
	  assertTrue(moneyZero.compareAmounts(moneyThreeCredit) < 0);
	  assertTrue(moneyZero.compareAmounts(moneyZero) == 0);
	  
	  // only comparing amount, below expected
	  assertTrue(moneyThreeCredit.compareAmounts(moneyThreeDebit)  == 0);
  }
  
  @Test public void testAddition()
  {
	  Money moneyOne = mint.printDebit(1, 0);
	  Money moneyMinusOne = mint.printCredit(1, 0);
	  Money zero = mint.printNothing();
	  Money answer = moneyOne.add(moneyOne);
	  assertEquals(new Money(2, 0, Type.debit()), answer);
	  answer = moneyMinusOne.add(moneyOne);
	  assertEquals(new Money(0, 0, Type.zeroType()), answer);
  }
}
