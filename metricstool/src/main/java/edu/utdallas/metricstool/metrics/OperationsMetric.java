package edu.utdallas.metricstool.metrics;

import edu.utdallas.metricstool.MTUtils;
import edu.utdallas.metricstool.MetricCollector;

import org.objectweb.asm.Label;
import org.objectweb.asm.MethodVisitor;

import java.io.IOException;
import java.util.ArrayList;
import java.util.EmptyStackException;
import java.util.HashSet;
import java.util.Stack;

public class OperationsMetric extends MetricCollector {
	
	private static String[] OP_STRS = {"=", "+", "-", "*", "/", "%", "<<", ">>", ">>>", "&", "|", "^", "!=", "==", ">=", ">", "<=", ">", "++", "--"};
	
	// operators
	int opCount;
	HashSet<Integer> operators;
	
	// operands
	int andCount;
	HashSet<String> operands;
	
	// basic block stack analysis
	Stack<String> stack;

	public OperationsMetric(MethodVisitor mv, String cName, int access, String mName, String desc, String signature,
			String[] exceptions) {
		super(mv, cName, access, mName, desc, signature, exceptions);
		opCount = 0;
		operators = new HashSet<Integer>();
		andCount = 0;
		operands = new HashSet<String>();
		stack = null;
	}
	
	public int numOperators() {
		return opCount;
	}
	
	public int numUniqueOperators() {
		return operators.size();
	}
	
	public int numOperands() {
		return andCount;
	}
	
	public int numUniqueOperands() {
		return operands.size();
	}
	
	// objects are considered unique operands depending on their constructors and are detected by looking for parentheses
	// other values must not contain operators
	private void addOperand(String operand) {
		if (operand.startsWith("(") && operand.endsWith(")")) {
			operands.add(operand);
		} else {
			for (String op : OP_STRS) {
				if (operand.contains(op))
					return;
			}
			operands.add(operand);
		}
	}

	@Override
	public void visitIincInsn(int var, int increment) {
		// ++
		if (increment == 1) {
			opCount++;
			andCount ++;
			operators.add(IINC);
			addOperand("[" + var + "]");
		}
		// --
		if (increment == -1) {
			opCount++;
			andCount ++;
			operators.add(-2); // Stand-in for decrement
			addOperand("[" + var + "]");
		}
	}

