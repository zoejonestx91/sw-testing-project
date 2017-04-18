package edu.utdallas.metricstool.metrics;

import org.objectweb.asm.Label;
import org.objectweb.asm.MethodVisitor;

import edu.utdallas.metricstool.MetricCollector;

public class VarDecMetric extends MetricCollector {
	
	int count;

	public VarDecMetric(MethodVisitor mv, String cName, int access, String mName, String desc, String signature,
			String[] exceptions) {
		super(mv, cName, access, mName, desc, signature, exceptions);
		this.count = 0;
		if ((access & ACC_STATIC) == 0)
			this.count--; // Correct for "declaration" of `this`
	}
	
	@Override
	public void visitLocalVariable(String name, String desc, String signature, Label start, Label end, int index) {
		count ++;
	}

	@Override
	public void visitEnd() {
		System.out.println("Variable Declarations: " + count);
	}

}
