package edu.utdallas.metricstool.metrics;

import org.objectweb.asm.Label;
import org.objectweb.asm.MethodVisitor;

import edu.utdallas.metricstool.MetricCollector;

public class LinesMetric extends MetricCollector {
	
	int minLine;
	int maxLine;

	public LinesMetric(MethodVisitor mv, String cName, int access, String mName, String desc, String signature,
			String[] exceptions) {
		super(mv, cName, access, mName, desc, signature, exceptions);
		this.maxLine = Integer.MIN_VALUE;
		this.minLine = Integer.MAX_VALUE;
	}
	
	@Override
	public void visitLineNumber(int line, Label start) {
		if (line > maxLine)
			maxLine = line;
		if (line < minLine)
			minLine = line;
	}
	
	@Override
	public void visitEnd() {
		// TODO: investigate <init> methods that don't return a number of lines
		int lines = maxLine - minLine + 1;
		System.out.println("Lines of Code: " + lines);
	}

}
