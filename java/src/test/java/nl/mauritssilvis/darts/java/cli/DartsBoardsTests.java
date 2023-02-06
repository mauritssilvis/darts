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

class DartsBoardsTests {
    @Test
    void getHelp() {
        Darts darts = new Darts();
        String[] args = {"help", "boards"};

        StringWriter stringWriter = new StringWriter();

        CommandLine commandLine = new CommandLine(darts);
        commandLine.setOut(new PrintWriter(stringWriter));
        commandLine.execute(args);

        String actual = stringWriter.toString();

        String expected1 = "Usage";
        String expected2 = "boards";
        String expected3 = "Print a dartboard.";
        String expected4 = "Dartboard type(s).";
        String expected5 = "<board type>";

        Assertions.assertAll(
                () -> Assertions.assertTrue(actual.contains(expected1)),
                () -> Assertions.assertTrue(actual.contains(expected2)),
                () -> Assertions.assertTrue(actual.contains(expected3)),
                () -> Assertions.assertTrue(actual.contains(expected4)),
                () -> Assertions.assertTrue(actual.contains(expected5))
        );
    }
}
