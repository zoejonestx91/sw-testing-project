package edu.utdallas.metricstool.plugins;

import edu.utdallas.metricstool.enums.ExecutionPhase;

import java.util.Collections;
import java.util.EnumMap;
import java.util.Set;
import java.util.TreeSet;

/**
 * DependencyHolder is used by VisitorPluginDescriptor to maintain it's list of dependencies.
 */
class DependencyHolder {
    private Set<VisitorPluginDescriptor> loadDependencies = new TreeSet<>();
    private EnumMap<ExecutionPhase, Set<VisitorPluginDescriptor>> waitsOn;

    DependencyHolder(){
        waitsOn = new EnumMap<>(ExecutionPhase.class);
        for (ExecutionPhase ep: ExecutionPhase.values()) {
            waitsOn.put(ep, new TreeSet<>());
        }
    }

    /**
     * Adds a load time dependency.
     * @param descriptors
     */
    void addLoadDependency(VisitorPluginDescriptor... descriptors){
        for (VisitorPluginDescriptor vpd: descriptors) {
            loadDependencies.add(vpd);
        }
    }

    /**
     *
     * @return An unmodifiable set of load dependencies.
     */
    Set<VisitorPluginDescriptor> getLoadDependencies(){
        return Collections.unmodifiableSet(loadDependencies);
    }

    /**
     * Adds dependencie(s) to the waitsOn map. Implicitly adds to load dependencies too.
     * @param executionPhase
     * @param depends
     */
    void addWaitsOnDependency(ExecutionPhase executionPhase, VisitorPluginDescriptor... depends){
        addLoadDependency(depends);
        Set<VisitorPluginDescriptor> phaseSet = waitsOn.get(executionPhase);
        for (VisitorPluginDescriptor vpd: depends) {
            phaseSet.add(vpd);
        }
    }

    Set<VisitorPluginDescriptor> getWaitsOnDependenciesForPhase(ExecutionPhase phase){
        Set<VisitorPluginDescriptor> phaseSet = waitsOn.get(phase);
        return Collections.unmodifiableSet(phaseSet);
    }
}
