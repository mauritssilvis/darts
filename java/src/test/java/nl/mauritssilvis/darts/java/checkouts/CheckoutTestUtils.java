/*
 * Copyright © 2023 Maurits H. Silvis
 * SPDX-License-Identifier: GPL-3.0-or-later
 */

package nl.mauritssilvis.darts.java.checkouts;

import java.util.Collection;
import java.util.List;

/**
 * A set of testing utilities for processing collections of {@code Checkout}
 * objects.
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
     * Gets the throws of the specified checkouts.
     *
     * @param checkouts a collection of checkouts
     * @return a list of the lists of throws of the specified checkouts
     */
    public static List<List<Throw>> getAllThrows(Collection<? extends Checkout> checkouts) {
        return checkouts.stream()
                .map(Checkout::getThrows)
                .toList();
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
}
