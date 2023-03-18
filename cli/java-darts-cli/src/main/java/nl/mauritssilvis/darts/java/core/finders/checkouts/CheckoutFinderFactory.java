/*
 * Copyright © 2023 Maurits Silvis
 * SPDX-License-Identifier: GPL-3.0-or-later
 */

package nl.mauritssilvis.darts.java.core.finders.checkouts;

import nl.mauritssilvis.darts.java.api.boards.Field;
import nl.mauritssilvis.darts.java.api.finders.checkouts.CheckoutFinder;
import nl.mauritssilvis.darts.java.api.settings.FinderType;

import java.util.Collection;

/**
 * A checkout finder factory.
 * <p>
 * Relevant design patterns: helper, parameterized static factory method.
 *
 * @author Maurits Silvis
 * @since 0.1.0
 */
public final class CheckoutFinderFactory {
    private CheckoutFinderFactory() {
    }

    /**
     * Returns a new {@code CheckoutFinder} of the specified type with the
     * specified fields per throw.
     *
     * @param finderType     the finder type
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
