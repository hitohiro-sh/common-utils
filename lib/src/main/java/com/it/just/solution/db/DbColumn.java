package com.it.just.solution.db;

public class DbColumn<T> {
	private String name;
	private Class<T> type;
	private String constraint;
	
	public DbColumn(String name, String typename, String constraint) throws ClassNotFoundException {
		this.name = name;
		this.type = (Class<T>) Class.forName(typename);
		this.constraint = constraint;
	}
	
	public DbColumn(String name, Class<T> type, String constraint) {
		this.name = name;
		this.type = type;
		this.constraint = constraint;
	}
	
	public String getName() {
		return this.name;
	}
	
	public Class<T> getType() {
		return this.type;
	}
	
	public String getConstraint() {
		return this.constraint;
	}
}
