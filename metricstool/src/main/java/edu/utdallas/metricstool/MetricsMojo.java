package edu.utdallas.metricstool;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;

@Mojo( name = "metrics" )
public class MetricsMojo extends AbstractMojo {

	@Parameter( property = "metricstool.targets", defaultValue = "")
	private String targets;
	
	@Parameter (property = "metricstool.base", defaultValue = "${project.build.outputDirectory}")
	private String base;
	
	@Override
	public void execute() throws MojoExecutionException, MojoFailureException {
		char sep = System.getProperty("file.separator").charAt(0);
		if (!targets.equals("") && targets != null) {
	    	for (String s : targets.split(",")) {
	    		runMetrics(new File(base + sep + s.replace('.', sep)));
	    		runMetrics(new File(base + sep + s.replace('.', sep) + ".class"));
	    	}
		}
	}
	
	private void runMetrics(File file) {
		if (!file.exists()) {
			return;
		} else if (file.isDirectory()) {
			for (File f : file.listFiles()) {
				runMetrics(f);
			}
		} else if (file.isFile() && file.getName().endsWith(".class")) {
			try {
				ClassReader reader = new ClassReader(new FileInputStream(file));
				ClassWriter writer = new ClassWriter(reader, 0);
				reader.accept(new MainClassVisitor(writer), 0);
			} catch (FileNotFoundException e) {
			} catch (IOException e) {}
		}
	}
	
}
