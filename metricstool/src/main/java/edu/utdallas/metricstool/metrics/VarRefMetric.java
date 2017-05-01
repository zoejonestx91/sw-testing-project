package edu.utdallas.metricstool.metrics;

import edu.utdallas.metricstool.MetricCollector;
import edu.utdallas.metricstool.annotations.InjectColumn;
import edu.utdallas.metricstool.annotations.Metric;
import edu.utdallas.metricstool.enums.ArtifactType;
import edu.utdallas.metricstool.tables.Column;
import edu.utdallas.metricstool.tables.Table;
import org.objectweb.asm.MethodVisitor;

import java.util.HashSet;

@Metric(key = "varref", name = "Variable References", artifactType = ArtifactType.METHOD, metricType = Integer.class)
public class VarRefMetric extends MetricCollector {
	
	HashSet<Integer> vars;
	@InjectColumn(key = "varref", type = ArtifactType.METHOD)
	static Column column;

	public void initColumns(Table table){
		column = new Column("varrefs", "Var References", Integer.class);
		table.addColumn(column);
	}

	public VarRefMetric(MethodVisitor mv, String cName, int access, String mName, String desc, String signature,
			String[] exceptions) {
		super(mv, cName, access, mName, desc, signature, exceptions);
		this.vars = new HashSet<Integer>();
	}

	@Override
	public void visitVarInsn(int opcode, int var) {
		vars.add(var);
	}

	@Override
	public void visitEnd() {
		System.out.print(vars.size());
	}

}
