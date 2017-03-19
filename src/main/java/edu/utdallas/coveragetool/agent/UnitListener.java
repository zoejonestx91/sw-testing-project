package edu.utdallas.coveragetool.agent;

import java.lang.instrument.Instrumentation;

import org.junit.runner.Description;
import org.junit.runner.notification.RunListener;

public class UnitListener extends RunListener {
	public static Instrumentation inst = null;
	
	public void testStarted(Description description) throws Exception {
        System.out.println("Starting: " + description.getMethodName());
    }
}
