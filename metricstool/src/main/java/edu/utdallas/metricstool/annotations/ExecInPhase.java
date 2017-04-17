package edu.utdallas.metricstool.annotations;


import edu.utdallas.metricstool.enums.ExecutionPhase;

public @interface ExecInPhase {
    ExecutionPhase[] value();
}
