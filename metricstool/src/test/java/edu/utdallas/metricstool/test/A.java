package edu.utdallas.metricstool.test;

public class A {
	
	boolean b;
	
	public A() {
		this.b = false;
	}
	
	public A(boolean b) {
		this.b = b;
	}
	
	public boolean toggle() {
		b = !b;
		return b;
	}
	
	public boolean getB() {
		return this.b;
	}
	
	public int sum(int a, int b) {
		return a + b;
	}
	public static void main(String[] args) {
		m1();
		m2();
	}
	public static void m1() {
		int a=1;
		int b=1;
		int c=a+b;
	}
	public static void m2() {
		m1();
		m1();
	}
	public static int m3(int x) {
		int a = x;
		int m,n,o,p,q;
		String z = "";
		boolean b = true;
		for (int i = 0; i < 30; i++) {
			if (b) {
				a ++;
				b = false;
			} else {
				z += "a";
				b = true;
			}
		}
		int i = 3;
		o = 4;
		double y = 4.0;
		y = y + i;
		boolean r = a > 3;
		boolean s = y > 3.0;
		return a;
	}
	public void m4() {
		int a = 3;
		int b = 4;
		boolean c = a > b;
		return;
	}
	public static void m5() {
		double a = 3;
		double b = 4;
		boolean c = a > b;
		return;
	}
	public static void m6() {
		int a = 3;
		int b = 4;
		if (a > b)
			a = 5;
		else
			a = 0;
		return;
	}
	public static int m7(int a, int b) {
		if (a < 2)
			a++; else b++;
		return a + b;
	}
	public static int m8(int a, int b) {
		a += b;
		return a;
	}
	public static int m9(int a, int b) {
		a = a + b;
		return a;
	}
	public static void m10() {
		Integer x = new Integer(30);
		boolean b = x instanceof Object;
		return;
	}
	public static int m11(boolean b, int x, int y) {
		int z;
		z = b ? x : y;
		return z;
	}
	public static int m12(boolean b, int x, int y) {
		int z;
		if (b) z = x; else z = y;
		return z;
	}
	public static int m13(int x) {
		return -x;
	}
	public static int m14(int x) {
		return ~x;
	}
	public static void m15(int[][] x, boolean b, String[] y, int z, long a) {
		return;
	}
}