package com.luxoft.psobczak.network;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class BankServer {
	
	ServerSocket serversocket;
	Socket connect;
	ObjectOutputStream out;
	ObjectInputStream in;
	
	
	public void run() {
		
		try {
			serversocket = new ServerSocket(5656);
			connect = serversocket.accept();
			
			System.out.println("Connection established with client: " + connect.getInetAddress().getHostName());
		}
	}


}
