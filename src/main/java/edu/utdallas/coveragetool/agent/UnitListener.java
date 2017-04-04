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

import edu.utdallas.coveragetool.record.ClassRecord;
import edu.utdallas.coveragetool.record.Records;
import edu.utdallas.coveragetool.record.TestRecord;

public class UnitListener extends RunListener {
	public static int maxClasses = 350;
	static int maxTests = 4500;
	public static int maxLines = 3000;
	
	static int numClasses = 0;
	static int numTests = 0;
	
	static TestRecord currentRecord;
	static TestRecord[] tests;
	static ClassRecord[] classes;
	
	private static int[] order;
	private static String[] classNames;
	
	private static BufferedOutputStream out;
	
	public static void init() {
		currentRecord = new TestRecord(null);
		tests = new TestRecord[maxTests];
		classes = new ClassRecord[maxClasses];
	}
	
	public static int mapClass(String className) {
		classes[numClasses] = new ClassRecord(numClasses, className);
		return numClasses ++;
	}
	
	public void testStarted(Description description) {
		currentRecord = new TestRecord(description.getClassName() + ':' + description.getMethodName());
		tests[numTests] = currentRecord;
		numTests++;
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
    	TestRecord[] records = Arrays.copyOf(tests, numTests);
    	Arrays.sort(records);
        for (int i = 0; i < records.length; i ++) {
            String name = records[i].getTestName();
            if (name == null)
            	continue;
            write(Records.PREAMBLE);
            write(" ");
            write(name);
            write("\r\n");
            setOrder();
            records[i].writeClassRecords(out, order, classNames);
        }
    }
    
    public static void write(String s) throws IOException {
        out.write(s.getBytes(), 0, s.length());
    }
    
    private static void setOrder() {
    	ClassRecord[] records = Arrays.copyOf(classes, numClasses);
    	Arrays.sort(records);
    	order = new int[records.length];
    	classNames = new String[records.length];
    	for (int i = 0; i < records.length; i ++) {
    		order[i] = records[i].getId();
    		classNames[i] = records[i].getName();
    	}
    }
}
