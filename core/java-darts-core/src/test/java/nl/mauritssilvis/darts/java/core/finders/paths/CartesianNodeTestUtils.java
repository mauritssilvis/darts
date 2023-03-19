/*
 * Copyright © 2023 Maurits Silvis
 * SPDX-License-Identifier: GPL-3.0-or-later
 */

package nl.mauritssilvis.darts.java.core.finders.paths;

import java.util.Collection;
import java.util.List;

/**
 * A set of testing utilities that create (collections of) {@code CartesianNode}
 * objects.
 *
 * @author Maurits Silvis
 * @since 0.5.0
 */
final class CartesianNodeTestUtils {
    private CartesianNodeTestUtils() {
    }

    /**
     * Returns a list of nodes with the specified weights.
     *
     * @param weights a collection of weights per node
     * @return a list of nodes with the specified weights
     */
    static List<Node> getNodes(Collection<? extends Collection<Integer>> weights) {
        return weights.stream()
                .map(CartesianNode::of)
                .toList();
    }
}
