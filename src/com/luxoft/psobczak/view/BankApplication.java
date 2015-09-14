package com.luxoft.psobczak.view;

import com.luxoft.psobczak.controller.BankServiceImpl;
import com.luxoft.psobczak.exceptions.BankException;
import com.luxoft.psobczak.exceptions.NotEnoughFundsException;
import com.luxoft.psobczak.exceptions.OverDraftLimitExceededException;
import com.luxoft.psobczak.model.Account;
import com.luxoft.psobczak.model.Bank;
import com.luxoft.psobczak.model.CheckingAccount;
import com.luxoft.psobczak.model.Client;
import com.luxoft.psobczak.model.ClientRegistrationListener;
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
			service.addAccount(Adam, new CheckingAccount(800, Adam.getInitialOverdraft()));
			service.addAccount(Adam, new SavingAccount(801.5f));
			service.addAccount(Anna, new SavingAccount(200));

			bank.printReport();

			modifyBank(Adam, 0, 300);
			modifyBank(Anna, 200, 400);
			modifyBank(Cris, 200, 300);
			System.out.println(Adam);
			Adam.getAccounts().get(1).decimalValue();

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

	public static void modifyBank(Client client, float deposit, float withdraw) throws BankException {
		// method made some changes on Client account(s)
		for (Account account : client.getAccounts()) {
			account.deposit(deposit);
			account.withdraw(withdraw);
		}
	}
}
