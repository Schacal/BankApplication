package com.luxoft.psobczak.unitTests;

import static org.junit.Assert.*;

import java.math.BigDecimal;

import org.junit.Assert;
import org.junit.Test;

import com.luxoft.psobczak.controller.BankServiceImpl;
import com.luxoft.psobczak.exceptions.BankException;
import com.luxoft.psobczak.model.CheckingAccount;
import com.luxoft.psobczak.model.Client;
import com.luxoft.psobczak.model.Gender;
import com.luxoft.psobczak.model.SavingAccount;
import com.luxoft.psobczak.view.BankApplication;

public class CheckingAccountTest {

	@Test
	public void test() throws BankException {
		Client Adam = new Client("Adam", Gender.MALE);
		
		BankServiceImpl service = new BankServiceImpl();
		service.addAccount(Adam, new CheckingAccount(BigDecimal.TEN, BigDecimal.TEN));

		//service.addAccount(Adam, new SavingAccount(BigDecimal.TEN));
		
		BigDecimal deposit = new BigDecimal(20);
		BigDecimal withdraw = new BigDecimal(30);
		Adam.printReport();
		
		BankApplication.modifyBank(Adam, deposit, withdraw);
		
		Adam.printReport();
		
		//////withdraw
		Assert.assertEquals(new BigDecimal(0), Adam.getAccounts().get(0).getBalance());
	}




/*@Test
public void testWithdraw() throws BankException {
	Client Adam = new Client("Adam", Gender.MALE);
	
	BankServiceImpl service = new BankServiceImpl();
	service.addAccount(Adam, new CheckingAccount(BigDecimal.TEN, BigDecimal.TEN));

	
	
	BigDecimal deposit = new BigDecimal(20);
	BigDecimal withdraw = new BigDecimal(35);
Adam.printReport();
	
	BankApplication.modifyBank(Adam, deposit, withdraw);
	
	Adam.printReport();
	
	//////withdraw
	Assert.assertEquals(new BigDecimal(-5), Adam.getAccounts().get(0).getBalance());
}
*/
}
