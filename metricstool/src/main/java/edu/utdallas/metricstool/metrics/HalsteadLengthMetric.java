package edu.utdallas.metricstool.metrics;

import org.objectweb.asm.MethodVisitor;

import edu.utdallas.metricstool.MetricCollector;

public class HalsteadLengthMetric extends MetricCollector {
	
	OperationsMetric om;
	
	public HalsteadLengthMetric(MethodVisitor mv, String cName, int access, String mName, String desc, String signature,
			String[] exceptions, OperationsMetric om) {
		super(mv, cName, access, mName, desc, signature, exceptions);
		this.om = om;
	}
	
	public double getHalsteadLength() {
		return om.numOperators() + om.numOperands();
	}
	
	@Override
	public void visitEnd() {
		//System.out.println(column);//TODO
		//getCurrentMethodRow().addEntry(column, mName);
		System.out.print(getHalsteadLength());
	}

}
