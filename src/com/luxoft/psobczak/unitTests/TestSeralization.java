package com.luxoft.psobczak.unitTests;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.math.BigDecimal;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.luxoft.psobczak.controller.BankServiceImpl;
import com.luxoft.psobczak.model.CheckingAccount;
import com.luxoft.psobczak.model.Client;
import com.luxoft.psobczak.model.Gender;

public class TestSeralization {

    private Client jan;
    BankServiceImpl service;

    @Before
    public void setUp() throws Exception {
	service = BankServiceImpl.INSTANCE;
	
	jan = new Client("Jan", Gender.MALE);
	jan.addAccount(new CheckingAccount(BigDecimal.TEN, BigDecimal.TEN));
	jan.setCity("Berlin");
    }
    
    @Test
    public void testSaveClient() {
	service.saveClient(jan);
	File file = new File("client.ser");
	Assert.assertTrue(file.exists());
    }


    @Test
    public void testLoadClient() {
	Client newClient = service.loadClient();
	assertEquals(jan, newClient);
	assertEquals(jan.getName(), "Jan");
    }

  
}
