package edu.utdallas.metricstool.annotations.processors;

import edu.utdallas.metricstool.enums.ArtifactType;
import edu.utdallas.metricstool.enums.ExecutionPhase;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class AnnotationProcessor {
    private static AnnotationProcessor ourInstance = new AnnotationProcessor();

    public static AnnotationProcessor getInstance() {
        return ourInstance;
    }

    private AnnotationProcessor() {
    }

    public String extractName(Class clazz){
        return null;//TODO IMPLEMENT
    }

    public List<ArtifactType> extractVisitArtifacts(Class clazz){
        List<ArtifactType> list = new ArrayList<>();//TODO IMPLEMENT
        return list;
    }

    public List<ExecutionPhase> extractExecInPhases(Class clazz){
        return null;//TODO IMPLEMENT
    }
}
