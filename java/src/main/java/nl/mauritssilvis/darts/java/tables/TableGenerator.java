/*
 * Copyright © 2023 Maurits H. Silvis
 * SPDX-License-Identifier: GPL-3.0-or-later
 */

package nl.mauritssilvis.darts.java.tables;

/**
 * A checkout table generator.
 * <p>
 * This functional interface does not fix the dartboard, check-in or checkout
 * types. Implementations can specify these properties.
 * <p>
 * Relevant design patterns: strategy, immutable interface.
 */
@FunctionalInterface
public interface TableGenerator {
    /**
     * Generates a table with checkouts with scores from the specified minimal
     * score to the specified maximal score.
     *
     * @param minScore the minimal score
     * @param maxScore the maximal score
     * @return a checkout table with the specified minimal and maximal scores
     */
    Table generate(int minScore, int maxScore);
}
