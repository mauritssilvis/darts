/*
 * Copyright © 2023 Maurits H. Silvis
 * SPDX-License-Identifier: GPL-3.0-or-later
 */

package nl.mauritssilvis.darts.checkouts.java.nodes;

import java.util.Collection;
import java.util.List;

/**
 * An implementation of the {@code Node} interface that stores unique edge
 * weights in a strictly descending order.
 * <p>
 * Relevant design patterns: Immutable object, static factory method.
 */
public class DescendingNode implements Node {
    private DescendingNode(Collection<Integer> weights) {
    }

    /**
     * Returns a new {@code DescendingNode} with unique edge weights in a
     * strictly descending order.
     *
     * @param weights a collection of integer weights
     * @return a new {@code DescendingNode} with the given weights
     */
    public static Node of(Collection<Integer> weights) {
        return new DescendingNode(weights);
    }

    @Override
    public List<Integer> getWeights() {
        return null;
    }

    @Override
    public boolean isConnected() {
        return false;
    }
}
