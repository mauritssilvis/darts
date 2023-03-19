/*
 * Copyright © 2023 Maurits Silvis
 * SPDX-License-Identifier: GPL-3.0-or-later
 */

package nl.mauritssilvis.darts.java.core.boards.output;

import nl.mauritssilvis.darts.java.api.boards.Board;
import nl.mauritssilvis.darts.java.api.output.Serializer;
import nl.mauritssilvis.darts.java.api.settings.OutputFormat;

/**
 * A dartboard serializer factory.
 * <p>
 * Relevant design patterns: helper, parameterized static factory method.
 *
 * @author Maurits Silvis
 * @since 0.5.0
 */
public final class BoardSerializerFactory {
    private BoardSerializerFactory() {
    }

    /**
     * Returns a new dartboard {@code Serializer} with the specified output
     * format.
     *
     * @param outputFormat the output format
     * @return a new dartboard {@code Serializer} with the specified output
     * format.
     */
    public static Serializer<Board> create(OutputFormat outputFormat) {
        return switch (outputFormat) {
            case STRING -> StringBoardSerializer.create();
            case MARKDOWN -> MarkdownBoardSerializer.create();
            case JSON -> JsonBoardSerializer.create();
            case HTML -> HtmlBoardSerializer.create();
        };
    }
}
