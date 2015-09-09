package com.luxoft.psobczak.model;

import com.luxoft.psobczak.exceptions.OverDraftLimitExceededException;

public class CheckingAccount extends AbstractAccount {

    float overDraft;

    public CheckingAccount(float balance, float overDraft) {
	super.balance = balance;
	this.overDraft = overDraft;
    }

    @Override
    public void printReport() {
	System.out.println(
		" -Checking Account with actual balance: " + this.balance + " and max overdraft: " + this.overDraft);

    }

    @Override
    public float getBalance() {
	return balance;
    }

    @Override
    public void withdraw(float withdraw) throws OverDraftLimitExceededException {
	if (this.balance > withdraw)
	    this.balance -= withdraw;
	if (this.balance <= withdraw && withdraw <= overDraft)
	    this.balance -= withdraw;

	if (this.balance <= withdraw && withdraw > overDraft) {
	    throw new OverDraftLimitExceededException(withdraw);

	}

    }

    public float getOverDraft() {
	return overDraft;
    }

    public void setOverDraft(float overDraft) {
	this.overDraft = overDraft;
    }

}
