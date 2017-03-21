package edu.utdallas.coveragetool.agent;

import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.security.ProtectionDomain;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;

public class UnitTransformer implements ClassFileTransformer {
	
	String targetClass; // The particular class this instance is transforming
	
	public UnitTransformer(String targetClass) {
		this.targetClass = targetClass.replace('.', '/');
	}

	public byte[] transform(ClassLoader loader,
							String className,
							Class<?> classBeingRedefined,
							ProtectionDomain protectionDomain,
							byte[] classfileBuffer)
			throws IllegalClassFormatException {
		if (targetClass.equals(className)) {
			ClassReader reader = new ClassReader(classfileBuffer);
			ClassWriter writer = new ClassWriter(reader, 0);
			reader.accept(new UnitClassVisitor(writer, className), 0);
			return writer.toByteArray();
		}
		return null; // When performing no transformation
	}
	
}
