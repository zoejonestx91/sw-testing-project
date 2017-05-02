package edu.utdallas.metricstool.metrics;

import edu.utdallas.metricstool.MTUtils;
import edu.utdallas.metricstool.MetricCollector;
import edu.utdallas.metricstool.annotations.InjectColumn;
import edu.utdallas.metricstool.annotations.Metric;
import edu.utdallas.metricstool.enums.ArtifactType;
import edu.utdallas.metricstool.tables.Column;
import org.objectweb.asm.MethodVisitor;

@Metric(key = "argc",
		name = "Argument Count",
		artifactType = ArtifactType.METHOD)
public class ArgcMetric extends MetricCollector {
	@InjectColumn(key = "argc", type = ArtifactType.METHOD)
	static Column column;

	public ArgcMetric(MethodVisitor mv, String cName, int access, String mName, String desc, String signature,
			String[] exceptions) {
		super(mv, cName, access, mName, desc, signature, exceptions);

	}

	@Override
	public void visitEnd() {
		MTUtils.write(MTUtils.parseDescArgs(desc).size());
	}

}
