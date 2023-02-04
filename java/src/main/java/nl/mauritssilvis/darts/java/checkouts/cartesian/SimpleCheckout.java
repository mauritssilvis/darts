/*
 * Copyright © 2023 Maurits H. Silvis
 * SPDX-License-Identifier: GPL-3.0-or-later
 */

package nl.mauritssilvis.darts.java.checkouts.cartesian;

import lombok.EqualsAndHashCode;
import nl.mauritssilvis.darts.java.boards.Field;
import nl.mauritssilvis.darts.java.checkouts.Checkout;
import nl.mauritssilvis.darts.java.checkouts.Throw;

import java.util.Collection;
import java.util.List;

/**
 * An implementation of the {@code Checkout} interface that represents a single
 * sequence of ungrouped, simple throws.
 * <p>
 * Relevant design patterns: immutable object, static factory method.
 */
@EqualsAndHashCode
public final class SimpleCheckout implements Checkout {
    private final List<Throw> throwList;
    private final int score;

    private SimpleCheckout(Collection<? extends Field> fields) {
        throwList = fields.stream()
                .map(SimpleThrow::of)
                .toList();

        score = throwList.stream()
                .mapToInt(Throw::getScore)
                .sum();
    }

    /**
     * Returns a new {@code SimpleCheckout} of simple throws with the specified
     * fields.
     *
     * @param fields a collection of fields
     * @return a new {@code SimpleCheckout} of simple throws with the specified
     * fields
     */
    public static Checkout of(Collection<? extends Field> fields) {
        return new SimpleCheckout(fields);
    }

    @Override
    public int getScore() {
        return score;
    }

    @Override
    public List<Throw> getThrows() {
        return throwList;
    }

    @Override
    public long getMultiplicity() {
        return throwList.isEmpty() ? 0 : 1;
    }
}
