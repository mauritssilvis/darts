/*
 * Copyright © 2023 Maurits Silvis
 * SPDX-License-Identifier: GPL-3.0-or-later
 */

package nl.mauritssilvis.darts.java.cli;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.List;

class CheckoutsCommandTests {
    @Test
    void getHelp() {
        String[] args = {"help", "checkouts"};

        StringWriter stringWriter = new StringWriter();

        DartsApp.create()
                .setOut(new PrintWriter(stringWriter))
                .execute(args);

        String output = stringWriter.toString();

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
                .filter(output::contains)
                .count();

        Assertions.assertEquals(elements.size(), count);
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

        StringWriter stringWriter = new StringWriter();

        DartsApp.create()
                .setOut(new PrintWriter(stringWriter))
                .execute(args);

        String output = stringWriter.toString();

        List<String> elements = List.of(
                "Table",
                "YORKSHIRE",
                "MASTER",
                "2=",
                "score=2"
        );

        long count = elements.stream()
                .filter(output::contains)
                .count();

        Assertions.assertEquals(elements.size(), count);
    }
}
