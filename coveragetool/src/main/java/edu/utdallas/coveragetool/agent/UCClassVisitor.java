package edu.utdallas.coveragetool.agent;

import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

public class UCClassVisitor extends ClassVisitor implements Opcodes {	
	int classId;
	
    public UCClassVisitor(final ClassVisitor cv, int classId) {
        super(ASM5, cv);
        this.classId = classId;
    }

    @Override
    public MethodVisitor visitMethod(final int access, final String name,
            final String desc, final String signature, final String[] exceptions) {
        MethodVisitor mv = cv.visitMethod(access, name, desc, signature, exceptions);
        return mv == null ? null : new UCMethodVisitor(mv, name, classId);
    }
}
