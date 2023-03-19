/*
 * Copyright © 2023 Maurits Silvis
 * SPDX-License-Identifier: GPL-3.0-or-later
 */

package nl.mauritssilvis.darts.java.core.finders.checkouts;

import lombok.EqualsAndHashCode;
import lombok.ToString;
import nl.mauritssilvis.darts.java.api.boards.Field;
import nl.mauritssilvis.darts.java.api.finders.checkouts.Checkout;
import nl.mauritssilvis.darts.java.api.finders.checkouts.Throw;

import java.util.Collection;
import java.util.List;

/**
 * An implementation of the {@code Checkout} interface that represents a single
 * sequence of ungrouped, simple throws.
 * <p>
 * Relevant design patterns: immutable object, static factory method.
 *
 * @author Maurits Silvis
 * @since 0.1.0
 */
@EqualsAndHashCode
@ToString
final class SimpleCheckout implements Checkout {
    private final int score;
    private final List<Throw> throwList;

    private SimpleCheckout(Collection<? extends Field> fields) {
        throwList = fields.stream()
                .map(SimpleThrow::of)
                .toList();

        score = throwList.stream()
                .mapToInt(Throw::getScore)
                .sum();
    }

    /**
     * Returns a new {@code SimpleCheckout} with simple throws of the specified
     * fields.
     *
     * @param fields a collection of fields
     * @return a new {@code SimpleCheckout} with simple throws of the specified
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
    @ToString.Include(name = "multiplicity")
    public long getMultiplicity() {
        return throwList.isEmpty() ? 0 : 1;
    }
}
