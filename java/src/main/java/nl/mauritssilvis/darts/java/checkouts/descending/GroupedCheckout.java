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
 * An implementation of the {@code Checkout} interface that represents multiple
 * sequence of throws, namely, one for each permutation of the grouped throws.
 * <p>
 * Relevant design patterns: Immutable object, static factory method.
 */
public final class GroupedCheckout implements Checkout {
    private final List<Throw> throwList;
    private final List<Boolean> grouping;
    private final int score;

    private GroupedCheckout(Collection<Throw> throwList, Collection<Boolean> grouping) {
        this.throwList = null;
        this.grouping = null;
        score = -1;
    }

    /**
     * Returns a new {@code GroupedCheckout} with the specified throws and
     * grouping signature.
     *
     * @param throwList a collection of throws
     * @param grouping  a collection of booleans representing the grouping
     *                  signature
     * @return a new {@code GroupedCheckout} with the specified throws and
     * grouping signature
     */
    public static Checkout of(Collection<Throw> throwList, Collection<Boolean> grouping) {
        return new GroupedCheckout(throwList, grouping);
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
        return throwList;
    }

    @Override
    public long getMultiplicity() {
        return -1;
    }
}
