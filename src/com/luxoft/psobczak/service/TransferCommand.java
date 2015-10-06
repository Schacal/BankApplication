package com.luxoft.psobczak.service;

import com.luxoft.psobczak.controller.BankCommander;
import com.luxoft.psobczak.controller.BankServiceImpl;
import com.luxoft.psobczak.dao.AccountDaoImpl;
import com.luxoft.psobczak.exceptions.DAOException;

public class TransferCommand implements Command {

	@Override
	public void execute() throws DAOException {
		AccountDaoImpl accaountDao = new AccountDaoImpl();
		BankServiceImpl.INSTANCE.transfer(BankCommander.currentClient, BankCommander.currentBank);

		accaountDao.saveAccount(BankCommander.currentClient.getActiveAccount(), BankCommander.currentClient);

	}

	@Override
	public void printCommandInfo() {
		System.out.println("Transfer cash between clients accounts");

	}

}
