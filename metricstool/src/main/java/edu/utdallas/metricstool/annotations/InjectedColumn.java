package edu.utdallas.metricstool.annotations;

import edu.utdallas.metricstool.enums.ArtifactType;

/**
 *
 */
public @interface InjectedColumn {
    String key();
    ArtifactType type();
}
