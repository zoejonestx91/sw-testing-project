package edu.utdallas.metricstool;

import java.util.ArrayList;

import org.objectweb.asm.AnnotationVisitor;
import org.objectweb.asm.Attribute;
import org.objectweb.asm.Handle;
import org.objectweb.asm.Label;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.TypePath;

import edu.utdallas.metricstool.metrics.*;

public class MainMethodVisitor extends MethodVisitor implements Opcodes {

	String cName;
	String mName;
	String signature;
	
	private ArrayList<MetricCollector> collectors = null;
	
	public MainMethodVisitor(MethodVisitor mv,
			String cName,
			int access,
			String mName,
			String desc,
			String signature,
			String[] exceptions) {
		super(ASM5, mv);
		this.cName = cName;
		this.mName = mName;
		this.signature = signature;
		
		this.collectors = new ArrayList<MetricCollector>();
		collectors.add(new NameMetric(mv, cName, access, mName, desc, signature, exceptions));
		collectors.add(new ArgcMetric(mv, cName, access, mName, desc, signature, exceptions));
		collectors.add(new LinesMetric(mv, cName, access, mName, desc, signature, exceptions));
	}

	@Override
	public AnnotationVisitor visitAnnotation(String arg0, boolean arg1) {
		for (MetricCollector m : collectors) { m.visitAnnotation(arg0, arg1); }
		return super.visitAnnotation(arg0, arg1);
	}

	@Override
	public AnnotationVisitor visitAnnotationDefault() {
		for (MetricCollector m : collectors) { m.visitAnnotationDefault(); }
		return super.visitAnnotationDefault();
	}

	@Override
	public void visitAttribute(Attribute arg0) {
		for (MetricCollector m : collectors) { m.visitAttribute(arg0); }
		super.visitAttribute(arg0);
	}

	@Override
	public void visitCode() {
		for (MetricCollector m : collectors) { m.visitCode(); }
		super.visitCode();
	}

	@Override
	public void visitEnd() {
		System.out.println("\n" + cName + ":" + mName + signature);
		for (MetricCollector m : collectors) { m.visitEnd(); }
		super.visitEnd();
	}

	@Override
	public void visitFieldInsn(int arg0, String arg1, String arg2, String arg3) {
		for (MetricCollector m : collectors) { m.visitFieldInsn(arg0, arg1, arg2, arg3); }
		super.visitFieldInsn(arg0, arg1, arg2, arg3);
	}

	@Override
	public void visitFrame(int arg0, int arg1, Object[] arg2, int arg3, Object[] arg4) {
		for (MetricCollector m : collectors) { m.visitFrame(arg0, arg1, arg2, arg3, arg4); }
		super.visitFrame(arg0, arg1, arg2, arg3, arg4);
	}

	@Override
	public void visitIincInsn(int arg0, int arg1) {
		for (MetricCollector m : collectors) { m.visitIincInsn(arg0, arg1); }
		super.visitIincInsn(arg0, arg1);
	}

	@Override
	public void visitInsn(int arg0) {
		for (MetricCollector m : collectors) { m.visitInsn(arg0); }
		super.visitInsn(arg0);
	}

	@Override
	public AnnotationVisitor visitInsnAnnotation(int arg0, TypePath arg1, String arg2, boolean arg3) {
		for (MetricCollector m : collectors) { m.visitInsnAnnotation(arg0, arg1, arg2, arg3); }
		return super.visitInsnAnnotation(arg0, arg1, arg2, arg3);
	}

	@Override
	public void visitIntInsn(int arg0, int arg1) {
		for (MetricCollector m : collectors) { m.visitIntInsn(arg0, arg1); }
		super.visitIntInsn(arg0, arg1);
	}

	@Override
	public void visitInvokeDynamicInsn(String arg0, String arg1, Handle arg2, Object... arg3) {
		for (MetricCollector m : collectors) { m.visitInvokeDynamicInsn(arg0, arg1, arg2, arg3); }
		super.visitInvokeDynamicInsn(arg0, arg1, arg2, arg3);
	}

	@Override
	public void visitJumpInsn(int arg0, Label arg1) {
		for (MetricCollector m : collectors) { m.visitJumpInsn(arg0, arg1); }
		super.visitJumpInsn(arg0, arg1);
	}

	@Override
	public void visitLabel(Label arg0) {
		for (MetricCollector m : collectors) { m.visitLabel(arg0); }
		super.visitLabel(arg0);
	}

	@Override
	public void visitLdcInsn(Object arg0) {
		for (MetricCollector m : collectors) { m.visitLdcInsn(arg0); }
		super.visitLdcInsn(arg0);
	}

