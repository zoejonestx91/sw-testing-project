package edu.utdallas.metricstool.tables;

import edu.utdallas.metricstool.enums.ArtifactType;

import java.util.Map;

/**
 * Created by z on 4/10/17.
 */
public class Table {
    private Map<String, Column> columns;
    private Map<String, Row> rows;
    private ArtifactType type;
    private int columnCount = 0;

    public Table(ArtifactType type) {
        this.type = type;

    }

    public void addColumn(Column column){
        columns.put(column.getKey(), column);
        columnCount++;
    }

    public Column getColumn(String key){
        return columns.get(key);
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
