package com.it.just.solution.db.impl;

import java.sql.DatabaseMetaData;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;



public class ResultMetaData implements Iterable<ResultMetaData.ColumnInfo> {
	// final private Map<String,Integer> columnNameIndexMap = new HashMap<>();
	public class ColumnInfo {
		final private int index;
		final private String name;
		final private String typename;
		final private int sqlType;
		
		public ColumnInfo(int index, String name, String typename, int sqlType) {
			this.index = index;
			this.name = name;
			this.typename = typename;
			this.sqlType = sqlType;
		}
		
		public int getIndex() {
			return index;
		}
		
		public String getName() {
			return name;
		}
		
		public String getTypeName() {
			return typename;
		}
		
		public String getTypeBaseName() {
			int index = this.typename.lastIndexOf('.');
			// this.typename.l
			return this.typename.substring(index + 1);
			// return StringUtils.substringAfterLast(this.typename, ".");
		}
		
		public int getSqlType() {
			return sqlType;
		}
		
		@Override
		public String toString() {
			return "[" + this.index + "," + this.name + "," + this.typename + "," + this.sqlType + "]";
		}
	}
	
	private List<ColumnInfo> columnInfos = new ArrayList<>();
	
	public ResultMetaData(ResultSetMetaData metaData) {
		try {
			int colCount = metaData.getColumnCount();
			for (int i = 0; i < colCount; i++) {
				ColumnInfo info = new ColumnInfo(
						i+1,
						metaData.getColumnName(i+1),
						metaData.getColumnClassName(i+1),
						metaData.getColumnType(i + 1)
						);
				columnInfos.add(info);
			}
		} catch (SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
	}
	
	public int getColumnCount() {
		return this.columnInfos.size();
	}
	
	public ColumnInfo getInfo(int idx) {
		return columnInfos.get(idx - 1);
	}

	public List<ColumnInfo> getList() {
		return Collections.unmodifiableList(this.columnInfos);
	}

	public boolean hasColumn(String name) {
		return this.columnInfos.stream().anyMatch((info)-> {
			return info.getName().equals(name);
		});
	}
	
	@Override
	public Iterator<ColumnInfo> iterator() {
		return this.columnInfos.iterator();
	}
	

}
