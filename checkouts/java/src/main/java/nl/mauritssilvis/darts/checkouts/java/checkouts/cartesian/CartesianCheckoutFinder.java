/*
 * Copyright © 2023 Maurits H. Silvis
 * SPDX-License-Identifier: GPL-3.0-or-later
 */

package nl.mauritssilvis.darts.checkouts.java.checkouts.cartesian;

import nl.mauritssilvis.darts.checkouts.java.boards.Field;
import nl.mauritssilvis.darts.checkouts.java.checkouts.Checkout;
import nl.mauritssilvis.darts.checkouts.java.checkouts.CheckoutFinder;

import java.util.Collection;
import java.util.List;

/**
 * An implementation of the {@code CheckoutFinder} interface that finds
 * checkouts of a specified score by selecting throws from available fields.
 * <p>
 * This implementation finds checkouts by considering all possible combinations
 * of the available fields and returns results in the form of a list of {@code
 * SimpleCheckout} objects.
 * <p>
 * Relevant design patterns: Strategy, immutable object, static factory method.
 */
public final class CartesianCheckoutFinder implements CheckoutFinder {
    private CartesianCheckoutFinder(Collection<? extends Collection<Field>> fieldsPerThrow) {
    }

    /**
     * Returns a new {@code CartesianCheckoutFinder} for the specified fields
     * per throw.
     *
     * @param fieldsPerThrow a collection of available fields per throw
     * @return a new {@code CartesianCheckoutFinder} for the specified fields
     * per throw
     */
    public static CheckoutFinder of(Collection<? extends Collection<Field>> fieldsPerThrow) {
        return new CartesianCheckoutFinder(fieldsPerThrow);
    }

    @Override
    public List<Checkout> find(int score) {
        return null;
    }
}
