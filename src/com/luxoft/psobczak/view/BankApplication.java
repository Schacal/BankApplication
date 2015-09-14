package com.luxoft.psobczak.view;

import java.math.BigDecimal;

import com.luxoft.psobczak.controller.BankServiceImpl;
import com.luxoft.psobczak.exceptions.BankException;
import com.luxoft.psobczak.exceptions.NotEnoughFundsException;
import com.luxoft.psobczak.exceptions.OverDraftLimitExceededException;
import com.luxoft.psobczak.model.Account;
import com.luxoft.psobczak.model.Bank;
import com.luxoft.psobczak.model.CheckingAccount;
import com.luxoft.psobczak.model.Client;
import com.luxoft.psobczak.model.Gender;
import com.luxoft.psobczak.model.SavingAccount;

public class BankApplication {

	static Client Adam;
	static Client Anna;
	static Client Cris;
	static Bank bank;
	static BankServiceImpl service;

	public static void main(String[] args) throws BankException {

		try {
			// initializing required objects
			initialize();

			service.addClient(bank, Adam);

			service.addClient(bank, Anna);
			service.addClient(bank, Cris);
			
			
			
			service.addAccount(Adam, new CheckingAccount(BigDecimal.TEN, BigDecimal.TEN));
			service.addAccount(Adam, new SavingAccount(BigDecimal.TEN));
			//service.addAccount(Anna, new SavingAccount(BigDecimal.TEN));
			
			

			bank.printReport();
			
			BigDecimal deposit = new BigDecimal(0);
			BigDecimal withdraw = new BigDecimal(10);

			System.out.println(deposit.compareTo(withdraw));
			
			modifyBank(Adam, deposit, withdraw);
			modifyBank(Anna, deposit, withdraw);
			modifyBank(Cris, deposit, withdraw);
			System.out.println(Adam);
			

		}

		catch (OverDraftLimitExceededException e) {
			e.printStackTrace();

		}

		catch (NotEnoughFundsException e) {
			e.printStackTrace();

		}

		finally {
			bank.printReport();
		}

	}

	public static void initialize() {

		Adam = new Client("Adam", Gender.MALE);
		Anna = new Client("Anna", Gender.FEMALE);
		Cris = new Client("Cris", Gender.MALE);
		bank = new Bank();

		service = new BankServiceImpl();

	}

	public static void modifyBank(Client client, BigDecimal deposit, BigDecimal withdraw) throws BankException {
		// method made some changes on Client account(s)
		for (Account account : client.getAccounts()) {
			account.deposit(deposit);
			account.withdraw(withdraw);
		}
	}
}
