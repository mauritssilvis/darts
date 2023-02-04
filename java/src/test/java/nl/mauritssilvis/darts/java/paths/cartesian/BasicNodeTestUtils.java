/*
 * Copyright © 2023 Maurits H. Silvis
 * SPDX-License-Identifier: GPL-3.0-or-later
 */

package nl.mauritssilvis.darts.java.paths.cartesian;

import nl.mauritssilvis.darts.java.paths.common.Node;

import java.util.Collection;
import java.util.List;

/**
 * A set of testing utilities that create (collections of) {@code BasicNode}
 * objects.
 */
public final class BasicNodeTestUtils {
    private BasicNodeTestUtils() {
    }

    /**
     * Returns a list of nodes with the specified weights.
     *
     * @param weights a collection of weights per node
     * @return a list of nodes with the specified weights
     */
    public static List<Node> getNodes(Collection<? extends Collection<Integer>> weights) {
        return weights.stream()
                .map(BasicNode::of)
                .toList();
    }
}
