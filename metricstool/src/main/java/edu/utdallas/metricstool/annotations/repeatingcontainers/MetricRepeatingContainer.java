package edu.utdallas.metricstool.annotations.repeatingcontainers;


import edu.utdallas.metricstool.annotations.Metric;

import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface MetricRepeatingContainer {
    Metric[] value();
}
