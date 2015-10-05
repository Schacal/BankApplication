/**
 * @author Piotr Sobczak
 * Oct 4, 2015
 */
package com.luxoft.psobczak.unitTests;

import static org.junit.Assert.assertEquals;

import java.util.TreeSet;

import org.junit.Before;
import org.junit.Test;

import com.luxoft.psobczak.dao.ClientDaoImpl;
import com.luxoft.psobczak.exceptions.ClientNotFoundException;
import com.luxoft.psobczak.exceptions.DAOException;
import com.luxoft.psobczak.model.Bank;
import com.luxoft.psobczak.model.Client;
import com.luxoft.psobczak.model.Gender;

public class ClientDaoImplTest {
	ClientDaoImpl testedClass;
	Bank bank;
	Client client;
	Client preapredCLient;
	Client clientToSave;
	TreeSet<Client> clientList;

	@Before
	public void setUp() throws Exception {
		testedClass = new ClientDaoImpl();
		bank = new Bank("MY BANK");
		bank.setId(1);
		preapredCLient = new Client("Malcolm Lawrence", Gender.FEMALE);
		preapredCLient.setCity("Bearberry");
		preapredCLient.setEmail("convallis.est.vitae@ultrices.edu");

		clientToSave = new Client("Test Client", Gender.MALE);
		clientToSave.setCity("Warsaw");
		clientToSave.setEmail("testmail@gmail.com");

	}
	
	@Test
	public void testFindClientByName() throws DAOException {
		client = testedClass.findClientByName(bank, "Malcolm Lawrence");
		assertEquals(preapredCLient, client);
	}
	
	
	
	@Test(expected = ClientNotFoundException.class)
	public void testFindClientByNameException() throws DAOException {
		client = testedClass.findClientByName(bank, "Lawrence");
	}
	
	@Test
	public void testGetAllClients() throws DAOException {
		clientList = testedClass.getAllClients(bank);

	}

	@Test
	public void testSaveClient() throws DAOException {
		Client check;
		testedClass.saveClient(clientToSave, bank);
		check = testedClass.findClientByName(bank, clientToSave.getName());
		assertEquals(clientToSave, check);

		testedClass.removeClient(clientToSave, bank);

	}

	@Test(expected = ClientNotFoundException.class)
	public void testRemoveClient() throws DAOException {
		testedClass.saveClient(clientToSave, bank);
		testedClass.removeClient(clientToSave, bank);
		Client check = testedClass.findClientByName(bank, clientToSave.getName());

	}

}
