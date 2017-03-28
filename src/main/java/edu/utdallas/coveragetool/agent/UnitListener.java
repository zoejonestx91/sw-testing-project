package edu.utdallas.coveragetool.agent;

import java.io.IOException;

import org.junit.runner.Description;
import org.junit.runner.Result;
import org.junit.runner.notification.RunListener;

import edu.utdallas.coveragetool.record.Records;

public class UnitListener extends RunListener {
	private static String test = "";
	
	public void testStarted(Description description) {
		test = description.getClassName() + ':' + description.getMethodName();
	}
	
	public void testRunFinished(Result result) throws IOException {
		Records.writeout();
	}
	
	public static void stmtCover(String className, int line) {
		Records.addTestRecord(test).addClassRecord(className.replace('/', '.')).addLine(line);
	}
}
