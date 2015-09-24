package com.luxoft.psobczak.model;

import java.math.BigDecimal;
import java.util.Map;

import com.luxoft.psobczak.exceptions.BankException;

public interface Account extends Report {
	
	public BigDecimal getBalance();
	public void deposit(BigDecimal x);
	public void withdraw(BigDecimal x) throws BankException;
	
	public void decimalValue();
	
}
