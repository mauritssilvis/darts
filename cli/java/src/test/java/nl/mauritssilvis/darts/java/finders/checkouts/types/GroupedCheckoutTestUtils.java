/*
 * Copyright © 2023 Maurits Silvis
 * SPDX-License-Identifier: GPL-3.0-or-later
 */

package nl.mauritssilvis.darts.java.finders.checkouts.types;

import nl.mauritssilvis.darts.java.finders.checkouts.Checkout;
import nl.mauritssilvis.darts.java.finders.checkouts.Throw;

import java.util.Collection;
import java.util.List;

/**
 * A set of testing utilities that create (collections of) fully grouped
 * {@code GroupedCheckout} objects.
 *
 * @author Maurits Silvis
 * @since 0.1.0
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
