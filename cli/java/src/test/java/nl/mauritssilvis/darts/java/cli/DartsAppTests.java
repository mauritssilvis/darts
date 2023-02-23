/*
 * Copyright © 2023 Maurits Silvis
 * SPDX-License-Identifier: GPL-3.0-or-later
 */

package nl.mauritssilvis.darts.java.cli;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.io.*;
import java.util.List;

class DartsAppTests {
    @Test
    void startTheApplication() {
        PrintStream standardOut = System.out;
        OutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));

        String[] args = {"-V"};
        Assertions.assertDoesNotThrow(() -> DartsApp.main(args));

        System.setOut(standardOut);
    }

    @Test
    void getHelpWithoutArguments() {
        String[] args = {};

        StringWriter out = new StringWriter();
        StringWriter err = new StringWriter();

        DartsApp.create()
                .setOut(new PrintWriter(out))
                .setErr(new PrintWriter(err))
                .execute(args);

        String outString = out.toString();
        String errString = err.toString();

        Assertions.assertAll(
                () -> Assertions.assertEquals(0, outString.length()),
                () -> Assertions.assertTrue(errString.contains("Usage"))
        );
    }

    @ParameterizedTest
    @ValueSource(strings = {"-h", "--help", "help"})
    void getHelpWithArguments(String arg) {
        String[] args = {arg};

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
                "darts",
                "A computational toolbox aimed at the game of darts",
                "Copyright",
                "Maurits Silvis",
                "SPDX-License-Identifier: GPL-3.0-or-later"
        );

        long count = elements.stream()
                .filter(outString::contains)
                .count();

        Assertions.assertAll(
                () -> Assertions.assertEquals(elements.size(), count),
                () -> Assertions.assertEquals(0, errString.length())
        );
    }

    @ParameterizedTest
    @ValueSource(strings = {"-V", "--version"})
    void getTheVersion(String arg) {
        String[] args = {arg};

        StringWriter out = new StringWriter();
        StringWriter err = new StringWriter();

        DartsApp.create()
                .setOut(new PrintWriter(out))
                .setErr(new PrintWriter(err))
                .execute(args);

        String outString = out.toString();
        String errString = err.toString();

        List<String> elements = List.of(
                "java-darts-cli",
                "Copyright",
                "Maurits Silvis",
                "SPDX-License-Identifier: GPL-3.0-or-later"
        );

        long count = elements.stream()
                .filter(outString::contains)
                .count();

        Assertions.assertAll(
                () -> Assertions.assertEquals(elements.size(), count),
                () -> Assertions.assertEquals(0, errString.length())
        );
    }

    @Test
    void convertToAString() {
        App app = DartsApp.create();
        String str = app.toString();

        Assertions.assertAll(
                () -> Assertions.assertTrue(str.startsWith(app.getClass().getSimpleName())),
                () -> Assertions.assertTrue(str.contains("commandLine"))
        );
    }
}
