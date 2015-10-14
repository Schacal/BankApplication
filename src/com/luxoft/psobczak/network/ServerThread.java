package com.luxoft.psobczak.network;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerThread implements Runnable {

	Socket clientSocket;

	Socket connect;
	ObjectOutputStream out;
	ObjectInputStream in;
	String messageFromClient;

	public ServerThread(Socket clientSocket) {
		this.clientSocket = clientSocket;

	}

	@Override
	public void run() {

		try {

			connect = clientSocket;

			//System.out.println("Connection established with client: " + connect.getInetAddress().getHostName());
			BankServerThreaded.counter.incrementAndGet();
			

			out = new ObjectOutputStream(connect.getOutputStream());

			out.flush();
			in = new ObjectInputStream(connect.getInputStream());

			//sendMessageToClient("Connected with server ");
			sendMessageToClient("");

			do {
				try {
					sendCloseSessionToClient(false);
				//	sendMessageToClient("Please enter command:> ");
					sendMessageToClient("");
					messageFromClient = (String) in.readObject();
				//	System.out.println(messageFromClient);

				}

				catch (ClassNotFoundException e) {
					e.printStackTrace();
				}

			} while (!messageFromClient.equalsIgnoreCase("bye"));

			sendCloseSessionToClient(true);
		//	System.out.println("Connection closed with client " + connect.getInetAddress().getHostName());
			BankServerThreaded.counter.decrementAndGet();
			

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
