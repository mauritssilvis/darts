/*
 * Copyright © 2023 Maurits Silvis
 * SPDX-License-Identifier: GPL-3.0-or-later
 */

package nl.mauritssilvis.darts.java.cli;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.List;
import java.util.stream.Stream;

class CheckoutsCommandTests {
    @Test
    void getAnErrorMessage() {
        String[] args = {"checkouts"};

        StringWriter out = new StringWriter();
        StringWriter err = new StringWriter();

        DartsApp.create()
                .setOut(new PrintWriter(out))
                .setErr(new PrintWriter(err))
                .execute(args);

        String outString = out.toString();
        String errString = err.toString();

        Assertions.assertAll(
                () -> Assertions.assertTrue(outString.isEmpty()),
                () -> Assertions.assertTrue(errString.contains("Usage"))
        );
    }

    @Test
    void getHelp() {
        String[] args = {"help", "checkouts"};

        StringWriter out = new StringWriter();
        StringWriter err = new StringWriter();

        DartsApp.create()
                .setOut(new PrintWriter(out))
                .setErr(new PrintWriter(err))
                .execute(args);

        String outString = out.toString();
        String errString = err.toString();

        List<String> elements = List.of(
                "Usage",
                "checkouts",
                "Generate a darts checkout table.",
                "The dartboard type.",
                "<board>",
                "The check-in type.",
                "<check-in>",
                "The checkout type.",
                "<checkout>",
                "The number of throws.",
                "<throws>",
                "The checkout finder mode.",
                "<mode>",
                "The checkout finder type.",
                "<finder>",
                "The checkout table type.",
                "<table>",
                "The output format.",
                "<output>",
                "The minimum score",
                "<minimum>",
                "The maximum score",
                "<maximum>"
        );

        long count = elements.stream()
                .filter(outString::contains)
                .count();

        Assertions.assertAll(
                () -> Assertions.assertEquals(elements.size(), count),
                () -> Assertions.assertTrue(errString.isEmpty())
        );
    }

    @ParameterizedTest
    @MethodSource("withTheScoreParameters")
    void processTheScoreParameters(String minScore, String maxScore, String output) {
        String[] args = {"checkouts", minScore, maxScore};

        StringWriter out = new StringWriter();
        StringWriter err = new StringWriter();

        DartsApp.create()
                .setOut(new PrintWriter(out))
                .setErr(new PrintWriter(err))
                .execute(args);

        String outString = out.toString();
        String errString = err.toString();

        Assertions.assertAll(
                () -> Assertions.assertTrue(outString.startsWith(output)),
                () -> Assertions.assertTrue(errString.isEmpty())
        );
    }

    private static Stream<Arguments> withTheScoreParameters() {
        return Stream.of(
                Arguments.of(
                        "1",
                        "1",
                        """
                                | Score | # |
                                |------:|--:|
                                |     1 | 0 |"""
                ),
                Arguments.of(
                        "2",
                        "2",
                        """
                                | Score |  1 | # |
                                |------:|---:|--:|
                                |     2 |  * | 1 |
                                |       | D1 | 1 |"""
                ),
                Arguments.of(
                        "1",
                        "2",
                        """
                                | Score |  1 | # |
                                |------:|---:|--:|
                                |     1 |  * | 0 |
                                |     2 |  * | 1 |
                                |       | D1 | 1 |"""
                )
        );
    }

    @ParameterizedTest
    @MethodSource("withTheBoardOption")
    void processTheBoardOption(String optionName, String boardType, String output) {
        String[] args = {"checkouts", optionName, boardType, "-j", "any", "3", "4"};

        StringWriter out = new StringWriter();
        StringWriter err = new StringWriter();

        DartsApp.create()
                .setOut(new PrintWriter(out))
                .setErr(new PrintWriter(err))
                .execute(args);

        String outString = out.toString();
        String errString = err.toString();

        Assertions.assertAll(
                () -> Assertions.assertTrue(outString.startsWith(output)),
                () -> Assertions.assertTrue(errString.isEmpty())
        );
    }

