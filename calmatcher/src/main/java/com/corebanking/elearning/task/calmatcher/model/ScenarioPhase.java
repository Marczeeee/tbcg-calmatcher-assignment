package com.corebanking.elearning.task.calmatcher.model;

/**
 * Specific {@link Phase} implementation for scenarios including test steps count for the current
 * phase.
 *
 * @since 1.0.0
 */
public class ScenarioPhase extends Phase {
    /** Serial version UID. */
    private static final long serialVersionUID = -5649921150764750401L;
    /** Number of test steps in the phase. */
    private final int testStepCount;

    /**
     * Ctor.
     *
     * @param phaseName
     *            phase name value
     * @param testStepCount
     *            number of test steps
     */
    public ScenarioPhase(final String phaseName, final int testStepCount) {
        super(phaseName);
        this.testStepCount = testStepCount;
    }

    public int getTestStepCount() {
        return testStepCount;
    }
}
