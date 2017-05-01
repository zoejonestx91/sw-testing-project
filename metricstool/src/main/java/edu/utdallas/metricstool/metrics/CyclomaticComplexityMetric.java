package edu.utdallas.metricstool.metrics;

import edu.utdallas.metricstool.MetricCollector;
import org.objectweb.asm.*;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map.Entry;

public class CyclomaticComplexityMetric extends MetricCollector {
	
	HashMap<Label, NodeData> nodeData; // should only be used via getNodeData()
	HashSet<Label> dests;
	
	int edges;
	int returns;
	
	boolean lastGoto;
	Label lastLabel;
	
	private static Label MANY = new Label();
	
	public CyclomaticComplexityMetric(MethodVisitor mv, String cName, int access, String mName, String desc, String signature,
			String[] exceptions) {
		super(mv, cName, access, mName, desc, signature, exceptions);
		nodeData = new HashMap<Label, NodeData>();
		dests = null;
		edges = 0;
		returns = 0;
		lastGoto = false;
		lastLabel = null;
	}
	
	private NodeData getNodeData(Label label) {
		NodeData nd = nodeData.get(label);
		if (nd == null) {
			nd = new NodeData(label);
			nodeData.put(label, nd);
			return nd;
		} else {
			return nd;
		}
	}
	
	@Override
	public void visitParameter(String name, int access) {
		lastGoto = false;
	}

	@Override
	public AnnotationVisitor visitAnnotationDefault() {
		lastGoto = false;
		return null;
	}

	@Override
	public AnnotationVisitor visitAnnotation(String desc, boolean visible) {
		lastGoto = false;
		return null;
	}

	@Override
	public AnnotationVisitor visitTypeAnnotation(int typeRef, TypePath typePath, String desc, boolean visible) {
		lastGoto = false;
		return null;
	}

	@Override
	public AnnotationVisitor visitParameterAnnotation(int parameter, String desc, boolean visible) {
		lastGoto = false;
		return null;
	}

	@Override
	public void visitAttribute(Attribute attr) {
		lastGoto = false;
	}

	@Override
	public void visitCode() {
		lastGoto = false;
	}

	@Override
	public void visitFrame(int type, int nLocal, Object[] local, int nStack, Object[] stack) {
		lastGoto = false;
	}

	@Override
	public void visitInsn(int opcode) {
		if (opcode == IRETURN || opcode == LRETURN || opcode == FRETURN || opcode == DRETURN || opcode == ARETURN || opcode == RETURN) {
			lastGoto = true;
			returns++;
			edges++;
		} else {
			lastGoto = false;
		}
	}

	@Override
	public void visitIntInsn(int opcode, int operand) {
		lastGoto = false;
	}

	@Override
	public void visitVarInsn(int opcode, int var) {
		lastGoto = false;
	}

	@Override
	public void visitTypeInsn(int opcode, String type) {
		lastGoto = false;
	}

	@Override
	public void visitFieldInsn(int opcode, String owner, String name, String desc) {
		lastGoto = false;
	}

	@Override
	public void visitMethodInsn(int opcode, String owner, String name, String desc) {
		lastGoto = false;
	}

	@Override
	public void visitMethodInsn(int opcode, String owner, String name, String desc, boolean itf) {
		lastGoto = false;
		}

	@Override
	public void visitInvokeDynamicInsn(String name, String desc, Handle bsm, Object... bsmArgs) {
		lastGoto = false;
	}

	@Override
	public void visitJumpInsn(int opcode, Label label) {
		NodeData lnd = getNodeData(lastLabel);
		NodeData nd = getNodeData(label);
		nd.in++;
		if (lnd.next == null)
			lnd.next = label;
		else if (lnd.next != label)
			lnd.next = MANY;
		if (opcode == GOTO || opcode == JSR) { // goto instructions
			lastGoto = true;
			if (dests.add(label))
				edges++;
		} else { // if instructions
			lastGoto = false;
			if (dests.add(label))
				edges++;
		}
	}

	@Override
	public void visitLabel(Label label) {
		dests = new HashSet<Label>();
		NodeData nd = getNodeData(label);
		if (!lastGoto && lastLabel != null) {
			nd.in++; // fall-through from last label
			edges++;
			NodeData lnd = getNodeData(lastLabel);
			if (lnd.next == null) {
				lnd.next = nd.label; // last only falls here for now
			} else if (lnd.next != label) {
				lnd.next = MANY; // last falls through but also goes elsewhere
			}
		}
		lastLabel = label;
		lastGoto = false;
	}

	@Override
	public void visitLdcInsn(Object cst) {
		lastGoto = false;
	}

	@Override
	public void visitIincInsn(int var, int increment) {
		lastGoto = false;
	}

	@Override
	public void visitTableSwitchInsn(int min, int max, Label dflt, Label... labels) {
		lastGoto = false;
	}

	@Override
	public void visitLookupSwitchInsn(Label dflt, int[] keys, Label[] labels) {
		lastGoto = false;
	}

	@Override
	public void visitMultiANewArrayInsn(String desc, int dims) {
		lastGoto = false;
	}

	@Override
	public AnnotationVisitor visitInsnAnnotation(int typeRef, TypePath typePath, String desc, boolean visible) {
		lastGoto = false;
		return null;
	}

	@Override
	public void visitTryCatchBlock(Label start, Label end, Label handler, String type) {
		lastGoto = false;
	}

	@Override
	public AnnotationVisitor visitTryCatchAnnotation(int typeRef, TypePath typePath, String desc, boolean visible) {
		lastGoto = false;
		return null;
	}

	@Override
	public void visitLocalVariable(String name, String desc, String signature, Label start, Label end, int index) {
		lastGoto = false;
	}

	@Override
	public AnnotationVisitor visitLocalVariableAnnotation(int typeRef, TypePath typePath, Label[] start, Label[] end,
			int[] index, String desc, boolean visible) {
		lastGoto = false;
		return null;
	}

	@Override
	public void visitLineNumber(int line, Label start) {
		lastGoto = false;
	}

	@Override
	public void visitMaxs(int maxStack, int maxLocals) {
		lastGoto = false;
	}
	
	@Override
	public void visitEnd() {
		int merges = 0;
		for (Entry<Label, NodeData> e : nodeData.entrySet()) {
			NodeData nd = e.getValue();
			if (nd.next == null || nd.next == MANY)
				continue;
			NodeData ndnext = nodeData.get(nd.next);
			if (ndnext.in == 1)
				merges++;
		}
		int nodes = nodeData.size();
		if (returns == 1) {
			nodes--; // account for a single return node that can be merged
			edges--;
		}
		int c = (edges - merges) - (nodes - merges) + 2;
		System.out.print(c);
	}
	
	private class NodeData {
		
		Label label;
		int in;		// number of incoming edges
		Label next;	// next label (null = none, many = many)
		
		public NodeData(Label label) {
			this.label = label;
			this.in = 0;
			this.next = null;
		}
	}

}
