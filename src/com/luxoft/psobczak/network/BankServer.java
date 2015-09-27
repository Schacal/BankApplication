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
			
			
			do{
				sendMessageToClient("Please enter command:> ");
				try{
				messageFromClient = (String) in.readObject();
				}
				catch(ClassNotFoundException e){
					e.printStackTrace();
				}
			}
			while(messageFromClient.equalsIgnoreCase("bye"));

		}
		catch(IOException e){
			e.printStackTrace();
		}
		finally{
			
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

	public static void main(String[] args) {
		BankServer server = new BankServer();
		server.run();
	}
}
