package edu.utdallas.metricstool.metrics;

import edu.utdallas.metricstool.MTUtils;
import edu.utdallas.metricstool.MetricCollector;
import edu.utdallas.metricstool.annotations.InjectColumn;
import edu.utdallas.metricstool.annotations.Metric;
import edu.utdallas.metricstool.enums.ArtifactType;
import edu.utdallas.metricstool.tables.Column;
import org.objectweb.asm.MethodVisitor;

import java.util.Arrays;
import java.util.Iterator;

@Metric(key = "exceptthrown", name = "Exceptions Thrown", artifactType = ArtifactType.METHOD)
public class ExceptionsThrownMetric extends MetricCollector {
	@InjectColumn(key = "exceptthrown", type = ArtifactType.METHOD)
	static Column column;
	public ExceptionsThrownMetric(MethodVisitor mv, String cName, int access, String mName, String desc, String signature,
			String[] exceptions) {
		super(mv, cName, access, mName, desc, signature, exceptions);
	}
	
	public String getExceptions() {
		String excns = "";
		if (exceptions != null) {
			Iterator<String> i = Arrays.asList(exceptions).iterator();
			boolean first = true;
			while (i.hasNext()) {
				if (!first)
					excns += ", ";
				else
					first = false;

				excns += MTUtils.descFormatToQualified(i.next());
			} 
		}
		return excns;
	}
	
	@Override
	public void visitEnd() {
		System.out.print(getExceptions());
	}

}
