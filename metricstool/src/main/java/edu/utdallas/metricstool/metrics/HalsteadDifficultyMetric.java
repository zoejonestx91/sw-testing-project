package edu.utdallas.metricstool.metrics;

import org.objectweb.asm.MethodVisitor;

import edu.utdallas.metricstool.MetricCollector;

public class HalsteadDifficultyMetric extends MetricCollector {
	
	OperationsMetric om;
	
	public HalsteadDifficultyMetric(MethodVisitor mv, String cName, int access, String mName, String desc, String signature,
			String[] exceptions, OperationsMetric om) {
		super(mv, cName, access, mName, desc, signature, exceptions);
		this.om = om;
	}
	
	public double getHalsteadDifficulty() {
		if (om.numUniqueOperands() == 0)
			return 0;
		else
			return (om.numOperators() / 2) * (om.numOperands() / om.numUniqueOperands());
	}
	
	@Override
	public void visitEnd() {
		//System.out.println(column);//TODO
		//getCurrentMethodRow().addEntry(column, mName);
		System.out.print(getHalsteadDifficulty());
	}

}
