package com.it.just.solution.db;

import static org.junit.Assert.*;

import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.sql.Date;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.it.just.solution.db.annotation.Column;

public class DbQueryUtilsTest {

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
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testInsert() {
		DbQuery q = DbQueryUtils.insertQuery("record", Result.class);
		
		System.out.println(q.getSql());
		
		
	}
	
	@Test
	public void testSelect() {
		try {
			DbQuery q = DbQueryUtils.selectQuery("record", new Result());
			
			System.out.println(q.getSql());
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			
			fail(e.getMessage());
		}
	}
	
	@Test
	public void testUpdate() {
		DbQuery q = DbQueryUtils.updateQuery("record", Result.class);
		
		System.out.println(q.getSql());
	}

	@Test
	public void testDelete() {
		try {
			DbQuery q = DbQueryUtils.deleteQuery("record", new Result());
			
			System.out.println(q.getSql());
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			
			fail(e.getMessage());
		}
	}
}
