/*
 * Copyright © 2023 Maurits Silvis
 * SPDX-License-Identifier: GPL-3.0-or-later
 */

package nl.mauritssilvis.darts.java.output.pretty;

import nl.mauritssilvis.darts.java.output.Formatter;

import java.util.Collection;

/**
 * An implementation of the {@code Formatter} interface that introduces newlines
 * and indentation in strings based on specified opening and closing brackets,
 * and delimiters.
 * <p>
 * Relevant design patterns: strategy, immutable object, static factory method.
 */
public class PrettyFormatter implements Formatter {
    private PrettyFormatter(
            Collection<Character> openingBrackets,
            Collection<Character> closingBrackets,
            Collection<Character> delimiters,
            int indentationSize
    ) {
    }

    /**
     * Returns a new {@code PrettyFormatter} with the specified properties.
     *
     * @param openingBrackets a collection of opening brackets
     * @param closingBrackets a collection of closing brackets
     * @param delimiters      a collection of delimiters
     * @param indentationSize the indentation size
     * @return a new {@code PrettyFormatter} with the specified properties
     */
    public static Formatter of(
            Collection<Character> openingBrackets,
            Collection<Character> closingBrackets,
            Collection<Character> delimiters,
            int indentationSize
    ) {
        return new PrettyFormatter(openingBrackets, closingBrackets, delimiters, indentationSize);
    }

    @Override
    public String format(String input) {
        return null;
    }
}
