package edu.utdallas.metricstool.annotations.processors;

/**
 * Responsible for reading metrics declarations from a metric.
 */
public class MetricsProcessor {
    private static MetricsProcessor ourInstance = new MetricsProcessor();

    public static MetricsProcessor getInstance(){
        return ourInstance;
    }
}
