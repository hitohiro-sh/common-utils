package com.it.just.solution.db.impl;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class JndiDBConnection implements DBConnection {

	private InitialContext context;
	final private String jndi;
	
	public JndiDBConnection(final String jndi) {
		this.jndi = jndi;
		try {
			this.context = new InitialContext();
		} catch (NamingException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		
	}
	
	@Override
	public Connection getConnection() throws NamingException, SQLException {
		DataSource dataSource = (DataSource)context.lookup(jndi);
		Connection conn = dataSource.getConnection();
		conn.setAutoCommit(false);
		
		return conn;
	}

	@Override
	public void close() throws IOException {
		if (this.context != null) {
			try {
				this.context.close();
			} catch (NamingException e) {

			}
		}
		
	}

	
}
