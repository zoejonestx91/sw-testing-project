package edu.utdallas.metricstool.metrics;

import java.util.HashSet;
import java.util.Iterator;

import org.objectweb.asm.Label;
import org.objectweb.asm.MethodVisitor;

import edu.utdallas.metricstool.MTUtils;
import edu.utdallas.metricstool.MetricCollector;

public class ExternalMethodsMetric extends MetricCollector {
	
	HashSet<String> methods;

	public ExternalMethodsMetric(MethodVisitor mv, String cName, int access, String mName, String desc, String signature,
			String[] exceptions) {
		super(mv, cName, access, mName, desc, signature, exceptions);
		methods = new HashSet<String>();
	}
	
	public String getExternalMethods() {
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
		if (!owner.equals(cName))
			methods.add(MTUtils.descFormatToQualified(owner) + ":" + name + desc);
	}
	
	@Override
	public void visitEnd() {
		System.out.println("External Methods: " + getExternalMethods());
	}

}
