package edu.utdallas.metricstool.annotations;

import edu.utdallas.metricstool.annotations.repeatingcontainers.DependsOnRepeatingContainer;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Repeatable(DependsOnRepeatingContainer.class)
@Target(ElementType.TYPE)
public @interface DependsOn {
    String value();//TODO Replace with concrete type?
}
