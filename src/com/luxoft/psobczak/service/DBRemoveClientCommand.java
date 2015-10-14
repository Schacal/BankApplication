/**
 * @author Piotr Sobczak
 * Oct 5, 2015
 */
package com.luxoft.psobczak.service;

import java.util.Scanner;

import com.luxoft.psobczak.controller.BankCommander;
import com.luxoft.psobczak.dao.ClientDaoImpl;
import com.luxoft.psobczak.exceptions.DAOException;

public class DBRemoveClientCommand implements Command {

	@Override
	public void execute() throws DAOException {
		ClientDaoImpl clientDao = new ClientDaoImpl();
		clientDao.remove(BankCommander.currentClient, BankCommander.currentBank);
		System.out.println("Client removed.");

	}

	@Override
	public void printCommandInfo() {
		System.out.println("Remove Client from database");

	}
	


}
