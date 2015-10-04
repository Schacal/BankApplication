/**
 * @author Piotr Sobczak
 * Oct 4, 2015
 */
package com.luxoft.psobczak.exceptions;

public class ClientNotFoundException extends RuntimeException {
	
	public ClientNotFoundException(String clienName) {
		super(clienName + " not found in database");
	}

}
