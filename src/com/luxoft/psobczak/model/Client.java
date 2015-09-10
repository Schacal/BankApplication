package com.luxoft.psobczak.model;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Client implements Report {

    private String name;
    private List<Account> accounts;
    private Account activeAccount;
    private float initialOverdraft = 1000;
    private Gender gender;

    public Client(String name, Gender gender) {

	this.name = name;
	this.gender = gender;
	accounts = new LinkedList<Account>();

    }

    public void setActiveAccount(Account activeAccount) {
	this.activeAccount = activeAccount;
    }

    public float getOverallBalance() {
	// method give overall balance on client's all accounts
	
	float overallBalance = 0;
	for (Account account : accounts) {
	    overallBalance += account.getBalance();
	}
	return overallBalance;
    }

    public List<Account> getAccounts() {
	return accounts;
    }

    @Override
    public void printReport() {
	System.out.println("Client: " + getClientSalutation() + name + " have total accounts: " + this.accounts.size());

	int listIncrementNumber = 0;

	while (listIncrementNumber < accounts.size()) {

	    accounts.get(listIncrementNumber).printReport();
	    listIncrementNumber++;
	}

    }

    public float getInitialOverdraft() {
	return initialOverdraft;
    }

    public Gender getGender() {
	return gender;
    }
    
    public String getName() {
    	return name;
        }

    public String getClientSalutation() {
	return gender.getGreeting();
    }

}
