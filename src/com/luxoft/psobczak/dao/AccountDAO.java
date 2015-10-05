/**
 * @author Piotr Sobczak
 * Oct 3, 2015
 */
package com.luxoft.psobczak.dao;

import java.util.TreeSet;

import com.luxoft.psobczak.exceptions.DAOException;
import com.luxoft.psobczak.model.Account;
import com.luxoft.psobczak.model.Client;

public interface AccountDAO {

	void saveAccount(Account accountToSave, Client client)throws DAOException;
	void addAccount(Account account, Client client)throws DAOException;
	void removeByClientID(int idClient)throws DAOException;
	TreeSet <Account> getClientAccounts(int idClient) throws DAOException;	
}
