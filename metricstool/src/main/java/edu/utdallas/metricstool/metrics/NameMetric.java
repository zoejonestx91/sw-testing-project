package edu.utdallas.metricstool.metrics;

import edu.utdallas.metricstool.MetricCollector;
import org.objectweb.asm.MethodVisitor;

public class NameMetric extends MetricCollector {
	
	public NameMetric(MethodVisitor mv, String cName, int access, String mName, String desc, String signature,
			String[] exceptions) {
		super(mv, cName, access, mName, desc, signature, exceptions);
	}
	
	@Override
	public void visitEnd() {
		System.out.println("Name: " +  mName);
	}

}
