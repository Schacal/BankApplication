/**
 * @author Piotr Sobczak
 * Oct 3, 2015
 */
package com.luxoft.psobczak.dao;

import com.luxoft.psobczak.exceptions.BankNotFoundException;
import com.luxoft.psobczak.exceptions.DAOException;
import com.luxoft.psobczak.model.Bank;

public interface BankDAO {

	Bank getBankByName(String bankName) throws DAOException, BankNotFoundException;

	void saveBank(Bank bankToSave) throws DAOException;

	void removeBank(Bank bankToRemove) throws DAOException;
}