    private static Stream<Arguments> withTheBoardOption() {
        return Stream.of(
                Arguments.of(
                        "-b",
                        "London",
                        """
                                | Score |       1 | # |
                                |------:|--------:|--:|
                                |     3 |       * | 2 |
                                |       |  3 / T1 | 2 |
                                |     4 |       * | 2 |
                                |       |  4 / D2 | 2 |"""
                ),
                Arguments.of(
                        "--board",
                        "boardtype.quadro",
                        """
                                | Score |            1 | # |
                                |------:|-------------:|--:|
                                |     3 |            * | 2 |
                                |       |       3 / T1 | 2 |
                                |     4 |            * | 3 |
                                |       |  4 / D2 / Q1 | 3 |"""
                ),
                Arguments.of(
                        "-b",
                        "YORKSHIRE",
                        """
                                | Score |       1 | # |
                                |------:|--------:|--:|
                                |     3 |       * | 1 |
                                |       |       3 | 1 |
                                |     4 |       * | 2 |
                                |       |  4 / D2 | 2 |"""
                )
        );
    }

    @ParameterizedTest
    @MethodSource("withTheCheckInOption")
    void processTheCheckInOption(String optionName, String checkInType, String output) {
        String[] args = {"checkouts", optionName, checkInType, "-j", "any", "2", "3"};

        StringWriter out = new StringWriter();
        StringWriter err = new StringWriter();

        DartsApp.create()
                .setOut(new PrintWriter(out))
                .setErr(new PrintWriter(err))
                .execute(args);

        String outString = out.toString();
        String errString = err.toString();

        Assertions.assertAll(
                () -> Assertions.assertTrue(outString.startsWith(output)),
                () -> Assertions.assertTrue(errString.isEmpty())
        );
    }

    private static Stream<Arguments> withTheCheckInOption() {
        return Stream.of(
                Arguments.of(
                        "-i",
                        "AnY",
                        """
                                | Score |       1 | # |
                                |------:|--------:|--:|
                                |     2 |       * | 2 |
                                |       |  2 / D1 | 2 |
                                |     3 |       * | 2 |
                                |       |  3 / T1 | 2 |"""
                ),
                Arguments.of(
                        "--check-in",
                        "master",
                        """
                                | Score |  1 | # |
                                |------:|---:|--:|
                                |     2 |  * | 1 |
                                |       | D1 | 1 |
                                |     3 |  * | 1 |
                                |       | T1 | 1 |"""
                ),
                Arguments.of(
                        "-i",
                        "checktype.DOUBLE",
                        """
                                | Score |  1 |  2 | # |
                                |------:|---:|---:|--:|
                                |     2 |  * |  * | 1 |
                                |       | D1 |  - | 1 |
                                |     3 |  * |  * | 1 |
                                |       | D1 |  1 | 1 |"""
                )
        );
    }

    @ParameterizedTest
    @MethodSource("withTheCheckoutOption")
    void processTheCheckoutOption(String optionName, String checkoutType, String output) {
        String[] args = {"checkouts", optionName, checkoutType, "2", "3"};

        StringWriter out = new StringWriter();
        StringWriter err = new StringWriter();

        DartsApp.create()
                .setOut(new PrintWriter(out))
                .setErr(new PrintWriter(err))
                .execute(args);

        String outString = out.toString();
        String errString = err.toString();

        Assertions.assertAll(
                () -> Assertions.assertTrue(outString.startsWith(output)),
                () -> Assertions.assertTrue(errString.isEmpty())
        );
    }

    private static Stream<Arguments> withTheCheckoutOption() {
        return Stream.of(
                Arguments.of(
                        "-j",
                        "ANY",
                        """
                                | Score |       1 | # |
                                |------:|--------:|--:|
                                |     2 |       * | 2 |
                                |       |  2 / D1 | 2 |
                                |     3 |       * | 2 |
                                |       |  3 / T1 | 2 |"""
                ),
                Arguments.of(
                        "--checkout",
                        "CheckType.MASTER",
                        """
                                | Score |  1 | # |
                                |------:|---:|--:|
                                |     2 |  * | 1 |
                                |       | D1 | 1 |
                                |     3 |  * | 1 |
                                |       | T1 | 1 |"""
                ),
                Arguments.of(
                        "-j",
                        "double",
                        """
                                | Score |  1 |  2 | # |
                                |------:|---:|---:|--:|
                                |     2 |  * |  * | 1 |
                                |       | D1 |  - | 1 |
                                |     3 |  * |  * | 1 |
                                |       |  1 | D1 | 1 |"""
                )
        );
    }

