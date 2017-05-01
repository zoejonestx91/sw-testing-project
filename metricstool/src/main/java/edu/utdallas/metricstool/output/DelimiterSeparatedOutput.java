package edu.utdallas.metricstool.output;

import edu.utdallas.metricstool.tables.Column;
import edu.utdallas.metricstool.tables.Row;
import edu.utdallas.metricstool.tables.Table;
import edu.utdallas.metricstool.tables.TableStore;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Delimiter separated output implementation. The following are defaults:
 */
public class DelimiterSeparatedOutput implements Output {
    private Map<String, Object> defaultConfig = new HashMap<String, Object>();
    private Map<String, Object> config = new HashMap<String, Object>();
    private String delim;
    private String quoteOpen;
    private String quoteClose;

    public DelimiterSeparatedOutput(){
        defaultConfig = new HashMap<>();
        defaultConfig.put("delim", ",");
        defaultConfig.put("quoteOpen", "\"");
        defaultConfig.put("quoteClose", "\"");
        config.putAll(defaultConfig);
        setConfig(config);
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
     * Writes out the tables given. The default assumes that all tables
     * and all columns should be output. Each different type is allowed to define it's own
     * configuration options.
     *
     * @param out
     * @param config
     * @param table
     * @throws IOException
     */
    @Override
    public void write(OutputStream out, Map<String, Object> config, Table table) throws IOException {
        if(config == null){
            config = getDefaultConfig();
        }
        setConfig(config);

        BufferedWriter bw = new BufferedWriter(new PrintWriter(out));

        //printing header
        List<String> columnNames = table.getColumnNameList();
        bw.write(quoteOpen + "id" + quoteClose);
        for (int i = 0; i < columnNames.size(); i++) {
            bw.write(delim);
            bw.write(quoteOpen + columnNames.get(i) + quoteClose);
        }
        bw.write('\n');

        for (Row row: table.getRows().values()) {
            bw.write(quoteOpen + row.getKey() + quoteClose);
            Map<Column, Object> entries = row.getEntries();
            for (Object element: entries.values()) {
                bw.write(delim);
                bw.write(quoteOpen + element.toString() +quoteClose);
            }
            bw.write('\n');
        }
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
        delim = (String) config.get("delim");

    }
}
