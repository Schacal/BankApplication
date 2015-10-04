/**
 * @author Piotr Sobczak
 * Oct 4, 2015
 */
package com.luxoft.psobczak.dao;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.TreeSet;

import com.luxoft.psobczak.exceptions.ClientNotFoundException;
import com.luxoft.psobczak.exceptions.DAOException;
import com.luxoft.psobczak.exceptions.UnknownAccountTypeException;
import com.luxoft.psobczak.model.Account;
import com.luxoft.psobczak.model.Bank;
import com.luxoft.psobczak.model.CheckingAccount;
import com.luxoft.psobczak.model.Client;
import com.luxoft.psobczak.model.Gender;
import com.luxoft.psobczak.model.SavingAccount;

public class ClientDaoImpl extends BaseDaoImpl implements ClientDAO {

	@Override
	public Client findClientByName(Bank bank, String clientName) throws DAOException {
		Client client;
		String sql = "SELECT * FROM CLIENTS WHERE NAME = ? AND BANK = ?";
		PreparedStatement statement;
		try {
			openConnection();
			statement = super.conn.prepareStatement(sql);
			statement.setString(1, clientName);
			statement.setInt(2, bank.getId());

			ResultSet result = statement.executeQuery();

			if (result.next()) {
				client = new Client(result.getString("NAME"),
						(result.getString("GENDER")).equals("MALE") ? Gender.MALE : Gender.FEMALE);
				client.setCity(result.getString("CITY"));
				client.setEmail(result.getString("EMAIL"));
				client.id = result.getInt("ID");
				return client;
			} else {
				throw new ClientNotFoundException(clientName);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeConnection();
		}

		return null;

	}

	@Override
	public List<Client> getAllClients(Bank bank) throws DAOException {
		List<Client> listOfClientsInBank = new LinkedList<Client>();
		String sql = "SELECT * FROM CLIENTS WHERE BANK = ?";

		PreparedStatement statement;
		try {
			openConnection();
			statement = super.conn.prepareStatement(sql);
			statement.setInt(1, bank.getId());
			ResultSet result = statement.executeQuery();
			while (result.next()) {
				Client client = new Client(result.getString("NAME"),
						(result.getString("GENDER")).equals("MALE") ? Gender.MALE : Gender.FEMALE);
				client.setCity(result.getString("CITY"));
				client.setEmail(result.getString("EMAIL"));
				client.id = result.getInt("ID");
				client.addAccountsSetToClient(getAccountsFromClient(client.id));
				listOfClientsInBank.add(client);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return listOfClientsInBank;
	}

	public TreeSet<Account> getAccountsFromClient(int clientId) {
		TreeSet<Account> accounts = new TreeSet<Account>();
		String sql = "SELECT * FROM ACCOUNTS WHERE CLIENT_ID = ?";
		PreparedStatement statement;

		try {
			openConnection();
			statement = super.conn.prepareStatement(sql);
			statement.setInt(1, clientId);

			ResultSet result = statement.executeQuery();
			while (result.next()) {
				Account newAccount;

				if (result.getString("TYPE").equalsIgnoreCase("SavingAccount")) {
					BigDecimal initialBalance = new BigDecimal(result.getFloat("BALANCE"));
					newAccount = new SavingAccount(initialBalance);
					accounts.add(newAccount);
				} else if (result.getString("TYPE").equalsIgnoreCase("CheckingAccount")) {
					BigDecimal initialBalance = new BigDecimal(result.getFloat("BALANCE"));
					BigDecimal initialOverdraft = new BigDecimal(result.getFloat("OVERDRAFT"));
					newAccount = new CheckingAccount(initialBalance, initialOverdraft);
					accounts.add(newAccount);
				}

				else {
					throw new UnknownAccountTypeException();
				}

			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return accounts;
	}

	@Override
	public void saveClient(Client clientToSave) throws DAOException {
		// TODO Auto-generated method stub

	}

	@Override
	public void removeClient(Client clientToRemove) throws DAOException {
		// TODO Auto-generated method stub
	}

}
