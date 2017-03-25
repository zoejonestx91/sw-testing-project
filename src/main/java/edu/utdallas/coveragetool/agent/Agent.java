package edu.utdallas.coveragetool.agent;

import java.lang.instrument.Instrumentation;

public class Agent {
    public static void premain(String agentArgs, Instrumentation inst) {
    	if (agentArgs != null)
	    	for (String s : agentArgs.split(","))
	    		option(s);
    	UCTransformer transform = new UCTransformer();
        inst.addTransformer(transform);
    }
    
    public static void option(String option) {
    	switch (option) {
    		case "writeclasses":
    		case "wc": UCTransformer.writeClasses = true; break;
    	}
    }
}