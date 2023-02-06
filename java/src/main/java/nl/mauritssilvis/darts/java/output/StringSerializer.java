/*
 * Copyright © 2023 Maurits H. Silvis
 * SPDX-License-Identifier: GPL-3.0-or-later
 */

package nl.mauritssilvis.darts.java.output;

import java.util.Collections;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.IntStream;

/**
 * An abstract class that provides a default implementation of the
 * {@code Serializer} interface for serialization of objects using their Java
 * string representation.
 *
 * @param <T> the type of object to be serialized
 */
public abstract class StringSerializer<T> implements Serializer<T> {
    private static final List<Character> OPENING_BRACKETS = List.of('(', '[');
    private static final List<Character> CLOSING_BRACKETS = List.of(')', ']');
    private static final List<Character> DELIMITERS = Collections.singletonList(',');
    private static final Pattern DOUBLE_NEWLINES = Pattern.compile("\\n *\\n");

    @Override
    public String serialize(T object) {
        String str = object.toString();

        StringBuilder stringBuilder = new StringBuilder(str.length());

        int indentation = 0;

        for (char ch : str.toCharArray()) {
            if (CLOSING_BRACKETS.contains(ch)) {
                indentation -= 2;
                addNewline(stringBuilder, indentation);
            }

            stringBuilder.append(ch);

            if (OPENING_BRACKETS.contains(ch)) {
                indentation += 2;
                addNewline(stringBuilder, indentation);
            } else if (DELIMITERS.contains(ch)) {
                addNewline(stringBuilder, indentation - 1);
            }
        }

        String indentedStr = stringBuilder.toString();

        return DOUBLE_NEWLINES.matcher(indentedStr)
                .replaceAll("\n");
    }

    private static void addNewline(StringBuilder stringBuilder, int indentation) {
        stringBuilder.append('\n');

        IntStream.range(0, indentation)
                .forEach(i -> stringBuilder.append(' '));
    }
}
