package com.luxoft.psobczak.model;

import java.math.BigDecimal;

import com.luxoft.psobczak.exceptions.BankException;
import com.luxoft.psobczak.exceptions.NotEnoughFundsException;
import com.luxoft.psobczak.exceptions.OverDraftLimitExceededException;

public abstract class AbstractAccount implements Account {
	
	protected float balance;

	@Override
	public float getBalance() {	
		return balance;
	}

	@Override
	public void deposit(float x) {
		balance += x;

	}

	@Override
	public String toString() {
		StringBuilder result = new StringBuilder("Actual balance: ");
		result.append(balance);
		
		return result.toString();
	}
	
	

}
