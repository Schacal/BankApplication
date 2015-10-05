/**
 * @author Piotr Sobczak
 * Oct 4, 2015
 */
package com.luxoft.psobczak.exceptions;

public class UnknownAccountTypeException extends RuntimeException {
	
	public UnknownAccountTypeException() {
		super("Unknown type of account");
	}

}
