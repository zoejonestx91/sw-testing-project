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
    String testName;
    private Set<ClassRecord> classRecords = new TreeSet<ClassRecord>();
    private Map<String, ClassRecord> classRecordMap = new HashMap<String, ClassRecord>();

    public TestRecord(String testName) {
        this.testName = testName;
    }

    public ClassRecord addClassRecord(String className){
        ClassRecord candidate = new ClassRecord(className);
        if(classRecords.add(candidate)){
            classRecordMap.put(className, candidate);
            return candidate;
        } else {
            return classRecordMap.get(className);
        }
    }

    public String getTestName() {
        return testName;
    }

    public Set<ClassRecord> getClassRecords() {
        return classRecords;
    }

    public void setTestName(String testName) {
        this.testName = testName;
    }

    public int compareTo(TestRecord that) {
        return testName.compareTo(that.getTestName());
    }
}
