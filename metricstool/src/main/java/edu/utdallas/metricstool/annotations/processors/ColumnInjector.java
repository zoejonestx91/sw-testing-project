package edu.utdallas.metricstool.annotations.processors;

import edu.utdallas.metricstool.tables.TableStore;

/**
 * ColumnInjector will, given a class, inject it's columns into it at @InjectedColumn annotations.
 */
public class ColumnInjector {
    private static ColumnInjector ourInstance = new ColumnInjector();

    public static ColumnInjector getInstance() {
        return ourInstance;
    }

    private ColumnInjector() {
    }

    /**
     * This will inject column references into @InjectedColumn annotations.
     * @param target The plugin/module we want to have processed for column injections.
     */
    public void injectInto(Class target){
        injectInto(target, TableStore.getInstance());
    }

    /**
     * This will inject column references into @InjectedColumn annotations. Only meant to
     * allow tests to inject stubbed TableStores.
     * @param target
     * @param tableStore
     */
    protected void injectInto(Class target, TableStore tableStore){
        //TODO implement
    }
}
