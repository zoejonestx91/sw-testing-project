#Metrics Tool Design Notes

##Key Considerations
MetricsTool is designed mainly to collect metrics for research purposes. As such, it needs to be designed to be easily extensible,
 and still be powerful. While speed is desired, it is not the highest priority. It is restricted to only analyzing Java projects.
 
##Key Operating Definitions
The set of **artifacts** consists of all packages, classes, methods, and basic blocks.
We can divide the program under test into Target Source and Target Tests.

##Plugins
MetricsTool will attempt on load to load any class in either a classfile in ./plugins/ or in any subdirectory that implements the
MetricsToolLoadable interface.
Keep in mind that unless MetricsTool is configured
to use only one thread, your plugin may execute on multiple threads at once. Plugins are given a set of annotations for
for configuration.

###Annotations
Note that underlined arguments are optional.

@Name(String name)
- Required
- The plugin's friendly name
@Metric(String name, String key, ArtifactType artifactType, _MetricType type_, _String description_)

@DependsOn(String dependency)
- Optional
- If the referenced plugin cannot be found, then an exception will be thrown.

@WaitFor(String plugin, _ExecutionPhase execPhase_)

- Optional
- For a given artifact, this plugin will not process this artifact until the referenced is processed. If this annotation is used without a 
  @DependsOnPlugin and the referenced plugin is not loaded, then this annotation will be ignored.
- If a phase is given, then this will only be considered for that particular execution phase.

@ExecInPhase(Phase {targetLoad, instrumentation, test, postprocess})

- Requires at least one.
- Phase is an enum defining which execution phase we are in.

@ExecBefore(ArtifactType)

@ExecAfter(ArtifactType)

###Datatypes and Enums
####ArtifactType
ArtifactType declares the type of artifact. This may be one of the following:
- SourcePackage
- SourceInterface
- SourceClass
- SourceMethod
- SourceBlock
- SourceStatement
- TestPackage
- TestInterface
- TestClass
- TestMethod
- TestBlock
- TestStatement

###Methods in MetricsToolLoadable Interface
void init()

void onPackage(Package package, Context context)

void onInterface(Interface interface, Context context)

void onClass(Class clazz, Context context)

void onMethod(Method method, Context context)

void onBlock(Block block, Context context)

void onStatement(Statement statement, Context context)

##Sequencer
The Sequencer is responsible for controlling when plugin jobs are permitted to run. This will multithread over all available processors up to the MAX_THREAD constant.


###Execution Phases
If there exist no plugins for a particular phase, it will of course be skipped.
0) Plugin load - All plugins are loaded.
1) Target Load   -   In this phase, we traverse all artifacts. This is useful for static analysis.
2) Instrumentation      -   At this phase, the target classes are instrumented. You probably should not use more than one instrumenter.
3) Test                 -   At this phase, test cases are actually ran.
4) Postprocessing
5) Output

##TableStore
The TableStore is used to store and retrieve metrics in tables. Plugins can use this to compute derived values. The current implementation is naive, but hopefully in the future a better optimized mechanism
will be offered. Each table can be visualized as having rows and columns. The columns are defined by the keys declared in the @Metric annotation, and the rows are defined by the artifact name. There is one table
for each of the types in the ArtifactType enum.

The TableStore itself conforms to the following API:

Table getTable(ArtifactType type)

void clearTables()

###Table
***Is there a nice, performant piece of off the shelf code we can use instead of this?***
List<String> getRows()
Map<String, Column> getColumns() - Map uses the metrics key as the key, and name as the value.
void addRow(String name)
void addColumn(String name, String key, MetricType type)
void addColumn(String name, String key, MetricType type, String description)
void insert(String row, String columnKey, Object value)
