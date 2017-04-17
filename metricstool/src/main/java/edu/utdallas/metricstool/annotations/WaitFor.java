package edu.utdallas.metricstool.annotations;

import edu.utdallas.metricstool.annotations.repeatingcontainers.WaitForRepeatingContainer;
import edu.utdallas.metricstool.enums.ExecutionPhase;

import java.lang.annotation.Repeatable;

/**
 * WaitFor implies the semantics of a DependsOn, plus that the tagged plugin may not execute before the named plugin.
 */
@Repeatable(WaitForRepeatingContainer.class)
public @interface WaitFor {
    String value();
    ExecutionPhase phase() default ExecutionPhase.ALL;
}
