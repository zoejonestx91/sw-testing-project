package edu.utdallas.metricstool.tables;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;


public class Row implements Comparable<Row>, Serializable{
    private String key;
    private Map<Column, Object> entries;

    public Row(String key) {
        this.key = key;
        entries = new HashMap<>();
    }

    /*
        Used to initialize the map with the number of columns.
     */
    public Row(String key, int numEntries) {
        this.key = key;
        entries = new HashMap<>(numEntries);
    }

    public String getKey() {
        return key;
    }

    public Map<Column, Object> getEntries() {
        return entries;
    }

    public void addEntry(Column col, Object entry){
        entries.put(col, entry);
    }

    @Override
    public int compareTo(Row that) {
        return key.compareTo(that.getKey());
    }
}
