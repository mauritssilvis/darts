/*
 * Copyright © 2023 Maurits Silvis
 * SPDX-License-Identifier: GPL-3.0-or-later
 */

package nl.mauritssilvis.darts.java.api.finders.checkouts;

import java.util.Collection;
import java.util.List;

/**
 * A set of testing utilities for processing collections of {@code Checkout}
 * objects.
 *
 * @author Maurits Silvis
 * @since 0.5.0
 */
public final class CheckoutTestUtils {
    private CheckoutTestUtils() {
    }

    /**
     * Gets the total score of the specified checkouts.
     *
     * @param checkouts a collection of checkouts
     * @return the total score of the specified checkouts
     */
    public static int getTotalScore(Collection<? extends Checkout> checkouts) {
        return checkouts.stream()
                .mapToInt(Checkout::getScore)
                .sum();
    }

    /**
     * Gets the average score of the specified checkouts.
     *
     * @param checkouts a collection of checkouts
     * @return the average score of the specified checkouts
     */
    public static int getAvgScore(Collection<? extends Checkout> checkouts) {
        return getTotalScore(checkouts) / checkouts.size();
    }

    /**
     * Gets the total multiplicity of the specified checkouts.
     *
     * @param checkouts a collection of checkouts
     * @return the total multiplicity of the specified checkouts
     */
    public static long getTotalMultiplicity(Collection<? extends Checkout> checkouts) {
        return checkouts.stream()
                .mapToLong(Checkout::getMultiplicity)
                .sum();
    }

    /**
     * Gets the names of the fields of the specified checkouts.
     *
     * @param checkouts a collection of checkouts
     * @return a list of the field names per checkout
     */
    public static List<List<List<String>>> getAllNames(Collection<? extends Checkout> checkouts) {
        return checkouts.stream()
                .map(Checkout::getThrows)
                .map(ThrowTestUtils::getAllNames)
                .toList();
    }
}
