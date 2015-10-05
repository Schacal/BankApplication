package com.luxoft.psobczak.service;

import com.luxoft.psobczak.exceptions.DAOException;

public interface Command {
	
	void execute() throws DAOException;
	
	void printCommandInfo();

}
