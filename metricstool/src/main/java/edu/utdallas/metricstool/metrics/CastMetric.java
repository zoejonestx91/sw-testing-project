package edu.utdallas.metricstool.metrics;

import org.objectweb.asm.MethodVisitor;

import edu.utdallas.metricstool.MetricCollector;

// TODO: Handle implicit casts.
public class CastMetric extends MetricCollector {
	
	int count;

	public CastMetric(MethodVisitor mv, String cName, int access, String mName, String desc, String signature,
			String[] exceptions) {
		super(mv, cName, access, mName, desc, signature, exceptions);
		count = 0;
	}
	
	@Override
	public void visitTypeInsn(int opcode, String type) {
		// Catches explicit downcasts.
		if (opcode == CHECKCAST)
			count++;
	}

	@Override
	public void visitEnd() {
		System.out.println("Number of Casts: " + count);
	}

}
