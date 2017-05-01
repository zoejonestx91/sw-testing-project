package edu.utdallas.metricstool.annotations;


import edu.utdallas.metricstool.annotations.repeatingcontainers.MetricRepeatingContainer;
import edu.utdallas.metricstool.enums.ArtifactType;

import java.lang.annotation.*;

/**
    This class is used to register a metric for the MetricsTable.
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Repeatable(MetricRepeatingContainer.class)
public @interface Metric {
    String name();
    String key();
    Class<?> metricType() default String.class;
    ArtifactType artifactType();
    String description() default "";
}
