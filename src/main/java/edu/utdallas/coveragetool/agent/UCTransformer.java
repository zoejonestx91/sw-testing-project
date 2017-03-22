package edu.utdallas.coveragetool.agent;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.security.ProtectionDomain;

import org.apache.commons.io.IOUtils;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;

public class UCTransformer implements ClassFileTransformer {
	
	public byte[] transform(ClassLoader loader,
							String className,
							Class<?> classBeingRedefined,
							ProtectionDomain protectionDomain,
							byte[] classfileBuffer)
			throws IllegalClassFormatException {
		if (className.indexOf("edu/utdallas/coveragetool/test") == 0) {
			ClassReader reader = new ClassReader(classfileBuffer);
			ClassWriter writer = new ClassWriter(reader, 0);
			reader.accept(new UCClassVisitor(writer, className), 0);
			
			try {
				IOUtils.write(writer.toByteArray(), new FileOutputStream(className.replace('/', '_') + ".class"));
			} catch (FileNotFoundException e) {
			} catch (IOException e) {
			}
			
			return writer.toByteArray();
		} else {
			return null;
		}
	}
	
}
