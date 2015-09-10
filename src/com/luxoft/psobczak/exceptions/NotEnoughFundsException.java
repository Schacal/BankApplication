package com.luxoft.psobczak.exceptions;

public class NotEnoughFundsException extends BankException {

	protected float amount;

	public NotEnoughFundsException(float amount, float balance) {

		this.amount = amount;
		this.balance = balance;
	}

	@Override
	public String getMessage() {
		System.out.println("Not enough funds " + this.amount + " on this account! Max balance is: " + this.balance);
		return super.getMessage();
	}

}
