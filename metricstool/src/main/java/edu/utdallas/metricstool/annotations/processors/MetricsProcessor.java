package edu.utdallas.metricstool.annotations.processors;

import edu.utdallas.metricstool.MetricCollector;
import edu.utdallas.metricstool.annotations.Metric;
import edu.utdallas.metricstool.tables.Column;
import edu.utdallas.metricstool.tables.Table;
import edu.utdallas.metricstool.tables.TableStore;

import java.util.ArrayList;
import java.util.List;

/**
 * Responsible for reading metrics declarations from a metric.
 */
public class MetricsProcessor {
    private static MetricsProcessor ourInstance = new MetricsProcessor();
    private TableStore tableStore;
    private ColumnInjector columnInjector;

    private MetricsProcessor(){
        tableStore = TableStore.getInstance();
        columnInjector = ColumnInjector.getInstance();
    }

    public static MetricsProcessor getInstance(){
        return ourInstance;
    }

    public void process(MetricCollector collector){
        System.out.println("process()");//TODO
        Metric[] metricsAnnotations =  collector.getClass().getDeclaredAnnotationsByType(Metric.class);
        List<Column> columns = new ArrayList<>();
        for (Metric m: metricsAnnotations) {
            Column column = new Column(m.key(), m.name(), m.metricType(), m.description());
            Table table = tableStore.getTable(m.artifactType());
            table.addColumn(column);
            columns.add(column);
            System.out.println(column);
        }
        columnInjector.inject(collector, columns);
    }
}
