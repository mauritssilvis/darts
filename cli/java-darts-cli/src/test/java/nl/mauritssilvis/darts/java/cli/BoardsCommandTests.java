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
import org.junit.jupiter.params.provider.ValueSource;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.List;
import java.util.stream.Stream;

class BoardsCommandTests {
    @Test
    void getHelpWithoutArguments() {
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
                () -> Assertions.assertEquals(0, outString.length()),
                () -> Assertions.assertTrue(errString.contains("Usage"))
        );
    }

    @ParameterizedTest
    @MethodSource("withHelpRequests")
    void getHelpWithArguments(String command, String argument) {
        String[] args = {command, argument};

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
                "<board>",
                "Copyright ©",
                "Maurits Silvis",
                "SPDX-License-Identifier: GPL-3.0-or-later"
        );

        long count = elements.stream()
                .filter(outString::contains)
                .count();

        Assertions.assertAll(
                () -> Assertions.assertEquals(elements.size(), count),
                () -> Assertions.assertEquals(0, errString.length())
        );
    }

    private static Stream<Arguments> withHelpRequests() {
        return Stream.of(
                Arguments.of("help", "boards"),
                Arguments.of("boards", "-h"),
                Arguments.of("boards", "--help")
        );
    }

    @ParameterizedTest
    @ValueSource(strings = {"-V", "--version"})
    void getTheVersion(String arg) {
        String[] args = {"boards", arg};

        StringWriter out = new StringWriter();
        StringWriter err = new StringWriter();

        DartsApp.create()
                .setOut(new PrintWriter(out))
                .setErr(new PrintWriter(err))
                .execute(args);

        String outString = out.toString();
        String errString = err.toString();

        List<String> elements = List.of(
                "java-darts-cli",
                "Copyright ©",
                "Maurits Silvis",
                "SPDX-License-Identifier: GPL-3.0-or-later"
        );

        long count = elements.stream()
                .filter(outString::contains)
                .count();

        Assertions.assertAll(
                () -> Assertions.assertEquals(elements.size(), count),
                () -> Assertions.assertEquals(0, errString.length())
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
                () -> Assertions.assertEquals(0, errString.length())
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
                () -> Assertions.assertEquals(0, errString.length())
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

    @Test
    void convertToAString() {
        Runnable runnable = new BoardsCommand();
        String str = runnable.toString();

        Assertions.assertAll(
                () -> Assertions.assertTrue(str.startsWith(runnable.getClass().getSimpleName())),
                () -> Assertions.assertTrue(str.contains("commandSpec")),
                () -> Assertions.assertTrue(str.contains("outputFormat")),
                () -> Assertions.assertTrue(str.contains("boardType"))
        );
    }
}
