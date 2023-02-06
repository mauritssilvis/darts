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

        String expected1 = "Usage";
        String expected2 = "darts";
        String expected3 = "computational toolbox";
        String expected4 = "Copyright";
        String expected5 = "2023";
        String expected6 = "GPL";

        Assertions.assertAll(
                () -> Assertions.assertTrue(actual.contains(expected1)),
                () -> Assertions.assertTrue(actual.contains(expected2)),
                () -> Assertions.assertTrue(actual.contains(expected3)),
                () -> Assertions.assertTrue(actual.contains(expected4)),
                () -> Assertions.assertTrue(actual.contains(expected5)),
                () -> Assertions.assertTrue(actual.contains(expected6))
        );
    }

    @Test
    void getSubCommandHelp() {
        Darts darts = new Darts();
        String[] args = {"help"};

        StringWriter stringWriter = new StringWriter();

        CommandLine commandLine = new CommandLine(darts);
        commandLine.setOut(new PrintWriter(stringWriter));
        commandLine.execute(args);

        String actual = stringWriter.toString();

        String expected1 = "Usage";
        String expected2 = "darts";
        String expected3 = "computational toolbox";
        String expected4 = "Copyright";
        String expected5 = "2023";
        String expected6 = "GPL";

        Assertions.assertAll(
                () -> Assertions.assertTrue(actual.contains(expected1)),
                () -> Assertions.assertTrue(actual.contains(expected2)),
                () -> Assertions.assertTrue(actual.contains(expected3)),
                () -> Assertions.assertTrue(actual.contains(expected4)),
                () -> Assertions.assertTrue(actual.contains(expected5)),
                () -> Assertions.assertTrue(actual.contains(expected6))
        );
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
        String expected3 = "2023";
        String expected4 = "GPL";

        Assertions.assertAll(
                () -> Assertions.assertTrue(actual.contains(expected1)),
                () -> Assertions.assertTrue(actual.contains(expected2)),
                () -> Assertions.assertTrue(actual.contains(expected3)),
                () -> Assertions.assertTrue(actual.contains(expected4))
        );
    }
}
