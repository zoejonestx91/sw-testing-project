package edu.utdallas.metricstool.annotations.repeatingcontainers;


import edu.utdallas.metricstool.annotations.WaitFor;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface WaitForRepeatingContainer {
    WaitFor[] value();
}
