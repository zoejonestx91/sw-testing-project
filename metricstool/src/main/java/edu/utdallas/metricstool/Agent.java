package edu.utdallas.metricstool;

import java.lang.instrument.Instrumentation;
import java.util.ArrayList;
import java.util.List;

// Options and arguments should be comma delimited
//
// Other arguments will be interpreted as class names or packages (for which
// class names containing the package name will be included) that should be
// instrumented.
public class Agent {
	private static List<String> paths;
	
    public static void premain(String agentArgs, Instrumentation inst) {
    	paths = new ArrayList<String>();
    	if (agentArgs != null)
	    	for (String s : agentArgs.split(","))
	    		argHandler(s);
        inst.addTransformer(new MainTransformer(paths.toArray(new String[paths.size()])));
    }
    
    public static void argHandler(String arg) {
    	switch (arg) {
    		default:
    			paths.add(arg.replace('.', '/'));
		}
    }
}