package com.luxoft.psobczak.view;

import java.math.BigDecimal;
import java.util.Scanner;

import com.luxoft.psobczak.controller.BankCommander;
import com.luxoft.psobczak.controller.BankServiceImpl;
import com.luxoft.psobczak.exceptions.BankException;
import com.luxoft.psobczak.exceptions.NotEnoughFundsException;
import com.luxoft.psobczak.exceptions.OverDraftLimitExceededException;
import com.luxoft.psobczak.model.Account;
import com.luxoft.psobczak.model.Bank;
import com.luxoft.psobczak.model.CheckingAccount;
import com.luxoft.psobczak.model.Client;
import com.luxoft.psobczak.model.Gender;
import com.luxoft.psobczak.model.SavingAccount;

public class BankApplication {

	static Client Adam;
	static Client Adam2;
	public static Client Anna;
	static Client Cris;
	static Bank bank;
	static BankServiceImpl service;

	public static void main(String[] args) throws BankException {
		
	
	       
		

		try {
			// initializing required objects
			initialize();
			
			
		   

			//service.addClient(bank, Adam);
			//service.addClient(bank, Anna);
			
			//service.addClient(bank);
			bank.addClient(Adam);
			bank.addClient(Anna);
			BankCommander.currentClient = Adam;

			
			
			
			service.addAccount(Adam, new CheckingAccount(BigDecimal.TEN, BigDecimal.TEN));
			service.addAccount(Adam, new SavingAccount(BigDecimal.TEN));
			service.addAccount(Anna, new CheckingAccount(BigDecimal.TEN, BigDecimal.TEN));

			Adam.setActiveAccount(Adam.getAccounts().get(0));
			Anna.setActiveAccount(Anna.getAccounts().get(0));
			
			  
            for (int i=0;i<BankCommander.commands.length;i++) { // show menu
                  System.out.print(i+") ");
                  BankCommander.commands[i].printCommandInfo();
            }
        
            int option = new Scanner(System.in).nextInt();
            BankCommander.commands[option].execute();
			
			
			

			bank.printReport();
			
			BigDecimal deposit = new BigDecimal(0);
			BigDecimal withdraw = new BigDecimal(0);

			
			
			modifyBank(Adam, deposit, withdraw);
			modifyBank(Anna, deposit, withdraw);
			
		
			

		}

		catch (OverDraftLimitExceededException e) {
			e.printStackTrace();

		}

		catch (NotEnoughFundsException e) {
			e.printStackTrace();

		}

		finally {
			bank.printReport();
		}

	}

	public static void initialize() {
		
		Adam2 = new Client("Adam");
		Adam = new Client("Adam", Gender.MALE);
		Anna = new Client("Anna", Gender.FEMALE);
		Cris = new Client("Cris", Gender.MALE);
		bank = BankCommander.currentBank;

		service = BankServiceImpl.INSTANCE;

	}

	public static void modifyBank(Client client, BigDecimal deposit, BigDecimal withdraw) throws BankException {
		// method made some changes on Client account(s)
		for (Account account : client.getAccounts()) {
			account.deposit(deposit);
			account.withdraw(withdraw);
		}
	}
}
