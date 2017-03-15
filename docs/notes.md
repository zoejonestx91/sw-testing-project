# General idea

The target project will contain some JUnit tests. Via Maven we will hook a JAR file into the project and specify a listener that will receive callbacks when tests are started, finished, and so on. When a test starts, we need to start collecting fresh coverage information about lines of code external to that test, so the tests themselves do not need to be instrumented except to facilitate this coverage collection.

In order to do better on the efficiency criteria, it may be best to only instrument classes as they are encountered. While the examples I've looked at use it to instrument all classes before `main` is ever executed, we should probably just perform some check whenever code is executed outside of the test code. For example, when a method is called on an object of type `A`, we should check to see whether that class (or maybe even just the method) has been instrumented yet. If not, we can instrument it prior to allowing the call to continue. If it has, we need to use the instrumented version.

I say the "instrumented version" because at first glance it looks like Java Agent, when transforming class files, *returns* the modified bytecode, so we'll have to figure out how to actually use it. This might be doubly interesting if we do end up needing to modify the same bytecode multiple times.
