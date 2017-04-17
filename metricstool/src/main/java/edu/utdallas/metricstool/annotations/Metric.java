package edu.utdallas.metricstool.annotations;


import edu.utdallas.metricstool.annotations.repeatingcontainers.MetricRepeatingContainer;
import edu.utdallas.metricstool.enums.ArtifactType;

import java.lang.annotation.Documented;
import java.lang.annotation.Repeatable;

/*
    This class is used to register a metric for the MetricsTable.
 */
@Documented
@Repeatable(MetricRepeatingContainer.class)
public @interface Metric {
    String name();
    String key();
    Class<?> metricType();
    ArtifactType artifactType();
    String description() default "";
}
