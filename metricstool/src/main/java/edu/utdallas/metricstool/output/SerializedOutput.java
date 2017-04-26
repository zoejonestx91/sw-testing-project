package edu.utdallas.metricstool.output;

import edu.utdallas.metricstool.tables.Table;
import edu.utdallas.metricstool.tables.TableStore;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Map;

/**
 * This will use Java's built in serialization mechanism to preserve the contents of TableStore. This is not
 * implemented at current.
 */
public class SerializedOutput implements Output {//TODO IMPLEMENT EVENTUALLY
    /**
     * Writes out the contents of TableStore. The default assumes that all tables
     * and all columns should be output. The settings given by getDefaultConfig() are assumed.
     *
     * @param outputStream The outputstream to write to.
     * @throws IOException
     */
    @Override
    public void write(OutputStream outputStream) throws IOException {

    }

    /**
     * Writes out the contents of TableStore. The default assumes that all tables
     * and all columns should be output. Each different type is allowed to define it's own
     * configuration options.
     *
     * @param outputStream
     * @param config
     * @param tableStore
     * @throws IOException
     */
    @Override
    public void write(OutputStream outputStream, Map<String, Object> config, TableStore tableStore) throws IOException {

    }

    /**
     * Writes out the tables given. The default assumes that all tables
     * and all columns should be output. Each different type is allowed to define it's own
     * configuration options.
     *
     * @param outputStream
     * @param config
     * @param tables
     * @throws IOException
     */
    @Override
    public void write(OutputStream outputStream, Map<String, Object> config, Table... tables) throws IOException {

    }

    /**
     * @return The default file extension, assuming we are outputting to a file.
     */
    @Override
    public String getDefaultFileExtension() {
        return null;
    }

    /**
     * @return The default configuration map for this output.
     */
    @Override
    public Map<String, Object> getDefaultConfig() {
        return null;
    }
}
