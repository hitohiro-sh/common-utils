package com.it.just.solution.db;

import com.it.just.solution.db.annotation.Column;

	public class Result3 {
		private String code;
		private Integer value;
        
        public Result3() {}
        
        public Result3(String code, Integer value) {
            this.code = code;
            this.value = value;
        }
        
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