    @ParameterizedTest
    @MethodSource("withTheThrowsOption")
    void processTheThrowsOption(String optionName, String numThrows, String minScore, String maxScore, String output) {
        String[] args = {"checkouts", optionName, numThrows, minScore, maxScore};

        StringWriter out = new StringWriter();
        StringWriter err = new StringWriter();

        DartsApp.create()
                .setOut(new PrintWriter(out))
                .setErr(new PrintWriter(err))
                .execute(args);

        String outString = out.toString();
        String errString = err.toString();

        Assertions.assertAll(
                () -> Assertions.assertTrue(outString.startsWith(output)),
                () -> Assertions.assertTrue(errString.isEmpty())
        );
    }

    private static Stream<Arguments> withTheThrowsOption() {
        return Stream.of(
                Arguments.of(
                        "-n",
                        "1",
                        "23",
                        "23",
                        """
                                | Score | 1 | # |
                                |------:|--:|--:|
                                |    23 | * | 0 |"""
                ),
                Arguments.of(
                        "--throws",
                        "10",
                        "29",
                        "29",
                        """
                                | Score |  1 |  2 |  3 |  4 |  5 |  6 |  7 |  8 |  9 | 10 | # |
                                |------:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|--:|
                                |    29 |  * |  * |  * |  * |  * |  * |  * |  * |  * |  * | 0 |"""
                ),
                Arguments.of(
                        "-n",
                        "1",
                        "22",
                        "23",
                        """
                                | Score |   1 | # |
                                |------:|----:|--:|
                                |    22 |   * | 1 |
                                |       | D11 | 1 |
                                |    23 |   * | 0 |"""
                ),
                Arguments.of(
                        "--throws",
                        "2",
                        "22",
                        "22",
                        """
                                | Score | 1 | 2 | # |
                                |------:|--:|--:|--:|
                                |    22 | * | * | 0 |"""
                ),
                Arguments.of(
                        "--throws",
                        "2",
                        "22",
                        "23",
                        """
                                | Score |         1 |         2 |  # |
                                |------:|----------:|----------:|---:|
                                |    22 |         * |         * |  0 |
                                |    23 |         * |         * | 14 |
                                |       |        T7 |        D1 |  1 |
                                |       |        19 |        D2 |  1 |
                                |       |        17 |        D3 |  1 |
                                |       |  15 /  T5 |        D4 |  2 |
                                |       |        13 |        D5 |  1 |
                                |       |        11 |        D6 |  1 |
                                |       |   9 /  T3 |        D7 |  2 |
                                |       |         7 |        D8 |  1 |
                                |       |         5 |        D9 |  1 |
                                |       |   3 /  T1 |       D10 |  2 |
                                |       |         1 |       D11 |  1 |"""
                )
        );
    }

    @ParameterizedTest
    @MethodSource("withTheModeOption")
    void processTheModeOption(String optionName, String finderMode, String output) {
        String[] args = {"checkouts", "-n", "2", optionName, finderMode, "22", "22"};

        StringWriter out = new StringWriter();
        StringWriter err = new StringWriter();

        DartsApp.create()
                .setOut(new PrintWriter(out))
                .setErr(new PrintWriter(err))
                .execute(args);

        String outString = out.toString();
        String errString = err.toString();

        Assertions.assertAll(
                () -> Assertions.assertTrue(outString.startsWith(output)),
                () -> Assertions.assertTrue(errString.isEmpty())
        );
    }

    private static Stream<Arguments> withTheModeOption() {
        return Stream.of(
                Arguments.of(
                        "-m",
                        "miniMUM",
                        """
                                | Score | 1 | 2 | # |
                                |------:|--:|--:|--:|
                                |    22 | * | * | 0 |"""
                ),
                Arguments.of(
                        "--mode",
                        "FinderMode.ALL",
                        """
                                | Score |               1 |               2 |  # |
                                |------:|----------------:|----------------:|---:|
                                |    22 |               * |               * | 23 |
                                |       |        20 / D10 |              D1 |  2 |
                                |       |  18 /  D9 /  T6 |              D2 |  3 |
                                |       |        16 /  D8 |              D3 |  2 |
                                |       |        14 /  D7 |              D4 |  2 |
                                |       |  12 /  D6 /  T4 |              D5 |  3 |
                                |       |        10 /  D5 |              D6 |  2 |
                                |       |         8 /  D4 |              D7 |  2 |
                                |       |   6 /  D3 /  T2 |              D8 |  3 |
                                |       |         4 /  D2 |              D9 |  2 |
                                |       |         2 /  D1 |             D10 |  2 |"""
                )
        );
    }

