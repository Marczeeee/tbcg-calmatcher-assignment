package com.corebanking.elearning.task.calmatcher.model;

import java.io.Serializable;
import java.util.List;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * Test scenario model. Contains the days describing the whole test scenario.
 *
 * @since 1.0.0
 */
public class TestScenario implements Serializable {
    /** Serial version UID. */
    private static final long serialVersionUID = 6247385204283560203L;
    /** Test scenario days. */
    private final List<CalDay> days;

    /**
     * Ctor.
     *
     * @param days
     *            test scenario days
     */
    public TestScenario(final List<CalDay> days) {
        super();
        this.days = days;
    }

    public List<CalDay> getDays() {
        return days;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}
