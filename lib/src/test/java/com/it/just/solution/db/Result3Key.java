package com.it.just.solution.db;

import com.it.just.solution.db.annotation.Column;

public class Result3Key {
	private String code;

	
	public Result3Key() {}
	
	public Result3Key(String code) {
		this.code = code;
	}
	
	@Column(name="code",constraint="key")
	public String getCode() {
		return code;
	}
	
	@Column(name="code",constraint="key")
	public void setCode(String code) {
		this.code = code;
	}
}