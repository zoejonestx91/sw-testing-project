package edu.utdallas.metricstool.metrics;

import edu.utdallas.metricstool.MTUtils;
import edu.utdallas.metricstool.MetricCollector;
import edu.utdallas.metricstool.annotations.InjectColumn;
import edu.utdallas.metricstool.annotations.Metric;
import edu.utdallas.metricstool.enums.ArtifactType;
import edu.utdallas.metricstool.tables.Column;
import edu.utdallas.metricstool.tables.Table;
import org.objectweb.asm.MethodVisitor;

import java.util.HashSet;
import java.util.Iterator;
@Metric(key = "exceptrefs", name = "Exceptions Referenced", artifactType = ArtifactType.METHOD)
public class ExceptionsReferencedMetric extends MetricCollector {
	
	HashSet<String> referenced;
	ClassReferencesMetric crm;
	@InjectColumn(key = "exceptrefs", type = ArtifactType.METHOD)
	static Column column;

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

	public void initColumns(Table table){
		column = new Column("exceptionrefs", "Exceptions Referenced", String.class);
		table.addColumn(column);
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
		MTUtils.write(referenced.size());
	}

}
