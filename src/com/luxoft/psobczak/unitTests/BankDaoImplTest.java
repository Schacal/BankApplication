/**
 * @author Piotr Sobczak
 * Oct 3, 2015
 */
package com.luxoft.psobczak.unitTests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import com.luxoft.psobczak.dao.BankDaoImpl;
import com.luxoft.psobczak.exceptions.BankNotFoundException;
import com.luxoft.psobczak.exceptions.DAOException;
import com.luxoft.psobczak.model.Bank;

public class BankDaoImplTest {
	BankDaoImpl bankdao;
	Bank testBank;
	String bankName;
	Bank bankToSave;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		bankdao = new BankDaoImpl();
		bankName = "MY BANK";
		bankToSave = new Bank("ING BANK");
	}

	@Test
	public void testgetBankNameMethod() throws DAOException, BankNotFoundException {

		testBank = bankdao.getBankByName(bankName);
		assertEquals(bankName, testBank.bankName);

	}

	@Test(expected = BankNotFoundException.class)
	public void testgetBankNameMethodShouldReturnException() throws DAOException, BankNotFoundException {
		bankName = "nameOfAbsentBankInDatabase";
		testBank = bankdao.getBankByName(bankName);
	}

	@Test
	public void saveBankTest() throws Exception {
		bankdao.saveBank(bankToSave);
		testBank = bankdao.getBankByName(bankToSave.bankName);
		assertEquals(bankToSave.bankName, testBank.bankName);

	}

	@Test(expected = BankNotFoundException.class)
	public void removeBankTest() throws Exception {
		bankdao.saveBank(bankToSave);
		bankdao.removeBank(bankToSave);
		testBank = bankdao.getBankByName(bankToSave.bankName);
		assertEquals(bankToSave.bankName, testBank.bankName);

	}

}
