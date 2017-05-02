package edu.utdallas.metricstool.metrics;

import edu.utdallas.metricstool.MTUtils;
import edu.utdallas.metricstool.MetricCollector;
import edu.utdallas.metricstool.annotations.InjectColumn;
import edu.utdallas.metricstool.annotations.Metric;
import edu.utdallas.metricstool.enums.ArtifactType;
import edu.utdallas.metricstool.tables.Column;

import java.io.IOException;

import org.objectweb.asm.Label;
import org.objectweb.asm.MethodVisitor;

@Metric(key = "vardecs", name = "Variable Declarations", artifactType = ArtifactType.METHOD, metricType = Integer.class)
public class VarDecMetric extends MetricCollector {
	
	int count;
	@InjectColumn(key = "vardecs", type = ArtifactType.METHOD)
	static Column column;

	public VarDecMetric(MethodVisitor mv, String cName, int access, String mName, String desc, String signature,
			String[] exceptions) {
		super(mv, cName, access, mName, desc, signature, exceptions);
		this.count = 0;
		if ((access & (ACC_STATIC | ACC_NATIVE | ACC_ABSTRACT)) == 0)
			this.count--; // Correct for "declaration" of `this`
	}

	@Override
	public void visitLocalVariable(String name, String desc, String signature, Label start, Label end, int index) {
		count ++;
	}

	@Override
	public void visitEnd() {
		MTUtils.write(count);
	}

}
