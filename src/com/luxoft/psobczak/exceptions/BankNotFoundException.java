/**
 * @author Piotr Sobczak
 * Oct 3, 2015
 */
package com.luxoft.psobczak.exceptions;

public class BankNotFoundException extends RuntimeException {

	public BankNotFoundException(String bankName) {
		super(bankName + " not found in database");
	}

}
