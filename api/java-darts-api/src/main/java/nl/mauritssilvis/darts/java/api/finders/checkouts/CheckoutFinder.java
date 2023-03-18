/*
 * Copyright © 2023 Maurits Silvis
 * SPDX-License-Identifier: GPL-3.0-or-later
 */

package nl.mauritssilvis.darts.java.api.finders.checkouts;

import java.util.List;

/**
 * A tool to find darts checkouts of a certain score.
 * <p>
 * This functional interface does not fix the number of throws or the dartboard
 * fields that are available per throw. Implementations can be used to specify
 * these properties.
 * <p>
 * Relevant design patterns: strategy, immutable interface.
 *
 * @author Maurits Silvis
 * @since 0.1.0
 */
@FunctionalInterface
public interface CheckoutFinder {
    /**
     * Finds checkouts having a specified score.
     *
     * @param score the target checkout score
     * @return a list of checkouts having the specified score
     */
    List<Checkout> find(int score);
}
