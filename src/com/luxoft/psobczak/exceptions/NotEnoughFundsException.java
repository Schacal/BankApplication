package com.luxoft.psobczak.exceptions;

public class NotEnoughFundsException extends BankException {
	
	protected float amount;

	public NotEnoughFundsException(){
		
	}
	public NotEnoughFundsException(float amount) {
		super();
		this.amount = amount;
	}

	
	@Override
	public String getMessage() {
		System.out.println("Not enough funds " + this.amount + " on this account!" );
		return super.getMessage();
	}
	
}