    @ParameterizedTest
    @MethodSource("withTheFinderOption")
    void processTheFinderOption(String optionName, String finderType, String output) {
        String[] args = {"checkouts", "-j", "any", optionName, finderType, "2", "2"};

        StringWriter out = new StringWriter();
        StringWriter err = new StringWriter();

        DartsApp.create()
                .setOut(new PrintWriter(out))
                .setErr(new PrintWriter(err))
                .execute(args);

        String outString = out.toString();
        String errString = err.toString();

        Assertions.assertAll(
                () -> Assertions.assertTrue(outString.startsWith(output)),
                () -> Assertions.assertTrue(errString.isEmpty())
        );
    }

    private static Stream<Arguments> withTheFinderOption() {
        return Stream.of(
                Arguments.of(
                        "-f",
                        "findertype.cartesian",
                        """
                                | Score |  1 | # |
                                |------:|---:|--:|
                                |     2 |  * | 2 |
                                |       |  2 | 1 |
                                |       | D1 | 1 |"""
                ),
                Arguments.of(
                        "--finder",
                        "Descending",
                        """
                                | Score |       1 | # |
                                |------:|--------:|--:|
                                |     2 |       * | 2 |
                                |       |  2 / D1 | 2 |"""
                )
        );
    }

    @ParameterizedTest
    @MethodSource("withTheTableOption")
    void processTheTableOption(String optionName, String tableType, String output) {
        String[] args = {"checkouts", optionName, tableType, "1", "2"};

        StringWriter out = new StringWriter();
        StringWriter err = new StringWriter();

        DartsApp.create()
                .setOut(new PrintWriter(out))
                .setErr(new PrintWriter(err))
                .execute(args);

        String outString = out.toString();
        String errString = err.toString();

        Assertions.assertAll(
                () -> Assertions.assertTrue(outString.startsWith(output)),
                () -> Assertions.assertTrue(errString.isEmpty())
        );
    }

    private static Stream<Arguments> withTheTableOption() {
        return Stream.of(
                Arguments.of(
                        "-t",
                        "ascending",
                        """
                                | Score |  1 | # |
                                |------:|---:|--:|
                                |     1 |  * | 0 |
                                |     2 |  * | 1 |
                                |       | D1 | 1 |"""
                ),
                Arguments.of(
                        "--table",
                        "TableType.ASCENDING",
                        """
                                | Score |  1 | # |
                                |------:|---:|--:|
                                |     1 |  * | 0 |
                                |     2 |  * | 1 |
                                |       | D1 | 1 |"""
                )
        );
    }

    @ParameterizedTest
    @MethodSource("withTheOutputOption")
    void processTheOutputOption(String optionName, String outputFormat, String output) {
        String[] args = {"checkouts", optionName, outputFormat, "1", "1"};

        StringWriter out = new StringWriter();
        StringWriter err = new StringWriter();

        DartsApp.create()
                .setOut(new PrintWriter(out))
                .setErr(new PrintWriter(err))
                .execute(args);

        String outString = out.toString();
        String errString = err.toString();

        Assertions.assertAll(
                () -> Assertions.assertTrue(outString.startsWith(output)),
                () -> Assertions.assertTrue(errString.isEmpty())
        );
    }

    private static Stream<Arguments> withTheOutputOption() {
        return Stream.of(
                Arguments.of("-o", "OutputFormat.MARKDOWN", "|"),
                Arguments.of("--output", "html", "<"),
                Arguments.of("-o", "JSoN", "{"),
                Arguments.of("--output", "STRING", "Ascending")
        );
    }

    @Test
    void getATableString() {
        String[] args = {
                "checkouts",
                "-b", "Yorkshire",
                "-i", "master",
                "-j", "master",
                "-f", "Cartesian",
                "-t", "ascending",
                "-o", "string",
                "2",
                "2"
        };

        StringWriter out = new StringWriter();
        StringWriter err = new StringWriter();

        DartsApp.create()
                .setOut(new PrintWriter(out))
                .setErr(new PrintWriter(err))
                .execute(args);

        String outString = out.toString();
        String errString = err.toString();

        List<String> elements = List.of(
                "Table",
                "YORKSHIRE",
                "MASTER",
                "2=",
                "score=2"
        );

        long count = elements.stream()
                .filter(outString::contains)
                .count();

        Assertions.assertAll(
                () -> Assertions.assertEquals(elements.size(), count),
                () -> Assertions.assertTrue(errString.isEmpty())
        );
    }
}
