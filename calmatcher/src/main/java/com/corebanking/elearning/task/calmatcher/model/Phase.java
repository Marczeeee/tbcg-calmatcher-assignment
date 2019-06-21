package com.corebanking.elearning.task.calmatcher.model;

import java.io.Serializable;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * Phase model. Contains the name of the phase.
 *
 * @since 1.0.0
 */
public class Phase implements Serializable {
    /** Serial version UID. */
    private static final long serialVersionUID = 6750605596138671072L;
    /** Phase name value. */
    private final String phaseName;

    /**
     * Ctor.
     *
     * @param phaseName
     *            phase name value
     */
    public Phase(final String phaseName) {
        super();
        this.phaseName = phaseName;
    }

    public String getPhaseName() {
        return phaseName;
    }

    @Override
    public boolean equals(final Object obj) {
        return obj != null && obj instanceof Phase
                && StringUtils.equals(phaseName, ((Phase) obj).getPhaseName());
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}
