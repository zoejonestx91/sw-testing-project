package edu.utdallas.metricstool.annotations.processors;

import edu.utdallas.metricstool.MetricCollector;
import edu.utdallas.metricstool.annotations.InjectColumn;
import edu.utdallas.metricstool.tables.Column;

import java.lang.reflect.Field;
import java.util.List;

/**
 * ColumnInjector will, given a class, inject it's columns into it at @InjectColumn annotations.
 */
public class ColumnInjector {
    private static ColumnInjector ourInstance = new ColumnInjector();

    public static ColumnInjector getInstance() {
        return ourInstance;
    }

    private ColumnInjector() {
    }

    public void inject(MetricCollector metricCollector, List<Column> columns){
        if(columns.size() == 0){return;}

        Field[] candidates = metricCollector.getClass().getDeclaredFields();


        for (Field field: candidates) {
            System.out.println(field);//TODO
            if(field.isAnnotationPresent(InjectColumn.class)){
                System.out.println("annotation present");//TODO
                Column columnToInject = matchToColumn(field, columns);
                System.out.println(columnToInject);//TODO
                setIfPossible(field, columnToInject, metricCollector);
            }
        }
    }

    private void setIfPossible(Field field, Column column, MetricCollector metricCollector){
        if (column == null) {
            System.out.println("Error: Cannot inject column into " + metricCollector.getClass().getName() + ". Bad @InjectColumn annotation.");
            return;
        }
        try {
            field.set(metricCollector, column);
        } catch (IllegalAccessException e) {
            System.out.println("Error: Cannot inject column into " + metricCollector.getClass().getName() + ". Terminating immediately.");
            e.printStackTrace();
            System.exit(-1);
        }
    }

    private Column matchToColumn(Field field, List<Column> columns){
        InjectColumn injectionSite = field.getAnnotation(InjectColumn.class);
        for (Column column: columns) {
            if(injectionSite.key().equals(column.getKey())){
                return column;
            }
        }
        return null;
    }
}
