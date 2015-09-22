package com.luxoft.psobczak.model;

import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;
import java.util.TreeMap;

public class BankReport {
	LinkedList<Client> list;

	public long getNumberOfClients(Bank bank) {

		return bank.getClients().size();
	}

	public long getAccountsNumber(Bank bank) {
		long result = 0;
		for (Client a : bank.getClients()) {
			result = result + a.getAccounts().size();
		}
		return result;
	}

	public void getClientsSorted(Bank bank) {
		StringBuilder result = new StringBuilder();
		for (Client a : bank.getClients()) {
			result.append(a.getName());
			result.append("\n");
		}
		System.out.println(result);
	}

	public BigDecimal getBankCreditSum(Bank bank) {

		BigDecimal result = new BigDecimal(0);

		for (Client a : bank.getClients()) {
			for (Account account : a.getAccounts()) {
				if (account instanceof CheckingAccount && account.getBalance().compareTo(BigDecimal.ZERO) < 0) {
					result = result.add(account.getBalance());
				}
			}
		}

		return result.abs();

	}
	
	public TreeMap<String, List<Client>> getClientsByCity(Bank bank){
		TreeMap<String, List<Client>> clientsWithCity = new TreeMap<String, List<Client>>();
		for(Client clients : bank.getClients()){
			if(clientsWithCity.containsKey(clients.getCity())){
				list.add(clients);
				clientsWithCity.put(clients.getCity(), list);
				
			}
			
			else{
				list = new LinkedList<Client>();
				list.add(clients);
				clientsWithCity.put(clients.getCity(), list);

			}
			
		}
		System.out.println(clientsWithCity.get("Gdansk").size());
		System.out.println(clientsWithCity.get("Cracow").size());
		System.out.println(clientsWithCity.get("Wroclaw").size());
		int b = 0;
		for(String a : clientsWithCity.keySet()){
			b+=1;
			System.out.println(a);
		}
		return clientsWithCity;
	}

}
