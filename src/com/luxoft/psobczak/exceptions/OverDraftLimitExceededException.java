package com.luxoft.psobczak.exceptions;

import java.math.BigDecimal;

public class OverDraftLimitExceededException extends NotEnoughFundsException {

	private BigDecimal overDraft;


	public OverDraftLimitExceededException(BigDecimal overDraft, BigDecimal balance, BigDecimal amount) {
		super(amount,balance);
		this.overDraft = overDraft;
	}

	@Override
	public String getMessage() {
		System.out.println("Overdraft " + this.overDraft + " is immposible");
		return super.getMessage();
	}

}
