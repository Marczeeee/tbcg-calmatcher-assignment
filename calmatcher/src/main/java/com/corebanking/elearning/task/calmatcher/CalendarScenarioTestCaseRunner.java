package com.corebanking.elearning.task.calmatcher;

import com.corebanking.elearning.task.calmatcher.model.CalDay;
import com.corebanking.elearning.task.calmatcher.model.Phase;
import com.corebanking.elearning.task.calmatcher.model.ScenarioPhase;
import com.corebanking.elearning.task.calmatcher.model.TestCalendar;
import com.corebanking.elearning.task.calmatcher.model.TestScenario;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.Set;

import org.apache.commons.io.FileUtils;

/**
 * Test case runner class running all test cases loaded from a file.
 *
 * @since 1.0.0
 */
public class CalendarScenarioTestCaseRunner {
    /** {@link CalendarScenarioMatcher} object. */
    private final CalendarScenarioMatcher calendarScenarioMatcher = new CalendarScenarioMatcher();

    /**
     * Loads a {@link File} with file name present. Only file name or full path can be presented.
     *
     * @param fileName
     *            file name or full path value
     * @return The loaded {@link File} object, or <code>null</code> if the file couldn't be found.
     */
    private File loadTestInputFile(final String fileName) {
        final ClassLoader classLoader = getClass().getClassLoader();
        final File testInputFile = FileUtils.toFile(classLoader.getResource(fileName));
        return testInputFile;
    }

    /**
     * Writes textual content to an output file.
     *
     * @param outputFileName
     *            file name or full path value of the file to be written
     * @param content
     *            file content
     * @throws IOException
     *             If writing the file fails.
     */
    private void writeToFile(final String outputFileName, final String content) throws IOException {
        FileUtils.write(new File(outputFileName), content, StandardCharsets.UTF_8);
    }

    /**
     * Loads test cases from the file specified and runs all the test cases sequentially.
     *
     * @param inputFileName
     *            source file name or full path to the file
     * @param outputFileName
     *            results file name or full path to the file
     * @throws IOException
     *             If the input file doesn't exists, or writing the output file fails.
     */
    public void runTestCasesFromFile(final String inputFileName, final String outputFileName)
            throws IOException {
        final File inputFile = loadTestInputFile(inputFileName);
        if (inputFile == null) {
            throw new FileNotFoundException(inputFileName);
        }
        final StringBuilder sb = new StringBuilder();
        final Scanner scanner = new Scanner(inputFile);
        try {
            final int testCases = scanner.nextInt();
            for (int tc = 1; tc <= testCases; tc++) {
                final int phaseNr = scanner.nextInt();
                final Map<String, Phase> phaseMap = new HashMap<>(phaseNr, 1.0f);
                for (int phaseCount = 0; phaseCount < phaseNr; phaseCount++) {
                    final String phaseName = scanner.next();
                    phaseMap.put(phaseName, new Phase(phaseName));
                }
                final int calDayNr = scanner.nextInt();
                scanner.nextLine();
                final List<CalDay> calDays = new LinkedList<>();
                for (int dayCount = 1; dayCount <= calDayNr; dayCount++) {
                    final String phasesLine = scanner.nextLine();
                    final Scanner phaseLineScanner = new Scanner(phasesLine);
                    final Set<Phase> dailyPhases = new HashSet<>();
                    try {
                        while (phaseLineScanner.hasNext()) {
                            final String dayPhase = phaseLineScanner.next();
                            dailyPhases.add(phaseMap.get(dayPhase));
                        }
                        final CalDay calDay = new CalDay(dailyPhases);
                        calDays.add(calDay);
                    } finally {
                        phaseLineScanner.close();
                    }
                }
                final TestCalendar testCalendar = new TestCalendar(calDays);

                final Map<Integer, Set<ScenarioPhase>> dailyScenarioPhaseMap = new HashMap<>();
                final int scenarioPhasesNr = scanner.nextInt();
                scanner.nextLine();
                for (int scenPhaseCount = 0; scenPhaseCount < scenarioPhasesNr; scenPhaseCount++) {
                    final String scenarioPhaseLine = scanner.nextLine();
                    final int slashIdx = scenarioPhaseLine.indexOf('/');
                    final String dayNrString = scenarioPhaseLine.substring(0, slashIdx);
                    final Integer dayNr = Integer.parseInt(dayNrString);
                    final int eqIdx = scenarioPhaseLine.indexOf('=');
                    final String phaseName = scenarioPhaseLine.substring(slashIdx + 1, eqIdx);
                    final String testStepCount = scenarioPhaseLine.substring(eqIdx + 1);
                    final ScenarioPhase scenarioPhase =
                            new ScenarioPhase(phaseName, Integer.parseInt(testStepCount));
                    if (!dailyScenarioPhaseMap.containsKey(dayNr)) {
                        dailyScenarioPhaseMap.put(dayNr, new LinkedHashSet<ScenarioPhase>());
                    }
                    dailyScenarioPhaseMap.get(dayNr).add(scenarioPhase);
                }
                final List<CalDay> days = new LinkedList<>();
                for (final Entry<Integer, Set<ScenarioPhase>> entry : dailyScenarioPhaseMap.entrySet()) {
                    days.add(new CalDay(entry.getValue()));
                }
                final TestScenario testScenario = new TestScenario(days);

                final String result = calendarScenarioMatcher.matchTestCalendarWithTestScenario(
                        testCalendar, testScenario);
                sb.append(String.format("Case#%d: %s", tc, result));
                sb.append(System.lineSeparator());
            }
        } finally {
            scanner.close();
        }
        final String result = sb.toString();
        writeToFile(outputFileName, result);
    }
}
