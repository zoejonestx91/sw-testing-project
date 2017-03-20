package edu.utdallas.coveragetool.agent;

import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

public class UnitMethodVisitor extends MethodVisitor implements Opcodes {
	String mName;
	
	public UnitMethodVisitor(MethodVisitor mv, String mName) {
		super(ASM5, mv);
		this.mName = mName;
	}
}
