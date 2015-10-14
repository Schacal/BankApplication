/**
 * @author Piotr Sobczak
 * Oct 3, 2015
 */
package com.luxoft.psobczak.dao;

import com.luxoft.psobczak.exceptions.BankNotFoundException;
import com.luxoft.psobczak.exceptions.DAOException;
import com.luxoft.psobczak.model.Bank;

public interface BankDAO {

	Bank getBankByName(String name) throws DAOException, BankNotFoundException;

	void save(Bank bank) throws DAOException;

	void remove(Bank bank) throws DAOException;
}
