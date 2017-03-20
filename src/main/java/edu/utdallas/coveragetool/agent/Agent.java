package edu.utdallas.coveragetool.agent;

import java.lang.instrument.Instrumentation;

public class Agent {
    public static void premain(String agentArgs, Instrumentation inst) {
        UnitListener.init(inst); // Initialize the test listener by giving it the instrumentation object
    }
}