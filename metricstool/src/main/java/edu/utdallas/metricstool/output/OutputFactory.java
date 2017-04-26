package edu.utdallas.metricstool.output;

/**
 * This gives the available Output modules.
 */
public class OutputFactory {
    private static OutputFactory ourInstance = new OutputFactory();

    public static OutputFactory getInstance() {
        return ourInstance;
    }

    private Output defaultOutput;

    private Output outputs[] = {new DelimiterSeparatedOutput()};

    private OutputFactory() {
        defaultOutput = new DelimiterSeparatedOutput();
    }

    public Output getDefaultOutput(){
        return defaultOutput;
    }

    public Output[] getOutputs(){
        return outputs;
    }


}
