package com.corebanking.elearning.task.calmatcher.test;

import com.corebanking.elearning.task.calmatcher.CalendarScenarioTestCaseRunner;

import org.junit.Test;

/**
 * Tests {@code CalendarScenarioMatcher} functionality.
 * 
 * @since 1.0.0
 */
public class TestCalendarMatcher {
    /** Testing scenario loader and runner object. */
    private CalendarScenarioTestCaseRunner runner = new CalendarScenarioTestCaseRunner();

    /**
     * Tests the test calendar matcher functionality with test cases loaded from (sample) input text
     * file.
     * 
     * @throws Exception
     *  If any error happens during the test.
     */
    @Test
    public void testMatchingTestInput1() throws Exception {
        runner.runTestCasesFromFile("testInput1.txt", "target/testOutput1.txt");
    }
}
