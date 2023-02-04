/*
 * Copyright © 2023 Maurits H. Silvis
 * SPDX-License-Identifier: GPL-3.0-or-later
 */

package nl.mauritssilvis.darts.java.checkouts.descending;

import nl.mauritssilvis.darts.java.checkouts.Checkout;
import nl.mauritssilvis.darts.java.checkouts.Throw;

import java.util.Collection;
import java.util.List;

/**
 * A set of testing utilities that create (collections of) fully grouped
 * {@code GroupedCheckout} objects.
 */
public final class GroupedCheckoutTestUtils {
    private GroupedCheckoutTestUtils() {
    }

    /**
     * Returns a list of fully grouped checkouts with the specified names.
     *
     * @param namesPerCheckout a collection of names per checkout
     * @return a list of fully grouped checkouts with the specified names
     */
    public static List<Checkout> getCheckouts(
            Collection<? extends Collection<? extends Collection<String>>> namesPerCheckout
    ) {
        return namesPerCheckout.stream()
                .map(CompoundThrowTestUtils::getThrows)
                .map(throwList -> GroupedCheckout.of(throwList, getGrouping(throwList)))
                .toList();
    }

    private static Collection<Boolean> getGrouping(Collection<? extends Throw> throwList) {
        return throwList.stream()
                .map(t -> true)
                .toList();
    }
}
