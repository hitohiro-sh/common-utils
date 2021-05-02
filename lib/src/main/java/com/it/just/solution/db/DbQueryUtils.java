package com.it.just.solution.db;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// import org.apache.commons.lang3.StringUtils;

import com.it.just.solution.CommonUtils;
import com.it.just.solution.Pair;
import com.it.just.solution.db.annotation.Column;

public class DbQueryUtils {
	
	private static Pair<List<String>,List<Method>> dbCols(Method[] methods) {
		List<String> db_cols = new ArrayList<>();
		List<Method> db_methods = new ArrayList<>();
		for (Method m : methods) {
			String name = m.getName();
			
			if (name.startsWith("get") && m.isAnnotationPresent(Column.class)) {

				db_cols.add(m.getAnnotation(Column.class).name());
				db_methods.add(m);
			}
		}
		return new Pair<>(db_cols, db_methods);
	}
	
	public static <T> DbQuery insertQuery(String tablename, Class<T> objClass) {
		DbQuery q = new DbQuery();
		
		List<String> db_cols = dbCols(objClass.getMethods()).getFirst();

		StringBuilder sb = new StringBuilder();
		sb.append("insert into " + tablename + " ");
		sb.append("(" + CommonUtils.join(db_cols,",") + ") ");
		sb.append("values ");
		String [] params = new String[db_cols.size()];
		Arrays.fill(params, "?");
		// StringUtils.join
		
		sb.append("(" + CommonUtils.join(Arrays.asList(params), ",") + ")");
		q.setSql(sb.toString());
		return q;
	}
	
	public static <T> DbQuery insertQuery(String tablename, T obj) 
			throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		Class<?> objClass = obj.getClass();
		DbQuery q = insertQuery(tablename, objClass);
		
		for (Method m : objClass.getMethods()) {
			String name = m.getName();
			// System.out.println(name);
			if (name.startsWith("get") && m.isAnnotationPresent(Column.class)) {

				q.addValues(m.invoke(obj));
			}
		}
		
		return q;
	}
	
	public static <T> DbQuery updateQuery(String tablename, Class<T> objClass) {
		DbQuery q = new DbQuery();
		
		List<String> db_cols = dbCols(objClass.getMethods()).getFirst();
		StringBuilder sb = new StringBuilder();
		sb.append("update " + tablename + " ");
		sb.append("set ");
		sb.append(CommonUtils.join(db_cols.stream().map((v)-> { return v + " = ?"; }).iterator(), ","));
		q.setBaseSql(sb.toString());
		return q;
	}
	

	
	public static <T,U> DbQuery updateQuery(String tablename, T obj, U key) 
			throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		Class<?> objClass = obj.getClass();
		DbQuery q = updateQuery(tablename, objClass);
		
		for (Pair<String,Method> pair : CommonUtils.zip(dbCols(objClass.getMethods()))) {
			q.addValues(pair.getSecond().invoke(obj));
		}
		
		Class<?> keyClass = key.getClass();
		
		for (Pair<String,Method> pair : CommonUtils.zip(dbCols(keyClass.getMethods()))) {
			q.addEqualCondtion(pair.getFirst(), pair.getSecond().invoke(key));
		}
		return q;	
	}
	
	public static DbQuery deleteQuery(String tablename) {
		DbQuery q = new DbQuery();
		
		q.setBaseSql("delete from " + tablename);
		
		return q;
	}
	
	public static <U> DbQuery deleteQuery(String tablename, U key) 
			throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		DbQuery q = deleteQuery(tablename);
		
		Class<?> keyClass = key.getClass();
		
		for (Pair<String,Method> pair : CommonUtils.zip(dbCols(keyClass.getMethods()))) {
			q.addEqualCondtion(pair.getFirst(), pair.getSecond().invoke(key));
		}
		return q;
	}
	
	public static <T> DbQuery selectQuery(String tablename) {
		DbQuery q = new DbQuery();
		
		q.setBaseSql("select * from " + tablename);
		
		return q;
	}
	
	public static <U> DbQuery selectQuery(String tablename, U key) 
			throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		DbQuery q = selectQuery(tablename);
		
		Class<?> keyClass = key.getClass();
		
		for (Pair<String,Method> pair : CommonUtils.zip(dbCols(keyClass.getMethods()))) {
			q.addEqualCondtion(pair.getFirst(), pair.getSecond().invoke(key));
		}
		return q;
	}
	
}
