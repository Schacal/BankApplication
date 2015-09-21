package com.luxoft.psobczak.view;

import java.math.BigDecimal;
import java.util.Scanner;

import com.luxoft.psobczak.controller.BankCommander;
import com.luxoft.psobczak.controller.BankServiceImpl;
import com.luxoft.psobczak.exceptions.BankException;
import com.luxoft.psobczak.model.Bank;
import com.luxoft.psobczak.model.CheckingAccount;
import com.luxoft.psobczak.model.Client;
import com.luxoft.psobczak.model.SavingAccount;

public class BankApplication {

	static Bank bank;
	static BankServiceImpl service;

	public static void main(String[] args) throws BankException {
		// initializing required objects
		initialize();

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
			testClient.addAccount(new CheckingAccount(BigDecimal.ZERO, BigDecimal.TEN));

			bank.addClient(testClient);

			// service.addAccount(testClient, new
			// SavingAccount(BigDecimal.ZERO));
			BankCommander.currentClient = testClient;

		}

	}

}
