# JHawk Method Metric Implementations

The following is the list of JHawk method metrics and the class implementing them in MetricTool. Some metrics cannot be implemented by MetricTool because inspection of bytecode is insufficient for the task.

* **Name**: `NameMetric`
* **Cyclomatic Complexity**: TODO
* **Number of Arguments**: `ArgcMetric`
* **Number of Comments**: N/A
  * Unable to be implemented since bytecode contains no information about comments.
* **Number of Comment Lines**: N/A
  * Unable to be implemented since bytecode contains no information about comments.
* **Variable Declarations**: `VarDecMetric`
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
* **Number of casts**: TODO
* **Number of loops**: TODO
* **Number of operators**: `OperatorMetric`
  * [http://docs.oracle.com/javase/specs/jls/se8/html/jls-3.html#jls-3.12](http://docs.oracle.com/javase/specs/jls/se8/html/jls-3.html#jls-3.12)
  * [https://docs.oracle.com/javase/tutorial/java/nutsandbolts/operators.html](https://docs.oracle.com/javase/tutorial/java/nutsandbolts/operators.html)
  * `instanceof` is considered an operator.
  * Certain operators listed in the Java specification and tutorials are implemented in bytecode in terms of other operators. We do not attempt to detect such operators. Examples include `~` (bitwise complement implemented via `^`, the bitwise XOR operator) and `?:` (the conditional ternary operator).
  * Operators are considered to have types. For instance, an operator that adds two doubles is not the same operator as that which adds integers, even though they are both represented by `+`. For this reason there may appear to be more unique operators than there actually are in the source code. We also consider operators that compare against 0 to be their own operators, even though they could be implemented by other comparison operators for the corresponding type.
* **Number of operands**: TODO
* **Class References**: TODO
* **External methods**: TODO
* **Local methods**: TODO
* **Exceptions referenced**: TODO
* **Exceptions thrown**: TODO
* **Modifiers**: TODO
* **Lines of Code**: `LinesMetric`
