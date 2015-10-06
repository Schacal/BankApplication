package com.luxoft.psobczak.service;

import java.util.Scanner;

import com.luxoft.psobczak.controller.BankCommander;
import com.luxoft.psobczak.dao.ClientDaoImpl;
import com.luxoft.psobczak.exceptions.ClientNotFoundException;
import com.luxoft.psobczak.exceptions.DAOException;
import com.luxoft.psobczak.model.Account;

public class FindClientCommand implements Command {

	@Override
	public void execute() throws DAOException {
	
		
		System.out.println("Please enter name of client");

		String temporaryName = new Scanner(System.in).nextLine();
		
		ClientDaoImpl clientDao = new ClientDaoImpl();
		
		try {
		
		BankCommander.currentClient = clientDao.findClientByName(BankCommander.currentBank, temporaryName);
		
		for(Account account : BankCommander.currentClient.getAccounts()){
			BankCommander.currentClient.setActiveAccount(account);
		}
		
		
		}
			
		catch(ClientNotFoundException e ){
			System.err.println(e.getMessage());
		}
		
		System.out.println("Current client is " + BankCommander.currentClient +"  with current account "+ BankCommander.currentClient.getActiveAccount());

	}

	@Override
	public void printCommandInfo() {
		System.out.println("Find Client in Bank database.");
		

	}

}
