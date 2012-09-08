package com.theladders.bankkata;

public class AccountNumber
{
  private String number;
  public AccountNumber(String number)
  {
    this.number= number;
  }
  
  private boolean isNull(String otherNumber)
  {
    return otherNumber == null;
  }
  
  @Override
  public boolean equals(Object o)
  {
    if (!(o instanceof AccountNumber))
      return false;
    AccountNumber other = (AccountNumber) o;
    String otherNumber = other.number;
    if (number == null)
    {
      return isNull(otherNumber);
    }
    return number.equals(otherNumber);
  }
  
  @Override
  public int hashCode()
  {
    if (number == null)
      return 0;
    return number.hashCode();
  }

}
