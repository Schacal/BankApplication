package com.luxoft.psobczak.service;

import java.util.Scanner;
import java.util.TreeSet;

import com.luxoft.psobczak.controller.BankCommander;
import com.luxoft.psobczak.dao.BankDaoImpl;
import com.luxoft.psobczak.dao.ClientDaoImpl;
import com.luxoft.psobczak.exceptions.BankNotFoundException;
import com.luxoft.psobczak.exceptions.DAOException;
import com.luxoft.psobczak.model.Client;

public class DBSelectBankCommander implements Command {
	
	BankDaoImpl bankDao;
	ClientDaoImpl clientDao;
	TreeSet<Client> list;
	
	
	public DBSelectBankCommander() throws DAOException, BankNotFoundException {
		bankDao = new BankDaoImpl();
		clientDao = new ClientDaoImpl();
		

	}
	
	
	public String getBankNameFromUser(){
		System.out.println("Please enter bank name");
		String name = new Scanner(System.in).nextLine();
		return name;
	}


	@Override
	public void execute() throws DAOException {
		BankCommander.currentBank = bankDao.getBankByName(getBankNameFromUser());
		list = clientDao.getAllClients(BankCommander.currentBank);
		BankCommander.currentBank.getClients().addAll(list);
		System.out.println("Bank: " +BankCommander.currentBank.bankName +" have "+BankCommander.currentBank.getClients().size() + " total clients");
		
	}


	@Override
	public void printCommandInfo() {
		System.out.println("Select Bank from database");
		
	}

}
