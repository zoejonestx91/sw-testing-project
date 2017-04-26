package edu.utdallas.metricstool.sequencer;

import edu.utdallas.metricstool.enums.ExecutionPhase;
import edu.utdallas.metricstool.plugins.VisitorPluginDescriptor;

import java.util.List;

/**
 * Created by z on 4/25/17.
 */
public class SimpleSequencer implements Sequencer {
    private List<VisitorPluginDescriptor> list;

    /**
     * @param vpds (var-arg) One or multiple VisitorPluginDescriptors conforming to the documented assumptions.
     */
    @Override
    public void add(VisitorPluginDescriptor... vpds) {
        for (VisitorPluginDescriptor vpd: vpds) {
            list.add(vpd);
        }
    }

    /**
     * Computes a valid sequence for the given phase.
     *
     * @param phase
     * @return A valid sequence.
     */
    @Override
    public Sequence resolveSequence(ExecutionPhase phase) {

        return null;
    }

    
}
