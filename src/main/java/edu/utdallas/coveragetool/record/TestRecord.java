package edu.utdallas.coveragetool.record;
import edu.utdallas.coveragetool.agent.UnitListener;
import edu.utdallas.coveragetool.record.ClassRecord;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

/**
 * Created by z on 3/20/17.
 */
public class TestRecord implements Comparable<TestRecord> {
    String testName;
    HashMap<Integer, ClassRecord> coverage;

    public TestRecord(String testId) {
        this.testName = testId;
        this.coverage = new HashMap<Integer, ClassRecord>();
    }
    
    public void cover(int classId, int line) {
    	ClassRecord record = coverage.get(classId);
    	if (record == null) {
    		record = new ClassRecord(UnitListener.revClassIndex.get(classId));
    		coverage.put(classId, record);
    	}
    	record.addLine(line);
    }

    public String getTestName() {
        return testName;
    }

    public void setTestName(String testId) {
        this.testName = testId;
    }

    public int compareTo(TestRecord that) {
        return testName.compareTo(that.getTestName());
    }
    
    public void writeClassRecords(HashMap<Integer, String> revClassIndex) throws IOException {
    	ClassRecord[] records = coverage.values().toArray(new ClassRecord[coverage.size()]);
    	Arrays.sort(records);
        for (int i = 0; i < records.length; i ++) {
        	records[i].writeLines();
        }
    }
}
