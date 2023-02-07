/*
 * Copyright © 2023 Maurits H. Silvis
 * SPDX-License-Identifier: GPL-3.0-or-later
 */

package nl.mauritssilvis.darts.java.boards.output;

import nl.mauritssilvis.darts.java.boards.Board;
import nl.mauritssilvis.darts.java.output.Serializer;

/**
 * An implementation of the generic {@code Serializer} interface that serializes
 * {@code Board} objects to Markdown.
 * <p>
 * Relevant design patterns: strategy, immutable object, static factory method.
 */
public class MarkdownBoardSerializer implements Serializer<Board> {
    private MarkdownBoardSerializer() {
    }

    /**
     * Returns a new {@code MarkdownBoardSerializer}.
     *
     * @return a new {@code MarkdownBoardSerializer}
     */
    public static Serializer<Board> create() {
        return new MarkdownBoardSerializer();
    }

    @Override
    public String serialize(Board object) {
        return null;
    }
}
