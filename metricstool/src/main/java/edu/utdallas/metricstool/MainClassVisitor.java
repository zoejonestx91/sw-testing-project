package edu.utdallas.metricstool;

import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

public class MainClassVisitor extends ClassVisitor implements Opcodes {

	String cName;
	
    public MainClassVisitor(final ClassVisitor cv, String cName) {
        super(ASM5, cv);
        this.cName = cName;
    }

    @Override
    public MethodVisitor visitMethod(final int access, final String name,
            final String desc, final String signature, final String[] exceptions) {
        MethodVisitor mv = cv.visitMethod(access, name, desc, signature, exceptions);
        return mv == null ? null : new MainMethodVisitor(mv, cName , access, name, desc, signature, exceptions);
    }
}
