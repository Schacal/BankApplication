package com.luxoft.psobczak.service;

import java.util.Scanner;

import com.luxoft.psobczak.controller.BankCommander;
import com.luxoft.psobczak.controller.BankServiceImpl;
import com.luxoft.psobczak.model.Bank;
import com.luxoft.psobczak.model.Client;
import com.luxoft.psobczak.model.Gender;

public class FindClientCommand implements Command {

	@Override
	public void execute() {
	
		
		System.out.println("Please enter name of client");

		Client temporaryClient = new Client(new Scanner(System.in).nextLine());
		
		BankServiceImpl.INSTANCE.findClient(temporaryClient, BankCommander.currentBank);

	}

	@Override
	public void PrintCommandInfo() {
		System.out.println("Find Client in Bank database.");
		

	}

}
