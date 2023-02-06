/*
 * Copyright © 2023 Maurits H. Silvis
 * SPDX-License-Identifier: GPL-3.0-or-later
 */

package nl.mauritssilvis.darts.java.cli;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import picocli.CommandLine;

import java.io.PrintWriter;
import java.io.StringWriter;

class DartsTests {
    @ParameterizedTest
    @ValueSource(strings = {"-h", "help"})
    void getHelp(String arg) {
        Darts darts = new Darts();
        String[] args = {arg};

        StringWriter stringWriter = new StringWriter();

        CommandLine commandLine = new CommandLine(darts);
        commandLine.setOut(new PrintWriter(stringWriter));
        commandLine.execute(args);

        String actual = stringWriter.toString();

        String expected1 = "Usage";
        String expected2 = "darts";
        String expected3 = "A computational toolbox aimed at the game of darts";
        String expected4 = "Copyright © 2023 Maurits H. Silvis";
        String expected5 = "SPDX-License-Identifier: GPL-3.0-or-later";

        Assertions.assertAll(
                () -> Assertions.assertTrue(actual.contains(expected1)),
                () -> Assertions.assertTrue(actual.contains(expected2)),
                () -> Assertions.assertTrue(actual.contains(expected3)),
                () -> Assertions.assertTrue(actual.contains(expected4)),
                () -> Assertions.assertTrue(actual.contains(expected5))
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
        String expected2 = "Copyright © 2023 Maurits H. Silvis";
        String expected3 = "SPDX-License-Identifier: GPL-3.0-or-later";

        Assertions.assertAll(
                () -> Assertions.assertTrue(actual.contains(expected1)),
                () -> Assertions.assertTrue(actual.contains(expected2)),
                () -> Assertions.assertTrue(actual.contains(expected3))
        );
    }
}
