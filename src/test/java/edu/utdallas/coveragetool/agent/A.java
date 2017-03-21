package edu.utdallas.coveragetool.agent;

class A {
	public static int count = 0;
	
	public int sum(int a, int b) {
		count ++;
		return a + b;
	}
}
