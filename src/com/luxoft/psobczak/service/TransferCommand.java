package com.luxoft.psobczak.service;

import com.luxoft.psobczak.controller.BankCommander;
import com.luxoft.psobczak.controller.BankServiceImpl;

public class TransferCommand implements Command {

	@Override
	public void execute() {

		BankServiceImpl.INSTANCE.transfer(BankCommander.currentClient, BankCommander.currentBank);

	}

	@Override
	public void printCommandInfo() {
		System.out.println("Transfer cash between clients accounts");

	}

}
