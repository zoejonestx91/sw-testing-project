package edu.utdallas.metricstool.tables;

/**
 * Used to store information on the column.
 */
public class Column implements Comparable<Column> {
    private String key;
    private String name;
    private Class type;
    private String description;
    private static final String BLANK = "";


    public Column(String key, String name, Class type){
        this(key, name, type, BLANK);
    }

    public Column(String key, String name, Class type, String description){
        this.key = key;
        this.name = name;
        this.type = type;
        this.description = description;
    }

    public String getKey() {
        return key;
    }

    public String getName() {
        return name;
    }

    public Class getType() {
        return type;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public int compareTo(Column o) {
        return key.compareTo(o.getKey());
    }
}
