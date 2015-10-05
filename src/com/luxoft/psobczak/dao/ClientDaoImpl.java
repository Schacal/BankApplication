/**
 * @author Piotr Sobczak
 * Oct 4, 2015
 */
package com.luxoft.psobczak.dao;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
	public TreeSet<Client> getAllClients(Bank bank) throws DAOException {
		TreeSet<Client> listOfClientsInBank = new TreeSet<Client>();
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
	public void saveClient(Client clientToSave, Bank bank) throws DAOException {
		String sql = "INSERT INTO CLIENTS (NAME, CITY, EMAIL, GENDER, BANK) VALUES (?,?,?,?,?)";
		PreparedStatement statement;
		try{
			openConnection();
			statement = super.conn.prepareStatement(sql);
			statement.setString(1, clientToSave.getName());
			statement.setString(2, clientToSave.getCity());
			statement.setString(3, clientToSave.getEmail());
			statement.setString(4, (clientToSave.getGender() == Gender.MALE) ? "MALE" : "FEMALE");
			statement.setInt(5, bank.getId());
			
			statement.executeUpdate();
			
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		finally{
			closeConnection();
		}

	}

	@Override
	public void removeClient(Client clientToRemove, Bank bank) throws DAOException {
		AccountDaoImpl removeAccounts = new AccountDaoImpl();
		
		String sql = "DELETE FROM CLIENTS WHERE NAME = ? AND BANK = ?";
		PreparedStatement statement;
		try{
			openConnection();
			statement = super.conn.prepareStatement(sql);
			statement.setString(1, clientToRemove.getName());
			statement.setInt(2, bank.getId());		
			statement.executeUpdate();
			removeAccounts.removeByClientID(clientToRemove.id);
			
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		finally{
			closeConnection();
		}
	}

}
