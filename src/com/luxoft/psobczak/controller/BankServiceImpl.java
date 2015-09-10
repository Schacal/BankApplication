package com.luxoft.psobczak.controller;

import com.luxoft.psobczak.model.Account;
import com.luxoft.psobczak.model.Bank;
import com.luxoft.psobczak.model.Client;
import com.luxoft.psobczak.model.ClientRegistrationListener;
import com.luxoft.psobczak.service.BankService;

public class BankServiceImpl implements BankService {

	@Override
	public void addClient(Bank bank, Client client) {
		bank.getClients().add(client);
		for(ClientRegistrationListener listeners: bank.getListeners()){
			listeners.onClientAdded(client);
		}

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

}
