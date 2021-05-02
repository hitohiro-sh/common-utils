package com.it.just.solution;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.it.just.solution.annotation.NotProperty;

public class ObjectUtils {
	/**
	 * true if error
	 * set getter value of dst to setter of src
	 * @param src 
	 * @param dst must have corresponding setter of src
	 * @param methods
	 * @return
	 */
	static public <T,U> boolean fillObject(T src, U dst) {
		
		try {
			return fillObject(src, dst, false);
		} catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
			return false;
		}
	}
	
	static public <T,U> boolean fillObject(
			T src, U dst, boolean throwException) 
					throws NoSuchMethodException,IllegalAccessException,InvocationTargetException {
		Class<?> srcClass = src.getClass();
		Class<?> dstClass = dst.getClass();
		
		boolean error_flg = false;
		for (Method m : dstClass.getMethods()) {
			String name = m.getName();
			// System.out.println(name);
			if (name.startsWith("set")) {
				// System.out.println(name);
				// System.out.println(m.getReturnType());
				/*
				if (name.equals("getClass") && m.getReturnType() == (Class<?>)Class.class) {
					continue;
				}
				*/
				String getMethodName = name.replaceFirst("^set", "get");
				// System.out.println(setMethodName);
				try {
					Method srcM = srcClass.getMethod(getMethodName);
					Method dstM = dstClass.getMethod(name, srcM.getReturnType());
					
					if (srcM.getAnnotation(NotProperty.class) != null || dstM.getAnnotation(NotProperty.class) != null) {
						continue;
					}
					
					dstM.invoke(dst, srcM.invoke(src));
					
				} catch (NoSuchMethodException | 
						IllegalAccessException | 
						IllegalArgumentException | 
						InvocationTargetException ex) {
					if (throwException) {
						throw ex;
					}
					Logger.getLogger(ObjectUtils.class.getName()).log(Level.WARNING, ex.getMessage(), ex);
					// System.err.println("error:" + name + ":" + ex.toString() + ":" + ex.getMessage());
					error_flg = true;
				}
			}
		}
		return error_flg;
	}
	
}
