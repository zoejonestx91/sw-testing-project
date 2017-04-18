package edu.utdallas.metricstool.annotations.processors;

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
}
