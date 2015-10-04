/**
 * @author Piotr Sobczak
 * Oct 3, 2015
 */
package com.luxoft.psobczak.dao;

import java.sql.Connection;

import com.luxoft.psobczak.exceptions.DAOException;

public interface BaseDAO {
	Connection openConnection() throws DAOException;

	void closeConnection();

}
