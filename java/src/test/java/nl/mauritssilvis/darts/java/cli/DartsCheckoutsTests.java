/*
 * Copyright © 2023 Maurits H. Silvis
 * SPDX-License-Identifier: GPL-3.0-or-later
 */

package nl.mauritssilvis.darts.java.cli;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.List;

class DartsCheckoutsTests {
    @Test
    void getHelp() {
        String[] args = {"help", "checkouts"};

        StringWriter stringWriter = new StringWriter();

        Darts.create()
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
    void getACheckoutTableString() {
        String[] args = {
                "checkouts",
                "-b", "Yorkshire",
                "-i", "master",
                "-j", "master",
                "-f", "Cartesian",
                "-t", "map",
                "-o", "string",
                "1",
                "1"
        };

        StringWriter stringWriter = new StringWriter();

        Darts.create()
                .setOut(new PrintWriter(stringWriter))
                .execute(args);

        String output = stringWriter.toString();

        List<String> elements = List.of(
                "CheckoutTable",
                "YORKSHIRE",
                "MASTER",
                "score=1"
        );

        long count = elements.stream()
                .filter(output::contains)
                .count();

        Assertions.assertEquals(elements.size(), count);
    }
}
