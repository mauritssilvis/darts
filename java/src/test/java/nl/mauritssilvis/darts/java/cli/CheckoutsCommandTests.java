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