	@Override
	public void visitLineNumber(int arg0, Label arg1) {
		for (MetricCollector m : collectors) { m.visitLineNumber(arg0, arg1); }
		super.visitLineNumber(arg0, arg1);
	}

	@Override
	public void visitLocalVariable(String arg0, String arg1, String arg2, Label arg3, Label arg4, int arg5) {
		for (MetricCollector m : collectors) { m.visitLocalVariable(arg0, arg1, arg2, arg3, arg4, arg5); }
		super.visitLocalVariable(arg0, arg1, arg2, arg3, arg4, arg5);
	}

	@Override
	public AnnotationVisitor visitLocalVariableAnnotation(int arg0, TypePath arg1, Label[] arg2, Label[] arg3,
			int[] arg4, String arg5, boolean arg6) {
		for (MetricCollector m : collectors) { m.visitLocalVariableAnnotation(arg0, arg1, arg2, arg3, arg4, arg5, arg6); }
		return super.visitLocalVariableAnnotation(arg0, arg1, arg2, arg3, arg4, arg5, arg6);
	}

	@Override
	public void visitLookupSwitchInsn(Label arg0, int[] arg1, Label[] arg2) {
		for (MetricCollector m : collectors) { m.visitLookupSwitchInsn(arg0, arg1, arg2); }
		super.visitLookupSwitchInsn(arg0, arg1, arg2);
	}

	@Override
	public void visitMaxs(int arg0, int arg1) {
		for (MetricCollector m : collectors) { m.visitMaxs(arg0, arg1); }
		super.visitMaxs(arg0, arg1);
	}

	@Override
	public void visitMethodInsn(int arg0, String arg1, String arg2, String arg3, boolean arg4) {
		for (MetricCollector m : collectors) { m.visitMethodInsn(arg0, arg1, arg2, arg3, arg4); }
		super.visitMethodInsn(arg0, arg1, arg2, arg3, arg4);
	}

	@Override
	public void visitMethodInsn(int arg0, String arg1, String arg2, String arg3) {
		for (MetricCollector m : collectors) { m.visitMethodInsn(arg0, arg1, arg2, arg3); }
		super.visitMethodInsn(arg0, arg1, arg2, arg3);
	}

	@Override
	public void visitMultiANewArrayInsn(String arg0, int arg1) {
		for (MetricCollector m : collectors) { m.visitMultiANewArrayInsn(arg0, arg1); }
		super.visitMultiANewArrayInsn(arg0, arg1);
	}

	@Override
	public void visitParameter(String arg0, int arg1) {
		for (MetricCollector m : collectors) { m.visitParameter(arg0, arg1); }
		super.visitParameter(arg0, arg1);
	}

	@Override
	public AnnotationVisitor visitParameterAnnotation(int arg0, String arg1, boolean arg2) {
		for (MetricCollector m : collectors) { m.visitParameterAnnotation(arg0, arg1, arg2); }
		return super.visitParameterAnnotation(arg0, arg1, arg2);
	}

	@Override
	public void visitTableSwitchInsn(int arg0, int arg1, Label arg2, Label... arg3) {
		for (MetricCollector m : collectors) { m.visitTableSwitchInsn(arg0, arg1, arg2, arg3); }
		super.visitTableSwitchInsn(arg0, arg1, arg2, arg3);
	}

	@Override
	public AnnotationVisitor visitTryCatchAnnotation(int arg0, TypePath arg1, String arg2, boolean arg3) {
		for (MetricCollector m : collectors) { m.visitTryCatchAnnotation(arg0, arg1, arg2, arg3); }
		return super.visitTryCatchAnnotation(arg0, arg1, arg2, arg3);
	}

	@Override
	public void visitTryCatchBlock(Label arg0, Label arg1, Label arg2, String arg3) {
		for (MetricCollector m : collectors) { m.visitTryCatchBlock(arg0, arg1, arg2, arg3); }
		super.visitTryCatchBlock(arg0, arg1, arg2, arg3);
	}

	@Override
	public AnnotationVisitor visitTypeAnnotation(int arg0, TypePath arg1, String arg2, boolean arg3) {
		for (MetricCollector m : collectors) { m.visitTypeAnnotation(arg0, arg1, arg2, arg3); }
		return super.visitTypeAnnotation(arg0, arg1, arg2, arg3);
	}

	@Override
	public void visitTypeInsn(int arg0, String arg1) {
		for (MetricCollector m : collectors) { m.visitTypeInsn(arg0, arg1); }
		super.visitTypeInsn(arg0, arg1);
	}

	@Override
	public void visitVarInsn(int arg0, int arg1) {
		for (MetricCollector m : collectors) { m.visitVarInsn(arg0, arg1); }
		super.visitVarInsn(arg0, arg1);
	}
	
}
