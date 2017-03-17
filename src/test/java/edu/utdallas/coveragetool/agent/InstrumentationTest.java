package edu.utdallas.coveragetool.agent;

import java.lang.instrument.Instrumentation;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class InstrumentationTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void staticStateTest() {
		System.out.println("hi");
		A test = new A();
		int x = test.sum(5, 6);
	}

}

class A {
	public static int count = 0;
	
	public int sum(int a, int b) {
		return a + b;
	}
}