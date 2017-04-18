package edu.utdallas.metricstool.metrics;

import org.objectweb.asm.MethodVisitor;

import edu.utdallas.metricstool.MetricCollector;

public class ArgcMetric extends MetricCollector {
	
	public ArgcMetric(MethodVisitor mv, String cName, int access, String mName, String desc, String signature,
			String[] exceptions) {
		super(mv, cName, access, mName, desc, signature, exceptions);
	}
	
	@Override
	public void visitEnd() {
		int argc = 0;
		if (desc != null) {
			String args = desc.substring(desc.indexOf('(') + 1, desc.indexOf(')'));
			boolean inObject = false;
			for (int i = 0; i < args.length(); i++) {
				char c = args.charAt(i);
				if (inObject) {
					if (c == ';')
						inObject = false;
				} else {
					argc++;
					if (c == 'L')
						inObject = true;
				}
			}
		}
		System.out.println("Number of Arguments: " +  argc);
	}

}
