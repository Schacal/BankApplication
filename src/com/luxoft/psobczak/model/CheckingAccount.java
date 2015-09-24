package com.luxoft.psobczak.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Map;

import com.luxoft.psobczak.exceptions.OverDraftLimitExceededException;

public class CheckingAccount extends AbstractAccount implements Comparable<Account>, Serializable {

    BigDecimal overDraft;

    public CheckingAccount(BigDecimal balance, BigDecimal overDraft) {
	super.balance = balance;
	this.overDraft = overDraft;
    }

    @Override
    public void printReport() {
	System.out.println(
		" -Checking Account with actual balance: " + this.balance + " and max overdraft: " + this.overDraft);

    }

    @Override
    public BigDecimal getBalance() {
	return balance;
    }

    @Override
    public void withdraw(BigDecimal withdraw) throws OverDraftLimitExceededException {
	// if balance is >= withdraw then subtract withdraw
	if (this.balance.compareTo(withdraw) >= 0) {
	    this.balance = balance.subtract(withdraw);
	}

	// if withdraw is >= balance and withdraw is <= overdraft
	else if (this.balance.compareTo(withdraw) <= 0 && (withdraw.subtract(balance)).compareTo(this.overDraft) <= 0) {
	    this.balance = balance.subtract(withdraw);
	}

	// if withdraw is >= balance and withdraw is > overdraft
	else if (this.balance.compareTo(withdraw) <= 0 && withdraw.compareTo(overDraft) > 0) {
	    throw new OverDraftLimitExceededException(withdraw, this.balance, this.overDraft);
	}

    }

    public BigDecimal getOverDraft() {
	return overDraft;
    }

    public void setOverDraft(BigDecimal overDraft) {
	this.overDraft = overDraft;
    }

    @Override
    public String toString() {
	return "CheckingAccount balance= " + balance + " and overdraft= " + overDraft;
    }

    @Override
    public void decimalValue() {

	System.out.println("Rounded balance on Checking Account: " + Math.round(balance.floatValue()));

    }

	@Override
	public int compareTo(Account o) {
		
		return this.overDraft.compareTo(o.getBalance());
	}



}
