package edu.utdallas.metricstool.metrics;

import org.objectweb.asm.MethodVisitor;

import edu.utdallas.metricstool.MetricCollector;

public class HalsteadEffortMetric extends MetricCollector {
	
	HalsteadDifficultyMetric hdif;
	HalsteadVolumeMetric hvol;
	
	public HalsteadEffortMetric(MethodVisitor mv, String cName, int access, String mName, String desc, String signature,
			String[] exceptions, HalsteadDifficultyMetric hdif, HalsteadVolumeMetric hvol) {
		super(mv, cName, access, mName, desc, signature, exceptions);
		this.hdif = hdif;
		this.hvol = hvol;
	}
	
	public double getHalsteadEffort() {
		return hdif.getHalsteadDifficulty() * hvol.getHalsteadVolume();
	}
	
	@Override
	public void visitEnd() {
		//System.out.println(column);//TODO
		//getCurrentMethodRow().addEntry(column, mName);
		System.out.print(getHalsteadEffort());
	}

}
