package com.theladders.bankkata.exception;

public class AccountExists extends Exception {
  public AccountExists()
  {
	  super("Account already exists.");
  }
}
