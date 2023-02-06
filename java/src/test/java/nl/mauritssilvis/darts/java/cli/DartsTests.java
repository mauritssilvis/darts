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
import java.util.List;

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

        List<String> elements = List.of(
                "Usage",
                "darts",
                "A computational toolbox aimed at the game of darts",
                "Copyright",
                "Maurits H. Silvis",
                "SPDX-License-Identifier: GPL-3.0-or-later"
        );

        long count = elements.stream()
                .filter(output::contains)
                .count();

        Assertions.assertEquals(elements.size(), count);
    }

    @Test
    void getVersion() {
        String[] args = {"-V"};

        StringWriter stringWriter = new StringWriter();

        App darts = Darts.create();
        darts.getCommandLine().setOut(new PrintWriter(stringWriter));
        darts.execute(args);

        String output = stringWriter.toString();

        List<String> elements = List.of(
                "darts",
                "Copyright",
                "Maurits H. Silvis",
                "SPDX-License-Identifier: GPL-3.0-or-later"
        );

        long count = elements.stream()
                .filter(output::contains)
                .count();

        Assertions.assertEquals(elements.size(), count);
    }
}
