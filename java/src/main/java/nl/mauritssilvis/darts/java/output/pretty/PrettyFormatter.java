/*
 * Copyright © 2023 Maurits Silvis
 * SPDX-License-Identifier: GPL-3.0-or-later
 */

package nl.mauritssilvis.darts.java.output.pretty;

import nl.mauritssilvis.darts.java.output.Formatter;

import java.util.ArrayDeque;
import java.util.Collection;
import java.util.Deque;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.IntStream;

/**
 * An implementation of the {@code Formatter} interface that introduces newlines
 * and indentation in strings based on specified opening and closing brackets,
 * and delimiters.
 * <p>
 * Relevant design patterns: strategy, immutable object, static factory method.
 */
public class PrettyFormatter implements Formatter {
    private final Collection<Character> openingBrackets;
    private final Collection<Character> closingBrackets;
    private final Collection<Character> delimiters;
    private final int indentationSize;
    private final Deque<Character> brackets = new ArrayDeque<>();
    private final Pattern doubleNewlines = Pattern.compile("\\n *\\n");


    private PrettyFormatter(
            Collection<Character> brackets,
            Collection<Character> delimiters,
            int indentationSize
    ) {
        openingBrackets = List.copyOf(brackets);
        closingBrackets = brackets.stream()
                .map(PrettyFormatter::getClosingBracket)
                .toList();

        this.delimiters = List.copyOf(delimiters);
        this.indentationSize = indentationSize;
    }

    /**
     * Returns a new {@code PrettyFormatter} with the specified properties.
     *
     * @param brackets        a collection of opening brackets
     * @param delimiters      a collection of delimiters
     * @param indentationSize the indentation size
     * @return a new {@code PrettyFormatter} with the specified properties
     */
    public static Formatter of(
            Collection<Character> brackets,
            Collection<Character> delimiters,
            int indentationSize
    ) {
        return new PrettyFormatter(brackets, delimiters, indentationSize);
    }

    @Override
    public String format(String input) {
        StringBuilder stringBuilder = new StringBuilder(input.length());

        int indentation = 0;
        int position = 0;

        for (char ch : input.toCharArray()) {
            if (closingBrackets.contains(ch)) {
                if (brackets.isEmpty() || ch != brackets.pop()) {
                    brackets.push('!');
                    break;
                }

                indentation -= indentationSize;
                addNewline(stringBuilder, indentation);
            }

            stringBuilder.append(ch);

            if (openingBrackets.contains(ch)) {
                char closingBracket = getClosingBracket(ch);
                brackets.push(closingBracket);

                indentation += indentationSize;
                addNewline(stringBuilder, indentation);
            } else if (delimiters.contains(ch)) {
                addNewline(stringBuilder, indentation);
            }

            position++;
        }

        if (!brackets.isEmpty()) {
            throw new IllegalArgumentException(
                    "Bracket mismatch at position " + position
            );
        }

        String indentedStr = stringBuilder.toString();

        return doubleNewlines.matcher(indentedStr)
                .replaceAll("\n");
    }

    private static char getClosingBracket(char ch) {
        return switch (ch) {
            case '{' -> '}';
            case '[' -> ']';
            case '(' -> ')';
            case '<' -> '>';
            default -> throw new IllegalArgumentException(
                    "Unsupported bracket '" + ch + "'"
            );
        };
    }

    private static void addNewline(StringBuilder stringBuilder, int indentation) {
        stringBuilder.append('\n');

        IntStream.range(0, indentation)
                .forEach(i -> stringBuilder.append(' '));
    }
}
