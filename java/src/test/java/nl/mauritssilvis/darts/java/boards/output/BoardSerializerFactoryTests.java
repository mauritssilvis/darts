/*
 * Copyright © 2023 Maurits H. Silvis
 * SPDX-License-Identifier: GPL-3.0-or-later
 */

package nl.mauritssilvis.darts.java.boards.output;

import nl.mauritssilvis.darts.java.boards.Board;
import nl.mauritssilvis.darts.java.output.Serializer;
import nl.mauritssilvis.darts.java.settings.OutputFormat;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class BoardSerializerFactoryTests {
    @Test
    void getAStringBoardSerializer() {
        OutputFormat outputFormat = OutputFormat.STRING;
        Serializer<Board> serializer = BoardSerializerFactory.create(outputFormat);

        Assertions.assertTrue(serializer instanceof StringBoardSerializer);
    }
}
