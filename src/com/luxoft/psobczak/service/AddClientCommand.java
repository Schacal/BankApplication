package com.luxoft.psobczak.service;

import com.luxoft.psobczak.controller.BankCommander;
import com.luxoft.psobczak.controller.BankServiceImpl;
import com.luxoft.psobczak.exceptions.DAOException;

public class AddClientCommand implements Command {

	@Override
	public void execute() throws DAOException {
		BankServiceImpl.INSTANCE.addClient(BankCommander.currentBank);

	}

	@Override
	public void printCommandInfo() {
		System.out.println("Add new client to bank");

	}

}
