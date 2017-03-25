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
	
	public static boolean writeClasses = false;
	
	public byte[] transform(ClassLoader loader,
							String className,
							Class<?> classBeingRedefined,
							ProtectionDomain protectionDomain,
							byte[] classfileBuffer)
			throws IllegalClassFormatException {
		// TODO: Parameterize this for general use
		if (className.contains("edu/utdallas/coveragetool/test/classes")) {
			ClassReader reader = new ClassReader(classfileBuffer);
			ClassWriter writer = new ClassWriter(reader, ClassWriter.COMPUTE_FRAMES);
			reader.accept(new UCClassVisitor(writer, className), 0);
			
			if (writeClasses) {
				try {
					IOUtils.write(writer.toByteArray(), new FileOutputStream(className.replace('/', '_') + ".class"));
				} catch (FileNotFoundException e) {
				} catch (IOException e) {
				}
			}
			
			return writer.toByteArray();
		} else {
			return null;
		}
	}
	
}