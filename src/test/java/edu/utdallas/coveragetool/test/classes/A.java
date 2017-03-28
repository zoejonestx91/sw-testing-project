package edu.utdallas.coveragetool.test.classes;

public class A {
	public static int count = 0;
	public int sums;
	
	public A(int sums) {
		this.sums = sums;
	}
	
	public A() {}
	
	public int sum(int a, int b) {
		count ++;
		return a + b;
	}
	
	public int switchMeth(int x, int y) {
		switch (x) {
			case 0: return x + y;
			case 1: return x;
			case 2: return y;
			case 3: x += 1; break;
			case 4: y += 1;
			case 5: break;
			case 6: x += 1; y += 1;
			default: return x + y + 1;
		}
		return x - y;
	}
}
