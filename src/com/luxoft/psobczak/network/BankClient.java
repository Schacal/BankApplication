package com.luxoft.psobczak.network;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.SocketException;
import java.util.Scanner;

public class BankClient {

	Socket requestSocket;
	ObjectOutputStream out;
	ObjectInputStream in;
	String message;


	public static void main(String[] args) {

		BankClient client = new BankClient();
		client.run();

	}

	public void run() {

		try {
			requestSocket = new Socket("127.0.0.1", 5656);

			out = new ObjectOutputStream(requestSocket.getOutputStream());
			out.flush();
			in = new ObjectInputStream(requestSocket.getInputStream());

			System.out.println(in.readObject());

			while (!(in.readObject().equals(new Boolean(true)))) {

				System.out.println(in.readObject());

				Scanner inputTextFromClient = new Scanner(System.in);
				message = inputTextFromClient.nextLine();

				sendMessageToCServer(message);

			}

			System.out.println("Connection with server ended.");
		}

		catch (SocketException e) {
			e.printStackTrace();
		}

		catch (IOException e) {
			e.printStackTrace();
		} 
		catch (ClassNotFoundException e) {
			e.printStackTrace();
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
}
