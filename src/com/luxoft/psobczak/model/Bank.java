package com.luxoft.psobczak.model;

import java.time.LocalTime;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

public class Bank implements Report {

	private TreeSet<Client> clients;
	private List<ClientRegistrationListener> listeners;
	private TreeMap<String, Client> clientsByName;


	public Bank() {

		clients = new TreeSet<Client>();
		listeners = new LinkedList<ClientRegistrationListener>();
		clientsByName = new TreeMap<String, Client>();

		// nested classes (listeners)
		class EmailNotificationListener implements ClientRegistrationListener {

			@Override
			public void onClientAdded(Client client) {
				System.out.println("Notification email for client " + client.getName() + " to be sent");
			}

		}

		class PrintClientListener implements ClientRegistrationListener {
			@Override
			public void onClientAdded(Client client) {
				System.out.println(client.getClientSalutation() + client.getName() + " client created.");
			}

		}
		
		class DebugListener implements ClientRegistrationListener{

			@Override
			public void onClientAdded(Client client) {
				LocalTime localTime = LocalTime.now();
				System.out.println(client.getName() +", time: "+ localTime.toString());
				
			}
			
		}
		

		listeners.add(new EmailNotificationListener());
		listeners.add(new PrintClientListener());
		listeners.add(new DebugListener());

	}

	@Override
	//method print report of bank
	public void printReport() {
		System.out.println("List of all clients in bank:");
		for (Client c : clients) {
			c.printReport();
		}

	}
	
	public void parseFeed(Map<String, String> feed){
		String name = feed.get("name");
		
		Client client = clientsByName.get(name);
		if(client == null){
			Gender genderOfNewClient;
			if (feed.get("gender").equals("m")) genderOfNewClient = Gender.MALE;
			else genderOfNewClient = Gender.FEMALE;
			
			client = new Client(name, genderOfNewClient);
			client.createAccount(feed);
			addClient(client);
		   
		}
	}
	
	//method add new client to actual clients database in bank
	public void addClient(Client newClient){
		clients.add(newClient);
		clientsByName.put(newClient.getName(), newClient);
		
	}
	
	//method remove selected client from bank database
	public void removeClient(Client client){
		clients.remove(client);
	}
	

	public List<ClientRegistrationListener> getListeners() {
		return listeners;
	}

	public Set<Client> getClients() {
		return   Collections.unmodifiableSet(clients);
	}
	
	public Map<String, Client> getClientsByName() {
		return Collections.unmodifiableMap(clientsByName);
	}


}
