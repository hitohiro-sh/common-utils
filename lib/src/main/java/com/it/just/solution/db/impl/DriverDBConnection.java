package com.it.just.solution.db.impl;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.naming.NamingException;

public class DriverDBConnection implements DBConnection {
	private Connection conn;
	
	public DriverDBConnection(
			Properties settings) throws ClassNotFoundException, SQLException {

		this(settings.getProperty("driver"),
				settings.getProperty("url"), 
	    	    settings.getProperty("user"), 
	    	    settings.getProperty("password"));
	}

	public DriverDBConnection(
			String driver,
			String url,
			String user,
			String password) throws ClassNotFoundException, SQLException {
		try {
			Class.forName(driver);


			this.conn = DriverManager.getConnection(
	    	    url, 
	    	    user, 
	    	    password);
		} catch (ClassNotFoundException | SQLException e) {
			Logger.getLogger(DriverDBConnection.class.getName()).log(Level.SEVERE, e.getMessage(), e);
			throw e;
		}
	}
	
	public DriverDBConnection(String driver, String connString) throws ClassNotFoundException, SQLException {
		try {
			Class.forName(driver);


			this.conn = DriverManager.getConnection(connString);
		} catch (ClassNotFoundException | SQLException e) {
			Logger.getLogger(DriverDBConnection.class.getName()).log(Level.SEVERE, e.getMessage(), e);
			throw e;
		}
	}

	@Override
	public Connection getConnection() throws NamingException, SQLException {
		return this.conn;
	}
	
	@Override
	public void close() throws IOException {
		if (this.conn != null) {
			try {
				this.conn.close();
			} catch (SQLException e) {

			}
		}

	}

}
