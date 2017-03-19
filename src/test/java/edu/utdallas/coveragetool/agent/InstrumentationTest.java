package edu.utdallas.coveragetool.agent;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class InstrumentationTest {
	@Before
	public void setUp() throws Exception {
	}
	
	@Test
	public void instrumentationReceived() {
		assertNotNull(UnitListener.inst);
	}
}

class A {
	public static int count = 0;
	
	public int sum(int a, int b) {
		return a + b;
	}
}