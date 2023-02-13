/*
 * Copyright © 2023 Maurits Silvis
 * SPDX-License-Identifier: GPL-3.0-or-later
 */

package nl.mauritssilvis.darts.java.finders.paths.types;

import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.Collection;
import java.util.List;

/**
 * An implementation of the {@code Node} interface that stores unique edge
 * weights in the order in which they are supplied.
 * <p>
 * Relevant design patterns: immutable object, static factory method.
 */
@EqualsAndHashCode
@ToString
public final class BasicNode implements Node {
    private final List<Integer> weights;

    private BasicNode(Collection<Integer> weights) {
        this.weights = weights.stream()
                .distinct()
                .toList();
    }

    /**
     * Returns a new {@code BasicNode} with unique edge weights in the order
     * determined by the supplied collection.
     *
     * @param weights a collection of weights
     * @return a new {@code BasicNode} with the specified weights
     */
    public static Node of(Collection<Integer> weights) {
        return new BasicNode(weights);
    }

    @Override
    public List<Integer> getWeights() {
        return weights;
    }

    @Override
    @ToString.Include(name = "connected")
    public boolean isConnected() {
        return !weights.isEmpty();
    }
}
