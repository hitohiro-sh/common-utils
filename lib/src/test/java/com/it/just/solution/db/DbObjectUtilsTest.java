package com.it.just.solution.db;

import static org.junit.Assert.*;

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

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.it.just.solution.db.annotation.Column;

public class DbObjectUtilsTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	private class Result2 {
		private String code;
		private Integer value;

		@Column(name="code",constraint="key")
		public String getCode() {
			return code;
		}
		
		@Column(name="code",constraint="key")
		public void setCode(String code) {
			this.code = code;
		}

		@Column(name="value",constraint="none")
		public Integer getValue() {
			return value;
		}

		@Column(name="value",constraint="none")
		public void setValue(Integer value) {
			this.value = value;
		}

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
	public void testFillObject() {
		
		try {
			Class.forName("org.postgresql.Driver");
		    Connection conn =
		    	    DriverManager.getConnection
		    	    ("jdbc:postgresql://localhost:5432/postgres", "dbuser", "pass");
		    Statement s = conn.createStatement();

		    s.executeUpdate("drop table if exists record2");
            s.executeUpdate("create table record2 (code varchar primary key, value int)");
			s.executeUpdate("insert into record2 values ('C0001', 10)");
			s.executeUpdate("insert into record2 values ('C0002', 20)");
			ResultSet rs = s.executeQuery("select * from record2 order by code");
		    List<Result2> list = new ArrayList<>();
		    while (rs.next()) {
		    	var row = new Result2();
		    	DbObjectUtils.fillObject(rs, row);
		    	list.add(row);
		    }
			assertEquals(2, list.size());
			assertEquals("C0001", list.get(0).getCode());
			assertEquals(Integer.valueOf(10), list.get(0).getValue());
		    for (Result2 r : list) {
		    	System.out.println(r.getCode());
		    }
		    list.clear();
		    rs.close();
		    s.close();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO 自動生成された catch ブロック
			fail(e.getMessage());
			// e.printStackTrace();
			
		}
		
		// fail("まだ実装されていません");
	}

}
