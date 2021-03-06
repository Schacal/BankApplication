/**
 * @author Piotr Sobczak
 * Oct 3, 2015
 */
package com.luxoft.psobczak.dao;

import java.util.TreeSet;

import com.luxoft.psobczak.exceptions.DAOException;
import com.luxoft.psobczak.model.Bank;
import com.luxoft.psobczak.model.Client;

public interface ClientDAO {
	Client findClientByName(Bank bank, String client) throws DAOException;

	TreeSet<Client> getAllClients(Bank bank) throws DAOException;

	void save(Client client, Bank bank) throws DAOException;

	void remove(Client client, Bank bank) throws DAOException;
}
