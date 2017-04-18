package edu.utdallas.metricstool.annotations.repeatingcontainers;


import java.lang.annotation.Documented;

import edu.utdallas.metricstool.annotations.Metric;

@Documented
public @interface MetricRepeatingContainer {
    Metric[] value();
}
