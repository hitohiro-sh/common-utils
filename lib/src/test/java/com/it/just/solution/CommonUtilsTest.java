package com.it.just.solution;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class CommonUtilsTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testUpperCamelToLowerUnderscore() {
		System.out.println(
				CommonUtils.upperCamelToLowerUnderscore("X"));
		System.out.println(
				CommonUtils.upperCamelToLowerUnderscore2("X"));
		System.out.println(
				CommonUtils.upperCamelToLowerUnderscore("X1"));
		System.out.println(
				CommonUtils.upperCamelToLowerUnderscore2("X1"));
		System.out.println(
				CommonUtils.upperCamelToLowerUnderscore("X1a"));
		System.out.println(
				CommonUtils.upperCamelToLowerUnderscore2("X1a"));
		System.out.println(
				CommonUtils.upperCamelToLowerUnderscore("Abc1"));
		System.out.println(
				CommonUtils.upperCamelToLowerUnderscore2("Abc1"));
		System.out.println(
				CommonUtils.upperCamelToLowerUnderscore("Abc12"));
		System.out.println(
				CommonUtils.upperCamelToLowerUnderscore2("Abc12"));
		System.out.println(
				CommonUtils.upperCamelToLowerUnderscore("Abc1Abc"));
		System.out.println(
				CommonUtils.upperCamelToLowerUnderscore2("Abc1Abc"));
	}

	@Test
	public void testLowerUnderscoreToUpperCamel() {
		System.out.println("toCamel");
		System.out.println(
				CommonUtils.lowerUnderscoreToUpperCamel("abc_1"));
		System.out.println(
				CommonUtils.lowerUnderscoreToUpperCamel2("abc_1"));
		System.out.println("toCamel");
		System.out.println(
				CommonUtils.lowerUnderscoreToUpperCamel("abc1"));
		System.out.println(
				CommonUtils.lowerUnderscoreToUpperCamel2("abc1"));
		System.out.println("toCamel");
		System.out.println(
				CommonUtils.lowerUnderscoreToUpperCamel("abc12"));
		System.out.println(
				CommonUtils.lowerUnderscoreToUpperCamel2("abc12"));
		System.out.println("toCamel");
		System.out.println(
				CommonUtils.lowerUnderscoreToUpperCamel("abc1_abc"));
		System.out.println(
				CommonUtils.lowerUnderscoreToUpperCamel2("abc1_abc"));
	}

}
