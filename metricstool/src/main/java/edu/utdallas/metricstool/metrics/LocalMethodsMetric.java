package edu.utdallas.metricstool.metrics;

import edu.utdallas.metricstool.MetricCollector;
import edu.utdallas.metricstool.annotations.InjectColumn;
import edu.utdallas.metricstool.annotations.Metric;
import edu.utdallas.metricstool.enums.ArtifactType;
import edu.utdallas.metricstool.tables.Column;
import org.objectweb.asm.MethodVisitor;

import java.util.HashSet;
import java.util.Iterator;
@Metric(key = "localmeths", name = "Local Methods", artifactType = ArtifactType.METHOD)
public class LocalMethodsMetric extends MetricCollector {
	
	HashSet<String> methods;
	@InjectColumn(key = "localmeths", type = ArtifactType.METHOD)
	static Column column;

	public LocalMethodsMetric(MethodVisitor mv, String cName, int access, String mName, String desc, String signature,
			String[] exceptions) {
		super(mv, cName, access, mName, desc, signature, exceptions);
		methods = new HashSet<String>();
	}
	
	public String getLocalMethods() {
		String meths = "";
		Iterator<String> i = methods.iterator();
		boolean first = true;
		while (i.hasNext()) {
			if (!first)
				meths += ", ";
			else
				first = false;
			
			meths += i.next();
		}
		return meths;
	}

	@Override
	public void visitMethodInsn(int opcode, String owner, String name, String desc, boolean itf) {
		if (owner.equals(cName))
			methods.add(name + desc);
	}
	
	@Override
	public void visitEnd() {
		System.out.print( getLocalMethods());
	}

}
