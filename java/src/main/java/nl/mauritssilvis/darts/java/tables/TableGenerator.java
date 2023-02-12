/*
 * Copyright © 2023 Maurits Silvis
 * SPDX-License-Identifier: GPL-3.0-or-later
 */

package nl.mauritssilvis.darts.java.tables;

/**
 * A checkout table generator.
 * <p>
 * This functional interface does not fix the dartboard type, or check-in and
 * checkout modes. Implementations can specify these properties.
 * <p>
 * Relevant design patterns: strategy, immutable interface.
 */
@FunctionalInterface
public interface TableGenerator {
    /**
     * Generates a table with checkouts with scores from the specified minimum
     * score to the specified maximum score.
     *
     * @param minScore the minimum score
     * @param maxScore the maximum score
     * @return a checkout table with the specified minimum and maximum scores
     */
    Table generate(int minScore, int maxScore);
}
