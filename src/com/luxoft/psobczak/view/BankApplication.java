package com.luxoft.psobczak.view;

import java.math.BigDecimal;
import java.util.Scanner;

import com.luxoft.psobczak.controller.BankCommander;
import com.luxoft.psobczak.controller.BankServiceImpl;
import com.luxoft.psobczak.exceptions.BankException;
import com.luxoft.psobczak.model.Bank;
import com.luxoft.psobczak.model.BankReport;
import com.luxoft.psobczak.model.CheckingAccount;
import com.luxoft.psobczak.model.Client;
import com.luxoft.psobczak.model.SavingAccount;

public class BankApplication {

	static Bank bank;
	static BankServiceImpl service;

	public static void main(String[] args) throws BankException {
		// initializing required objects
		initialize();
		BankReport bankReport = new BankReport();
		Client Client1 = new Client("Client1");
		Client Client2 = new Client("Client2");
		Client1.setCity("Gdansk");
		Client2.setCity("Gdansk");
		bank.addClient(Client2);
		bank.addClient(Client1);
		
		System.out.println(bankReport.getClientsByCity(bank));
		

		// show program menu
		while (true) {
			for (int i = 0; i < BankCommander.commands.length; i++) {
				System.out.print(i + ") ");
				BankCommander.commands[i].printCommandInfo();
			}

			int option = new Scanner(System.in).nextInt();
			BankCommander.commands[option].execute();

		}

	}

	public static void initialize() {

		bank = BankCommander.currentBank;

		service = BankServiceImpl.INSTANCE;

		//creating 100 clients
		for (int i = 0; i < 100; i++) {

			Client testClient = new Client("Client " + i);
			testClient.addAccount(new CheckingAccount(new BigDecimal(-9), BigDecimal.TEN));

		
			testClient.addAccount(new SavingAccount(BigDecimal.ZERO));
			
			    
			
			    if ( i < 50)
			    {
			    	testClient.setCity("Wroclaw");
			    }
			    else
			    {
			    	testClient.setCity("Cracow");
			    }
			
			
			//System.out.println(testClient.getCity());

			bank.addClient(testClient);

			// service.addAccount(testClient, new
			// SavingAccount(BigDecimal.ZERO));
			BankCommander.currentClient = testClient;

		}

	}

}
