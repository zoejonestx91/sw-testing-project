package edu.utdallas.metricstool;

import edu.utdallas.metricstool.tables.Row;
import edu.utdallas.metricstool.tables.Table;
import org.objectweb.asm.*;

public class MetricCollector extends MethodVisitor implements Opcodes, MetricMarker {

	protected String cName;
	protected int access;
	protected String mName;
	protected String desc;
	protected String signature;
	protected String[] exceptions;


	protected static Row currentMethodRow;
	public static Row getCurrentMethodRow() {
		return currentMethodRow;
	}

	public static void setCurrentMethodRow(Row currentMethodRow) {
		MetricCollector.currentMethodRow = currentMethodRow;
	}

	public void initColumns(Table table){

	}


	
	public MetricCollector(MethodVisitor mv,
			String cName,
			int access,
			String mName,
			String desc,
			String signature,
			String[] exceptions) {
		super(ASM5, mv);
		this.cName = cName;
		this.access = access;
		this.mName = mName;
		this.desc = desc;
		this.signature = signature;
		this.exceptions = exceptions;
	}

	@Override
	public void visitParameter(String name, int access) {}

	@Override
	public AnnotationVisitor visitAnnotationDefault() {
		return null;
	}

	@Override
	public AnnotationVisitor visitAnnotation(String desc, boolean visible) {
		return null;
	}

	@Override
	public AnnotationVisitor visitTypeAnnotation(int typeRef, TypePath typePath, String desc, boolean visible) {
		return null;
	}

	@Override
	public AnnotationVisitor visitParameterAnnotation(int parameter, String desc, boolean visible) {
		return null;
	}

	@Override
	public void visitAttribute(Attribute attr) {}

	@Override
	public void visitCode() {}

	@Override
	public void visitFrame(int type, int nLocal, Object[] local, int nStack, Object[] stack) {}

	@Override
	public void visitInsn(int opcode) {}

	@Override
	public void visitIntInsn(int opcode, int operand) {}

	@Override
	public void visitVarInsn(int opcode, int var) {}

	@Override
	public void visitTypeInsn(int opcode, String type) {}

	@Override
	public void visitFieldInsn(int opcode, String owner, String name, String desc) {}

	@Override
	public void visitMethodInsn(int opcode, String owner, String name, String desc) {}

	@Override
	public void visitMethodInsn(int opcode, String owner, String name, String desc, boolean itf) {}

	@Override
	public void visitInvokeDynamicInsn(String name, String desc, Handle bsm, Object... bsmArgs) {}

	@Override
	public void visitJumpInsn(int opcode, Label label) {}

	@Override
	public void visitLabel(Label label) {}

	@Override
	public void visitLdcInsn(Object cst) {}

	@Override
	public void visitIincInsn(int var, int increment) {}

	@Override
	public void visitTableSwitchInsn(int min, int max, Label dflt, Label... labels) {}

	@Override
	public void visitLookupSwitchInsn(Label dflt, int[] keys, Label[] labels) {}

	@Override
	public void visitMultiANewArrayInsn(String desc, int dims) {}

	@Override
	public AnnotationVisitor visitInsnAnnotation(int typeRef, TypePath typePath, String desc, boolean visible) {
		return null;
	}

	@Override
	public void visitTryCatchBlock(Label start, Label end, Label handler, String type) {}

	@Override
	public AnnotationVisitor visitTryCatchAnnotation(int typeRef, TypePath typePath, String desc, boolean visible) {
		return null;
	}

	@Override
	public void visitLocalVariable(String name, String desc, String signature, Label start, Label end, int index) {}

	@Override
	public AnnotationVisitor visitLocalVariableAnnotation(int typeRef, TypePath typePath, Label[] start, Label[] end,
			int[] index, String desc, boolean visible) {
		return null;
	}

	@Override
	public void visitLineNumber(int line, Label start) {}

	@Override
	public void visitMaxs(int maxStack, int maxLocals) {}

	@Override
	public void visitEnd() {}
	
}
