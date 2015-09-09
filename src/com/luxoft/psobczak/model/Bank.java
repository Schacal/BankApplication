package com.luxoft.psobczak.model;

import java.util.LinkedList;
import java.util.List;

public class Bank implements Report {
	
	private List<Client> clients = new LinkedList<Client>();

	@Override
	public void printReport() {
		System.out.println("List of all clients in bank:");
		for(Client c : clients ){
			c.printReport();
		}
		

	}
	
	public List<Client> getClients(){
		return clients;
	}

}
