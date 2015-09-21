package com.luxoft.psobczak.unitTests;

import java.math.BigDecimal;
import java.util.Iterator;

import org.junit.Assert;
import org.junit.Test;

import com.luxoft.psobczak.controller.BankServiceImpl;
import com.luxoft.psobczak.exceptions.BankException;
import com.luxoft.psobczak.exceptions.OverDraftLimitExceededException;
import com.luxoft.psobczak.model.Account;
import com.luxoft.psobczak.model.CheckingAccount;
import com.luxoft.psobczak.model.Client;
import com.luxoft.psobczak.model.Gender;
import com.luxoft.psobczak.view.BankApplication;

public class CheckingAccountTest {

    @Test
    public void testForWithdrawMethodInFirstIf() throws BankException {
	Client Adam = new Client("Adam", Gender.MALE);

	BankServiceImpl service = BankServiceImpl.INSTANCE;
	service.addAccount(Adam, new CheckingAccount(BigDecimal.TEN, BigDecimal.TEN));
	BigDecimal deposit = new BigDecimal(30);
	BigDecimal withdraw = new BigDecimal(39);

	//BankApplication.modifyBank(Adam, deposit, withdraw);
	
  
 
//	Assert.assertEquals(new BigDecimal(1), Adam.getAccounts().get(0).getBalance());
	
    }

    @Test
    public void testForWithdrawMethodInSecondIf() throws BankException {
	Client Adam = new Client("Adam", Gender.MALE);

	BankServiceImpl service = BankServiceImpl.INSTANCE;
	service.addAccount(Adam, new CheckingAccount(BigDecimal.TEN, BigDecimal.TEN));

	BigDecimal deposit = new BigDecimal(20);
	BigDecimal withdraw = new BigDecimal(40);

	//BankApplication.modifyBank(Adam, deposit, withdraw);

//	Assert.assertEquals(new BigDecimal(-10), Adam.getAccounts().get(0).getBalance());
    }

    @Test(expected = OverDraftLimitExceededException.class)
    public void testForWithdrawMethodInThirdIf() throws BankException {
	Client Adam = new Client("Adam", Gender.MALE);

	BankServiceImpl service = BankServiceImpl.INSTANCE;
	service.addAccount(Adam, new CheckingAccount(BigDecimal.TEN, BigDecimal.TEN));

	BigDecimal deposit = new BigDecimal(20);
	BigDecimal withdraw = new BigDecimal(41);

	//BankApplication.modifyBank(Adam, deposit, withdraw);

    }

}
