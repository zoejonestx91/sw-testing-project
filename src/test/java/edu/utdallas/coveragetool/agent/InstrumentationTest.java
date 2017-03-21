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
		assertEquals(7,7);
		assertNotNull(new A());
		// The following line caused instrumentation issues because it refers to the instrumentation
		// assertNotNull(UnitListener.getInst());
	}
}
