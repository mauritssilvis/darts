/*
 * Copyright © 2023 Maurits H. Silvis
 * SPDX-License-Identifier: GPL-3.0-or-later
 */

package nl.mauritssilvis.darts.java.boards.output;

import nl.mauritssilvis.darts.java.boards.Board;
import nl.mauritssilvis.darts.java.output.Serializer;

/**
 * An implementation of the generic {@code Serializer} interface that serializes
 * {@code Board} objects to HTML.
 * <p>
 * Relevant design patterns: strategy, immutable object, static factory method.
 */
public final class HtmlBoardSerializer implements Serializer<Board> {
    private HtmlBoardSerializer() {
    }

    /**
     * Returns a new {@code HtmlBoardSerializer}.
     *
     * @return a new {@code HtmlBoardSerializer}
     */
    public static Serializer<Board> create() {
        return new HtmlBoardSerializer();
    }

    @Override
    public String serialize(Board object) {
        throw new UnsupportedOperationException(
                "Dartboards do not yet support serialization to HTML."
        );
    }
}
