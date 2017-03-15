# General idea

The target project will contain some JUnit tests. Via Maven we will hook a JAR file into the project and specify a listener that will receive callbacks when tests are started, finished, and so on. When a test starts, we need to start collecting fresh coverage information about lines of code external to that test, so the tests themselves do not need to be instrumented except to facilitate this coverage collection.

In order to do better on the efficiency criteria, it may be best to only instrument classes as they are encountered. While the examples I've looked at use it to instrument all classes before `main` is ever executed, we should probably just perform some check whenever code is executed outside of the test code. For example, when a method is called on an object of type `A`, we should check to see whether that class (or maybe even just the method) has been instrumented yet. If not, we can instrument it prior to allowing the call to continue. If it has, we need to use the instrumented version.

I say the "instrumented version" because at first glance it looks like Java Agent, when transforming class files, *returns* the modified bytecode, so we'll have to figure out how to actually use it. This might be doubly interesting if we do end up needing to modify the same bytecode multiple times.

# Design notes

`Instrumentation.redefineClasses()` can be used to supply new class definitions at runtime. The `Instrumentation` class has facilities for repeatedly "transforming" classes, but these are not what we need. Rather, we can take runtime bytecode, supply it to ASM for transformation, and use the resulting bytecode to create a new `ClassDefinition` with which we can redefine the runtime class.

There may be a few issues with this. In JUnit, static data in a class persists between tests, so if the state of data in a *class* (not the objects instantiated during the setup of the test fixture) changes during one test, that same state will be available for use by the next test. If we transform entire classes as they are encountered during testing (including when static field accesses are made in the test itself, as opposed to just intercepting the first method call to an object of that type), this is no problem. If we want to optimize further by instrumenting at a method-level granularity, this may have implications depending on how transformations in ASM affect static data.
