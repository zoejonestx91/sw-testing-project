package edu.utdallas.metricstool.plugins;

import edu.utdallas.metricstool.enums.ArtifactType;
import edu.utdallas.metricstool.enums.ExecutionPhase;

import java.util.EnumSet;

/**
 *
 */
public class VisitorPluginDescriptor {


    private VisitorPlugin visitorPlugin;
    private EnumSet<ArtifactType> artifactTypes = EnumSet.noneOf(ArtifactType.class);
    private EnumSet<ExecutionPhase> executionPhases = EnumSet.noneOf(ExecutionPhase.class);
    private String name;


    public VisitorPlugin getVisitorPlugin() {
        return visitorPlugin;
    }

    protected void setVisitorPlugin(VisitorPlugin visitorPlugin) {
        this.visitorPlugin = visitorPlugin;
    }

    /**
     * Indicates whether the plugin should execute for a given phase and type.
     * @param artifactType
     * @param executionPhase
     * @return
     */
    public boolean isInterestedIn(ArtifactType artifactType, ExecutionPhase executionPhase){
        return artifactTypes.contains(artifactType) && executionPhases.contains(executionPhase);
    }


}
