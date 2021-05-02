package com.it.just.solution.db;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.it.just.solution.CommonUtils;
import com.it.just.solution.ObjectUtils;
import com.it.just.solution.db.annotation.Column;
import com.it.just.solution.db.impl.ResultMetaData;

public class DbObjectUtils {
	public static <T> boolean fillObject(ResultSet rs, T obj) throws SQLException {
		ResultMetaData metaData = new ResultMetaData(rs.getMetaData());
		Class<?> objClass = obj.getClass();
		
		for (Method m : objClass.getMethods()) {
			if (m.getName().startsWith("set") && m.isAnnotationPresent(Column.class)) {
				// System.out.println(m.getName());
				
				String colName = m.getAnnotation(Column.class).name();

				if (metaData.hasColumn(colName)) {
					// System.out.println(colName);
					try {
						
						m.invoke(obj, rs.getObject(colName));
					} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
						Logger.getLogger(DbObjectUtils.class.getName()).log(Level.WARNING, ex.getMessage(), ex);
						return false;
					}
				}
			}
		}
		return true;
	}
	
	

	public static Object [] toObjectArray(ResultSet rs) throws SQLException {
		ResultSetMetaData metadata = rs.getMetaData();
		int col_count = metadata.getColumnCount();
		Object[] array = new Object[col_count];
		for (int i = 0; i < col_count; i++) {
			array[i] = rs.getObject(i + 1);
		}
		return array;
	}
}
