package edu.utdallas.coveragetool.agent;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class ATest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void sumTest() {
		assertEquals(11, new A().sum(5, 6));
	}
	
	class C {
		
	}

}

class B {
	
}
