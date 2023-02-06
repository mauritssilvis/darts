/*
 * Copyright © 2023 Maurits H. Silvis
 * SPDX-License-Identifier: GPL-3.0-or-later
 */

package nl.mauritssilvis.darts.java.boards.output;

import nl.mauritssilvis.darts.java.boards.Board;
import nl.mauritssilvis.darts.java.output.Serializer;

import java.util.regex.Pattern;
import java.util.stream.IntStream;

/**
 * An implementation of the generic {@code Serializer} interface that serializes
 * {@code Board} objects using their Java string representation.
 * <p>
 * Relevant design patterns: strategy, immutable object, static factory method.
 */
public class StringBoardSerializer implements Serializer<Board> {
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

    @Override
    public String serialize(Board object) {
        String str = object.toString();

        StringBuilder stringBuilder = new StringBuilder(str.length());

        int indentation = 0;

        for (char ch : str.toCharArray()) {
            if (ch == ')' || ch == ']') {
                indentation -= 2;
                addNewline(stringBuilder, indentation);
            }

            stringBuilder.append(ch);

            if (ch == '(' || ch == '[') {
                indentation += 2;
                addNewline(stringBuilder, indentation);
            } else if (ch == ',') {
                addNewline(stringBuilder, indentation - 1);
            }
        }

        return PATTERN.matcher(stringBuilder.toString()).replaceAll("\n");
    }

    private static void addNewline(StringBuilder stringBuilder, int indentation) {
        stringBuilder.append('\n');

        IntStream.range(0, indentation)
                .forEach(i -> stringBuilder.append(' '));
    }
}
