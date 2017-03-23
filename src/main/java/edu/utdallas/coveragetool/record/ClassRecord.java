package edu.utdallas.coveragetool.record;

import java.util.HashMap;
import java.util.Set;
import java.util.TreeSet;

/**
 * Created by z on 3/20/17.
 */
public class ClassRecord implements Comparable<ClassRecord> {
    String className;
    Set<Integer> lineRecords = new TreeSet<Integer>();

    public Set<Integer> getLineRecords() {
        return lineRecords;
    }

    public ClassRecord(String className) {
        this.className = className;
    }

    public void addLine(int line){
        lineRecords.add(line);
    }

    public String getClassName() {
        return className;
    }

    public int compareTo(ClassRecord o) {
        return className.compareTo(o.getClassName());
    }
}
