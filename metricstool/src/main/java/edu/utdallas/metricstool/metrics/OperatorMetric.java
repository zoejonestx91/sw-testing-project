package edu.utdallas.metricstool.metrics;

import java.util.HashSet;

import org.objectweb.asm.Label;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes.*;

import edu.utdallas.metricstool.MetricCollector;

public class OperatorMetric extends MetricCollector {
	
	int count;
	HashSet<Integer> operators;

	public OperatorMetric(MethodVisitor mv, String cName, int access, String mName, String desc, String signature,
			String[] exceptions) {
		super(mv, cName, access, mName, desc, signature, exceptions);
		operators = new HashSet<Integer>();
	}
	
	public int numUniqueOperators() {
		return operators.size();
	}

	@Override
	public void visitInsn(int opcode) {
		if (       opcode == ISTORE || opcode == LSTORE || opcode == FSTORE || opcode == DSTORE
				|| opcode == ASTORE || opcode == IASTORE || opcode == LASTORE || opcode == FASTORE
				|| opcode == DASTORE || opcode == AASTORE || opcode == BASTORE || opcode == CASTORE || opcode == SASTORE
				|| opcode == IADD || opcode == LADD || opcode == FADD || opcode == DADD
				|| opcode == ISUB || opcode == LSUB || opcode == FSUB || opcode == DSUB
				|| opcode == IMUL || opcode == LMUL || opcode == FMUL || opcode == DMUL
				|| opcode == IDIV || opcode == LDIV || opcode == FDIV || opcode == DDIV
				|| opcode == IREM || opcode == LREM || opcode == FREM || opcode == DREM
				|| opcode == INEG || opcode == LNEG || opcode == FNEG || opcode == DNEG
				|| opcode == ISHL || opcode == LSHL || opcode == ISHR || opcode == LSHR
				|| opcode == IUSHR || opcode == LUSHR || opcode == IAND || opcode == LAND
				|| opcode == IOR || opcode == LOR || opcode == IXOR || opcode == LXOR
				|| opcode == LCMP || opcode == FCMPL || opcode == FCMPG || opcode == DCMPL
				|| opcode == DCMPG || opcode == IFEQ || opcode == IFNE || opcode == IFLT
				|| opcode == IFGE || opcode == IFGT || opcode == IFLE || opcode == IF_ICMPEQ
				|| opcode == IF_ICMPNE || opcode == IF_ICMPLT || opcode == IF_ICMPGE || opcode == IF_ICMPGT
				|| opcode == IF_ICMPLE || opcode == IF_ACMPEQ || opcode == IF_ACMPNE
				|| opcode == IFNULL || opcode == IFNONNULL) {
			count++;
			operators.add(opcode);
		}
	}

	@Override
	public void visitTypeInsn(int opcode, String type) {
		if (opcode == INSTANCEOF) {
			count++;
			operators.add(opcode);
		}
	}

	@Override
	public void visitEnd() {
		System.out.println("Number of Operators: " + count);
	}

}
