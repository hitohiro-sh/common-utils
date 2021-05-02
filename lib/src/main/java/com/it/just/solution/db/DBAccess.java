package com.it.just.solution.db;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.naming.NamingException;

import com.it.just.solution.db.impl.DBConnection;

public class DBAccess implements AutoCloseable {
	final private DBConnection connection;
	
	public DBAccess(DBConnection connection) {
		this.connection = connection;
	}
	
	public Connection getConnection() throws NamingException, SQLException {
		return this.connection.getConnection();
	}
	
	public <T> List<T> select(DbQuery q, Class<T> entityClass) throws NamingException, SQLException, InstantiationException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		Connection conn = this.getConnection();
		List<T> ret = new ArrayList<>();
		PreparedStatement s = null;
		ResultSet rs = null;
		try {
			var cons = entityClass.getDeclaredConstructor();
			s = conn.prepareStatement(q.getSql());
			for (int i = 0; i < q.getValues().size(); i++) {
				s.setObject(i + 1, q.getValues().get(i));
			}
			rs = s.executeQuery();
			while (rs.next()) {
				T obj = cons.newInstance();
				DbObjectUtils.fillObject(rs, obj);
				
				ret.add(obj);
			}
		} finally {
			if (rs != null) {
				rs.close();
			}
			if (s != null) {
				s.close();
			}
		}
		return ret;
	}
	
	public <T> List<T> select(String sql, Class<T> entityClass) throws NamingException, SQLException, InstantiationException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		Connection conn = this.getConnection();
		List<T> ret = new ArrayList<>();
		Statement s = null;
		ResultSet rs = null;
		try {
			s = conn.createStatement();
			rs = s.executeQuery(sql);
			var cons = entityClass.getDeclaredConstructor();
			while (rs.next()) {
				T obj = cons.newInstance();
				DbObjectUtils.fillObject(rs, obj);
				
				ret.add(obj);
			}
		} finally {
			if (rs != null) {
				rs.close();
			}
			if (s != null) {
				s.close();
			}
		}
		return ret;
	}

	public <T> int insert(String tablename, T entity) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, NamingException, SQLException {
		DbQuery q = DbQueryUtils.insertQuery(tablename, entity);
		Connection conn = this.getConnection();

		PreparedStatement s = null;
		try {
			s = conn.prepareStatement(q.getSql());
			for (int i = 0; i < q.getValues().size(); i++) {
				s.setObject(i + 1, q.getValues().get(i));
			}
			return s.executeUpdate();
			
		} finally {
			
			if (s != null) {
				s.close();
			}
		}
	}
	
	public <T,U> int update(String tablename, T entity, U key) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, NamingException, SQLException {
		DbQuery q = DbQueryUtils.updateQuery(tablename, entity, key);
		Connection conn = this.getConnection();
		// List<T> ret = new ArrayList<>();
		PreparedStatement s = null;
		// ResultSet rs = null;
		try {
			s = conn.prepareStatement(q.getSql());
			for (int i = 0; i < q.getValues().size(); i++) {
				s.setObject(i + 1, q.getValues().get(i));
			}
			
			return s.executeUpdate();
			
		} finally {
			
			if (s != null) {
				s.close();
			}
		}		
		
		
	}
	
	public int execute(String sql) throws NamingException, SQLException {
		Connection conn = this.getConnection();
		// List<T> ret = new ArrayList<>();
		Statement s = null;
		try {
			s = conn.createStatement();
			return s.executeUpdate(sql);
			
		} finally {

			if (s != null) {
				s.close();
			}
		}
	}
	
	
	@Override
	public void close() {
		try {
			this.connection.close();
		} catch (Exception e) {}
		
	}
}
