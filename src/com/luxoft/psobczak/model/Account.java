package com.luxoft.psobczak.model;

import com.luxoft.psobczak.exceptions.BankException;

public interface Account extends Report {
	
	public float getBalance();
	public void deposit(float x);
	public void withdraw(float x) throws BankException;
	

}
