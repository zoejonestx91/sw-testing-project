package edu.utdallas.coveragetool.record;

import java.util.HashMap;
import java.util.Set;
import java.util.TreeSet;

/**
 * Created by z on 3/20/17.
 */
public class ClassRecord implements Comparable<ClassRecord> {
    Integer classId;
    Set<Integer> lineRecords = new TreeSet<Integer>();

    public Set<Integer> getLineRecords() {
        return lineRecords;
    }

    public ClassRecord(Integer classId) {
        this.classId = classId;
    }

    public void addLine(int line){
        lineRecords.add(line);
    }

    public Integer getClassName() {
        return classId;
    }

    public int compareTo(ClassRecord o) {
        return classId.compareTo(o.getClassName());
    }
}
