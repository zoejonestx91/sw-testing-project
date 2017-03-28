package edu.utdallas.coveragetool.record;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

/**
 * The purpose of Records is to house test output, and write it out to stmt-cov.txt when the run is over. This should be
 * a singleton.
 */
public class Records {
    private static Set<TestRecord> testRecords = new TreeSet<TestRecord>();
    private static Map<String, TestRecord> testRecordMap = new HashMap<String, TestRecord>();
    private static final String PATH = "stmt-cov.txt";
    private static final String PREAMBLE = "[TEST]";
    private static BufferedOutputStream out;

    public static TestRecord addTestRecord(String testName){
        TestRecord candidate = new TestRecord(testName);
        if(testRecords.add(candidate)){
            testRecordMap.put(testName, candidate);
            return candidate;
        } else {
            return testRecordMap.get(testName);
        }
    }

    public static void writeout() throws IOException {
        out = new BufferedOutputStream(new FileOutputStream(PATH));
        writeTestRecords();
        out.flush();
        out.close();
    }

    static void writeTestRecords() throws IOException {
        for (TestRecord testRecord: testRecords) {
            write(PREAMBLE);
            write(" ");
            write(testRecord.getTestName());
            write("\r\n");
            writeClassRecords(testRecord);
        }
    }

    static void writeClassRecords(TestRecord testRecord) throws IOException {
        for (ClassRecord classRecord: testRecord.getClassRecords())
        {
            writeLines(classRecord);
        }
    }

    static void writeLines(ClassRecord classRecord) throws IOException {
        String className = classRecord.getClassName();
        for(Integer lineNum: classRecord.getLineRecords()){
            write(className+":"+lineNum+"\r\n");
        }
    }

    static void write(String s) throws IOException {
        out.write(s.getBytes(), 0, s.length());
    }
}
