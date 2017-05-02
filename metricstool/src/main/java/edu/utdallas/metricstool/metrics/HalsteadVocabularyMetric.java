package edu.utdallas.metricstool.metrics;

import org.objectweb.asm.MethodVisitor;

import edu.utdallas.metricstool.MTUtils;
import edu.utdallas.metricstool.MetricCollector;

public class HalsteadVocabularyMetric extends MetricCollector {
	
	OperationsMetric om;
	
	public HalsteadVocabularyMetric(MethodVisitor mv, String cName, int access, String mName, String desc, String signature,
			String[] exceptions, OperationsMetric om) {
		super(mv, cName, access, mName, desc, signature, exceptions);
		this.om = om;
	}
	
	public double getHalsteadVocabulary() {
		return om.numUniqueOperators() + om.numUniqueOperands();
	}
	
	@Override
	public void visitEnd() {
		//System.out.println(column);//TODO
		//getCurrentMethodRow().addEntry(column, mName);
		MTUtils.write(getHalsteadVocabulary());
	}

}
