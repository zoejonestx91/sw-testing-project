package edu.utdallas.coveragetool.test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import edu.utdallas.coveragetool.test.classes.A;

public class ATest {
	
	A tester;

	@Before
	public void setUp() throws Exception {
		tester = new A();
	}

	@Test @Ignore
	public void sumTest() {
		assertEquals(11, tester.sum(5, 6));
	}
	
	@Test @Ignore
	public void switchTest() {
		tester.switchMeth(3, 4);
	}
	
	class C {
		
	}

}

class B {
	
}
