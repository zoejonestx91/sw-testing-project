package edu.utdallas.metricstool.annotations;

import edu.utdallas.metricstool.annotations.repeatingcontainers.WaitForRepeatingContainer;
import edu.utdallas.metricstool.enums.ExecutionPhase;
import edu.utdallas.metricstool.plugins.VisitorPlugin;

import java.lang.annotation.*;

/**
 * WaitFor implies the semantics of a DependsOn, plus that the tagged plugin may not execute before the named plugin.
 */
@Retention(RetentionPolicy.RUNTIME)
@Repeatable(WaitForRepeatingContainer.class)
@Target(ElementType.TYPE)
public @interface WaitFor {
    Class<VisitorPlugin> value();
    ExecutionPhase phase();
}
