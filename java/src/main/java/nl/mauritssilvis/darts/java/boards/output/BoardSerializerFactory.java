/*
 * Copyright © 2023 Maurits H. Silvis
 * SPDX-License-Identifier: GPL-3.0-or-later
 */

package nl.mauritssilvis.darts.java.boards.output;

import nl.mauritssilvis.darts.java.boards.Board;
import nl.mauritssilvis.darts.java.output.Serializer;
import nl.mauritssilvis.darts.java.settings.OutputFormat;

/**
 * A dartboard serializer factory.
 * <p>
 * Relevant design patterns: helper, parameterized static factory method.
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
            case JSON -> throw new UnsupportedOperationException(
                    "Dartboards do not yet support serialization to JSON."
            );
            case HTML -> throw new UnsupportedOperationException(
                    "Dartboards do not yet support serialization to HTML."
            );
        };
    }
}
