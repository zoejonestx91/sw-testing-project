package edu.utdallas.metricstool.annotations;

import edu.utdallas.metricstool.enums.ArtifactType;

/**
 *
 */
public @interface InjectColumn {
    String key();
    ArtifactType type();
}
