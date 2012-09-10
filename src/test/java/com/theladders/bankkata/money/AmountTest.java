package com.theladders.bankkata.money;
import static org.junit.Assert.*;

import org.junit.Test;

public class AmountTest
{
  private String expectedString(int dollars, int cents)
  {
    String leadingZero = "";
    if (cents < 10)
      leadingZero = "0";
    return "" + dollars + "." + leadingZero + cents;
  }

  @Test
  public void testExpectedStringInTest()
  {
    assertEquals("3.50", expectedString(3, 50));
    assertEquals("533.03", expectedString(533, 03));
    assertEquals("0.00", expectedString(0, 0));
  }
  

  @Test
  public void testToString()
  {
    assertEquals("3.50", new Amount(3, 50).toString());
    assertEquals("533.03", new Amount(533, 03).toString());
    assertEquals("0.00", new Amount(0, 0).toString());
  }
  
  @Test
  public void testIsZero()
  {
    assertTrue(new Amount(0,0).isZero());
    assertFalse(new Amount(0,1).isZero());
  }
  
  @Test
  public void testCreation()
  {
    int dollars = 3;
    int cents = 25;
    String expected = expectedString(dollars, cents);
    Amount amount = new Amount(dollars, cents);
    assertEquals(expected, amount.toString());
    

  }
  
  @Test
  public void testNegation()
  {
    int dollars = 36;
    int cents = 25;
    Amount amount = new Amount(dollars, cents);
    Amount negativeAmount = amount.negate();
    assertFalse(negativeAmount.equals(amount));
    assertTrue(negativeAmount.isNegative());
    assertFalse(amount.isNegative());
    assertEquals(amount.toString(), negativeAmount.toString());
  }
  
  @Test
  public void testAdditionTwoPositives()
  {
    Amount amount1 = new Amount(35, 20);
    Amount amount2 = new Amount(33, 90);
    Amount expectedAnswer = new Amount(69, 10);
    Amount answer = amount1.add(amount2);
    assertEquals(expectedAnswer.toString(), answer.toString());
    assertFalse(answer.isNegative());
    
  }
  
  @Test
  public void testAdditionOnePositiveOneNegative()
  {
    Amount amount1 = new Amount(35, 20);
    Amount amount2 = new Amount(33, 90).negate();
    Amount expectedAnswer = new Amount(1, 30);
    Amount answer = amount1.add(amount2);
    assertEquals(expectedAnswer.toString(), answer.toString());
    assertFalse(answer.isNegative());
    
  }
  
  @Test
  public void testNegativeAddition()
  {
    Amount amount1 = new Amount(35, 20).negate();
    Amount amount2 = new Amount(33, 90).negate();
    Amount expectedAnswer = new Amount(69, 10);
    Amount answer = amount1.add(amount2);
    assertEquals(expectedAnswer.toString(), answer.toString());
    assertTrue(answer.isNegative());
   
  }
  
  @Test
  public void testCompareTo()
  {
	  Amount amountOne = new Amount(35, 20);
	  Amount amountOne2 = new Amount(35, 20);
	  assertEquals(0, amountOne.compareTo(amountOne));
	  assertEquals(0, amountOne.compareTo(amountOne2));
	  assertEquals(1, amountOne.compareTo(amountOne.negate()));
  }

}
