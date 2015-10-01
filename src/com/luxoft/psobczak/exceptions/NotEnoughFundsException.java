package com.luxoft.psobczak.exceptions;

import java.math.BigDecimal;

public class NotEnoughFundsException extends BankException {

	protected BigDecimal amount;

	public NotEnoughFundsException(BigDecimal amount, BigDecimal balance) {

		this.amount = amount;
		this.balance = balance;
	}

	@Override
	public String getMessage() {
		System.out.println("Not enough funds " + this.amount + " on this account! Max balance is: " + this.balance);
		return super.getMessage();
	}

}
