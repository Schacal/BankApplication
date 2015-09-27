package com.luxoft.psobczak.network;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;

public class BankClient {

	Socket requestSocket;
	ObjectOutputStream out;
	ObjectInputStream in;
	String message;
	
	public void run() {
		try{
			requestSocket = new Socket("127.0.0.1", 5656);
			
			out = new ObjectOutputStream(requestSocket.getOutputStream());
			out.flush();
			in = new ObjectInputStream(requestSocket.getInputStream());
		
		
		do{
			Scanner inputTextFromClient = new Scanner(System.in);
			message = inputTextFromClient.nextLine();
			sendMessageToCServer(message);
			
		}
		while(requestSocket.isConnected());
		}
		catch(IOException e){
			e.printStackTrace();
		}
		finally {
			try{
			in.close();
			out.close();
			requestSocket.close();
			}
			catch(IOException e){
				e.printStackTrace();
			}
		}
	}
	
	
	
	
	
	
	
	
	public void sendMessageToCServer(String text) {
		try {
			out.writeObject(text);
			out.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	public static void main(String[] args) {
		
		BankClient client = new BankClient();
		client.run();

	}

}
