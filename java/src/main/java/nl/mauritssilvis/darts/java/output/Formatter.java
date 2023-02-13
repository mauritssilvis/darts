/*
 * Copyright © 2023 Maurits Silvis
 * SPDX-License-Identifier: GPL-3.0-or-later
 */

package nl.mauritssilvis.darts.java.output;

/**
 * A string formatter.
 * <p>
 * Other than requiring a string as input, this functional interface does not
 * fix the formatting properties. Implementations can be used to define these
 * properties.
 * <p>
 * Relevant design patterns: strategy, immutable interface.
 */
@FunctionalInterface
public interface Formatter {
    /**
     * Formats the input string.
     *
     * @param input the input string
     * @return a formatted string
     */
    String format(String input);
}
