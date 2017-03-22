package edu.utdallas.coveragetool.agent;

import java.lang.instrument.Instrumentation;

public class Agent {
    public static void premain(String agentArgs, Instrumentation inst) {
        UCTransformer transform = new UCTransformer();
        inst.addTransformer(transform);
    }
}