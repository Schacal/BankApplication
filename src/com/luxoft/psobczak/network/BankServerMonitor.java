package com.luxoft.psobczak.network;

public class BankServerMonitor implements Runnable {

	@Override
	public void run() {
		
		while(true){
		System.out.println("Current connected clients: " + BankServerThreaded.counter.get());
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	}
}
