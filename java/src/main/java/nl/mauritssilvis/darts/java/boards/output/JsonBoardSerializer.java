/*
 * Copyright © 2023 Maurits H. Silvis
 * SPDX-License-Identifier: GPL-3.0-or-later
 */

package nl.mauritssilvis.darts.java.boards.output;

import nl.mauritssilvis.darts.java.boards.Board;
import nl.mauritssilvis.darts.java.output.Serializer;

/**
 * An implementation of the generic {@code Serializer} interface that serializes
 * {@code Board} objects to JSON.
 * <p>
 * Relevant design patterns: strategy, immutable object, static factory method.
 */
public final class JsonBoardSerializer implements Serializer<Board> {
    private JsonBoardSerializer() {
    }

    /**
     * Returns a new {@code JsonBoardSerializer}.
     *
     * @return a new {@code JsonBoardSerializer}
     */
    public static Serializer<Board> create() {
        return new JsonBoardSerializer();
    }

    @Override
    public String serialize(Board object) {
        throw new UnsupportedOperationException(
                "Dartboards do not yet support serialization to JSON."
        );
    }
}
