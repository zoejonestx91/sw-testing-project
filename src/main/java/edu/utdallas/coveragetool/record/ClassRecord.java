package edu.utdallas.coveragetool.record;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

import edu.utdallas.coveragetool.agent.UnitListener;

/**
 * Created by z on 3/20/17.
 */
public class ClassRecord implements Comparable<ClassRecord> {
    HashSet<Integer> coverage;
    String className;
    
    public ClassRecord(String className) {
    	this.className = className;
    	coverage = new HashSet<Integer>();
    }

    public void addLine(int line){
        coverage.add(line);
    }
    
    public void writeLines() throws IOException {
    	Integer[] lines = coverage.toArray(new Integer[coverage.size()]);
    	Arrays.sort(lines);
    	for (int i = 0; i < lines.length; i ++) {
			UnitListener.write(className);
			UnitListener.write(":");
			UnitListener.write(Integer.toString(lines[i]));
			UnitListener.write("\r\n");
    	}
    }

	@Override
	public int compareTo(ClassRecord that) {
		return className.compareTo(that.className);
	}
}
