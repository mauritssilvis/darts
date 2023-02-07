/*
 * Copyright © 2023 Maurits H. Silvis
 * SPDX-License-Identifier: GPL-3.0-or-later
 */

package nl.mauritssilvis.darts.java.tables.output;

import nl.mauritssilvis.darts.java.output.Serializer;
import nl.mauritssilvis.darts.java.settings.OutputFormat;
import nl.mauritssilvis.darts.java.tables.Table;

/**
 * A dartboard serializer factory.
 * <p>
 * Relevant design patterns: helper, parameterized static factory method.
 */
public final class TableSerializerFactory {
    private TableSerializerFactory() {
    }

    /**
     * Returns a new checkout table {@code Serializer} with the specified output
     * format.
     *
     * @param outputFormat the output format
     * @return a new checkout table {@code Serializer} with the specified output
     * format.
     */
    public static Serializer<Table> create(OutputFormat outputFormat) {
        return switch (outputFormat) {
            case STRING -> StringTableSerializer.create();
            case MARKDOWN -> MarkdownTableSerializer.create();
            case JSON -> JsonTableSerializer.create();
            case HTML -> HtmlTableSerializer.create();
        };
    }
}
