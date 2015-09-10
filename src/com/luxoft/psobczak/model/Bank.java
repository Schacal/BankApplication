package com.luxoft.psobczak.model;

import java.time.LocalTime;
import java.util.LinkedList;
import java.util.List;

public class Bank implements Report {

	private List<Client> clients;
	private List<ClientRegistrationListener> listeners;;

	public Bank() {

		clients = new LinkedList<Client>();
		listeners = new LinkedList<ClientRegistrationListener>();

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
	public void printReport() {
		System.out.println("List of all clients in bank:");
		for (Client c : clients) {
			c.printReport();
		}

	}

	public List<ClientRegistrationListener> getListeners() {
		return listeners;
	}

	public List<Client> getClients() {
		return clients;
	}

}
