package edu.utdallas.coveragetool;

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


    public static TestRecord addTestRecord(String testName){
        TestRecord candidate = new TestRecord(testName);
        if(testRecords.add(candidate)){
            testRecordMap.put(testName, candidate);
            return candidate;
        } else {
            return testRecordMap.get(testName);
        }
    }

    public static void writeout(){

    }
}
