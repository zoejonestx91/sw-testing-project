package edu.utdallas.metricstool.metrics;

import org.objectweb.asm.MethodVisitor;

import edu.utdallas.metricstool.MTUtils;
import edu.utdallas.metricstool.MetricCollector;

public class HalsteadBugsMetric extends MetricCollector {
	
	HalsteadVolumeMetric hvol;
	
	public HalsteadBugsMetric(MethodVisitor mv, String cName, int access, String mName, String desc, String signature,
			String[] exceptions, HalsteadVolumeMetric hvol) {
		super(mv, cName, access, mName, desc, signature, exceptions);
		this.hvol = hvol;
	}
	
	public double getHalsteadBugs() {
		return hvol.getHalsteadVolume() / 3000;
	}
	
	@Override
	public void visitEnd() {
		//System.out.println(column);//TODO
		//getCurrentMethodRow().addEntry(column, mName);
		MTUtils.write(getHalsteadBugs());
	}

}
