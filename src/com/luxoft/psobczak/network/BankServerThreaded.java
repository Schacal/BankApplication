package com.luxoft.psobczak.network;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

public class BankServerThreaded {

	static AtomicInteger counter = new AtomicInteger();
	static long time;

	public static void main(String[] args) throws IOException, InterruptedException {

		Random generator = new Random();
		time = generator.nextInt(10) + 1;

		ServerSocket serverSocket = new ServerSocket(5656);
		ExecutorService pool = Executors.newFixedThreadPool(1000);
		Thread connectedClientsCounter = new Thread(new BankServerMonitor());
		connectedClientsCounter.start();

		for (int i = 1; i < 3000; i++) {

			Thread t1 = new Thread(new BankClientMock());
			t1.start();
			Socket clientSocket = serverSocket.accept();
			pool.execute(new ServerThread(clientSocket));

		}
	}

}
