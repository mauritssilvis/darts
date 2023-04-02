/*
 * Copyright © 2023 Maurits Silvis
 * SPDX-License-Identifier: GPL-3.0-or-later
 */

package nl.mauritssilvis.darts.java.core.finders.paths;

import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.Collection;
import java.util.Comparator;
import java.util.List;

/**
 * An implementation of the {@code Node} interface that stores unique edge
 * weights in a strictly descending order.
 * <p>
 * Relevant design patterns: immutable object, simple factory.
 *
 * @author Maurits Silvis
 * @since 0.5.0
 */
@EqualsAndHashCode
@ToString
final class DescendingNode implements Node {
    private final List<Integer> weights;

    private DescendingNode(Collection<Integer> weights) {
        this.weights = weights.stream()
                .distinct()
                .sorted(Comparator.reverseOrder())
                .toList();
    }

    /**
     * Returns a new {@code DescendingNode} with unique edge weights in a
     * strictly descending order.
     *
     * @param weights a collection of weights
     * @return a new {@code DescendingNode} with the specified weights
     */
    public static Node of(Collection<Integer> weights) {
        return new DescendingNode(weights);
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
