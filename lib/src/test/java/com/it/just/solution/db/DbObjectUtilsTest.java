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
		    	    ("jdbc:postgresql://localhost:5432/postgres", "user", "pass");
		    Statement s = conn.createStatement();
		    ResultSet rs = s.executeQuery("select distinct code from record");
		    List<Result> list = new ArrayList<>();
		    while (rs.next()) {
		    	Result row = new Result();
		    	DbObjectUtils.fillObject(rs, row);
		    	list.add(row);
		    }
		    for (Result r : list) {
		    	System.out.println(r.getCode());
		    }
		    list.clear();
		    rs.close();
		    s.close();
		    s = conn.createStatement();
		    rs = s.executeQuery("select code, t_date, open_val from record where code = '0000.A'");
		    while (rs.next()) {
		    	Result row = new Result();
		    	DbObjectUtils.fillObject(rs, row);
		    	list.add(row);
		    }
		    for (Result r : list) {
		    	System.out.println(r.getCode() + ":" + r.getTDate() + ":" + r.getOpenVal());
		    }
		    PreparedStatement ps = conn.prepareStatement("update stock set open_val = ? where code = ? and t_date = ?");
		    
		} catch (ClassNotFoundException | SQLException e) {
			// TODO 自動生成された catch ブロック
			// fail(e.getMessage());
			// e.printStackTrace();
			
		}
		
		// fail("まだ実装されていません");
	}

}
