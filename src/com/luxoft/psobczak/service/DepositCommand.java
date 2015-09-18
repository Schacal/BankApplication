package com.luxoft.psobczak.service;

import com.luxoft.psobczak.controller.BankCommander;
import com.luxoft.psobczak.controller.BankServiceImpl;

public class DepositCommand implements Command {

	@Override
	public void execute() {
		BankServiceImpl.INSTANCE.deposit(BankCommander.currentClient, BankCommander.currentBank);
		
	}

	@Override
	public void printCommandInfo() {
		
		System.out.println("Deposit cash on client account");
	}

}
