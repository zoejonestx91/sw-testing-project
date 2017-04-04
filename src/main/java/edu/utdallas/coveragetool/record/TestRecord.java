package edu.utdallas.coveragetool.record;
import edu.utdallas.coveragetool.agent.UnitListener;
import edu.utdallas.coveragetool.record.ClassRecord;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

/**
 * Created by z on 3/20/17.
 */
public class TestRecord implements Comparable<TestRecord> {
    String testName;
    boolean[][] coverage;
    boolean[] classCoverage;

    public TestRecord(String testId, int maxClasses, int maxLines) {
        this.testName = testId;
        this.coverage = new boolean[maxClasses][maxLines];
        this.classCoverage = new boolean[maxClasses];
    }
    
    public void cover(int classId, int line) {
    	coverage[classId][line] = true;
    	classCoverage[classId] = true;
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
    
    public void writeClassRecords(BufferedOutputStream out, HashMap<Integer, String> revClassIndex) throws IOException {
        for (int i = 0; i < classCoverage.length; i ++) {
        	if (classCoverage[i] == false)
        		continue;
        	for (int j = 0; j < coverage[i].length; j ++) {
        		if (coverage[i][j] == true)
        			UnitListener.write(revClassIndex.get(i)+":"+j+"\r\n");
        	}
        }
    }
}
