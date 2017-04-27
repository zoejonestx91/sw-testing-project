package edu.utdallas.metricstool.sequencer;

import edu.utdallas.metricstool.enums.ExecutionPhase;
import edu.utdallas.metricstool.plugins.VisitorPluginDescriptor;

/**
 * A Sequencer will create a Sequence representing a valid execution of subvisitors for a given ExecutionPhase,
 * ArtifactType, and list of subvisitors.
 *
 * By the time a Sequencer is used, the following minimal assumptions are made:
 * - The VisitorPluginDescriptors have properly populated DependencyHolders.
 * - There are no cycles present in the graph.
 * - All dependencies have been loaded.
 */
public interface Sequencer {
    /**
     *
     * @param vpds (var-arg) One or multiple VisitorPluginDescriptors conforming to the documented assumptions.
     */
    public void add(VisitorPluginDescriptor... vpds);
    /**
     * Computes a valid sequence for the given phase.
     * @param phase
     * @return A valid sequence.
     */
    public Sequence resolveSequence(ExecutionPhase phase);
}
