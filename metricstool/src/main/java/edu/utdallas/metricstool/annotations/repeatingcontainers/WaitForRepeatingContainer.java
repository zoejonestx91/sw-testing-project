package edu.utdallas.metricstool.annotations.repeatingcontainers;


import edu.utdallas.metricstool.annotations.WaitFor;

public @interface WaitForRepeatingContainer {
    WaitFor[] value();
}
