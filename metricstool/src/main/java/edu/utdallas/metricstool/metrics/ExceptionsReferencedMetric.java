package edu.utdallas.metricstool.metrics;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;

import org.objectweb.asm.MethodVisitor;

import edu.utdallas.metricstool.MTUtils;
import edu.utdallas.metricstool.MetricCollector;

public class ExceptionsReferencedMetric extends MetricCollector {
	
	HashSet<String> referenced;
	ClassReferencesMetric crm;

	public ExceptionsReferencedMetric(MethodVisitor mv, String cName, int access, String mName, String desc, String signature,
			String[] exceptions, ClassReferencesMetric crm) {
		super(mv, cName, access, mName, desc, signature, exceptions);
		referenced = new HashSet<String>();
		this.crm = crm;
		if (exceptions != null) {
			for (String s : exceptions)
				referenced.add(MTUtils.descFormatToQualified(s));
		}
	}
	
	public String getExceptions() {
		String excns = "";
		Iterator<String> i = referenced.iterator();
		boolean first = true;
		while (i.hasNext()) {
			if (!first)
				excns += ", ";
			else
				first = false;
			
			excns += i.next();
		}
		return excns;
	}
	
	@Override
	public void visitEnd() {
		if (crm != null && crm.classes != null && !crm.classes.isEmpty()) {
			for (String s : crm.classes) {
				if (s.endsWith("Exception"))
					referenced.add(s);
			}
		}
		System.out.println("Exceptions Referenced: " + getExceptions());
	}

}
