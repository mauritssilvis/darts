/*
 * Copyright © 2023 Maurits H. Silvis
 * SPDX-License-Identifier: GPL-3.0-or-later
 */

package nl.mauritssilvis.darts.checkouts.java.paths.common;

import nl.mauritssilvis.darts.checkouts.java.paths.cartesian.BasicNode;

import java.util.Collection;
import java.util.List;

public final class PathFinderTestUtils {
    private PathFinderTestUtils() {
    }

    public static List<Node> getNodes(Collection<Collection<Integer>> weights) {
        return weights.stream()
                .map(BasicNode::of)
                .toList();
    }

}
