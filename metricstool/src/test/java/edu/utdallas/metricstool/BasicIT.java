package edu.utdallas.metricstool;

import static org.junit.Assert.*;

import org.junit.Test;

import edu.utdallas.metricstool.test.A;

public class BasicIT {

	@Test
	public void test() {
		assertEquals(5, new A().sum(2,3));
	}

}
