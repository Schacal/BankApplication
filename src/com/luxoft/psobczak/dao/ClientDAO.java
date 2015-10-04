/**
 * @author Piotr Sobczak
 * Oct 3, 2015
 */
package com.luxoft.psobczak.dao;

import java.util.List;

import com.luxoft.psobczak.exceptions.DAOException;
import com.luxoft.psobczak.model.Bank;
import com.luxoft.psobczak.model.Client;

public interface ClientDAO {
	Client findClientByName(Bank bank, String client) throws DAOException;

	List<Client> getAllClients(Bank bank) throws DAOException;

	void saveClient(Client clientToSave) throws DAOException;

	void removeClient(Client clientToRemove) throws DAOException;
}
