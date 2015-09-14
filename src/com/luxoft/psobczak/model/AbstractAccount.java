package com.luxoft.psobczak.model;

import java.math.BigDecimal;

public abstract class AbstractAccount implements Account {
	
	protected BigDecimal balance;

	@Override
	public BigDecimal getBalance() {	
		return balance;
	}

	@Override
	public void deposit(BigDecimal x) {
		balance = balance.add(x);

	}

	@Override
	public String toString() {
		StringBuilder result = new StringBuilder("Actual balance: ");
		result.append(balance);
		
		return result.toString();
	}
	
	

}
