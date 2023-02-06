/*
 * Copyright © 2023 Maurits H. Silvis
 * SPDX-License-Identifier: GPL-3.0-or-later
 */

package nl.mauritssilvis.darts.java.boards.output;

import nl.mauritssilvis.darts.java.boards.Board;
import nl.mauritssilvis.darts.java.output.Serializer;
import nl.mauritssilvis.darts.java.output.StringSerializer;

import java.util.regex.Pattern;

/**
 * An implementation of the generic {@code Serializer} interface that serializes
 * {@code Board} objects using their Java string representation.
 * <p>
 * Relevant design patterns: strategy, immutable object, static factory method.
 */
public final class StringBoardSerializer extends StringSerializer<Board> {
    private static final Pattern PATTERN = Pattern.compile("\\n *\\n");

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
