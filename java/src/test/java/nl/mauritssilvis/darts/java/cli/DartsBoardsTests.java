/*
 * Copyright © 2023 Maurits H. Silvis
 * SPDX-License-Identifier: GPL-3.0-or-later
 */

package nl.mauritssilvis.darts.java.cli;

import nl.mauritssilvis.darts.java.settings.BoardType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.List;

class DartsBoardsTests {
    @Test
    void getHelp() {
        String[] args = {"help", "boards"};

        StringWriter stringWriter = new StringWriter();

        Darts.create()
                .setOut(new PrintWriter(stringWriter))
                .execute(args);

        String output = stringWriter.toString();

        List<String> elements = List.of(
                "Usage",
                "boards",
                "Print a dartboard.",
                "The output format.",
                "<output format>",
                "Dartboard type(s).",
                "<board type>"
        );

        long count = elements.stream()
                .filter(output::contains)
                .count();

        Assertions.assertEquals(elements.size(), count);
    }

    @ParameterizedTest
    @EnumSource(BoardType.class)
    void getADartboardString(BoardType boardType) {
        String boardName = getBoardName(boardType);
        String[] args = {"boards", "-f", "string", boardName};

        StringWriter stringWriter = new StringWriter();

        Darts.create()
                .setOut(new PrintWriter(stringWriter))
                .execute(args);

        String output = stringWriter.toString();

        Assertions.assertTrue(output.contains(boardName));
    }

    private static String getBoardName(BoardType boardType) {
        String fullName = boardType.toString();
        String shortName = fullName.split("\\.")[1];

        char first = shortName.charAt(0);
        String rest = shortName.substring(1);

        return first + rest.toLowerCase();
    }
}
