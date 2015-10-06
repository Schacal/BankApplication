package com.luxoft.psobczak.service;

import com.luxoft.psobczak.controller.BankCommander;
import com.luxoft.psobczak.controller.BankServiceImpl;
import com.luxoft.psobczak.dao.AccountDaoImpl;
import com.luxoft.psobczak.exceptions.DAOException;

public class WithdrawCommand implements Command {

	@Override
	public void execute() throws DAOException {
		BankServiceImpl.INSTANCE.withdraw(BankCommander.currentClient, BankCommander.currentBank);
		AccountDaoImpl accaountDao = new AccountDaoImpl();

		accaountDao.saveAccount(BankCommander.currentClient.getActiveAccount(), BankCommander.currentClient);
	}

	@Override
	public void printCommandInfo() {
		System.out.println("Withdraw cash on client account");

	}

}
