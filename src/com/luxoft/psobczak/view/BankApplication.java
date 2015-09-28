package com.luxoft.psobczak.view;

import java.math.BigDecimal;
import java.util.Scanner;

import com.luxoft.psobczak.controller.BankCommander;
import com.luxoft.psobczak.controller.BankServiceImpl;
import com.luxoft.psobczak.exceptions.BankException;
import com.luxoft.psobczak.model.Bank;
import com.luxoft.psobczak.model.BankFeedService;
import com.luxoft.psobczak.model.BankReport;
import com.luxoft.psobczak.model.CheckingAccount;
import com.luxoft.psobczak.model.Client;
import com.luxoft.psobczak.model.SavingAccount;
import com.luxoft.psobczak.network.BankServer;

public class BankApplication {

    static Bank bank;
    static BankServiceImpl service;

    public static void main(String[] args) throws BankException {
	// initializing required objects
	initialize();
	BankReport bankReport = new BankReport();
	System.out.println(bankReport.getAccountsNumber(bank));

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

    public static void initialize() {

	bank = BankCommander.currentBank;

	service = BankServiceImpl.INSTANCE;
	BankCommander bankCommander = new BankCommander();

	BankFeedService feed = new BankFeedService();
	feed.loadFeed("clients.txt");

	// creating 100 clients
	for (int i = 0; i < 100; i++) {

	    Client testClient = new Client("Client " + i);
	    testClient.addAccount(new CheckingAccount(new BigDecimal(-9), BigDecimal.TEN));

	    testClient.addAccount(new SavingAccount(BigDecimal.ZERO));

	    if (i < 50) {
		testClient.setCity("Wroclaw");
	    } else {
		testClient.setCity("Cracow");
	    }

	    bank.addClient(testClient);

	    BankCommander.currentClient = testClient;

	}
	
	
	
	

    }

}
