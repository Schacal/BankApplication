package com.luxoft.psobczak.service;

import com.luxoft.psobczak.model.Account;
import com.luxoft.psobczak.model.Bank;
import com.luxoft.psobczak.model.Client;

public interface BankService {
	
	public void addClient(Bank bank, Client client);
	public void removeClient(Bank bank, Client client);
	public void addAccount(Client client, Account account);
	public void setActiveAccount(Client client, Account account);

}
