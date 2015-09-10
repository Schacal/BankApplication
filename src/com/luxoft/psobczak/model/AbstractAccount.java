package com.luxoft.psobczak.model;

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

}
