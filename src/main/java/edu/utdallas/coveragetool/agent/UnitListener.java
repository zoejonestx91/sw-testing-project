package edu.utdallas.coveragetool.agent;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import org.junit.runner.Description;
import org.junit.runner.Result;
import org.junit.runner.notification.RunListener;

import edu.utdallas.coveragetool.record.Records;
import edu.utdallas.coveragetool.record.TestRecord;

public class UnitListener extends RunListener {
	private static int numTests = 0;
	static int numClasses = 0;
	
	static TestRecord currentRecord;
	static ArrayList<TestRecord> tests;
	static HashMap<String, Integer> classIndex;
	public static HashMap<Integer, String> revClassIndex;
	
	private static BufferedOutputStream out;
	
	public static void init() {
		currentRecord = new TestRecord(null);
		tests = new ArrayList<TestRecord>();
		classIndex = new HashMap<String, Integer>();
		revClassIndex = new HashMap<Integer, String>();
	}
	
	public static int mapClass(String className) {
		classIndex.put(className, numClasses);
		revClassIndex.put(numClasses, className);
		return numClasses ++;
	}
	
	public void testStarted(Description description) {
		currentRecord = new TestRecord(description.getClassName() + ':' + description.getMethodName());
		tests.add(currentRecord);
	}
	
	public void testFinished(Description description) {
		numTests ++;
	}
	
	public void testRunFinished(Result result) throws IOException {
		writeout();
	}
	
	public static void stmtCover(int classId, int line) {
		currentRecord.cover(classId, line);
	}
	
	private static void writeout() throws IOException {
		out = new BufferedOutputStream(new FileOutputStream(Records.PATH));
        writeTestRecords();
        out.flush();
        out.close();
    }
	
    private static void writeTestRecords() throws IOException {
        for (int i = 0; i < numTests; i ++) {
            String name = tests.get(i).getTestName();
            if (name == null)
            	continue;
            write(Records.PREAMBLE);
            write(" ");
            write(name);
            write("\r\n");
            tests.get(i).writeClassRecords(revClassIndex);
        }
    }
    
    public static void write(String s) throws IOException {
        out.write(s.getBytes(), 0, s.length());
    }
}
