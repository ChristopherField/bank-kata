package com.theladders.bankkata;
import static org.junit.Assert.*;

import org.junit.Test;

public class AccountNumberTest
{
  
  

  @Test
  public void testEquality()
  {
    AccountNumber number = new AccountNumber("3564");
    AccountNumber another = new AccountNumber("3564");
    assertTrue(number.equals(another));
    assertTrue(another.equals(number));
  }
  
  @Test
  public void testInEquality()
  {
    AccountNumber number = new AccountNumber("3564");
    AccountNumber another = new AccountNumber("3563");
    assertFalse(number.equals(another));
    assertFalse(another.equals(number));
  }
  
  @Test
  public void testNullEquality()
  {
    AccountNumber number = new AccountNumber("3564");
    AccountNumber another = new AccountNumber(null);
    assertFalse(number.equals(another));
    assertFalse(another.equals(number));
  }
  
  @Test
  public void testHashCode()
  {
    AccountNumber number = new AccountNumber("666");
    AccountNumber sameNumber = new AccountNumber("666");
    AccountNumber anotherNumber = new AccountNumber("9084");
    AccountNumber anotherNull = new AccountNumber(null);
    AccountNumber nullNumber = new AccountNumber(null);
    
    // below required for hash code contract
    assertTrue(number.hashCode() == sameNumber.hashCode());
    assertTrue(nullNumber.hashCode() == anotherNull.hashCode());
    
    // below not required strictly but nice to make sure happening
    assertFalse(number.hashCode() == anotherNumber.hashCode());
    assertFalse(number.hashCode() == nullNumber.hashCode());
    
  }
  
  
}
