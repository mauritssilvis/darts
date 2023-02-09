/*
 * Copyright © 2023 Maurits Silvis
 * SPDX-License-Identifier: GPL-3.0-or-later
 */

package nl.mauritssilvis.darts.java.boards.output;

import nl.mauritssilvis.darts.java.boards.Board;
import nl.mauritssilvis.darts.java.output.Serializer;
import nl.mauritssilvis.darts.java.output.StringSerializer;

/**
 * An implementation of the generic {@code StringSerializer} interface that
 * allows for the creation of objects that can serialize {@code Board} objects
 * using their Java string representation.
 * <p>
 * Relevant design patterns: strategy, immutable object, static factory method.
 */
public final class StringBoardSerializer implements StringSerializer<Board> {
    private StringBoardSerializer() {
    }

    /**
     * Returns a new {@code StringBoardSerializer}.
     *
     * @return a new {@code StringBoardSerializer}
     */
    public static Serializer<Board> create() {
        return new StringBoardSerializer();
    }
}
