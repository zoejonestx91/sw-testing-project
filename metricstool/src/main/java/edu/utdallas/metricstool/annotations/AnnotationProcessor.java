package edu.utdallas.metricstool.annotations;

/**
 * Created by z on 4/16/17.
 */
public class AnnotationProcessor {
    private static AnnotationProcessor ourInstance = new AnnotationProcessor();

    public static AnnotationProcessor getInstance() {
        return ourInstance;
    }

    private AnnotationProcessor() {
    }
}
