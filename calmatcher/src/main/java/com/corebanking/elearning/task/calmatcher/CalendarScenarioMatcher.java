package com.corebanking.elearning.task.calmatcher;

import com.corebanking.elearning.task.calmatcher.model.CalDay;
import com.corebanking.elearning.task.calmatcher.model.Phase;
import com.corebanking.elearning.task.calmatcher.model.TestCalendar;
import com.corebanking.elearning.task.calmatcher.model.TestScenario;

import java.util.List;
import java.util.Set;

/**
 * Matcher class to decide if a {@link TestCalendar} and a {@link TestScenario} can be fitted.
 *
 * @since 1.0.0
 */
public final class CalendarScenarioMatcher {
    /** Result string for incompatible results. */
    private static final String RESULT_INCOMPATIBLE = "INCOMPATIBLE";

    /**
     * Decides if the {@link TestScenario} present can fit in the {@link TestCalendar} or not.
     *
     * @param testCalendar
     *            test calendar object
     * @param testScenario
     *            test scenario object
     * @return If the {@link TestScenario} can be fit in the {@link TestCalendar} then returns the
     *         number of the first matching day, returns <i>INCOMPATIBLE</i> string otherwise.
     */
    public String matchTestCalendarWithTestScenario(
            final TestCalendar testCalendar,
            final TestScenario testScenario) {
        assert testCalendar != null;
        assert testScenario != null;

        String result = "";

        final List<CalDay> scenarioDays = testScenario.getDays();
        final List<CalDay> calendarDays = testCalendar.getDays();

        if (scenarioDays.size() > calendarDays.size()) {
            result = CalendarScenarioMatcher.RESULT_INCOMPATIBLE;
        } else {
            int currentFirstCalDayNr = 0;
            int matchingScenarioDaysCount = 0;
            for (int i = currentFirstCalDayNr; i < calendarDays.size(); i++) {
                final CalDay calDay = calendarDays.get(i);
                boolean hasMatch = false;
                for (int j = matchingScenarioDaysCount; j < scenarioDays.size(); j++) {
                    final CalDay scenarioDay = scenarioDays.get(j);
                    final boolean isDaysMatch = isCalDayMatchToScenarioDay(calDay, scenarioDay);
                    if (isDaysMatch) {
                        hasMatch = true;
                        matchingScenarioDaysCount++;
                        break;
                    }
                }
                if (!hasMatch) {
                    currentFirstCalDayNr++;
                    matchingScenarioDaysCount = 0;
                    if (currentFirstCalDayNr + scenarioDays.size() > calendarDays.size()) {
                        result = CalendarScenarioMatcher.RESULT_INCOMPATIBLE;
                    }
                } else if (matchingScenarioDaysCount == scenarioDays.size()) {
                    break;
                }
            }
            result = Integer.toString(currentFirstCalDayNr + 1);
        }

        return result;
    }

    /**
     * Decides if a scenario day can be fit to a calendar day. A scenario day fits if all it's
     * phases is available within the calendar day.
     *
     * @param calendarDay
     *            calendar day object
     * @param scenarioDay
     *            scenario day object
     * @return <code>true</code> if scenario day can be fit to calendar day, <code>false</code> if
     *         the two days can't be matched
     */
    private boolean isCalDayMatchToScenarioDay(final CalDay calendarDay, final CalDay scenarioDay) {
        final Set<? extends Phase> scenarioPhases = scenarioDay.getDailyPhases();
        int phaseMatchCount = 0;
        for (final Phase scnPhase : scenarioPhases) {
            for (final Phase calPhase : calendarDay.getDailyPhases()) {
                if (scnPhase.equals(calPhase)) {
                    phaseMatchCount++;
                    break;
                }
            }
        }
        return phaseMatchCount == scenarioPhases.size();
    }
}