	@Override
	public void visitInsn(int opcode) {
		try{
		// =
		if (       opcode == ISTORE || opcode == LSTORE || opcode == FSTORE || opcode == DSTORE
				|| opcode == ASTORE || opcode == IASTORE || opcode == LASTORE || opcode == FASTORE
				|| opcode == DASTORE || opcode == AASTORE || opcode == BASTORE || opcode == CASTORE
				|| opcode == SASTORE) {
			opCount++;
			operators.add(ISTORE);
			andCount += 2;
		}
		// +
		else if (  opcode == IADD || opcode == LADD || opcode == FADD || opcode == DADD) {
			opCount++;
			andCount += 2;
			operators.add(IADD);
			String s1 = stack.pop();
			String s2 = stack.pop();
			addOperand(s1);
			addOperand(s2);
			stack.push(s1 + " + " + s2);
		}
		// - (binary)
		else if (  opcode == ISUB || opcode == LSUB || opcode == FSUB || opcode == DSUB) {
			opCount++;
			andCount += 2;
			operators.add(ISUB);
			String s1 = stack.pop();
			String s2 = stack.pop();
			addOperand(s1);
			addOperand(s2);
			stack.push(s1 + " - " + s2);
		}
		// *
		else if (  opcode == IMUL || opcode == LMUL || opcode == FMUL || opcode == DMUL) {
			opCount++;
			andCount += 2;
			operators.add(IMUL);
			String s1 = stack.pop();
			String s2 = stack.pop();
			addOperand(s1);
			addOperand(s2);
			stack.push(s1 + " * " + s2);
		}
		// /
		else if (  opcode == IDIV || opcode == LDIV || opcode == FDIV || opcode == DDIV) {
			opCount++;
			andCount += 2;
			operators.add(IDIV);
			String s1 = stack.pop();
			String s2 = stack.pop();
			addOperand(s1);
			addOperand(s2);
			stack.push(s1 + " / " + s2);
		}
		// %
		else if (  opcode == IREM || opcode == LREM || opcode == FREM || opcode == DREM) {
			opCount++;
			andCount += 2;
			operators.add(IREM);
			String s1 = stack.pop();
			String s2 = stack.pop();
			addOperand(s1);
			addOperand(s2);
			stack.push(s1 + " % " + s2);
		}
		// - (unary)
		else if (  opcode == INEG || opcode == LNEG || opcode == FNEG || opcode == DNEG) {
			opCount++;
			andCount++;
			operators.add(INEG);
			String s1 = stack.pop();
			addOperand(s1);
			stack.push("-" + s1);
		}
		// <<
		else if (  opcode == ISHL || opcode == LSHL) {
			opCount++;
			andCount += 2;
			operators.add(ISHL);
			String s1 = stack.pop();
			String s2 = stack.pop();
			addOperand(s1);
			addOperand(s2);
			stack.push(s1 + " << " + s2);
		}
		// >>
		else if (  opcode == ISHR || opcode == LSHR) {
			opCount++;
			andCount += 2;
			operators.add(ISHR);
			String s1 = stack.pop();
			String s2 = stack.pop();
			addOperand(s1);
			addOperand(s2);
			stack.push(s1 + " >> " + s2);
		}
		// >>>
		else if (  opcode == IUSHR || opcode == LUSHR) {
			opCount++;
			andCount += 2;
			operators.add(IUSHR);
			String s1 = stack.pop();
			String s2 = stack.pop();
			addOperand(s1);
			addOperand(s2);
			stack.push(s1 + " >>> " + s2);
		}
		// &
		else if (  opcode == IAND || opcode == LAND) {
			opCount++;
			andCount += 2;
			operators.add(IAND);
			String s1 = stack.pop();
			String s2 = stack.pop();
			addOperand(s1);
			addOperand(s2);
			stack.push(s1 + " & " + s2);
		}
		// |
		else if (  opcode == IOR || opcode == LOR) {
			opCount++;
			andCount += 2;
			operators.add(IOR);
			String s1 = stack.pop();
			String s2 = stack.pop();
			addOperand(s1);
			addOperand(s2);
			stack.push(s1 + " | " + s2);
		}
		// ^
		else if (  opcode == IXOR || opcode == LXOR) {
			opCount++;
			andCount += 2;
			operators.add(IXOR);
			String s1 = stack.pop();
			String s2 = stack.pop();
			addOperand(s1);
			addOperand(s2);
			stack.push(s1 + " ^ " + s2);
		}
		// !=
		else if (  opcode == IFEQ || opcode == IF_ICMPEQ || opcode == IF_ACMPEQ || opcode == IFNULL) {
			opCount++;
			andCount += 2;
			operators.add(IFEQ);
			String s1 = stack.pop();
			String s2 = stack.pop();
			addOperand(s1);
			addOperand(s2);
			stack.push(s1 + " != " + s2);
		}
		// ==
		else if (  opcode == IFNE || opcode == IF_ICMPNE || opcode == IF_ACMPNE || opcode == IFNONNULL) {
			opCount++;
			andCount += 2;
			operators.add(IFNE);
			String s1 = stack.pop();
			String s2 = stack.pop();
			addOperand(s1);
			addOperand(s2);
			stack.push(s1 + " == " + s2);
		}
		// >=
		else if (  opcode == IFLT || opcode == IF_ICMPLT) {
			opCount++;
			andCount += 2;
			operators.add(IFLT);
			String s1 = stack.pop();
			String s2 = stack.pop();
			addOperand(s1);
			addOperand(s2);
			stack.push(s1 + " >= " + s2);
		}
		// <
		else if (  opcode == IFGE || opcode == IF_ICMPGE) {
			opCount++;
			andCount += 2;
			operators.add(IFGE);
			String s1 = stack.pop();
			String s2 = stack.pop();
			addOperand(s1);
			addOperand(s2);
			stack.push(s1 + " < " + s2);
		}
		// <=
		else if (  opcode == IFGT || opcode == IF_ICMPGT) {
			opCount++;
			andCount += 2;
			operators.add(IFGT);
			String s1 = stack.pop();
			String s2 = stack.pop();
			addOperand(s1);
			addOperand(s2);
			stack.push(s1 + " <= " + s2);
		}
		// >
		else if (  opcode == IFLE || opcode == IF_ICMPLE) {
			opCount++;
			andCount += 2;
			operators.add(IFLE);
			String s1 = stack.pop();
			String s2 = stack.pop();
			addOperand(s1);
			addOperand(s2);
			stack.push(s1 + " > " + s2);
		}
		
		if (opcode == DUP) {
			stack.push(stack.peek());
		}
		
		if (opcode == ACONST_NULL) {
			stack.push("null");
		} else if (opcode == ICONST_M1) {
			stack.push("-1i");
		} else if (opcode == ICONST_0) {
			stack.push("0i");
		} else if (opcode == ICONST_1) {
			stack.push("1i");
		} else if (opcode == ICONST_2) {
			stack.push("2i");
		} else if (opcode == ICONST_3) {
			stack.push("3i");
		} else if (opcode == ICONST_4) {
			stack.push("4i");
		} else if (opcode == ICONST_5) {
			stack.push("5i");
		} else if (opcode == LCONST_0) {
			stack.push("0l");
		} else if (opcode == LCONST_1) {
			stack.push("1l");
		} else if (opcode == FCONST_0) {
			stack.push("0f");
		} else if (opcode == FCONST_1) {
			stack.push("1f");
		} else if (opcode == FCONST_2) {
			stack.push("2f");
		} else if (opcode == DCONST_0) {
			stack.push("0d");
		} else if (opcode == DCONST_1) {
			stack.push("1d");
		}
		} catch (EmptyStackException e) {}
	}

