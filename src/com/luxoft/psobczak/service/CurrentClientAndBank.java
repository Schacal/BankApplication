/**
 * @author Piotr Sobczak
 * Oct 5, 2015
 */
package com.luxoft.psobczak.service;

import com.luxoft.psobczak.controller.BankCommander;
import com.luxoft.psobczak.exceptions.DAOException;

public class CurrentClientAndBank implements Command {

	@Override
	public void execute() throws DAOException {
		System.out.println("Current Client: " + BankCommander.currentClient.getName() + ", and current bank: " + BankCommander.currentBank.bankName);

	}

	@Override
	public void printCommandInfo() {
		System.out.println("Print current selected client and bank");

	}

}
