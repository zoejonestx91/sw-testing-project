package edu.utdallas.coveragetool.agent;

import java.lang.instrument.Instrumentation;
import java.lang.instrument.UnmodifiableClassException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

import org.junit.runner.Description;
import org.junit.runner.Result;
import org.junit.runner.notification.RunListener;

import edu.utdallas.coveragetool.ClassRecord;

public class UnitListener extends RunListener {
	String currentTest;
	Map<String, ClassRecord> classes;
	
	public void testStarted(Description description) {
		currentTest = description.getClassName() + ':' + description.getMethodName();
		classes = new HashMap<String, ClassRecord>();
	}
	
	public static void stmtCover(String className, int line) {
		System.err.println(className + " - " + line);
	}
}
