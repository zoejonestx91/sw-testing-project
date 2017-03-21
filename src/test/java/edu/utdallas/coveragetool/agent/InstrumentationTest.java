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
//		assertEquals(new A().sum(3, 4), 7);
		assertNotNull(UnitListener.getInst());
	}
}