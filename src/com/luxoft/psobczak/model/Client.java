package com.luxoft.psobczak.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collections;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import com.luxoft.psobczak.exceptions.FeedException;

public class Client implements Report, Comparable<Client>, Serializable {

	private static final long serialVersionUID = 1L;
	private String name;
	private TreeSet<Account> accounts;
	private Account activeAccount;
	private String email;
	private Iterator<Account> iterator;
	private String city;
	public int id;

	private float initialOverdraft = 1000;

	private Gender gender;

	public Client(String name, Gender gender) {

		id = 0;
		this.name = name;
		this.gender = gender;
		accounts = new TreeSet<Account>();

	}

	public Client(String name) {
		id = 0;
		this.name = name;
		this.gender = Gender.UNKNOWN;
		accounts = new TreeSet<Account>();
	}

	public void setActiveAccount(Account activeAccount) {
		this.activeAccount = activeAccount;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;

		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Client other = (Client) obj;

		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	@Override
	public String toString() {

		StringBuilder result = new StringBuilder("Client name: ");
		result.append(name);
		result.append(", have account(s): ");
		result.append(accounts);
		result.append(", gender: ");
		result.append(gender);

		return result.toString();
	}

	public BigDecimal getOverallBalance() {
		// method give overall balance on client's all accounts

		BigDecimal overallBalance = new BigDecimal(0);
		for (Account account : accounts) {
			overallBalance = overallBalance.add(account.getBalance());
		}
		return overallBalance;
	}

	public Set<Account> getAccounts() {
		return Collections.unmodifiableSet(accounts);
	}

	@Override
	public void printReport() {
		System.out.println("Client: " + getClientSalutation() + name + " have total accounts: " + this.accounts.size());

		iterator = accounts.iterator();
		while (iterator.hasNext()) {
			iterator.next().printReport();
		}
	}

	public float getInitialOverdraft() {
		return initialOverdraft;
	}

	public void setInitialOverdraft(float initialOverdraft) {
		this.initialOverdraft = initialOverdraft;
	}

	public void addAccount(Account account) {
		this.accounts.add(account);
		setActiveAccount(account);
	}

	public Gender getGender() {
		return gender;
	}

	public String getName() {
		return name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getClientSalutation() {
		return gender.getGreeting();
	}

	public Account getActiveAccount() {
		return activeAccount;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public void createAccount(Map<String, String> feed) {
		Account acc;
		if (feed.get("accounttype").equals("c")) {
			float balance = Float.valueOf(feed.get("balance"));
			BigDecimal balanceAsBigDecimal = new BigDecimal(balance);

			float overdraft = Float.valueOf(feed.get("overdraft"));
			BigDecimal overdraftAsBigDecimal = new BigDecimal(overdraft);

			acc = new CheckingAccount(balanceAsBigDecimal, overdraftAsBigDecimal);

			accounts.add(acc);
		}

		else if (feed.get("accounttype").equals("s")) {
			float balance = Float.valueOf(feed.get("balance"));
			BigDecimal balanceAsBigDecimal = new BigDecimal(balance);

			acc = new SavingAccount(balanceAsBigDecimal);

			accounts.add(acc);
		}

		else {
			throw new FeedException("Account type not found in external file");
		}

	}

	@Override
	public int compareTo(Client o) {
		return this.name.compareTo(o.getName());
	}

}
