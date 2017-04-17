package edu.utdallas.metricstool;

import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.security.ProtectionDomain;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;

public class MainTransformer implements ClassFileTransformer {
	
	private String[] classesToInstrument = null;
	
	public MainTransformer(String[] classesToInstrument) {
		this.classesToInstrument = classesToInstrument;
	}
	
	public byte[] transform(ClassLoader loader,
							String className,
							Class<?> classBeingRedefined,
							ProtectionDomain protectionDomain,
							byte[] classfileBuffer)
			throws IllegalClassFormatException {
		if (classToInstrument(className)) {
			ClassReader reader = new ClassReader(classfileBuffer);
			ClassWriter writer = new ClassWriter(reader, ClassWriter.COMPUTE_FRAMES);
			reader.accept(new MainClassVisitor(writer, className), 0);
			return writer.toByteArray();
		} else {
			return null;
		}
	}
	
	public boolean classToInstrument(String className) {
		if (classesToInstrument == null)
			return false;
		for (String s : classesToInstrument) {
			if (className.indexOf(s) >= 0)
				return true;
		}
		return false;
	}
	
}
