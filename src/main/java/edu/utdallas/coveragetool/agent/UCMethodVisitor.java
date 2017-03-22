package edu.utdallas.coveragetool.agent;

import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

public class UCMethodVisitor extends MethodVisitor implements Opcodes {
	String mName;
	int maxStackAdditions;
	
	public UCMethodVisitor(MethodVisitor mv, String mName) {
		super(ASM5, mv);
		this.mName = mName;
		this.maxStackAdditions = 0;
	}
	
	private void stackAdditions(int amount) {
		if (amount > maxStackAdditions)
			maxStackAdditions = amount;
	}
	
	@Override
	public void visitMethodInsn(int opcode,
            String owner,
            String name,
            String desc,
            boolean itf) {
		mv.visitFieldInsn(GETSTATIC, "java/lang/System", "err", "Ljava/io/PrintStream;");
    	mv.visitLdcInsn("Method execution: " + mName + " - " + owner + " - " + name + " - " + desc);
    	mv.visitMethodInsn(INVOKEVIRTUAL, "java/io/PrintStream", "println", "(Ljava/lang/String;)V", false);
    	stackAdditions(2);
    	super.visitMethodInsn(opcode, owner, name, desc, itf);
    }
	
	@Override
	public void visitMaxs(int maxStack, int maxLocals) {
		super.visitMaxs(maxStack, maxLocals);
		mv.visitMaxs(maxStack + maxStackAdditions, maxLocals);
	}
}
