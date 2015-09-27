package com.luxoft.psobczak.network;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class BankServer {

	ServerSocket serversocket;
	Socket connect;
	ObjectOutputStream out;
	ObjectInputStream in;
	String messageFromClient;

	public static void main(String[] args) {
		BankServer server = new BankServer();
		server.run();
	}

	public void run() {
		System.out.println("Server");

		try {
			serversocket = new ServerSocket(5656);
			connect = serversocket.accept();

			System.out.println("Connection established with client: " + connect.getInetAddress().getHostName());

			out = new ObjectOutputStream(connect.getOutputStream());

			out.flush();
			in = new ObjectInputStream(connect.getInputStream());

			sendMessageToClient("Connected with server " + serversocket.getInetAddress());

			do {
				try {
					sendCloseSessionToClient(false);
					sendMessageToClient("Please enter command:> ");
					messageFromClient = (String) in.readObject();
					System.out.println(messageFromClient);

				}

				catch (ClassNotFoundException e) {
					e.printStackTrace();
				}

			} while (!messageFromClient.equalsIgnoreCase("bye"));

			sendCloseSessionToClient(true);
			System.out.println("Server stopped");

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				out.close();
				in.close();
				connect.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public void sendMessageToClient(String text) {
		try {
			out.writeObject(text);
			out.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void sendCloseSessionToClient(Boolean value) {
		try {
			Boolean valueToSend = new Boolean(value);
			out.writeObject(valueToSend);
			out.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
