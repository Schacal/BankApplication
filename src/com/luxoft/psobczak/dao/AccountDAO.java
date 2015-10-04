/**
 * @author Piotr Sobczak
 * Oct 3, 2015
 */
package com.luxoft.psobczak.dao;

import java.util.List;

import com.luxoft.psobczak.exceptions.DAOException;
import com.luxoft.psobczak.model.Account;

public interface AccountDAO {

	void saveAccount(Account accountToSave)throws DAOException;
	void addAccount(Account account)throws DAOException;
	void removeByClientID(int idClient)throws DAOException;
	List <Account> getClientAccounts(int idClient) throws DAOException;	
}
