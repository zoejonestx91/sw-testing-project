package edu.utdallas.metricstool.metrics;

import edu.utdallas.metricstool.MTUtils;
import edu.utdallas.metricstool.MetricCollector;
import org.objectweb.asm.MethodVisitor;

public class ArgcMetric extends MetricCollector {
	
	public ArgcMetric(MethodVisitor mv, String cName, int access, String mName, String desc, String signature,
			String[] exceptions) {
		super(mv, cName, access, mName, desc, signature, exceptions);
	}
	
	@Override
	public void visitEnd() {
		System.out.println("Number of Arguments: " +  MTUtils.parseDescArgs(desc).size());
	}

}
