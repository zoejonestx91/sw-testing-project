package edu.utdallas.metricstool.metrics;

import edu.utdallas.metricstool.MetricCollector;
import edu.utdallas.metricstool.annotations.InjectColumn;
import edu.utdallas.metricstool.annotations.Metric;
import edu.utdallas.metricstool.enums.ArtifactType;
import edu.utdallas.metricstool.tables.Column;
import org.objectweb.asm.MethodVisitor;

import java.util.HashSet;

@Metric(key = "numops", name = "Number of Operators", artifactType = ArtifactType.METHOD, metricType = Integer.class)
public class OperatorMetric extends MetricCollector {
	
	int count;
	HashSet<Integer> operators;
	@InjectColumn(key = "numops", type = ArtifactType.METHOD)
	static Column column;

	public OperatorMetric(MethodVisitor mv, String cName, int access, String mName, String desc, String signature,
			String[] exceptions) {
		super(mv, cName, access, mName, desc, signature, exceptions);
		count = 0;
		operators = new HashSet<Integer>();
	}
	
	public int numUniqueOperators() {
		return operators.size();
	}

	@Override
	public void visitIincInsn(int var, int increment) {
		// ++
		if (increment == 1) {
			count++;
			operators.add(IINC);
		}
		// --
		if (increment == -1) {
			count++;
			operators.add(-2); // Stand-in for decrement
		}
	}

	@Override
	public void visitInsn(int opcode) {
		// =
		if (       opcode == ISTORE || opcode == LSTORE || opcode == FSTORE || opcode == DSTORE
				|| opcode == ASTORE || opcode == IASTORE || opcode == LASTORE || opcode == FASTORE
				|| opcode == DASTORE || opcode == AASTORE || opcode == BASTORE || opcode == CASTORE
				|| opcode == SASTORE) {
			count++;
			operators.add(ISTORE);
		}
		// +
		else if (  opcode == IADD || opcode == LADD || opcode == FADD || opcode == DADD) {
			count++;
			operators.add(IADD);
		}
		// - (binary)
		else if (  opcode == ISUB || opcode == LSUB || opcode == FSUB || opcode == DSUB) {
			count++;
			operators.add(ISUB);
		}
		// *
		else if (  opcode == IMUL || opcode == LMUL || opcode == FMUL || opcode == DMUL) {
			count++;
			operators.add(IMUL);
		}
		// /
		else if (  opcode == IDIV || opcode == LDIV || opcode == FDIV || opcode == DDIV) {
			count++;
			operators.add(IDIV);
		}
		// %
		else if (  opcode == IREM || opcode == LREM || opcode == FREM || opcode == DREM) {
			count++;
			operators.add(IREM);
		}
		// - (unary)
		else if (  opcode == INEG || opcode == LNEG || opcode == FNEG || opcode == DNEG) {
			count++;
			operators.add(INEG);
		}
		// <<
		else if (  opcode == ISHL || opcode == LSHL) {
			count++;
			operators.add(ISHL);
		}
		// >>
		else if (  opcode == ISHR || opcode == LSHR) {
			count++;
			operators.add(ISHR);
		}
		// >>>
		else if (  opcode == IUSHR || opcode == LUSHR) {
			count++;
			operators.add(IUSHR);
		}
		// &
		else if (  opcode == IAND || opcode == LAND) {
			count++;
			operators.add(IAND);
		}
		// |
		else if (  opcode == IOR || opcode == LOR) {
			count++;
			operators.add(IOR);
		}
		// ^
		else if (  opcode == IXOR || opcode == LXOR) {
			count++;
			operators.add(IXOR);
		}
		// !=
		else if (  opcode == IFEQ || opcode == IF_ICMPEQ || opcode == IF_ACMPEQ || opcode == IFNULL) {
			count++;
			operators.add(IFEQ);
		}
		// ==
		else if (  opcode == IFNE || opcode == IF_ICMPNE || opcode == IF_ACMPNE || opcode == IFNONNULL) {
			count++;
			operators.add(IFNE);
		}
		// >=
		else if (  opcode == IFLT || opcode == IF_ICMPLT) {
			count++;
			operators.add(IFLT);
		}
		// <
		else if (  opcode == IFGE || opcode == IF_ICMPGE) {
			count++;
			operators.add(IFGE);
		}
		// <=
		else if (  opcode == IFGT || opcode == IF_ICMPGT) {
			count++;
			operators.add(IFGT);
		}
		// >
		else if (  opcode == IFLE || opcode == IF_ICMPLE) {
			count++;
			operators.add(IFLE);
		}
	}

	@Override
	public void visitVarInsn(int opcode, int var) {
		// =
		if (       opcode == ISTORE || opcode == LSTORE || opcode == FSTORE || opcode == DSTORE
				|| opcode == ASTORE) {
			count++;
			operators.add(ISTORE);
		}
	}

	@Override
	public void visitTypeInsn(int opcode, String type) {
		// instanceof
		if (opcode == INSTANCEOF) {
			count++;
			operators.add(opcode);
		}
	}

	@Override
	public void visitEnd() {
		System.out.print(count);
	}

}
