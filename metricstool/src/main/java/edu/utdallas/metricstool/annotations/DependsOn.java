package edu.utdallas.metricstool.annotations;

import edu.utdallas.metricstool.annotations.repeatingcontainers.DependsOnRepeatingContainer;
import edu.utdallas.metricstool.plugins.VisitorPlugin;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Repeatable(DependsOnRepeatingContainer.class)
@Target(ElementType.TYPE)
public @interface DependsOn {
    Class<VisitorPlugin> value();//TODO Replace with concrete type?
}
