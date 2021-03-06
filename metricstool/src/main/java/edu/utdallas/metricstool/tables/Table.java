package edu.utdallas.metricstool.tables;

import edu.utdallas.metricstool.enums.ArtifactType;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by z on 4/10/17.
 */
public class Table implements Serializable {
    private Map<String, Column> columns;
    private Map<String, Row> rows;
    private ArtifactType type;
    private int columnCount = 0;

    public Table(ArtifactType type) {
        this.type = type;
        columns = new TreeMap<>();
        rows = new TreeMap<>();
    }

    public void addColumn(Column column){
        columns.put(column.getKey(), column);
        columnCount++;
    }

    public Column getColumn(String key){
        return columns.get(key);
    }

    public Map<String, Column> getColumns(){
        return columns;
    }

    public Map<String, Row> getRows(){
        return rows;
    }

    public List<String> getColumnNameList(){
        List<String> list = new ArrayList<>();
        for (Column column: columns.values()) {
            list.add(column.getName());
        }
        return list;
    }

    /*
        Returns all entries for a given row. If row does not exist, then null will be returned. Order is not guaranteed.
     */
    public Map<Column, Object> getRowEntries(String key){
        Row row = rows.get(key);
        if(row == null){
            return null;
        }
        return row.getEntries();
    }

    public Row addRow(String key){
        Row row = new Row(key, columnCount);
        return rows.put(key, row);
    }


}
