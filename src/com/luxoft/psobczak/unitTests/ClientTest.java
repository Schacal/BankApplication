package com.luxoft.psobczak.unitTests;

import java.math.BigDecimal;

import org.junit.Assert;
import org.junit.Test;

import com.luxoft.psobczak.controller.BankServiceImpl;
import com.luxoft.psobczak.model.CheckingAccount;
import com.luxoft.psobczak.model.Client;
import com.luxoft.psobczak.model.Gender;
import com.luxoft.psobczak.model.SavingAccount;

public class ClientTest {
	

	

	@Test
	public void equalsTestShouldBeTrue() {
		Client Adam = new Client("Adam", Gender.MALE);
		Client Adam2 = new Client("Adam", Gender.MALE);
		
		Assert.assertEquals(true, Adam.equals(Adam2));
	}
	
	@Test
	public void OverAllBalanceTestMetohd() {
		Client Adam = new Client("Adam", Gender.MALE);
		
		BankServiceImpl service = new BankServiceImpl();
		service.addAccount(Adam, new CheckingAccount(BigDecimal.TEN, BigDecimal.TEN));
		service.addAccount(Adam, new CheckingAccount(BigDecimal.TEN, BigDecimal.TEN));
		service.addAccount(Adam, new SavingAccount(BigDecimal.TEN));
		
		BigDecimal result = new BigDecimal(30); 
		
		Assert.assertEquals(result, Adam.getOverallBalance());
		
	
	}
	
	

}
