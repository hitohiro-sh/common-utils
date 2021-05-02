package com.it.just.solution;

import static org.junit.Assert.*;

import org.junit.Test;

import junit.framework.Assert;

public class ObjectUtilsTest {

	private class A {
		private String x;
		private int y;
		public String getX() {
			return x;
		}
		public void setX(String x) {
			this.x = x;
		}
		public int getY() {
			return y;
		}
		public void setY(int y) {
			this.y = y;
		}
	}
	
	private class B {
		private String x;
		private int y;
		public String getX() {
			return x;
		}
		public void setX(String x) {
			this.x = x;
		}
		public int getY() {
			return y;
		}
		public void setY(int y) {
			this.y = y;
		}
	}
	
	@Test
	public void testFillObject() {
		A src = new A();
		src.setX("x");
		src.setY(1);
		B dst = new B();
		boolean ret = ObjectUtils.fillObject(src, dst);
		assertEquals(src.getX(), "x");
		assertEquals(src.getY(), 1);
		assertEquals(src.getX(), dst.getX());
		assertEquals(src.getY(), dst.getY());
		assertFalse(ret);
		
	}

}
