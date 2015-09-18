package com.luxoft.psobczak.service;

import com.luxoft.psobczak.controller.BankCommander;
import com.luxoft.psobczak.controller.BankServiceImpl;

public class GetAccountsCommand implements Command {

	@Override
	public void execute() {
		StringBuilder result = new StringBuilder("Client ");
		result.append(BankCommander.currentClient.getName());
		result.append(" have accounts: ");
		System.out.print(result);
		BankServiceImpl.INSTANCE.getAccountsOnClient(BankCommander.currentClient, BankCommander.currentBank);

	}

	@Override
	public void printCommandInfo() {
		System.out.println("Print all accounts for current client.");

	}

}
