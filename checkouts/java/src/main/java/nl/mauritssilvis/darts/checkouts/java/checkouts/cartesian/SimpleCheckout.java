/*
 * Copyright © 2023 Maurits H. Silvis
 * SPDX-License-Identifier: GPL-3.0-or-later
 */

package nl.mauritssilvis.darts.checkouts.java.checkouts.cartesian;

import nl.mauritssilvis.darts.checkouts.java.boards.Field;
import nl.mauritssilvis.darts.checkouts.java.checkouts.Checkout;
import nl.mauritssilvis.darts.checkouts.java.checkouts.Throw;

import java.util.Collection;
import java.util.List;

/**
 * An implementation of the {@code Checkout} interface that represents a single
 * sequence of ungrouped, simple throws.
 * <p>
 * Relevant design patterns: Immutable object, static factory method.
 */
public final class SimpleCheckout implements Checkout {
    private SimpleCheckout(Collection<Field> fields) {
    }

    /**
     * Returns a new {@code SimpleCheckout} of simple throws with the specified
     * fields.
     *
     * @param fields a collection of fields
     * @return a new {@code SimpleCheckout} of simple throws with the specified
     * fields
     */
    public static SimpleCheckout of(Collection<Field> fields) {
        return new SimpleCheckout(fields);
    }

    @Override
    public int getScore() {
        return -1;
    }

    @Override
    public int countThrows() {
        return -1;
    }

    @Override
    public List<Throw> getThrows() {
        return null;
    }

    @Override
    public long getMultiplicity() {
        return -1;
    }
}
