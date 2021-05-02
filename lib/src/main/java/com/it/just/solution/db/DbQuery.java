package com.it.just.solution.db;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * 
 * @author hitohiro
 * 
 * 
 */
public class DbQuery {
	final public static String eq = "=";
	final public static String neq = "<>";
	final public static String lt = "<";
	final public static String gt = ">";
	final public static String leq = "<=";
	final public static String geq = ">=";
	
	private String sql;
	private List<Object> values = new ArrayList<>();
	private String tableAlias;
	
	public DbQuery() {
		this.tableAlias = "";
	}
	
	public List<Object> getValues() {
		return this.values;
	}
	
	public Object getValue(int index) {
		return this.values.get(index);
	}
	
	public void setValue(int index, Object value) {
		this.values.set(index, value);
	}
	
	public void addValues(Object... value) {
		this.values.addAll(Arrays.asList(value));
	}
	
	public void clearValues() {
		this.values.clear();
	}
	
	public String getSql() {
		return this.sql;
	}
	
	/**
	 * 
	 * @param sql
	 * @return
	 */
	public DbQuery setSql(String sql) {
		this.sql = sql;
		return this;
	}
	
	/**
	 * 
	 * @param sql
	 * @return
	 */
	public DbQuery setBaseSql(String sql) {
		this.sql = sql + " where 1=1";
		return this;
	}
	
	public DbQuery setTableAlias(String v) {
		this.tableAlias = v + ".";
		return this;
	}
	
	private String col(String col) {
		return this.tableAlias + col;
	}
	
	public DbQuery addCondition(String statement, Object...value) {
		this.sql = this.sql + " and (" + statement + ")";
		this.values.addAll(Arrays.asList(value));
		return this;
	}
	
	public DbQuery addCondition(String col, String cnd, Object value) {
		this.sql = this.sql + " and " + this.col(col) + " " + cnd + " ?";
		this.values.add(value);
		return this;
	}
	
	public DbQuery addEqualCondtion(String col, Object value) {
		this.addCondition(col, DbQuery.eq, value);
		return this;
	}
	
	public DbQuery addIsNullCondition(String col) {
		this.sql = this.sql + " and " + this.col(col) + " is null";
		return this;
	}
	
	public DbQuery addIsNotNullCondition(String col) {
		this.sql = this.sql + " and " + this.col(col) + " is not null";
		return this;
	}
	

}
