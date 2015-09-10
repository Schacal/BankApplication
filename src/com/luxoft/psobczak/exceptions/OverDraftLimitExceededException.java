package com.luxoft.psobczak.exceptions;

public class OverDraftLimitExceededException extends NotEnoughFundsException {

	private float overDraft;
	private float b = 1234;

	public OverDraftLimitExceededException(float overDraft) {
		super();
		this.overDraft = overDraft;
	}

	@Override
	public String getMessage() {
		System.out.println("Overdraft " + this.overDraft + " is immposible");
		return super.getMessage();
	}

}
