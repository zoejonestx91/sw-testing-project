package edu.utdallas.metricstool;

import java.lang.instrument.Instrumentation;

import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

public class MetricCollector extends MethodVisitor implements Opcodes {

	protected String cName;
	protected int access;
	protected String mName;
	protected String desc;
	protected String signature;
	protected String[] exceptions;
	
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
	
}
