/*
 * Copyright © 2023 Maurits Silvis
 * SPDX-License-Identifier: GPL-3.0-or-later
 */

package nl.mauritssilvis.darts.java.boards.output;

import nl.mauritssilvis.darts.java.boards.Board;
import nl.mauritssilvis.darts.java.output.Formatter;
import nl.mauritssilvis.darts.java.output.Serializer;
import nl.mauritssilvis.darts.java.output.pretty.PrettyFormatter;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * An implementation of the generic {@code Serializer} interface that serializes
 * {@code Board} objects using their Java string representation.
 * <p>
 * Relevant design patterns: strategy, immutable object, static factory method.
 */
public final class StringBoardSerializer implements Serializer<Board> {
    private static final int indentationSize = 2;
    private final Collection<Character> brackets = List.of('{', '[', '(');
    private final Collection<Character> delimiters = Collections.singleton(',');
    private final Formatter formatter = PrettyFormatter.of(brackets, delimiters, indentationSize);

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

    @Override
    public String serialize(Board object) {
        return formatter.format(object.toString());
    }
}
