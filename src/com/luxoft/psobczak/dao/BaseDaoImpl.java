/**
 * @author Piotr Sobczak
 * Oct 3, 2015
 */
package com.luxoft.psobczak.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.luxoft.psobczak.exceptions.DAOException;

public class BaseDaoImpl implements BaseDAO {

	Connection conn;

	@Override
	public Connection openConnection() throws DAOException {
		try {
			Class.forName("org.h2.Driver");
			conn = DriverManager.getConnection("jdbc:h2:~/workspace/BankApplication/test", "sa", "");

			return conn;
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			throw new DAOException();
		}

	}

	@Override
	public void closeConnection() {
		try {
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
