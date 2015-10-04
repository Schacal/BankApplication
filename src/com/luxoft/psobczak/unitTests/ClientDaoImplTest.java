/**
 * @author Piotr Sobczak
 * Oct 4, 2015
 */
package com.luxoft.psobczak.unitTests;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Before;
import org.junit.Ignore;
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
	List<Client> clientList;


	@Before
	public void setUp() throws Exception {
		testedClass = new ClientDaoImpl();
		bank = new Bank("MY BANK");
		bank.setId(1);
		preapredCLient = new Client("Malcolm Lawrence", Gender.FEMALE);
		preapredCLient.setCity("Bearberry");
		preapredCLient.setEmail("convallis.est.vitae@ultrices.edu");
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
		int i = 1;
		for(Client a : clientList){
			System.out.println(i + a.toString());
			i++;
		}
	}

	@Ignore
	@Test
	public void testSaveClient() {
		
	}

	@Ignore
	@Test
	public void testRemoveClient() {

	}

}
