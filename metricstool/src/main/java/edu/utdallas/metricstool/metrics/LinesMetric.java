package edu.utdallas.metricstool.metrics;

import edu.utdallas.metricstool.MetricCollector;
import edu.utdallas.metricstool.annotations.InjectColumn;
import edu.utdallas.metricstool.annotations.Metric;
import edu.utdallas.metricstool.enums.ArtifactType;
import edu.utdallas.metricstool.tables.Column;
import org.objectweb.asm.Label;
import org.objectweb.asm.MethodVisitor;

import java.util.HashSet;

@Metric(key = "numlines", name = "Number of Lines", artifactType = ArtifactType.METHOD)
public class LinesMetric extends MetricCollector {
	
	HashSet<Integer> lines;
	@InjectColumn(key = "numlines", type = ArtifactType.METHOD)
	static Column column;

	public LinesMetric(MethodVisitor mv, String cName, int access, String mName, String desc, String signature,
			String[] exceptions) {
		super(mv, cName, access, mName, desc, signature, exceptions);
		lines = new HashSet<Integer>();
	}

	@Override
	public void visitLineNumber(int line, Label start) {
		lines.add(line);
	}
	
	@Override
	public void visitEnd() {
		System.out.print(lines.size());
	}

}
