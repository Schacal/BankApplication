package com.luxoft.psobczak.model;

import java.io.Serializable;
import java.math.BigDecimal;

import com.luxoft.psobczak.exceptions.NotEnoughFundsException;

public class SavingAccount extends AbstractAccount implements Comparable<Account>, Serializable {
	
	public int id;


	public SavingAccount(BigDecimal initialBalance) {
		this.id = 0;
		balance = initialBalance;
	}

	@Override
	public void printReport() {
		System.out.println(" -Saving Account with actual balance: " + this.balance.toString());

	}

	@Override
	public BigDecimal getBalance() {
		return this.balance;
	}

	@Override
	public void withdraw(BigDecimal x) throws NotEnoughFundsException {
		if (this.balance.compareTo(x) >= 0)
			this.balance = balance.subtract(x);
		else
			throw new NotEnoughFundsException(x, this.balance);

	}

	@Override
	public String toString() {
		return "SavingAccount balance= " + balance;
	}

	@Override
	public void decimalValue() {
		System.out.println("Rounded balance on Saving Account: " + Math.round(balance.floatValue()));

	}

	@Override
	public int compareTo(Account o) {
		return this.balance.compareTo(o.getBalance());
	}
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	

}
