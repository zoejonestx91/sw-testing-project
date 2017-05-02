package edu.utdallas.metricstool.metrics;

import org.objectweb.asm.MethodVisitor;

import edu.utdallas.metricstool.MTUtils;
import edu.utdallas.metricstool.MetricCollector;

public class HalsteadVolumeMetric extends MetricCollector {

	HalsteadLengthMetric hlth;
	HalsteadVocabularyMetric hvoc;
	
	public HalsteadVolumeMetric(MethodVisitor mv, String cName, int access, String mName, String desc, String signature,
			String[] exceptions, HalsteadLengthMetric hlth, HalsteadVocabularyMetric hvoc) {
		super(mv, cName, access, mName, desc, signature, exceptions);
		this.hlth = hlth;
		this.hvoc = hvoc;
	}
	
	public double getHalsteadVolume() {
		if (hlth.getHalsteadLength() == 0)
			return 0;
		else
			return hlth.getHalsteadLength() * (Math.log(hvoc.getHalsteadVocabulary()) / Math.log(2));
	}
	
	@Override
	public void visitEnd() {
		//System.out.println(column);//TODO
		//getCurrentMethodRow().addEntry(column, mName);
		MTUtils.write(getHalsteadVolume());
	}

}
