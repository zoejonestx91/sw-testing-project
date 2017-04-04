package edu.utdallas.coveragetool.agent;

import org.objectweb.asm.Label;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

public class UCMethodVisitor extends MethodVisitor implements Opcodes {
	String mName;
	int cId;
	int line;
	
	public UCMethodVisitor(MethodVisitor mv, String mName, int classId) {
		super(ASM5, mv);
		this.mName = mName;
		this.line = 0;
		this.cId = classId;
	}
	
	@Override
	public void visitLineNumber(int line, Label start) {
		this.line = line;
		mvStmtCover();
		super.visitLineNumber(line, start);
	}
	
	@Override
	public void visitLabel(Label label) {
		mvStmtCover();
		super.visitLabel(label);
	}
	
	// Invokes the main coverage function given a class name and line number
	private void mvStmtCover() {
		if (line > 0) {
			mv.visitIntInsn(SIPUSH, cId);
			mv.visitIntInsn(SIPUSH, line);
			mv.visitMethodInsn(INVOKESTATIC, "edu/utdallas/coveragetool/agent/UnitListener", "stmtCover", "(II)V", false);
		}
	}
}
