package com.luxoft.psobczak.unitTests;

import static org.junit.Assert.fail;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import com.luxoft.psobczak.dao.AccountDaoImpl;
import com.luxoft.psobczak.dao.ClientDaoImpl;
import com.luxoft.psobczak.exceptions.DAOException;
import com.luxoft.psobczak.model.Account;
import com.luxoft.psobczak.model.Bank;
import com.luxoft.psobczak.model.CheckingAccount;
import com.luxoft.psobczak.model.Client;
import com.luxoft.psobczak.model.Gender;
import com.luxoft.psobczak.model.SavingAccount;

public class AccountDaoImplTest {
	
	Account savingAccountToSave;
	Account checkingAccountToSave;
	AccountDaoImpl accountDaoImpl;
	Client testClient;
	ClientDaoImpl clientDao;
	Bank bank;

	@Before
	public void setUp() throws Exception {
		savingAccountToSave = new SavingAccount(BigDecimal.TEN);
		checkingAccountToSave = new CheckingAccount(BigDecimal.TEN, BigDecimal.TEN);
		accountDaoImpl = new AccountDaoImpl();
		clientDao = new ClientDaoImpl();
		bank = new Bank("MY BANK");
		bank.setId(1);

		testClient = new Client("testClient", Gender.MALE);
		testClient.setCity("TestCity");
		testClient.setEmail("email@gmail.com");
	}
	
	@Test
	public void testSaveAccount() throws DAOException {
	
		clientDao.saveClient(testClient, bank);

		accountDaoImpl.saveAccount(checkingAccountToSave, clientDao.findClientByName(bank, testClient.getName()));
		
	}
	@Ignore
	@Test
	public void testAddAccount() {
		fail("Not yet implemented");
	}
	
	@Test
	public void testRemoveByClientID() throws DAOException {
		accountDaoImpl.removeByClientID(testClient.id);
		clientDao.removeClient(testClient, bank);
	}
	@Ignore
	@Test
	public void testGetClientAccounts() {
		fail("Not yet implemented");
	}

}
