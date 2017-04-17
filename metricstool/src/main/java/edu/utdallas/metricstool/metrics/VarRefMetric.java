package edu.utdallas.metricstool.metrics;

import java.util.HashSet;

import org.objectweb.asm.Label;
import org.objectweb.asm.MethodVisitor;

import edu.utdallas.metricstool.MetricCollector;

public class VarRefMetric extends MetricCollector {
	
	HashSet<Integer> vars;

	public VarRefMetric(MethodVisitor mv, String cName, int access, String mName, String desc, String signature,
			String[] exceptions) {
		super(mv, cName, access, mName, desc, signature, exceptions);
		this.vars = new HashSet<Integer>();
	}

	@Override
	public void visitVarInsn(int opcode, int var) {
		vars.add(var);
	}

	@Override
	public void visitEnd() {
		System.out.println("Variable References: " + vars.size());
	}

}
