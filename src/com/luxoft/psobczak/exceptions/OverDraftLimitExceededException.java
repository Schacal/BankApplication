package com.luxoft.psobczak.exceptions;

public class OverDraftLimitExceededException extends NotEnoughFundsException {

	private float overDraft;


	public OverDraftLimitExceededException(float overDraft, float balance, float amount) {
		super(amount,balance);
		this.overDraft = overDraft;
	}

	@Override
	public String getMessage() {
		System.out.println("Overdraft " + this.overDraft + " is immposible");
		return super.getMessage();
	}

}
