package edu.utdallas.metricstool.sequencer;

import edu.utdallas.metricstool.enums.ArtifactType;
import edu.utdallas.metricstool.enums.ExecutionPhase;
import edu.utdallas.metricstool.plugins.VisitorPluginDescriptor;

/**
 * A naive, not very optimized, non-thread safe implementation of Sequence.
 */
public class SimpleSequence implements Sequence {
    private ExecutionPhase executionPhase;
    private ArtifactType artifactType;
    int levelNum = 0;
    int elementInLevel = 0;

    protected SimpleSequence(SimpleSequence sequence){
        //TODO
    }

    public SimpleSequence(ExecutionPhase phase){
        this.executionPhase = phase;

    }

    @Override
    public ExecutionPhase getExecutionPhase() {
        return this.executionPhase;
    }

    @Override
    public ArtifactType getArtifactType() {
        return this.artifactType;
    }

    @Override
    public boolean isValid() {
        return false;//TODO
    }

    /**
     * @return A clone of the Sequence, with a shared list of plugins. This is useful for multithreading.
     */
    @Override
    public Sequence clone() {
        return null;
    }

    /**
     * Prepares the sequence for use. This includes making sure the list is sorted for dependencies.
     */
    @Override
    public void compile() {

    }

    /**
     * Adds descriptor. If compile was called, then the operation will be silently dropped.
     *
     * @param descriptor
     */
    @Override
    public void add(VisitorPluginDescriptor descriptor) {

    }

    /**
     * If a plugin is available to run, "pop" it off.
     * Note: - The descriptor must be "returned" to the Sequence using pushback().
     * Precondition: compile() was called.
     *
     * @return
     */
    @Override
    public VisitorPluginDescriptor pop() {
        return null;
    }

    /**
     * "Peeks" at the next VisitorPluginDescriptor in line.
     *
     * @return
     */
    @Override
    public VisitorPluginDescriptor peek() {
        return null;
    }

    /**
     * Used to return the descriptor to the sequence.
     *
     * @param descriptor
     */
    @Override
    public void pushback(VisitorPluginDescriptor descriptor) {

    }

    /**
     * @return Whether there is another available plugin to pop. Please note,
     * even if this returns true, calling pop() may still cause a block.
     */
    @Override
    public boolean hasNext() {
        return false;
    }

    /**
     * Restarts the Sequence.
     */
    @Override
    public void restart() {

    }


}
