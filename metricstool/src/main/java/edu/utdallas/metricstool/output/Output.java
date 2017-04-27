package edu.utdallas.metricstool.output;

import edu.utdallas.metricstool.tables.Table;
import edu.utdallas.metricstool.tables.TableStore;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Map;

/**
 * Top level interface for outputting our results.
 * Notes:   - The caller must open and close the stream as needed.
 *          - Any null cells in a Table will be output as nil or blank.
 *
 * Future plans : Outputs will be able to be loaded by OutputFactory as plugins.
 */
public interface Output {
    /**
     * Writes out the contents of TableStore. The default assumes that all tables
     * and all columns should be output. The settings given by getDefaultConfig() are assumed.
     * @param outputStream The outputstream to write to.
     * @throws IOException
     */
    public void write(OutputStream outputStream) throws IOException;

    /**
     * Writes out the contents of TableStore. The default assumes that all tables
     * and all columns should be output. Each different type is allowed to define it's own
     * configuration options.
     * @param outputStream
     * @param config
     * @param tableStore
     * @throws IOException
     */
    public void write(OutputStream outputStream, Map<String, Object> config, TableStore tableStore) throws IOException;

    /**
     * Writes out the tables given. The default assumes that all tables
     * and all columns should be output. Each different type is allowed to define it's own
     * configuration options.
     * @param outputStream
     * @param config
     * @param tables
     * @throws IOException
     */
    public void write(OutputStream outputStream, Map<String, Object> config, Table... tables) throws IOException;

    /**
     *
     * @return The default file extension, assuming we are outputting to a file.
     */
    public String getDefaultFileExtension();

    /**
     *
     * @return The default configuration map for this output.
     */
    public Map<String, Object> getDefaultConfig();

}
