package edu.utdallas.coveragetool.agent;

import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

public class UnitClassVisitor extends ClassVisitor implements Opcodes {
	public UnitClassVisitor() {
		super(ASM5);
	}
	
    public UnitClassVisitor(final ClassVisitor cv) {
        super(ASM5, cv);
    }

    @Override
    public MethodVisitor visitMethod(final int access, final String name,
            final String desc, final String signature, final String[] exceptions) {
        MethodVisitor mv = cv.visitMethod(access, name, desc, signature, exceptions);
        return mv == null ? null : new UnitMethodVisitor(mv, name);
    }
}
