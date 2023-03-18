/*
 * Copyright © 2023 Maurits Silvis
 * SPDX-License-Identifier: GPL-3.0-or-later
 */

package nl.mauritssilvis.darts.java.tables.output;

import nl.mauritssilvis.darts.java.api.output.Serializer;
import nl.mauritssilvis.darts.java.api.settings.OutputFormat;
import nl.mauritssilvis.darts.java.api.tables.Table;
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

    @Test
    void getAJsonTableSerializer() {
        OutputFormat outputFormat = OutputFormat.JSON;
        Serializer<Table> serializer = TableSerializerFactory.create(outputFormat);

        Assertions.assertTrue(serializer instanceof JsonTableSerializer);
    }

    @Test
    void getAnHtmlTableSerializer() {
        OutputFormat outputFormat = OutputFormat.HTML;
        Serializer<Table> serializer = TableSerializerFactory.create(outputFormat);

        Assertions.assertTrue(serializer instanceof HtmlTableSerializer);
    }
}
