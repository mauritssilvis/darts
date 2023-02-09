/*
 * Copyright © 2023 Maurits Silvis
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

class DartsAppTests {
    @Test
    void startTheApplication() {
        String[] args = {"-V"};
        Assertions.assertDoesNotThrow(() -> DartsApp.main(args));
    }

    @Test
    void getAnErrorMessageWithoutArguments() {
        String[] args = {};

        StringWriter stringWriter = new StringWriter();

        DartsApp.create()
                .setErr(new PrintWriter(stringWriter))
                .execute(args);

        String output = stringWriter.toString();

        Assertions.assertTrue(output.contains("Usage"));
    }

    @ParameterizedTest
    @ValueSource(strings = {"-h", "help"})
    void getHelp(String arg) {
        String[] args = {arg};

        StringWriter stringWriter = new StringWriter();

        DartsApp.create()
                .setOut(new PrintWriter(stringWriter))
                .execute(args);

        String output = stringWriter.toString();

        List<String> elements = List.of(
                "Usage",
                "darts",
                "A computational toolbox aimed at the game of darts",
                "Copyright",
                "Maurits Silvis",
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

        DartsApp.create()
                .setOut(new PrintWriter(stringWriter))
                .execute(args);

        String output = stringWriter.toString();

        List<String> elements = List.of(
                "darts",
                "Copyright",
                "Maurits Silvis",
                "SPDX-License-Identifier: GPL-3.0-or-later"
        );

        long count = elements.stream()
                .filter(output::contains)
                .count();

        Assertions.assertEquals(elements.size(), count);
    }
}
