package com.luxoft.psobczak.view;

import java.math.BigDecimal;
import java.util.Scanner;

import com.luxoft.psobczak.controller.BankCommander;
import com.luxoft.psobczak.controller.BankServiceImpl;
import com.luxoft.psobczak.dao.BankDaoImpl;
import com.luxoft.psobczak.exceptions.BankException;
import com.luxoft.psobczak.exceptions.BankNotFoundException;
import com.luxoft.psobczak.exceptions.DAOException;
import com.luxoft.psobczak.model.Bank;
import com.luxoft.psobczak.model.BankFeedService;
import com.luxoft.psobczak.model.BankReport;
import com.luxoft.psobczak.model.CheckingAccount;
import com.luxoft.psobczak.model.Client;
import com.luxoft.psobczak.model.SavingAccount;
import com.luxoft.psobczak.service.DBSelectBankCommander;

public class BankApplication {

	static Bank bank;
	static BankServiceImpl service;

	public static void main(String[] args) throws BankException, DAOException, BankNotFoundException {
		// initializing required objects
		initialize();
		
		System.out.println(bank.getClients().size());
		// show program menu
		while (true) {

			for (String command : BankCommander.commands.keySet()) {

				System.out.print(command + ") ");
				BankCommander.commands.get(command).printCommandInfo();
			}

			String option = new Scanner(System.in).nextLine();
			BankCommander.commands.get(option).execute();

		}

	}

	public static void initialize() throws DAOException, BankNotFoundException {

		

		service = BankServiceImpl.INSTANCE;
		BankCommander bankCommander = new BankCommander();
		
		bank = BankCommander.currentBank;
		
		
		BankDaoImpl dao =  new BankDaoImpl();
		



	}

}
