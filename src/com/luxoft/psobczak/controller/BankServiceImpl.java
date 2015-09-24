package com.luxoft.psobczak.controller;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
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
	String email = "mail@temp.com";
	boolean a = false;
	System.out.println("Enter client name: ");
	String name = new Scanner(System.in).nextLine();

	while (a != true) {
	    // boolean a = false;
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

	newClient.addAccount(new CheckingAccount(BigDecimal.ZERO, initialoverdraft));

	bank.addClient(newClient);
	System.out.println("Client " + newClient.getName() + " has been added");

    }

    @Override
    public void removeClient(Bank bank, Client client) {
	bank.removeClient(client);

    }

    @Override
    public void addAccount(Client client, Account account) {
	client.addAccount(account);

    }

    @Override
    public void setActiveAccount(Client client, Account account) {
	client.setActiveAccount(account);

    }

    @Override
    public void findClient(String name, Bank bank) {

	boolean check = false;

	for (Client a : bank.getClients()) {
	    if (a.getName().equals(name)) {
		System.out.println(name + " client has been found");
		BankCommander.currentClient = a;
		System.out.println("Current client: " + a.getName());
		check = true;
	    }
	}

	if (check == false)
	    System.out.println("Cannot find client " + name);

    }

    @Override
    public void getAccountsOnClient(Client client, Bank bank) {

	if (client.getAccounts() == null || client.getAccounts() == Collections.EMPTY_LIST)
	    System.out.println(client.getName() + " doesn't have accounts");

	client.printReport();

    }

    @Override
    public void deposit(Client client, Bank bank) {

	System.out.println("Please put deposit value");
	BigDecimal deposit = new BigDecimal(new Scanner(System.in).nextLine());
	client.getActiveAccount().deposit(deposit);

    }

    @Override
    public void withdraw(Client client, Bank bank) {

	System.out.println("Please put withdraw value");
	BigDecimal withdraw = new BigDecimal(new Scanner(System.in).nextLine());
	try {
	    client.getActiveAccount().withdraw(withdraw);
	} catch (BankException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();

	}

    }

    @Override
    public void transfer(Client clientFirst, Bank bank) {

	if (clientFirst.getActiveAccount() == null)
	    System.out.println("Client doesn't have selected active account");

	else {
	    System.out.println("Please put transfer value");
	    BigDecimal transfer = new BigDecimal(new Scanner(System.in).nextLine());

	    try {
		clientFirst.getActiveAccount().withdraw(transfer);
		BankCommander.currentClient.getActiveAccount().deposit(transfer);
	    } catch (BankException e) {
		e.printStackTrace();
	    }
	}
    }

    @Override
    public Client getClient(Bank bank, String clientName) {
	if (bank.getClientsByName().get(clientName) == null) {
	    System.out.println("Client not exist in bank");
	}

	return bank.getClientsByName().get(clientName);
    }

    @Override
    public Client loadClient() {
	Client deserializedClient = null;
	
	try (FileInputStream fileIn = new FileInputStream("client.ser");
		ObjectInputStream in  = new ObjectInputStream(fileIn)){
	    deserializedClient = (Client) in.readObject();
	    
	} catch (IOException e) {
	    e.printStackTrace();
	} catch (ClassNotFoundException e) {
	    e.printStackTrace();
	   
	}
	
	
	return deserializedClient;
    }

    @Override
    public void saveClient(Client clienttoSave) {
	try (FileOutputStream fileOut = new FileOutputStream("client.ser");
		ObjectOutputStream out = new ObjectOutputStream(fileOut)) {
	    out.writeObject(clienttoSave);
	} catch (IOException e) {
	    e.printStackTrace();
	}

    }

}
