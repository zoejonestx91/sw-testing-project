package edu.utdallas.metricstool.tables;

import edu.utdallas.metricstool.enums.ArtifactType;

import java.io.Serializable;
import java.util.EnumMap;

/**
 * TableStore is a singleton class that is responsible for managing Tables. It contains a single Table for each ArtifactType.
 */
public class TableStore implements Serializable {
    static private TableStore instance;
    private EnumMap<ArtifactType, Table> tables;

    private TableStore(){
        initTables();
    }

    private void initTables(){
        tables = new EnumMap<>(ArtifactType.class);
        for (ArtifactType type: ArtifactType.values()) {
            tables.put(type, new Table(type));
        }
    }

    public static TableStore getInstance(){
        if(instance == null){
            instance = new TableStore();
        }
        return instance;
    }

    public Table getTable(ArtifactType type){
        return tables.get(type);
    }

    public void clearStore(){
        initTables();
    }
}
