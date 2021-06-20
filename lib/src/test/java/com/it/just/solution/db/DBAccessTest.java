package com.it.just.solution.db;

import static org.junit.Assert.*;

import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.naming.NamingException;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.it.just.solution.db.annotation.Column;
import com.it.just.solution.db.impl.DriverDBConnection;

public class DBAccessTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}


	
	private class Result {
		private String code;
		private BigDecimal openVal;
		private Date tDate;
		
		@Column(name="code",constraint="key")
		public String getCode() {
			return code;
		}
		
		@Column(name="code",constraint="key")
		public void setCode(String code) {
			this.code = code;
		}
		
		@Column(name="open_val",constraint="none")
		public BigDecimal getOpenVal() {
			return openVal;
		}
		
		@Column(name="open_val",constraint="none")
		public void setOpenVal(BigDecimal openVal) {
			this.openVal = openVal;
		}
		
		@Column(name="t_date",constraint="none")
		public Date getTDate() {
			return tDate;
		}
		
		@Column(name="t_date",constraint="none")
		public void setTDate(Date tDate) {
			this.tDate = tDate;
		}
	}
	
	@Test
	public void testSelect2() {
		try {
            int ret;
            var db = new DBAccess(new DriverDBConnection("org.postgresql.Driver", "jdbc:postgresql://localhost:5432/postgres", "dbuser", "pass"));
            ret = db.execute("drop table if exists record2");
            assertEquals(0, ret);
            ret = db.execute("create table record2 (code varchar primary key, value int)");
            assertEquals(0, ret);

            var list = db.select("select * from record2 order by code", Result3.class);
            assertEquals(0, list.size());

            db.insert("record2", new Result3("C0003", 30));
            db.insert("record2", new Result3("COOO4", 40));
			// db.execute("insert into record2 values ('C0003', 30)");
            // db.close();
            // db = new DBAccess(new DriverDBConnection("org.sqlite.JDBC", "jdbc:sqlite:sample.db"));
            
            list = db.select("select * from record2 order by code", Result3.class);
            assertEquals(2, list.size());
			assertEquals("C0003", list.get(0).getCode());
			assertEquals(Integer.valueOf(30), list.get(0).getValue());

            list = db.select(DbQueryUtils.selectQuery("record2", new Result3Key("C0003")), Result3.class);
            assertEquals(1, list.size());
			assertEquals("C0003", list.get(0).getCode());
			assertEquals(Integer.valueOf(30), list.get(0).getValue());

		} catch (ClassNotFoundException | 
            SQLException | 
            NamingException | 
            InstantiationException | 
            IllegalAccessException | 
            InvocationTargetException |
            NoSuchMethodException
            e) {
            System.err.println(e.getMessage());
			fail(e.getMessage());
			// e.printStackTrace();
		}
	}
}
