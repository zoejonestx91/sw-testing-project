# CoverageTool

## Usage

Our tool is quite fast, so we will demonstrate how to set it up for the Joda Time project. Due to some hardcoded values that were introduced to perform better for the second round of the testing competition, other projects may fail if their specifications are not as expected. Previous iterations of this project could handle any project but were slower. A middle-ground could be found in the future.

In the CoverageTool project run `mvn compile` followed by `mvn package`. This will produce two JAR files in the `target` directory. Ignore the one mentioning included dependencies; it is not necessary. The JAR should be named `coveragetool-1.0.jar`. Move this file into the root directory of the project you want to use it with. Then modify the target project's POM to include the following.

```
<dependency>
	<groupId>org.ow2.asm</groupId>
	<artifactId>asm-all</artifactId>
	<version>5.0.3</version>
</dependency>
<dependency>
	<groupId>junit</groupId>
	<artifactId>junit</artifactId>
	<version>4.12</version>
	<scope>test</scope>
</dependency>
```

The dependencies block of the project's POM must include the ASM framework as a dependency. JUnit is also necessary.

```
<plugin>
	<groupId>org.apache.maven.plugins</groupId>
    	<artifactId>maven-surefire-plugin</artifactId>
    	<configuration>
		<argLine>-Xms512m -Xmx12g -javaagent:coveragetool-1.0.jar=root.package</argLine>
		<properties>
			<property>	
				<name>listener</name>
				<value>edu.utdallas.coveragetool.agent.UnitListener</value>
			</property>
		</properties>
	</configuration>
</plugin>
```

The build block of the POM should have the SureFire plugin configured this way. Memory allocation on the argument line can be adjusted as needed, as the tool is very memory-hungry as it is now (this is the primary issue with its current implementation, but it allows the tool to be fast). Replace `root.package` with the highest-level package of the project that you want to collect statement coverage information for. You may add additional classes or packages using commas as a delimiter.

Once the POM has been configured, simply run `mvn test` and a file will be generated named `stmt-cov.txt`.

## Example

This project is known to work for the Joda-Time project (https://github.com/JodaOrg/joda-time.git, SHA-1: acff94148b2110b95f7aeae6a1bdcafb756061f0). Excluding the dependencies updates to Joda-Time's POM, the following configuration is known to work for this commit.

```
<plugin>
	<groupId>org.apache.maven.plugins</groupId>
    	<artifactId>maven-surefire-plugin</artifactId>
    	<configuration>
		<argLine>-Xms512m -Xmx12g -javaagent:coveragetool-1.0.jar=org.joda.time</argLine>
		<properties>
			<property>	
				<name>listener</name>
				<value>edu.utdallas.coveragetool.agent.UnitListener</value>
			</property>
		</properties>
	</configuration>
</plugin>
```

# MetricsTool

## Usage

Navigate to the MetricsTool project root and run `mvn install`. This will prepare the Maven plugin for usage on your system.

The build block of the target project's POM should include the following plugin configuration.

```
<plugin>
	<groupId>edu.utdallas.metricstool</groupId>
	<artifactId>metricstool</artifactId>
	<executions>
		<execution>
			<phase>compile</phase>
			<goals>
				<goal>metrics</goal>
			</goals>
		</execution>
	</executions>
	<configuration>
		<targets>root.package</targets>
	</configuration>
</plugin>
```

This time the only thing that needs to be configured are the targets. These follow the same rules as in CoverageTool. Generally you just want to specify the top-level package.

Once everything is set up, just use `mvn compile` and a file named `metrics.csv` will be generated in the root directory of the project.

## Example

This project is known to work for the Commons IO project (https://github.com/apache/commons-io.git, SHA-1: bfd83b00eb5743ad4ad0d24957f84b61ef9f5f79). The following configuration is known to work for this commit.

```
<plugin>
	<groupId>edu.utdallas.metricstool</groupId>
	<artifactId>metricstool</artifactId>
	<executions>
		<execution>
			<phase>compile</phase>
			<goals>
				<goal>metrics</goal>
			</goals>
		</execution>
	</executions>
	<configuration>
		<targets>org.apache.commons.io</targets>
	</configuration>
</plugin>
```
