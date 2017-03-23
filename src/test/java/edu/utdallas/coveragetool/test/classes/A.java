package edu.utdallas.coveragetool.test.classes;

public class A {
	public static int count = 0;
	
	public int sum(int a, int b) {
		count ++;
		return a + b;
	}
}
