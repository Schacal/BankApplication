package com.luxoft.psobczak.unitTests;

import static org.junit.Assert.*;

import java.math.BigDecimal;

import org.junit.Assert;
import org.junit.Test;

import com.luxoft.psobczak.controller.BankServiceImpl;
import com.luxoft.psobczak.exceptions.BankException;
import com.luxoft.psobczak.exceptions.NotEnoughFundsException;
import com.luxoft.psobczak.model.CheckingAccount;
import com.luxoft.psobczak.model.Client;
import com.luxoft.psobczak.model.Gender;
import com.luxoft.psobczak.model.SavingAccount;
import com.luxoft.psobczak.view.BankApplication;

public class SavingAccountTest {

    @Test
    public void withdrawTest() throws BankException {
	Client Adam = new Client("Adam", Gender.MALE);

	BankServiceImpl service = new BankServiceImpl();
	service.addAccount(Adam, new SavingAccount(BigDecimal.TEN));
	BigDecimal deposit = new BigDecimal(30);
	BigDecimal withdraw = new BigDecimal(40);
	

	
	BankApplication.modifyBank(Adam, deposit, withdraw);

	
	Assert.assertEquals(new BigDecimal(0), Adam.getAccounts().get(0).getBalance());
    }
    
    @Test(expected = NotEnoughFundsException.class)
    public void withdrawTestException() throws BankException {
	Client Adam = new Client("Adam", Gender.MALE);

	BankServiceImpl service = new BankServiceImpl();
	service.addAccount(Adam, new SavingAccount(BigDecimal.TEN));
	BigDecimal deposit = new BigDecimal(30);
	BigDecimal withdraw = new BigDecimal(41);

	
	BankApplication.modifyBank(Adam, deposit, withdraw);

    }
    
    

}
