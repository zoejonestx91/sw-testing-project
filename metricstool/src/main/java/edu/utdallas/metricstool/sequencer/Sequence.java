package edu.utdallas.metricstool.sequencer;

import edu.utdallas.metricstool.enums.ArtifactType;
import edu.utdallas.metricstool.enums.ExecutionPhase;
import edu.utdallas.metricstool.plugins.VisitorPluginDescriptor;

import java.util.Collection;

/**
 * A Sequence defines execution order for VisitorPluginDescriptors. Unless it is otherwise defined,
 * a Sequence is *not* thread safe.
 */
public interface Sequence extends Cloneable {

    public ExecutionPhase getExecutionPhase();
    public ArtifactType getArtifactType();
    public boolean isValid();

    /**
     *
     * @return A clone of the Sequence, with a shared list of plugins. This is useful for multithreading.
     */
    public Sequence clone();

    /**
     * Prepares the sequence for use. This includes making sure the list is sorted for dependencies.
     */
    void compile();

    /**
     * Adds descriptor. If compile was called, then the operation will be silently dropped.
     * @param descriptor
     */
    void add(VisitorPluginDescriptor descriptor);

    default void addAll(Collection<VisitorPluginDescriptor> list) {
        for (VisitorPluginDescriptor vpd:list) {
            this.add(vpd);
        }
    }

    /**
     * If a plugin is available to run, "pop" it off.
     * Note: - The descriptor must be "returned" to the Sequence using pushback().
     * Precondition: compile() was called.
     * @return
     */
    public VisitorPluginDescriptor pop();

    /**
     * "Peeks" at the next VisitorPluginDescriptor in line.
     * @return
     */
    public VisitorPluginDescriptor peek();

    /**
     * Used to return the descriptor to the sequence.
     * @param descriptor
     */
    public void pushback(VisitorPluginDescriptor descriptor);

    /**
     *
     * @return Whether there is another available plugin to pop. Please note,
     *          even if this returns true, calling pop() may still cause a block.
     */
    public boolean hasNext();

    /**
     * Restarts the Sequence.
     */
    public void restart();
    /**
     * Returns the default implementation.
     * @param phase
     * @param type
     * @return
     */
    public static Sequence getDefaultSequence(ExecutionPhase phase, ArtifactType type){
        return null;
    }
}
