/*
 * Copyright © 2023 Maurits H. Silvis
 * SPDX-License-Identifier: GPL-3.0-or-later
 */

package nl.mauritssilvis.darts.java.tables.output;

import nl.mauritssilvis.darts.java.output.Serializer;
import nl.mauritssilvis.darts.java.settings.OutputFormat;
import nl.mauritssilvis.darts.java.tables.Table;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class TableSerializerFactoryTests {
    @Test
    void getAStringTableSerializer() {
        OutputFormat outputFormat = OutputFormat.STRING;
        Serializer<Table> serializer = TableSerializerFactory.create(outputFormat);

        Assertions.assertTrue(serializer instanceof StringTableSerializer);
    }

    @Test
    void getAMarkdownTableSerializer() {
        OutputFormat outputFormat = OutputFormat.MARKDOWN;
        Serializer<Table> serializer = TableSerializerFactory.create(outputFormat);

        Assertions.assertTrue(serializer instanceof MarkdownTableSerializer);
    }
}
