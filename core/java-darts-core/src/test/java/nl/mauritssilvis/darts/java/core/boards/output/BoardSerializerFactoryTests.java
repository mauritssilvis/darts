/*
 * Copyright © 2023 Maurits Silvis
 * SPDX-License-Identifier: GPL-3.0-or-later
 */

package nl.mauritssilvis.darts.java.core.boards.output;

import nl.mauritssilvis.darts.java.api.boards.Board;
import nl.mauritssilvis.darts.java.api.output.Serializer;
import nl.mauritssilvis.darts.java.api.settings.OutputFormat;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class BoardSerializerFactoryTests {
    @Test
    void getAStringBoardSerializer() {
        OutputFormat outputFormat = OutputFormat.STRING;
        Serializer<Board> serializer = BoardSerializerFactory.create(outputFormat);

        Assertions.assertTrue(serializer instanceof StringBoardSerializer);
    }

    @Test
    void getAMarkdownBoardSerializer() {
        OutputFormat outputFormat = OutputFormat.MARKDOWN;
        Serializer<Board> serializer = BoardSerializerFactory.create(outputFormat);

        Assertions.assertTrue(serializer instanceof MarkdownBoardSerializer);
    }

    @Test
    void getAJsonBoardSerializer() {
        OutputFormat outputFormat = OutputFormat.JSON;
        Serializer<Board> serializer = BoardSerializerFactory.create(outputFormat);

        Assertions.assertTrue(serializer instanceof JsonBoardSerializer);
    }

    @Test
    void getAnHtmlBoardSerializer() {
        OutputFormat outputFormat = OutputFormat.HTML;
        Serializer<Board> serializer = BoardSerializerFactory.create(outputFormat);

        Assertions.assertTrue(serializer instanceof HtmlBoardSerializer);

    }
}
