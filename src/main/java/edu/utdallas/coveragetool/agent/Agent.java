package edu.utdallas.coveragetool.agent;

import java.lang.instrument.Instrumentation;
import java.util.ArrayList;
import java.util.List;

// Options and arguments should be comma delimited
//
// --writebytecode |
// -b              | write modified bytecode to files named after the class
//
// Other arguments will be interpreted as class names or packages (for which
// class names containing the package name will be included) that should be
// instrumented.
public class Agent {
	private static List<String> paths;
	private static UCTransformer transform;
	
    public static void premain(String agentArgs, Instrumentation inst) {
    	paths = new ArrayList<String>();
    	transform = new UCTransformer();
    	if (agentArgs != null)
	    	for (String s : agentArgs.split(","))
	    		argHandler(s);
    	UnitListener.init();
    	transform.classesToInstrument = paths.toArray(new String[paths.size()]);
        inst.addTransformer(transform);
    }
    
    public static void argHandler(String arg) {
    	switch (arg) {
    		case "--writebytecode":
    		case "-b":
    			transform.writeClasses = true;
    			break;
    		default:
    			if (arg.startsWith("-t")) {
    				UnitListener.maxTests = Integer.parseInt(arg.substring(2)) + 1;
    			} else if (arg.startsWith("-c")) {
    				UnitListener.maxClasses = Integer.parseInt(arg.substring(2)) + 1;
    			} else if (arg.startsWith("-n")) {
    				UnitListener.maxLines = Integer.parseInt(arg.substring(2)) + 1;
    			} else {
    				paths.add(arg.replace('.', '/'));
    			}
		}
    }
}