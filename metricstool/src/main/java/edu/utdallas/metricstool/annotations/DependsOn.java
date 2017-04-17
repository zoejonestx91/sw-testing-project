package edu.utdallas.metricstool.annotations;

import edu.utdallas.metricstool.annotations.repeatingcontainers.DependsOnRepeatingContainer;

import java.lang.annotation.Repeatable;

@Repeatable(DependsOnRepeatingContainer.class)
public @interface DependsOn {
    String value();//TODO Replace with concrete type?
}
