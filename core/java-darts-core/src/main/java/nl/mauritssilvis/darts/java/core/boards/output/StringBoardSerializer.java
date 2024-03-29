/*
 * Copyright © 2023 Maurits Silvis
 * SPDX-License-Identifier: GPL-3.0-or-later
 */

package nl.mauritssilvis.darts.java.core.boards.output;

import lombok.EqualsAndHashCode;
import lombok.ToString;
import nl.mauritssilvis.darts.java.api.boards.Board;
import nl.mauritssilvis.darts.java.api.output.Formatter;
import nl.mauritssilvis.darts.java.api.output.Serializer;
import nl.mauritssilvis.darts.java.core.output.PrettyFormatter;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * An implementation of the generic {@code Serializer} interface that serializes
 * {@code Board} objects using their Java string representation.
 * <p>
 * Relevant design patterns: strategy, immutable object, simple factory.
 *
 * @author Maurits Silvis
 * @since 0.5.0
 */
@EqualsAndHashCode
@ToString
final class StringBoardSerializer implements Serializer<Board> {
    private final Formatter formatter;

    private StringBoardSerializer() {
        int indentationSize = 2;
        Collection<Character> brackets = List.of('{', '[', '(');
        Collection<Character> delimiters = Collections.singleton(',');

        formatter = PrettyFormatter.of(brackets, delimiters, indentationSize);
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
