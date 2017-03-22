package edu.utdallas.coveragetool.agent;

import org.objectweb.asm.AnnotationVisitor;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

public class UnitMethodVisitor extends MethodVisitor implements Opcodes {
	String mName;
	boolean isTest;
	boolean isIgnored;
	
	public UnitMethodVisitor(MethodVisitor mv, String mName) {
		super(ASM5, mv);
		this.mName = mName;
		this.isTest = false;
		this.isIgnored = false;
	}
	
	@Override
	public AnnotationVisitor visitAnnotation(String desc, boolean visible) {
		if (desc.equals("Lorg/junit/Test;"))
			this.isTest = true;
		else if (desc.equals("Lorg/junit/Ignore;"))
			this.isIgnored = true;
		return super.visitAnnotation(desc, visible);
	}
	
	@Override
	public void visitMethodInsn(int opcode,
            String owner,
            String name,
            String desc,
            boolean itf) {
		// We don't instrument methods unless they're tests and not ignored
		if (!isTest || isIgnored) {
			super.visitMethodInsn(opcode, owner, name, desc, itf);
			return;
		}
		
		// TODO: the actual test instrumentation
		// Will require instrumenting tests to configure the current testing "context" (i.e. the current test class and test name,
		// per the output specification) so that instrumented user code will write coverage information specific to that test case.
		// This should be easily done by having the user code call a "coverage output" function in this class (UnitListener), which
		// can unite the current context as set by the test case with the coverage information being collected by instrumentation
		// in the user code.
		
		// This causes verify errors?
    	mv.visitFieldInsn(GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
    	mv.visitLdcInsn(mName+" executed");
    	mv.visitMethodInsn(INVOKEVIRTUAL, "java/io/PrintStream", "println", "(Ljava/lang/String;)V", false);
		
		super.visitMethodInsn(opcode, owner, name, desc, itf);
	}
}
