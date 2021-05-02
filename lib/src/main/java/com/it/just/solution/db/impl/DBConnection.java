package com.it.just.solution.db.impl;

import java.io.Closeable;
import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.NamingException;

public interface DBConnection extends Closeable {
	public Connection getConnection() throws NamingException, SQLException ;
}
