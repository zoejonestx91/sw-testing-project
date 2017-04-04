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
    ArrayList<ArrayList<Integer>> coverage;
    
    static BufferedOutputStream out;

    public TestRecord(String testId) {
        this.testName = testId;
        this.coverage = new ArrayList<ArrayList<Integer>>();
    }
    
    public void cover(int classId, int line) {
    	while (classId >= coverage.size())
    		coverage.add(new ArrayList<Integer>());
    	ArrayList<Integer> lines = coverage.get(classId);
    	int index = line / 32;
    	int subindex = line % 32;
    	while (index >= lines.size())
    		lines.add(0);
    	lines.set(index, lines.get(index) | (1 << subindex));
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
    		ArrayList<Integer> classLines;
    		try {
    			classLines = coverage.get(order[i]);
    		} catch (IndexOutOfBoundsException e) {
    			continue;
    		}
    		for (int j = 0; j < classLines.size(); j ++) {
    			int x = classLines.get(j);
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
