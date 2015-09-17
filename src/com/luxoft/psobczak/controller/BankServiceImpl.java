package com.luxoft.psobczak.controller;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.Scanner;

import com.luxoft.psobczak.exceptions.BankException;
import com.luxoft.psobczak.model.Account;
import com.luxoft.psobczak.model.Bank;
import com.luxoft.psobczak.model.CheckingAccount;
import com.luxoft.psobczak.model.Client;
import com.luxoft.psobczak.service.BankService;

public class BankServiceImpl implements BankService {
	
	public static BankServiceImpl INSTANCE = new BankServiceImpl();
	
	private BankServiceImpl() {
		
	}

	@Override
	public void addClient(Bank bank) {
		String email = "text";
		boolean a = false;
		System.out.println("Enter client name: ");
		String name = new Scanner(System.in).nextLine();

		
		while(a!=true){
			//boolean a = false;
			System.out.println("Enter email fo client: ");
		 email = new Scanner(System.in).nextLine();
		 a = email.matches("\\w+[@]\\w+[.]\\w+");
			
		}

		System.out.println("Enter initial overdraft for client: ");
		float overdraft = new Scanner(System.in).nextFloat();

		
		Client newClient = new Client(name);
		newClient.setInitialOverdraft(overdraft);
		newClient.setEmail(email);
		
		BigDecimal initialoverdraft = new BigDecimal(overdraft);
		
		newClient.getAccounts().add(new CheckingAccount(BigDecimal.ZERO, initialoverdraft));
		
		bank.getClients().add(newClient);
		


	}

	@Override
	public void removeClient(Bank bank, Client client) {
		bank.getClients().remove(client);

	}

	@Override
	public void addAccount(Client client, Account account) {
	client.getAccounts().add(account);

	}

	@Override
	public void setActiveAccount(Client client, Account account) {
		client.setActiveAccount(account);

	}

	@Override
	public void findClient(Client client, Bank bank) {
		if (bank.getClients().contains(client)){
			BankCommander.currentClient = client;
			StringBuilder result = new StringBuilder("Client ");
			result.append(client.getName());
			result.append(" has been found in bank database. \nCurrent Client in Bank Commander is ");
			result.append(client.getName());
				
			System.out.println(result);
		}
		
		else{
			System.out.println("Cannot find client " + client.getName());
		}
		
		
	}

	@Override
	public void getAccountsOnClient(Client client, Bank bank) {
		if(client.getAccounts() == null || client.getAccounts() == Collections.EMPTY_LIST) System.out.println(client.getName() + " doesn't have accounts");
		else System.out.println(client.getAccounts());
		
	}

	@Override
	public void deposit(Client client, Bank bank) {

		if (client.getActiveAccount() == null) System.out.println("Client doesn't have selected active account");
		else {
			
			System.out.println("Please put deposit value");
			BigDecimal deposit = new BigDecimal(new Scanner(System.in).nextLine());
			client.getActiveAccount().deposit(deposit);		
			}
		
	}

	@Override
	public void withdraw(Client client, Bank bank) {

		if (client.getActiveAccount() == null) System.out.println("Client doesn't have selected active account");
		else {
			
			System.out.println("Please put withdraw value");
			BigDecimal withdraw = new BigDecimal(new Scanner(System.in).nextLine());
			try {
				client.getActiveAccount().withdraw(withdraw);
			} catch (BankException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}		
			}
		
	}

	@Override
	public void transfer(Client clientFirst, Bank bank) {
		
		if (clientFirst.getActiveAccount() == null) System.out.println("Client doesn't have selected active account");
		
		else{
		System.out.println("Please put transfer value");
		BigDecimal transfer = new BigDecimal(new Scanner(System.in).nextLine());
		

		
		try {
			clientFirst.getActiveAccount().withdraw(transfer);
			BankCommander.currentClient.getActiveAccount().deposit(transfer);
		} catch (BankException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
	}
		
	
	
}
