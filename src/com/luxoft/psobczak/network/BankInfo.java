package com.luxoft.psobczak.network;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.luxoft.psobczak.model.Bank;
import com.luxoft.psobczak.model.BankReport;
import com.luxoft.psobczak.model.Client;

public class BankInfo {
	
	private long accountsNumber;
	private long clientsNumber;
	private BigDecimal creditSumOfBank;
	private Map<String, List<Client>> listOfClientsByCity;
	private Set<Client> clientsInBank;
	
	
	public BankInfo(Bank bank){
		
		BankReport report = new BankReport(); //creating Bank report
		
		
		accountsNumber = report.getAccountsNumber(bank);
		clientsNumber = report.getNumberOfClients(bank);
		creditSumOfBank = report.getBankCreditSum(bank);
		listOfClientsByCity = report.getClientsByCity(bank);
		clientsInBank = bank.getClients();
		
	}


	public long getAccountsNumber() {
		return accountsNumber;
	}


	public void setAccountsNumber(long accountsNumber) {
		this.accountsNumber = accountsNumber;
	}


	public long getClientsNumber() {
		return clientsNumber;
	}


	public void setClientsNumber(long clientsNumber) {
		this.clientsNumber = clientsNumber;
	}


	public BigDecimal getCreditSumOfBank() {
		return creditSumOfBank;
	}


	public void setCreditSumOfBank(BigDecimal creditSumOfBank) {
		this.creditSumOfBank = creditSumOfBank;
	}


	public Map<String, List<Client>> getListOfClientsByCity() {
		return listOfClientsByCity;
	}


	public void setListOfClientsByCity(Map<String, List<Client>> listOfClientsByCity) {
		this.listOfClientsByCity = listOfClientsByCity;
	}


	public Set<Client> getClientsInBank() {
		return clientsInBank;
	}


	public void setClientsInBank(Set<Client> clientsInBank) {
		this.clientsInBank = clientsInBank;
	}
	
	
	
	

}
