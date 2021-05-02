package com.it.just.solution;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;


// import org.apache.commons.lang3.StringUtils;
// import org.apache.commons.lang3.text.WordUtils;

import com.google.common.base.CaseFormat;

public class CommonUtils {
	
	/*
	public static String upperCamelToLowerUnderscore(String str) {
		return StringUtils.join(StringUtils.splitByCharacterTypeCamelCase(str), "_").toLowerCase();
	}
	*/
	public static String upperCamelToLowerUnderscore(String str) {
		int start_idx = 0;
		StringBuilder sb = new StringBuilder("");
		for (int i = 1; i < str.length(); i++) {
			if (Character.isUpperCase(str.charAt(i))) {
				sb.append(str.substring(start_idx, i).toLowerCase() + "_");
				start_idx = i;
			}
		}
		sb.append(str.substring(start_idx).toLowerCase());
		return sb.toString();
	}
	
	public static String capitalize(String str) {
		return Character.toUpperCase(str.charAt(0)) + str.substring(1);
	}
	
	
	
	public static <T> String join(List<T> list, String sep) {
		return join(list.listIterator(), sep);
	}
	
	public static <T> String join(Iterator<T> it, String sep) {
		StringBuilder sb = new StringBuilder("");
		
		// ListIterator<T> it = list.listIterator();
		if (it.hasNext()) {
			T obj = it.next();
			sb.append(obj.toString());
		}
		if (it.hasNext()) {
			while (it.hasNext()) {
				T obj = it.next();
				sb.append(sep + obj.toString());
			}
		}
		return sb.toString();
	}
	
	
	public static String lowerUnderscoreToUpperCamel(String str) {
		// return StringUtils.remove(WordUtils.capitalizeFully(str, '_'), "_");
		String [] ss = str.split("_");
		for (int i = 0; i < ss.length; i++) {
			ss[i] = capitalize(ss[i]);
		}
		
		return join(Arrays.asList(ss), "");
	}
	
	public static String upperCamelToLowerUnderscore2(String str) {
		return CaseFormat.UPPER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, str);
		// return StringUtils.join(StringUtils.splitByCharacterTypeCamelCase(str), "_").toLowerCase();
	}
	
	public static String lowerUnderscoreToUpperCamel2(String str) {
		return CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.UPPER_CAMEL, str);
		// return StringUtils.remove(WordUtils.capitalizeFully(str, '_'), "_");
	}
	
	
	/*
	public static String upperCamelToLowerUnderscore(String str) {
		String [] ss = str.
	}
	*/
	
	
	
	
	public static double toDouble(BigDecimal v) {
		return Double.valueOf(v.toEngineeringString());
	}
	
	public static boolean isNullOrEmpty(String v) {
		if (v == null) {
			return true;
		} else {
			return v.isEmpty();
		}
	}
	
	public static <T,U> List<Pair<T,U>> zip(List<T> f, List<U> s) {
		List<Pair<T,U>> list = new ArrayList<>();
		int len = f.size() < s.size() ? f.size() : s.size();
		for (int i = 0; i < len; i++) {
			list.add(new Pair<>(f.get(i), s.get(i)));
		}
		return list;
	}
	
	public static <T,U> List<Pair<T,U>> zip(Pair<List<T>,List<U>> pair) {
		return zip(pair.getFirst(), pair.getSecond());
	}
	
	public static <T,U> Pair<List<T>,List<U>> unzip(List<Pair<T,U>> list) {
		List<T> f_list = new ArrayList<>();
		List<U> s_list = new ArrayList<>();
		
		for (Pair<T,U> pair : list) {
			f_list.add(pair.getFirst());
			s_list.add(pair.getSecond());
		}
		return new Pair<>(f_list, s_list);
	}
}
