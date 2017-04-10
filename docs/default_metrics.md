#List and Description of Default Metrics#
This list is mainly derived from the list provided by JHawk at http://www.virtualmachinery.com/jhawkmetricslist.htm

##Metrics Collected at Method level##
 - Name               
    - Name of method.
 - Arguments Count
 - Modifiers
    - modifiers applied to a method
    - {public, static, default, protected, private, transient, constructor}
    - 'constructor' and 'default' are special cases in that they do not explicitly exist, but are provided for convenience. 
 - Cyclomatic Complexity
    - McCabes Cyclomatic Complexity
    - Roughly, sections of code present with no branches
 - Comments Count
 - Comment Lines Count
 - Variable Declarations Count
 - Variable References Count
 - Statement Count
 - Expression Count
 - Max Nesting Depth
 - Halstead Volume
 - Halstead Difficulty
 - Halstead Effort
 - Halstead Bugs
 - Total depth of nesting
 - Cast Count
 - Loop Count
 - Operator Count
 - Operand Count
 - Class References:list
 - Class Reference Count
 - External Methods Called:list
 - External Methods Called Count
 - Local Methods Called:list
 - Local Methods Called Count
 - Exceptions Referenced List
 - Exceptions Referenced Count
 - Exceptions Thrown List
 - Exceptions Thrown Count
 
 ###Metrics that may be collected at Method Level, but not guaranteed in this revision###
 - Overrides (opt)
    - The full canonical class name of the class or interface that contains the overridden method, if exist. If it does not, then this will be nil.
 - External Callers List
 - External Callers Count
 - Local Callers List
 - Local Callers Count
 - Return Type
 - Annotations Count
 - Annotations List
 - Test Callers List
 - Test Callers Count
    