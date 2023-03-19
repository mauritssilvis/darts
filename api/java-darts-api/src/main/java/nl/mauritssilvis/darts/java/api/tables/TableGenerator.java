/*
 * Copyright © 2023 Maurits Silvis
 * SPDX-License-Identifier: GPL-3.0-or-later
 */

package nl.mauritssilvis.darts.java.api.tables;

/**
 * A checkout table generator.
 * <p>
 * This functional interface does not fix the checkout table settings.
 * Implementations can specify these properties.
 * <p>
 * Relevant design patterns: strategy, immutable interface.
 *
 * @author Maurits Silvis
 * @since 0.5.0
 */
@FunctionalInterface
public interface TableGenerator {
    /**
     * Generates a checkout table with scores from the specified minimum score
     * to the specified maximum score.
     *
     * @param minScore the minimum score
     * @param maxScore the maximum score
     * @return a checkout table with the specified minimum and maximum scores
     */
    Table generate(int minScore, int maxScore);
}
