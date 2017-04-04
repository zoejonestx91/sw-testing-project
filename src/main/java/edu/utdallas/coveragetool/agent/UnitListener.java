package edu.utdallas.coveragetool.agent;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
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
	static int maxTests = 5000;
	private static int numTests = 0;
	static int maxClasses = 200;
	static int numClasses = 0;
	static int maxLines = 5000;
	
	static TestRecord currentRecord;
	static TestRecord[] tests;
	static HashMap<String, Integer> classIndex;
	static HashMap<Integer, String> revClassIndex;
	
	private static BufferedOutputStream out;
	
	public static void init() {
		currentRecord = new TestRecord(null, maxClasses, maxLines);
		tests = new TestRecord[maxTests];
		classIndex = new HashMap<String, Integer>(2 * maxClasses);
		revClassIndex = new HashMap<Integer, String>(2 * maxClasses);
	}
	
	public static int mapClass(String className) {
		classIndex.put(className, numClasses);
		revClassIndex.put(numClasses, className);
		return numClasses ++;
	}
	
	public void testStarted(Description description) {
		currentRecord = new TestRecord(description.getClassName() + ':' + description.getMethodName(), maxClasses, maxLines);
		tests[numTests] = currentRecord;
	}
	
	public void testFinished(Description description) {
		numTests ++;
	}
	
	public void testRunFinished(Result result) throws IOException {
		Arrays.sort(tests, 0, numTests, null);
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
            String name = tests[i].getTestName();
            if (name == null)
            	continue;
            write(Records.PREAMBLE);
            write(" ");
            write(name);
            write("\r\n");
            tests[i].writeClassRecords(out, revClassIndex);
        }
    }
    
    public static void write(String s) throws IOException {
        out.write(s.getBytes(), 0, s.length());
    }
}
