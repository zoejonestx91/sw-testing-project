package edu.utdallas.coveragetool.test;

import static org.junit.Assert.*;

import org.junit.Ignore;
import org.junit.Test;

import edu.utdallas.coveragetool.record.Records;

@Ignore
public class RecordsTest {

	@Test
	public void sameTest() {
		Records.addTestRecord("test1");
		Records.addTestRecord("test2");
		Records.addTestRecord("test1");
	}

	@Test
	public void sameClass() {
		Records.addTestRecord("test1").addClassRecord("class1");
		Records.addTestRecord("test2").addClassRecord("class2");
		Records.addTestRecord("test1").addClassRecord("class1");
	}

	@Test
	public void sameLine() {
		Records.addTestRecord("test1").addClassRecord("class1").addLine(0);
		Records.addTestRecord("test2").addClassRecord("class2").addLine(1);
		Records.addTestRecord("test1").addClassRecord("class1").addLine(0);
	}

}
