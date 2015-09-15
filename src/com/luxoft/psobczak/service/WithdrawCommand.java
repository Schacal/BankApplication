package com.luxoft.psobczak.service;

import com.luxoft.psobczak.controller.BankCommander;
import com.luxoft.psobczak.controller.BankServiceImpl;

public class WithdrawCommand implements Command {

	@Override
	public void execute() {
		BankServiceImpl.INSTANCE.withdraw(BankCommander.currentClient, BankCommander.currentBank);
	}

	@Override
	public void PrintCommandInfo() {
		System.out.println("Withdraw cash on client account");

	}

}
