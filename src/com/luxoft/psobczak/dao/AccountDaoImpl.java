package com.luxoft.psobczak.dao;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.TreeSet;

import com.luxoft.psobczak.exceptions.DAOException;
import com.luxoft.psobczak.exceptions.UnknownAccountTypeException;
import com.luxoft.psobczak.model.Account;
import com.luxoft.psobczak.model.CheckingAccount;
import com.luxoft.psobczak.model.Client;
import com.luxoft.psobczak.model.SavingAccount;

public class AccountDaoImpl extends BaseDaoImpl implements AccountDAO {
	

	@Override
	public void save(Account account, Client client) throws DAOException {
		String sql = "UPDATE ACCOUNTS SET TYPE = ?, BALANCE = ?, OVERDRAFT = ? WHERE CLIENT_ID = ? AND id = ?";
		PreparedStatement statement;
		try {
			openConnection();
			statement = super.conn.prepareStatement(sql);
			
			statement.setString(1, (account instanceof SavingAccount) ? "SavingAccount" : "CheckingAccount");
			statement.setInt(2, account.getBalance().intValue());			
			statement.setInt(3, (account instanceof CheckingAccount) ? ((CheckingAccount) account).getOverDraft().intValue() : 0);
			statement.setInt(4, client.id);
			statement.setInt(5, (account instanceof CheckingAccount) ? ((CheckingAccount) account).id : ((SavingAccount) account).id);
			statement.executeUpdate();
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		
		finally {
			closeConnection();
		}
		

	}

	@Override
	public void add(Account account, Client client) throws DAOException {
		String sql = "INSERT INTO ACCOUNTS (TYPE, BALANCE, OVERDRAFT, CLIENT_ID) VALUES (?,?,?,?)";
		PreparedStatement statement;
		try {
			openConnection();
			statement = super.conn.prepareStatement(sql);
			
			statement.setString(1, (account instanceof SavingAccount) ? "SavingAccount" : "CheckingAccount");
			statement.setInt(2, account.getBalance().intValue());			
			statement.setInt(3, (account instanceof CheckingAccount) ? ((CheckingAccount) account).getOverDraft().intValue() : 0);
			statement.setInt(4, client.id);
			statement.executeUpdate();
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		
		finally {
			closeConnection();
		}
		

	}

	@Override
	public void removeByClientID(int idClient) throws DAOException {
		String sql = "DELETE FROM ACCOUNTS WHERE CLIENT_ID = ?";
		PreparedStatement statement;
		try {
			openConnection();
			statement = super.conn.prepareStatement(sql);
			statement.setInt(1, idClient);			
			statement.executeUpdate();
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		
		finally {
			closeConnection();
		}
		

	}

	@Override
	public TreeSet<Account> getClientAccounts(int idClient) throws DAOException {
		TreeSet<Account> accounts = new TreeSet<Account>();
		String sql = "SELECT * FROM ACCOUNTS WHERE CLIENT_ID = ?";
		PreparedStatement statement;

		try {
			openConnection();
			statement = super.conn.prepareStatement(sql);
			statement.setInt(1, idClient);

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

}
