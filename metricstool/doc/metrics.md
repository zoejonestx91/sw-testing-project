# JHawk Method Metric Implementations

The following is the list of JHawk method metrics and the class implementing them in MetricTool. Some metrics cannot be implemented by MetricTool because inspection of bytecode is insufficient for the task.

* **Name**: `NameMetric`
* **Cyclomatic Complexity**: `CyclomaticComplexityMetric`
  * This metric is actually not implemented using any explicit graph analysis. There may be a few corner cases that are not handled, such as thrown exceptions, finally clauses, and switch statements.
* **Number of Arguments**: `ArgcMetric`
* **Number of Comments**: N/A
  * Unable to be implemented since bytecode contains no information about comments.
* **Number of Comment Lines**: N/A
  * Unable to be implemented since bytecode contains no information about comments.
* **Variable Declarations**: `VarDecMetric`
  * It is possible for declared but unused variables to be placed on the stack such that the existence of unused variables can be inferred, but it can be impossible to determine how many there are due to differently-sized data types. As a result, this tool only counts declarations of used variables.
* **Variable References**: `VarRefMetric`
* **Number of statements**: TODO
  * [http://docs.oracle.com/javase/specs/jls/se8/html/jls-14.html](http://docs.oracle.com/javase/specs/jls/se8/html/jls-14.html)
* **Number of expressions**: TODO
  * [http://docs.oracle.com/javase/specs/jls/se8/html/jls-15.html](http://docs.oracle.com/javase/specs/jls/se8/html/jls-15.html)
* **Max depth of nesting**: TODO
* **Halstead length**: TODO
* **Halstead vocabulary**: TODO
* **Halstead volume**: TODO
* **Halstead difficulty**: TODO
* **Halstead effort**: TODO
* **Halstead bugs**: TODO
* **Total depth of nesting**: TODO
* **Number of casts**: `CastMetric`
  * Currently only handles explicit downcasts.
* **Number of loops**: TODO
* **Number of operators**: `OperationsMetric`
  * [http://docs.oracle.com/javase/specs/jls/se8/html/jls-3.html#jls-3.12](http://docs.oracle.com/javase/specs/jls/se8/html/jls-3.html#jls-3.12)
  * [https://docs.oracle.com/javase/tutorial/java/nutsandbolts/operators.html](https://docs.oracle.com/javase/tutorial/java/nutsandbolts/operators.html)
  * `instanceof` is considered an operator.
  * Certain operators listed in the Java specification and tutorials are implemented in bytecode in terms of other operators. We do not attempt to detect such operators. Examples include `~` (bitwise complement implemented via `^`, the bitwise XOR operator) and `?:` (the conditional ternary operator).
  * Operators are not considered to have a type. As such, the addition operator is considered a single operator regardless of the types of the operands (at the bytecode level these operators may be different).
* **Number of operands**: `OperationsMetric`
  * For an expression like `2 + x * 3` we consider there to be 3 operands (`2`, `x`, and `3`) to 2 operators. This is opposed to there being 4 operands (`x`, `3`, `2`, and `x * 3`). As such, we attempt to detect when a stack configuration occurs that uses a previous result as an operand to an operator.
  * Arrays are probably not handled properly.
  * Operators that can be used on Strings are not necessarily counted accurately. Notably, String concatenation may be computed statically or using a StringBuilder.
* **Class References**: `ClassReferencesMetric`
* **External methods**: `ExternalMethodsMetric`
* **Local methods**: `LocalMethodsMetric`
* **Exceptions referenced**: `ExceptionsReferencedMetric`
  * This metric relies on the heuristic that exception class names end with `Exception`.
* **Exceptions thrown**: `ExceptionsThrownMetric`
  * This metric is limited to the exceptions listed as being thrown in the method signature. There may be other exceptions (or children of listed exceptions) that are not included in the signature.
* **Modifiers**: `ModifierMetric`
* **Lines of Code**: `LinesMetric`
  * Counts the number of lines that have a direct representation in the bytecode. Blank lines, comments, and some source (such as variable declarations) are not reflected in this count.
