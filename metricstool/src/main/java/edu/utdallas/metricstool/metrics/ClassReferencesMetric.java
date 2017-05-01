package edu.utdallas.metricstool.metrics;

import edu.utdallas.metricstool.MTUtils;
import edu.utdallas.metricstool.MetricCollector;
import edu.utdallas.metricstool.annotations.InjectColumn;
import edu.utdallas.metricstool.annotations.Metric;
import edu.utdallas.metricstool.enums.ArtifactType;
import edu.utdallas.metricstool.tables.Column;
import org.objectweb.asm.*;

import java.util.HashSet;
import java.util.Iterator;

@Metric(key = "classrefs", name = "Class References", artifactType = ArtifactType.METHOD)
public class ClassReferencesMetric extends MetricCollector {
	
	HashSet<String> classes;
	@InjectColumn(key = "classrefs", type = ArtifactType.METHOD)
	static Column column = null;

	public ClassReferencesMetric(MethodVisitor mv, String cName, int access, String mName, String desc, String signature,
			String[] exceptions) {
		super(mv, cName, access, mName, desc, signature, exceptions);
		classes = new HashSet<String>();
	}
	
	public String getClasses() {
		String result = "";
		Iterator<String> i = classes.iterator();
		boolean first = true;
		while (i.hasNext()) {
			if (!first)
				result += ", ";
			else
				first = false;
			
			result += i.next();
		}
		return result;
	}

	@Override
	public AnnotationVisitor visitAnnotation(String desc, boolean visible) {
		classes.addAll(MTUtils.internalNamesReferenced(desc));
		return null;
	}

	@Override
	public AnnotationVisitor visitTypeAnnotation(int typeRef, TypePath typePath, String desc, boolean visible) {
		classes.addAll(MTUtils.internalNamesReferenced(desc));
		return null;
	}

	@Override
	public AnnotationVisitor visitParameterAnnotation(int parameter, String desc, boolean visible) {
		classes.addAll(MTUtils.internalNamesReferenced(desc));
		return null;
	}

	@Override
	public void visitTypeInsn(int opcode, String type) {
		classes.add(MTUtils.descFormatToQualified(type));
	}

	@Override
	public void visitFieldInsn(int opcode, String owner, String name, String desc) {
		classes.add(MTUtils.descFormatToQualified(owner));
	}

	@Override
	public void visitMethodInsn(int opcode, String owner, String name, String desc) {
		classes.add(MTUtils.descFormatToQualified(owner));
	}

	@Override
	public void visitMethodInsn(int opcode, String owner, String name, String desc, boolean itf) {
		classes.add(MTUtils.descFormatToQualified(owner));
	}

	@Override
	public void visitInvokeDynamicInsn(String name, String desc, Handle bsm, Object... bsmArgs) {
		classes.addAll(MTUtils.internalNamesReferenced(desc));
	}

	@Override
	public void visitMultiANewArrayInsn(String desc, int dims) {
		classes.addAll(MTUtils.internalNamesReferenced(desc));
	}

	@Override
	public AnnotationVisitor visitInsnAnnotation(int typeRef, TypePath typePath, String desc, boolean visible) {
		classes.addAll(MTUtils.internalNamesReferenced(desc));
		return null;
	}

	@Override
	public void visitTryCatchBlock(Label start, Label end, Label handler, String type) {
		classes.add(MTUtils.descFormatToQualified(type));
	}

	@Override
	public AnnotationVisitor visitTryCatchAnnotation(int typeRef, TypePath typePath, String desc, boolean visible) {
		classes.addAll(MTUtils.internalNamesReferenced(desc));
		return null;
	}

	@Override
	public void visitLocalVariable(String name, String desc, String signature, Label start, Label end, int index) {
		classes.addAll(MTUtils.internalNamesReferenced(desc));
	}

	@Override
	public void visitEnd() {
		System.out.print(getClasses());
	}

}
