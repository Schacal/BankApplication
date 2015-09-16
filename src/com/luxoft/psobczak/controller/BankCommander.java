package com.luxoft.psobczak.controller;

import com.luxoft.psobczak.model.Bank;
import com.luxoft.psobczak.model.Client;
import com.luxoft.psobczak.service.AddClientCommand;
import com.luxoft.psobczak.service.Command;
import com.luxoft.psobczak.service.DepositCommand;
import com.luxoft.psobczak.service.FindClientCommand;
import com.luxoft.psobczak.service.GetAccountsCommand;
import com.luxoft.psobczak.service.TransferCommand;
import com.luxoft.psobczak.service.WithdrawCommand;

public class BankCommander {
	
	public static Bank currentBank = new Bank();
	public static Client currentClient;
	
	
	
	public static Command[] commands = {
			new FindClientCommand(),
			new GetAccountsCommand(), 
			new DepositCommand(),
			new WithdrawCommand(), 
			new TransferCommand(), 
			new AddClientCommand()};
	
	
	}


