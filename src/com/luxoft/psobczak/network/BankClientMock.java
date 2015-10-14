package com.luxoft.psobczak.network;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.SocketException;
import java.util.Random;

public class BankClientMock implements Runnable{

	Socket requestSocket;
	ObjectOutputStream out;
	ObjectInputStream in;
	String message;
	long time;

	public BankClientMock() {
		
		Random generator = new Random(); 
		time = generator.nextInt(1000) + 50;

	}

	public void run() {

		try {

			requestSocket = new Socket("127.0.0.1", 5656);

			out = new ObjectOutputStream(requestSocket.getOutputStream());

			out.flush();
		
			

			in = new ObjectInputStream(requestSocket.getInputStream());
			

			//System.out.println(in.readObject());
			System.out.print(in.readObject());

			while (!(in.readObject().equals(Boolean.TRUE))) {

				//System.out.println(in.readObject());
				System.out.print(in.readObject());

				// Scanner inputTextFromClient = new Scanner(System.in);
				// message = inputTextFromClient.nextLine();
				Thread.sleep(time);
				message = "bye";

				sendMessageToCServer(message);

			}

			//System.out.println("Connection with server ended.");
		}

		catch (SocketException e) {
			e.printStackTrace();
		}

		catch (IOException e) {
			e.printStackTrace();
	
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
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
