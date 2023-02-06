/*
 * Copyright © 2023 Maurits H. Silvis
 * SPDX-License-Identifier: GPL-3.0-or-later
 */

package nl.mauritssilvis.darts.java.cli;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import picocli.CommandLine;

import java.io.PrintWriter;
import java.io.StringWriter;

class DartsTests {
    @Test
    void getHelp() {
        Darts darts = new Darts();
        String[] args = {"-h"};

        StringWriter stringWriter = new StringWriter();

        CommandLine commandLine = new CommandLine(darts);
        commandLine.setOut(new PrintWriter(stringWriter));
        commandLine.execute(args);

        String actual = stringWriter.toString();
        String expected = "Usage";

        Assertions.assertTrue(actual.contains(expected));
    }

    @Test
    void getVersion() {
        Darts darts = new Darts();
        String[] args = {"-V"};

        StringWriter stringWriter = new StringWriter();

        CommandLine commandLine = new CommandLine(darts);
        commandLine.setOut(new PrintWriter(stringWriter));
        commandLine.execute(args);

        String actual = stringWriter.toString();

        String expected1 = "darts";
        String expected2 = "Copyright";
        String expected3 = "GPL";

        Assertions.assertAll(
                () -> Assertions.assertTrue(actual.contains(expected1)),
                () -> Assertions.assertTrue(actual.contains(expected2)),
                () -> Assertions.assertTrue(actual.contains(expected3))
        );
    }
}