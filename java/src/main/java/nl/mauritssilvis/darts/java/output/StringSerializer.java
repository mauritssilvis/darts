/*
 * Copyright © 2023 Maurits H. Silvis
 * SPDX-License-Identifier: GPL-3.0-or-later
 */

package nl.mauritssilvis.darts.java.output;

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
    private static final Pattern PATTERN = Pattern.compile("\\n *\\n");

    @Override
    public String serialize(T object) {
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
