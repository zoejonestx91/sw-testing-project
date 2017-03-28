package edu.utdallas.coveragetool.agent;

import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;

import org.junit.runner.Description;
import org.junit.runner.Result;
import org.junit.runner.notification.RunListener;

import edu.utdallas.coveragetool.record.Records;

public class UnitListener extends RunListener {
	private static int test = -1;
	private static Map<String, Integer> nameMap = new TreeMap<String, Integer>();
	private static Map<Integer, String> idMap = new TreeMap<Integer, String>();
	private static int nextId = 0;
	
	public void testStarted(Description description) {
		String testName = description.getClassName() + ':' + description.getMethodName();
		test = condMap(testName);
	}
	
	public void testRunFinished(Result result) throws IOException {
		Records.writeout(nameMap, idMap);
	}
	
	public static void stmtCover(String className, int line) {
		Records.addTestRecord(test).addClassRecord(condMap(className)).addLine(line);
	}
	
	public static int condMap(String name) {
		Integer id = nameMap.get(name);
		if (id == null) {
			nameMap.put(name, nextId);
			idMap.put(nextId, name);
			nextId++;
			return nextId - 1;
		}
		return id;
	}
}
