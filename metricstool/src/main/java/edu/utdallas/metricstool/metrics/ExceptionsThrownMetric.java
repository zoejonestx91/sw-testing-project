package edu.utdallas.metricstool.metrics;

import java.util.Arrays;
import java.util.Iterator;

import org.objectweb.asm.MethodVisitor;

import edu.utdallas.metricstool.MTUtils;
import edu.utdallas.metricstool.MetricCollector;

public class ExceptionsThrownMetric extends MetricCollector {

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
		System.out.println("Exceptions Thrown: " + getExceptions());
	}

}
