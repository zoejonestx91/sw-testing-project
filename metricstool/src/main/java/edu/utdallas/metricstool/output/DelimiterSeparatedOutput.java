package edu.utdallas.metricstool.output;

import edu.utdallas.metricstool.tables.Table;
import edu.utdallas.metricstool.tables.TableStore;

import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * Delimiter separated output implementation. The following are defaults:
 */
public class DelimiterSeparatedOutput implements Output {
    private Map<String, Object> defaultConfig = new HashMap<String, Object>();
    private Map<String, Object> config = new HashMap<String, Object>();

    public DelimiterSeparatedOutput(){
        defaultConfig = new HashMap<>();
        defaultConfig.put("delim", ",");
        defaultConfig.put("quotes", true);
        config.putAll(defaultConfig);
    }

    /**
     * Writes out the contents of TableStore. The default assumes that all tables
     * and all columns should be output. The settings given by getDefaultConfig() are assumed, unless setConfig()
     * was used.
     *
     * @param outputStream The outputstream to write to.
     * @throws IOException
     */
    @Override
    public void write(OutputStream outputStream) throws IOException {
        write(outputStream, getDefaultConfig(), TableStore.getInstance());
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
        //TODO
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
        //TODO
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
        Map<String, Object> map = new HashMap<>();
        map.putAll(defaultConfig);
        return map;
    }

    public Map<String, Object> getConfig() {
        return this.config;
    }

    public void setConfig(Map<String, Object> config) {
        this.config = config;
    }
}
