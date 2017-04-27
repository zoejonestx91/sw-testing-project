package edu.utdallas.metricstool;

import static org.junit.Assert.*;

import org.junit.Test;

import edu.utdallas.metricstool.test.A;

// This used to trigger the metrics on class A, but since the switch to a
// Maven plugin this is no longer the case. We have not restructured
// the project to be able to test this again yet. For now a separate test
// project is used.
public class BasicIT {

	@Test
	public void test() {
		assertEquals(5, new A().sum(2,3));
	}

}
