package com.luxoft.psobczak.controller;

import java.util.TreeMap;

import com.luxoft.psobczak.model.Bank;
import com.luxoft.psobczak.model.Client;
import com.luxoft.psobczak.service.AddClientCommand;
import com.luxoft.psobczak.service.Command;
import com.luxoft.psobczak.service.DepositCommand;
import com.luxoft.psobczak.service.ExitCommand;
import com.luxoft.psobczak.service.FindClientCommand;
import com.luxoft.psobczak.service.GetAccountsCommand;
import com.luxoft.psobczak.service.TransferCommand;
import com.luxoft.psobczak.service.WithdrawCommand;

public class BankCommander {
	
	public static Bank currentBank = new Bank();
	public static Client currentClient;
	
	public static TreeMap<String, Command> commands = new TreeMap<String, Command>();
	
	
	
	public BankCommander(){
		commands.put("1", new FindClientCommand());
		commands.put("2", new GetAccountsCommand());
		commands.put("3", new DepositCommand());
		commands.put("4", new WithdrawCommand());
		commands.put("5", new TransferCommand());
		commands.put("6", new AddClientCommand());
		commands.put("7", new ExitCommand());
		
	}
	
	public static void registerCommand(String name, Command command){
		commands.put(name, command);
	}
	
	public static void remove(String name){
		commands.remove(name);
	}
	
	}


