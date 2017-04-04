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
    int[][] coverage;
    
    static BufferedOutputStream out;

    public TestRecord(String testId) {
        this.testName = testId;
        this.coverage = new int[UnitListener.maxClasses][UnitListener.maxLines / 32 + 1];
    }
    
    public void cover(int classId, int line) {
    	int[] lines = coverage[classId];
    	int index = line / 32;
    	int subindex = line % 32;
    	lines[index] = lines[index] | (1 << subindex);
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
    
    public void writeClassRecords(BufferedOutputStream out, int[] order, String[] classNames) throws IOException {
    	this.out = out;
    	for (int i = 0; i < order.length; i ++) {
    		int[] classLines;
    		try {
    			classLines = coverage[order[i]];
    		} catch (IndexOutOfBoundsException e) {
    			continue;
    		}
    		for (int j = 0; j < classLines.length; j ++) {
    			int x = classLines[j];
    			if (x == 0)
    				continue;
    			for (int k = 0; k < 32; k ++) {
    				if ((x & (1 << k)) != 0) {
    					write(classNames[i]);
    					write(":");
    					write(Integer.toString(j * 32 + k));
    					write("\r\n");
    				}
    			}
    		}
    	}
    }
    
    private static void write(String s) throws IOException {
        out.write(s.getBytes(), 0, s.length());
    }
}
