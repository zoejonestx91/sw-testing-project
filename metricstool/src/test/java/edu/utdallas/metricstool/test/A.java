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
	
	public int sum(int a, int b, int c) {
		return a + b + c;
	}
	
	public int sum(int a, int b, int c, int d) {
		int e = a + b;
		return e + c + d;
	}
	
	public double sum(int a, long b, double c, float d, short e, byte f, char g) {
		return a + b + c + d + e + f + g;
	}
	
	public static void main(String[] args) {
		m1();
		m2();
		new A().sum(3, 4);
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
		// have a comment
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
		double s;
		boolean c = a > b;
		double t;
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
	public static int m16(int x) {
		x = x >> 2;
		x = x >>> 3;
		return x;
	}
	public static boolean m17(boolean a, boolean b) {
		return a && b;
	}
	public static boolean m18(long x, long y) {
		return x > y;
	}
	public static boolean m19(long x, long y) {
		return x == y;
	}
	public static int m20(int x) {
		int y = -20;
		y--;
		y--;
		y++;
		--y;
		++y;
		return y + x;
	}
	public static void m21() {
		String x = "test";
		int y = 3;
		double z = (double) y;
		Object o = (Object) x;
		String s = (String) o;
		Character c = '3';
	}
}
