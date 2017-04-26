package edu.utdallas.metricstool.annotations.processors;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by z on 4/17/17.
 */
public class WaitForProcessor {
    private static WaitForProcessor ourInstance = new WaitForProcessor();

    public static WaitForProcessor getInstance() {
        return ourInstance;
    }

    private WaitForProcessor() {
    }

    public List<String> extractFrom(Class clazz){
        List<String> list = new ArrayList<>();
        //TODO: IMPLEMENT
        return list;
    }
}
