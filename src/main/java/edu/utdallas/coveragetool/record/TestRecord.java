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
    public long[][] coverage;
    public final static byte WORD_SIZE = 64;
    
    static BufferedOutputStream out;

    public TestRecord(String testId) {
        this.testName = testId;
        this.coverage = new long[UnitListener.maxClasses][UnitListener.maxLines / WORD_SIZE + 1];
    }
    
    public void cover(int classId, int line) {
    	coverage[classId][line / WORD_SIZE] = coverage[classId][line / WORD_SIZE] | (1 << (line % WORD_SIZE));
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
    		long[] classLines;
    		try {
    			classLines = coverage[order[i]];
    		} catch (IndexOutOfBoundsException e) {
    			continue;
    		}
    		for (int j = 0; j < classLines.length; j ++) {
    			long x = classLines[j];
    			if (x == 0)
    				continue;
    			for (byte k = 0; k < WORD_SIZE; k ++) {
    				if ((x & (1 << k)) != 0) {
    					write(classNames[i]);
    					write(":");
    					write(Integer.toString(j * WORD_SIZE + k));
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
