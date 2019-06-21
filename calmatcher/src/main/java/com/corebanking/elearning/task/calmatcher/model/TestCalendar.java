package com.corebanking.elearning.task.calmatcher.model;

import java.io.Serializable;
import java.util.List;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * Test calendar model. Contains the days available for testing.
 *
 * @since 1.0.0
 */
public class TestCalendar implements Serializable {
    /** Serial version UID. */
    private static final long serialVersionUID = -7818548359011896136L;
    /** {@link List} of days available to be used. */
    private final List<CalDay> days;

    /**
     * Ctor.
     *
     * @param days
     *            available days
     */
    public TestCalendar(final List<CalDay> days) {
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
