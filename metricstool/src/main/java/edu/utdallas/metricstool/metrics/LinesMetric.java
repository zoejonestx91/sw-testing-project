package edu.utdallas.metricstool.metrics;

import java.util.HashSet;

import org.objectweb.asm.Label;
import org.objectweb.asm.MethodVisitor;

import edu.utdallas.metricstool.MetricCollector;

public class LinesMetric extends MetricCollector {
	
	HashSet<Integer> lines;

	public LinesMetric(MethodVisitor mv, String cName, int access, String mName, String desc, String signature,
			String[] exceptions) {
		super(mv, cName, access, mName, desc, signature, exceptions);
		lines = new HashSet<Integer>();
	}
	
	@Override
	public void visitLineNumber(int line, Label start) {
		lines.add(line);
	}
	
	@Override
	public void visitEnd() {
		System.out.println("Lines of Code: " + lines.size());
	}

}
