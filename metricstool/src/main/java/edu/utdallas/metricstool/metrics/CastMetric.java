package edu.utdallas.metricstool.metrics;

import edu.utdallas.metricstool.MTUtils;
import edu.utdallas.metricstool.MetricCollector;
import edu.utdallas.metricstool.annotations.InjectColumn;
import edu.utdallas.metricstool.annotations.Metric;
import edu.utdallas.metricstool.enums.ArtifactType;
import edu.utdallas.metricstool.tables.Column;
import org.objectweb.asm.MethodVisitor;

// TODO: Handle implicit casts.
@Metric(key="castc", name="Cast Count", metricType = Integer.class, artifactType = ArtifactType.METHOD)
public class CastMetric extends MetricCollector {
	
	int count;
	@InjectColumn(key = "castc", type = ArtifactType.METHOD)
	static Column column;

	public CastMetric(MethodVisitor mv, String cName, int access, String mName, String desc, String signature,
			String[] exceptions) {
		super(mv, cName, access, mName, desc, signature, exceptions);
		count = 0;
	}
	
	@Override
	public void visitTypeInsn(int opcode, String type) {
		// Catches explicit downcasts.
		if (opcode == CHECKCAST)
			count++;
	}

	@Override
	public void visitEnd() {
		MTUtils.write(count );
	}

}
