package edu.utdallas.metricstool.metrics;

import org.objectweb.asm.Label;
import org.objectweb.asm.MethodVisitor;

import edu.utdallas.metricstool.MetricCollector;

public class LinesMetric extends MetricCollector {
	
	int lines;

	public LinesMetric(MethodVisitor mv, String cName, int access, String mName, String desc, String signature,
			String[] exceptions) {
		super(mv, cName, access, mName, desc, signature, exceptions);
		this.lines = 0;
	}
	
	@Override
	public void visitLineNumber(int line, Label start) {
		if (line > lines)
			lines = line;
		super.visitLineNumber(line, start);
	}
	
	@Override
	public void visitEnd() {
		System.out.println(cName + ":" + mName + " - " + lines);
		super.visitEnd();
	}

}
