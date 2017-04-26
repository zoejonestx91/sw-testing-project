package edu.utdallas.metricstool.annotations.processors;

import edu.utdallas.metricstool.annotations.DependsOn;
import edu.utdallas.metricstool.annotations.WaitFor;
import edu.utdallas.metricstool.enums.ExecutionPhase;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class DependencyProcessor {
    private static DependencyProcessor ourInstance = new DependencyProcessor();
    private WaitForProcessor waitForProcessor;

    public static DependencyProcessor getInstance() {
        return ourInstance;
    }

    private DependencyProcessor() {
        waitForProcessor = WaitForProcessor.getInstance();
    }

    /**
     * For a given class, return a list of all dependency references. This looks for {@link DependsOn} and
     * {@link WaitFor} annotations.
     */
    public List<String> extractFrom(Class clazz, ExecutionPhase phase){
        List<String> list = new ArrayList<>();
        //TODO implement
        return list;
    }
    public List<String> extractFrom(Class clazz){
        List<String> list = new ArrayList<>();
        //TODO implement
        return list;
    }
}
