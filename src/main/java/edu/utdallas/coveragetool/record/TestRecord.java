package edu.utdallas.coveragetool.record;
import edu.utdallas.coveragetool.record.ClassRecord;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

/**
 * Created by z on 3/20/17.
 */
public class TestRecord implements Comparable<TestRecord> {
    Integer testId;
    private Set<ClassRecord> classRecords = new TreeSet<ClassRecord>();
    private Map<Integer, ClassRecord> classRecordMap = new HashMap<Integer, ClassRecord>();

    public TestRecord(Integer testId) {
        this.testId = testId;
    }

    public ClassRecord addClassRecord(Integer classId){
        ClassRecord candidate = new ClassRecord(classId);
        if(classRecords.add(candidate)){
            classRecordMap.put(classId, candidate);
            return candidate;
        } else {
            return classRecordMap.get(classId);
        }
    }

    public Integer getTestName() {
        return testId;
    }

    public Set<ClassRecord> getClassRecords() {
        return classRecords;
    }

    public void setTestName(Integer testId) {
        this.testId = testId;
    }

    public int compareTo(TestRecord that) {
        return testId.compareTo(that.getTestName());
    }
}
