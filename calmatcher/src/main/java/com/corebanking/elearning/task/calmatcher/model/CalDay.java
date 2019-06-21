package com.corebanking.elearning.task.calmatcher.model;

import java.io.Serializable;
import java.util.Set;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * Calendar day model. Contains a day sequence number and the available {@link Phase} object within
 * that day.
 *
 * @since 1.0.0
 */
public class CalDay implements Serializable {
    /** Serial version UID. */
    private static final long serialVersionUID = -8051715386038196914L;
    /**
     * {@link Set} of available phases of the current day.
     */
    private final Set<? extends Phase> dailyPhases;

    /**
     * Ctor.
     *
     * @param dailyPhases
     *            set of available daily phases
     */
    public CalDay(final Set<? extends Phase> dailyPhases) {
        super();
        this.dailyPhases = dailyPhases;
    }

    public Set<? extends Phase> getDailyPhases() {
        return dailyPhases;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}
