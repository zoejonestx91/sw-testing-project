package edu.utdallas.metricstool.annotations;

import edu.utdallas.metricstool.enums.ArtifactType;

/**
 * Created by z on 4/16/17.
 */
public @interface VisitArtifacts {
    ArtifactType[] value();
}