	@Override
	public void visitIntInsn(int opcode, int operand) {
		if (opcode == BIPUSH || opcode == SIPUSH) {
			stack.push(operand + "i");
		}
	}

	@Override
	public void visitVarInsn(int opcode, int var) {
		// =
		if (       opcode == ISTORE || opcode == LSTORE || opcode == FSTORE || opcode == DSTORE
				|| opcode == ASTORE) {
			opCount++;
			operators.add(ISTORE);
			andCount += 2;
			String s1 = "[" + var + "]";
			addOperand(s1);
			String s2 = "";
			try {
				s2 = stack.pop();
				addOperand(s2);
			} catch (EmptyStackException e) {}
			stack.push(s1 + " = " + s2);
		}
		if (       opcode == ILOAD || opcode == LLOAD || opcode == FLOAD || opcode == DLOAD
				|| opcode == ALOAD) {
			stack.push("[" + var + "]");
		}
	}

	@Override
	public void visitTypeInsn(int opcode, String type) {
		// instanceof
		if (opcode == INSTANCEOF) {
			opCount++;
			operators.add(opcode);
		}
		if (opcode == NEW) {
			stack.push("(new " + type);
		}
	}

	@Override
	public void visitLdcInsn(Object cst) {
		if (cst instanceof Long) {
			stack.push(((Long) cst).longValue() + "l");
		} else if (cst instanceof Double) {
			stack.push(((Double) cst).doubleValue() + "d");
		}
	}

	@Override
	public void visitMethodInsn(int opcode, String owner, String name, String desc, boolean itf) {
		if (opcode == INVOKESPECIAL && name.equals("<init>")) {
			ArrayList<String> args = MTUtils.parseDescArgs(desc);
			String s = "";
			// iterate for all of the args as well as the object
			for (int i = 0; i <= args.size(); i++) {
				try {
					s = stack.pop() + " " + s;
				} catch (EmptyStackException e) {}
			}
			s += ")";
			try {
				stack.pop(); // remove the duplicated object reference
			} catch (EmptyStackException e) {}
			stack.push(s.trim());
		} else if (opcode == INVOKEVIRTUAL) {
			ArrayList<String> args = MTUtils.parseDescArgs(desc);
			String s = "." + name + "(";
			// iterate for all of the args as well as the object
			for (int i = 0; i < args.size(); i++) {
				try {
					s = s + " " + stack.pop();
				} catch (EmptyStackException e) {}
			}
			s += ")";
			try {
				s = stack.pop() + s;
			} catch (EmptyStackException e) {}
			stack.push(s.trim());
		} else if (opcode == INVOKESTATIC) {
			ArrayList<String> args = MTUtils.parseDescArgs(desc);
			String s = "(" + owner + "." + name + "(";
			// iterate for all of the args
			for (int i = 0; i < args.size(); i++) {
				try {
					s = s + " " + stack.pop();
				} catch (EmptyStackException e) {}
			}
			s += "))";
			stack.push(s.trim());
		}
	}

	@Override
	public void visitLabel(Label label) {
		stack = new Stack<String>();
	}

	@Override
	public void visitEnd() {
		MTUtils.write(opCount + "," + andCount);
	}

}
