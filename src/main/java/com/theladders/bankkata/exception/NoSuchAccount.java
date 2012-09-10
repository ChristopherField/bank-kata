package com.theladders.bankkata.exception;

public class NoSuchAccount extends Exception {
  public NoSuchAccount(String accountNumber)
  {
	  super("No such account '" + accountNumber + "' exists.");
  }
}
