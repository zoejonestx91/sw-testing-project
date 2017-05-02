package edu.utdallas.metricstool.metrics;

import edu.utdallas.metricstool.MTUtils;
import edu.utdallas.metricstool.MetricCollector;
import edu.utdallas.metricstool.annotations.InjectColumn;
import edu.utdallas.metricstool.annotations.Metric;
import edu.utdallas.metricstool.enums.ArtifactType;
import edu.utdallas.metricstool.tables.Column;

import java.io.IOException;

import org.objectweb.asm.MethodVisitor;
@Metric(key = "name", name = "Name", artifactType = ArtifactType.METHOD)
public class NameMetric extends MetricCollector {
	@InjectColumn(key = "name", type = ArtifactType.METHOD)
	public static Column column;
	
	public NameMetric(MethodVisitor mv, String cName, int access, String mName, String desc, String signature,
			String[] exceptions) {
		super(mv, cName, access, mName, desc, signature, exceptions);
	}
	
	@Override

	public void visitEnd() {
		//System.out.println(column);//TODO
		//getCurrentMethodRow().addEntry(column, mName);
		if (mName.equals("<init>")) {
			int start = cName.lastIndexOf('/');
			MTUtils.write(cName.substring(start + 1));
		} else {
			MTUtils.write(mName);
		}
	}

}
