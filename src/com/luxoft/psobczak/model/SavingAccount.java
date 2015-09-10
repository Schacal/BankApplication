package com.luxoft.psobczak.model;

import com.luxoft.psobczak.exceptions.NotEnoughFundsException;

public class SavingAccount extends AbstractAccount {

	public SavingAccount(float initialBalance) {
		balance = initialBalance;
	}

	@Override
	public void printReport() {
		System.out.println(" -Saving Account with actual balance: " + this.balance);

	}

	@Override
	public float getBalance() {
		return this.balance;
	}

	@Override
	public void withdraw(float x) throws NotEnoughFundsException {
		if (this.balance >= x)
			this.balance -= x;
		else
			throw new NotEnoughFundsException(x, this.balance);

	}

}
