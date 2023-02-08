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
 * An extension of the {@code Serializer} interface that provides a default
 * implementation of serialization of objects using their Java string
 * representation.
 * <p>
 * Relevant design patterns: strategy.
 *
 * @param <T> the type of object to be serialized
 */
public interface StringSerializer<T> extends Serializer<T> {
    /**
     * Detected opening brackets in string formatting.
     */
    List<Character> OPENING_BRACKETS = List.of('{', '[', '(');

    /**
     * Detected closing brackets in string formatting.
     */
    List<Character> CLOSING_BRACKETS = List.of(')', ']', '}');

    /**
     * Detected delimiters in string formatting.
     */
    List<Character> DELIMITERS = Collections.singletonList(',');

    /**
     * Detected double newlines in string formatting.
     */
    Pattern DOUBLE_NEWLINES = Pattern.compile("\\n *\\n");

    @Override
    default String serialize(T object) {
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
