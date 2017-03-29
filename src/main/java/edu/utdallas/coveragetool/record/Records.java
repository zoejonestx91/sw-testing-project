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
    private static Map<Integer, TestRecord> testRecordMap = new HashMap<Integer, TestRecord>();
    private static final String PATH = "stmt-cov.txt";
    private static final String PREAMBLE = "[TEST]";
    private static BufferedOutputStream out;

    public static TestRecord addTestRecord(Integer testId){
        TestRecord candidate = new TestRecord(testId);
        if(testRecords.add(candidate)){
            testRecordMap.put(testId, candidate);
            return candidate;
        } else {
            return testRecordMap.get(testId);
        }
    }

    public static void writeout(Map<String, Integer> nameMap, Map<Integer, String> idMap) throws IOException {
        out = new BufferedOutputStream(new FileOutputStream(PATH));
        writeTestRecords(nameMap, idMap);
        out.flush();
        out.close();
    }

    static void writeTestRecords(Map<String, Integer> nameMap, Map<Integer, String> idMap) throws IOException {
        for (TestRecord testRecord: testRecords) {
            String name = idMap.get(testRecord.getTestName());
            if (name == null)
            	continue;
            write(PREAMBLE);
            write(" ");
            write(name);
            write("\r\n");
            writeClassRecords(testRecord, idMap);
        }
    }

    static void writeClassRecords(TestRecord testRecord, Map<Integer, String> idMap) throws IOException {
        for (ClassRecord classRecord: testRecord.getClassRecords())
        {
            writeLines(classRecord, idMap);
        }
    }

    static void writeLines(ClassRecord classRecord, Map<Integer, String> idMap) throws IOException {
        Integer className = classRecord.getClassName();
        for(Integer lineNum: classRecord.getLineRecords()){
            write(idMap.get(className)+":"+lineNum+"\r\n");
        }
    }

    static void write(String s) throws IOException {
        out.write(s.getBytes(), 0, s.length());
    }
}
