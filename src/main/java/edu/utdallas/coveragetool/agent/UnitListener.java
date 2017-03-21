package edu.utdallas.coveragetool.agent;

import java.io.IOException;
import java.lang.instrument.ClassDefinition;
import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.Instrumentation;
import java.lang.instrument.UnmodifiableClassException;
import java.util.HashSet;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;
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
	
	public void testRunStarted(Description description) {
		for (Description child : description.getChildren()) {
			instrumentTestClass(child.toString());
		}
	}
	
	// When testing is complete, write the coverage information
	public void testRunFinished(Result result) {
		cov.writeCoverage();
	}
	
	// Inserts instrumentation into the specified TEST class
	private static void instrumentTestClass(String className) {
		System.err.println(className);
		ClassFileTransformer transformer = new UnitTransformer(className);
		inst.addTransformer(transformer, true);
		try {
			inst.retransformClasses(Class.forName(className));
			inst.removeTransformer(transformer);
		} catch (ClassNotFoundException e) {
		} catch (UnmodifiableClassException e) {
			System.err.println("Class to be retransformed was not modifiable: " + e.getMessage());
			System.exit(1);
		}
	}
}
