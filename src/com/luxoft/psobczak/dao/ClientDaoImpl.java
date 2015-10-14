/**
 * @author Piotr Sobczak
 * Oct 4, 2015
 */
package com.luxoft.psobczak.dao;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Iterator;
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
	public Client findClientByName(Bank bank, String name) throws DAOException {
		Client client;
		String sql = "SELECT * FROM CLIENTS WHERE NAME = ? AND BANK = ?";
		PreparedStatement statement;
		try {
			openConnection();
			statement = super.conn.prepareStatement(sql);
			statement.setString(1, name);
			statement.setInt(2, bank.getId());

			ResultSet result = statement.executeQuery();

			if (result.next()) {
				client = new Client(result.getString("NAME"),
						(result.getString("GENDER")).equals("MALE") ? Gender.MALE : Gender.FEMALE);
				client.setCity(result.getString("CITY"));
				client.setEmail(result.getString("EMAIL"));
				client.id = result.getInt("ID");
				client.addAccountsSetToClient(getAccountsFromClient(client.id));
				return client;
			} else {
				throw new ClientNotFoundException(name);
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
				for(Account account: client.getAccounts()){
					client.setActiveAccount(account);
				}
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
				

				if (result.getString("TYPE").equalsIgnoreCase("SavingAccount")) {
					BigDecimal initialBalance = new BigDecimal(result.getFloat("BALANCE"));
					SavingAccount newAccount = new SavingAccount(initialBalance);
					newAccount.id = result.getInt("id");
					
					
					accounts.add(newAccount);
				} else if (result.getString("TYPE").equalsIgnoreCase("CheckingAccount")) {
					BigDecimal initialBalance = new BigDecimal(result.getFloat("BALANCE"));
					BigDecimal initialOverdraft = new BigDecimal(result.getFloat("OVERDRAFT"));
					CheckingAccount newAccount = new CheckingAccount(initialBalance, initialOverdraft);
					newAccount.id = result.getInt("id");
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
	public void save(Client client, Bank bank) throws DAOException {
		String sql = "INSERT INTO CLIENTS (NAME, CITY, EMAIL, GENDER, BANK) VALUES (?,?,?,?,?)";
		PreparedStatement statement;
		try{
			openConnection();
			statement = super.conn.prepareStatement(sql);
			statement.setString(1, client.getName());
			statement.setString(2, client.getCity());
			statement.setString(3, client.getEmail());
			statement.setString(4, (client.getGender() == Gender.MALE) ? "MALE" : "FEMALE");
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
	public void remove(Client client, Bank bank) throws DAOException {
		AccountDaoImpl removeAccounts = new AccountDaoImpl();
		removeAccounts.removeByClientID(client.id);
		
		String sql = "DELETE FROM CLIENTS WHERE NAME = ? AND BANK = ?";
		PreparedStatement statement;
		try{
			openConnection();
			
			statement = super.conn.prepareStatement(sql);
			statement.setString(1, client.getName());
			statement.setInt(2, bank.getId());		
			statement.executeUpdate();
			
			
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		finally{
			closeConnection();
		}
	}

}
