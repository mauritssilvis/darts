/*
 * Copyright © 2023 Maurits H. Silvis
 * SPDX-License-Identifier: GPL-3.0-or-later
 */

package nl.mauritssilvis.darts.java.checkouts.factory;

import nl.mauritssilvis.darts.java.boards.Field;
import nl.mauritssilvis.darts.java.checkouts.CheckoutFinder;
import nl.mauritssilvis.darts.java.checkouts.cartesian.CartesianCheckoutFinder;
import nl.mauritssilvis.darts.java.checkouts.descending.DescendingCheckoutFinder;
import nl.mauritssilvis.darts.java.settings.FinderType;

import java.util.Collection;

/**
 * A checkout finder factory.
 * <p>
 * Relevant design patterns: parameterized static factory method.
 */
public final class CheckoutFinderFactory {
    private CheckoutFinderFactory() {
    }

    /**
     * Returns a new {@code CheckoutFinder} of the specified type with the
     * specified fields per throw.
     *
     * @param finderType     the checkout finder type
     * @param fieldsPerThrow a collection of available fields per throw
     * @return a new {@code CheckoutFinder} of the specified type with the
     * specified fields per throw
     */
    public static CheckoutFinder create(
            FinderType finderType, Collection<? extends Collection<? extends Field>> fieldsPerThrow
    ) {
        return switch (finderType) {
            case CARTESIAN -> CartesianCheckoutFinder.of(fieldsPerThrow);
            case DESCENDING -> DescendingCheckoutFinder.of(fieldsPerThrow);
        };
    }
}
