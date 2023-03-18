/*
 * Copyright © 2023 Maurits Silvis
 * SPDX-License-Identifier: GPL-3.0-or-later
 */

package nl.mauritssilvis.darts.java.api.output;

/**
 * A string formatter.
 * <p>
 * Other than requiring a string as input, this functional interface does not
 * fix the formatting properties. Implementations can be used to define these
 * properties.
 * <p>
 * Relevant design patterns: strategy, immutable interface.
 *
 * @author Maurits Silvis
 * @since 0.1.0
 */
@FunctionalInterface
public interface Formatter {
    /**
     * Formats the input string.
     *
     * @param input the input string
     * @return a formatted string
     * @throws IllegalArgumentException if the input string cannot be processed
     */
    String format(String input);
}
