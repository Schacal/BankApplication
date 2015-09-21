package com.luxoft.psobczak.service;

import com.luxoft.psobczak.model.Account;
import com.luxoft.psobczak.model.Bank;
import com.luxoft.psobczak.model.Client;

public interface BankService {
	
	public void addClient(Bank bank);
	public void removeClient(Bank bank, Client client);
	public void addAccount(Client client, Account account);
	public void setActiveAccount(Client client, Account account);
	public void findClient(String name, Bank bank);
	public void getAccountsOnClient(Client client, Bank bank);
	public void deposit(Client client, Bank bank);
	public void withdraw(Client client, Bank bank);
	public void transfer(Client clientFirst, Bank bank);

}
