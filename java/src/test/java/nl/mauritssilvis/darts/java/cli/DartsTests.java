/*
 * Copyright © 2023 Maurits H. Silvis
 * SPDX-License-Identifier: GPL-3.0-or-later
 */

package nl.mauritssilvis.darts.java.cli;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.io.PrintWriter;
import java.io.StringWriter;

class DartsTests {
    @Test
    void startTheApplication() {
        String[] args = {"-V"};
        Assertions.assertDoesNotThrow(() -> Darts.main(args));
    }

    @ParameterizedTest
    @ValueSource(strings = {"-h", "help"})
    void getHelp(String arg) {
        String[] args = {arg};

        StringWriter stringWriter = new StringWriter();

        App darts = Darts.create();
        darts.getCommandLine().setOut(new PrintWriter(stringWriter));
        darts.execute(args);

        String output = stringWriter.toString();

        String expected1 = "Usage";
        String expected2 = "darts";
        String expected3 = "A computational toolbox aimed at the game of darts";
        String expected4 = "Copyright";
        String expected5 = "Maurits H. Silvis";
        String expected6 = "SPDX-License-Identifier: GPL-3.0-or-later";

        Assertions.assertAll(
                () -> Assertions.assertTrue(output.contains(expected1)),
                () -> Assertions.assertTrue(output.contains(expected2)),
                () -> Assertions.assertTrue(output.contains(expected3)),
                () -> Assertions.assertTrue(output.contains(expected4)),
                () -> Assertions.assertTrue(output.contains(expected5)),
                () -> Assertions.assertTrue(output.contains(expected6))
        );
    }

    @Test
    void getVersion() {
        String[] args = {"-V"};

        StringWriter stringWriter = new StringWriter();

        App darts = Darts.create();
        darts.getCommandLine().setOut(new PrintWriter(stringWriter));
        darts.execute(args);

        String output = stringWriter.toString();

        String expected1 = "darts";
        String expected2 = "Copyright";
        String expected3 = "Maurits H. Silvis";
        String expected4 = "SPDX-License-Identifier: GPL-3.0-or-later";

        Assertions.assertAll(
                () -> Assertions.assertTrue(output.contains(expected1)),
                () -> Assertions.assertTrue(output.contains(expected2)),
                () -> Assertions.assertTrue(output.contains(expected3)),
                () -> Assertions.assertTrue(output.contains(expected4))
        );
    }
}
