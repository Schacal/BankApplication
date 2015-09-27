package com.luxoft.psobczak.model;

import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class BankReport {
	LinkedList<Client> list;

	public long getNumberOfClients(Bank bank) {

		return bank.getClients().size();
	}

	public long getAccountsNumber(Bank bank) {
		long result = 0;
		for (Client client : bank.getClients()) {
			result = result + client.getAccounts().size();
		}
		return result;
	}

	public void getClientsSorted(Bank bank) {
		StringBuilder result = new StringBuilder();
		for (Client client : bank.getClients()) {
			result.append(client.getName());
			result.append("\n");
		}
		System.out.println(result);
	}

	public BigDecimal getBankCreditSum(Bank bank) {

		BigDecimal result = new BigDecimal(0);

		for (Client client : bank.getClients()) {
			for (Account account : client.getAccounts()) {
				if (account instanceof CheckingAccount && account.getBalance().compareTo(BigDecimal.ZERO) < 0) {
					result = result.add(account.getBalance());
				}
			}
		}

		return result.abs();

	}

	public Map<String, List<Client>> getClientsByCity(Bank bank) {
		Map<String, List<Client>> clientsWithCity = new TreeMap<String, List<Client>>();
		for (Client clients : bank.getClients()) {
			if (clientsWithCity.containsKey(clients.getCity())) {
				list = (LinkedList<Client>) clientsWithCity.get(clients.getCity());
				list.add(clients);
				clientsWithCity.put(clients.getCity(), list);

			}

			else {
				list = new LinkedList<Client>();
				list.add(clients);
				clientsWithCity.put(clients.getCity(), list);

			}

		}

		for (String clientKey : clientsWithCity.keySet()) {
			StringBuilder result = new StringBuilder(clientKey);
			result.append(": ");
			result.append(clientsWithCity.get(clientKey));
			System.out.println(result);

		}
		return clientsWithCity;
	}

}
