package edu.utdallas.coveragetool.agent;

import java.io.IOException;
import java.lang.instrument.Instrumentation;
import java.util.HashSet;

import org.objectweb.asm.ClassReader;

import org.junit.runner.Description;
import org.junit.runner.Result;
import org.junit.runner.notification.RunListener;

public class UnitListener extends RunListener {
	private static Instrumentation inst = null;
	private static HashSet<String> instTestClasses = null; // The TEST classes that have been instrumented
	private static Coverage cov = null;					   // The coverage collection implementation
	// TODO: a hashset indicating which user code methods have been instrumented
	
	public static void init(Instrumentation inst) {
		UnitListener.inst = inst;					// Instrumentation from the agent
		instTestClasses = new HashSet<String>();
		cov = new Coverage();
	}
	
	protected static Instrumentation getInst() {
		return inst;
	}
	
	// Checks test class and instruments it if necessary
	public void testStarted(Description description) {
		String testClass = description.getClassName();
//        if (!instTestClasses.contains(testClass)) {
        	instrumentTestClass(testClass);
//        }
    }
	
	// When testing is complete, write the coverage information
	public void testRunFinished(Result result) {
		cov.writeCoverage();
	}
	
	// Inserts instrumentation into the specified TEST class
	private static void instrumentTestClass(String className) {
		// TODO
		// Will require instrumenting tests to configure the current testing "context" (i.e. the current test class and test name,
		// per the output specification) so that instrumented user code will write coverage information specific to that test case.
		// This should be easily done by having the user code call a "coverage output" function in this class (UnitListener), which
		// can unite the current context as set by the test case with the coverage information being collected by instrumentation
		// in the user code.
		//
		// When traversing the class, only instrument methods with annotation @Test and without annotation @Ignore.
		
		
		ClassReader reader = null;
		
		try {
			reader = new ClassReader(className);
		} catch (IOException e) {
			System.err.println("Problem reading test class for instrumenting: " + e.getMessage());
			System.exit(1);
		}
		
		reader.accept(new UnitClassVisitor(), 0);
	}
	
//	public void testRunStarted(Description description) throws Exception {
//        System.out.println("Starting a test run: ");
//        ArrayList<Description> children = null;
//        if (description != null)
//        	children = description.getChildren();
//        if (children != null)
//        	printChildren(children);
//    }
//	
//	private void printChildren(ArrayList<Description> children) {
//		if (children == null)
//			return;
//		for (Description child : children) {
//			System.out.println(child.getMethodName());
//		}
//	}
}
