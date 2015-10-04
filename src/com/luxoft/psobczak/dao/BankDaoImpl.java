/**
 * @author Piotr Sobczak
 * Oct 3, 2015
 */
package com.luxoft.psobczak.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.luxoft.psobczak.exceptions.BankNotFoundException;
import com.luxoft.psobczak.exceptions.DAOException;
import com.luxoft.psobczak.model.Bank;

public class BankDaoImpl extends BaseDaoImpl implements BankDAO {

	@Override
	public Bank getBankByName(String bankName) throws DAOException, BankNotFoundException {
		Bank bank = new Bank(bankName);
		String sql = "SELECT ID, NAME FROM BANK WHERE name = ?";
		PreparedStatement statement;
		try {
			openConnection();

			statement = super.conn.prepareStatement(sql);
			statement.setString(1, bankName);
			ResultSet result = statement.executeQuery();

			if (result.next()) {
				int id = result.getInt("ID");
				bank.setId(id);
			} else {
				throw new BankNotFoundException(bankName);
			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw new DAOException();
		}

		finally {
			closeConnection();
		}

		return bank;
	}

	@Override
	public void saveBank(Bank bankToSave) throws DAOException {

		String sql = "INSERT INTO BANK (NAME) VALUES (?)";
		PreparedStatement statement;
		try {
			openConnection();

			statement = super.conn.prepareStatement(sql);
			statement.setString(1, bankToSave.bankName);
			statement.execute();

		} catch (SQLException e) {
			e.printStackTrace();
			throw new DAOException();
		} finally {
			closeConnection();
		}

	}

	@Override
	public void removeBank(Bank bankToRemove) throws DAOException {
		String sql = "DELETE FROM BANK WHERE NAME = ?";
		PreparedStatement statement;
		try {
			openConnection();

			statement = super.conn.prepareStatement(sql);
			statement.setString(1, bankToRemove.bankName);
			statement.execute();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DAOException();
		} finally {
			closeConnection();
		}

	}
}
