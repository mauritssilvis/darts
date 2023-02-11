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

class BoardsCommandTests {
    @Test
    void getAnErrorMessage() {
        String[] args = {"boards"};

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
        String[] args = {"help", "boards"};

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
                "boards",
                "Print a dartboard.",
                "The output format.",
                "<output>",
                "The dartboard type.",
                "<board>"
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
    @MethodSource("withTheBoardParameter")
    void processTheBoardParameter(String boardType, String output) {
        String[] args = {"boards", boardType};

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

    private static Stream<Arguments> withTheBoardParameter() {
        return Stream.of(
                Arguments.of("london", "|   S |   D |   T |\n|"),
                Arguments.of("Quadro", "|   S |   D |   T |   Q |\n|"),
                Arguments.of("boardType.YorkshirE", "|   S |   D |\n|")
        );
    }

    @ParameterizedTest
    @MethodSource("withTheOutputOption")
    void processTheOutputOption(String optionName, String outputFormat, String boardType, String output) {
        String[] args = {"boards", optionName, outputFormat, boardType};

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
                Arguments.of("-o", "markDOWN", "London", "|"),
                Arguments.of("--output", "HTML", "Quadro", "<"),
                Arguments.of("-o", "json", "Yorkshire", "{"),
                Arguments.of("--output", "OutputFormat.strIng", "London", "London")
        );
    }
}
