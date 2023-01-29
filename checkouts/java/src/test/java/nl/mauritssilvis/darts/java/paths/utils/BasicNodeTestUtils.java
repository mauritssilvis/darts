/*
 * Copyright © 2023 Maurits H. Silvis
 * SPDX-License-Identifier: GPL-3.0-or-later
 */

package nl.mauritssilvis.darts.java.paths.utils;

import nl.mauritssilvis.darts.java.paths.cartesian.BasicNode;
import nl.mauritssilvis.darts.java.paths.common.Node;

import java.util.Collection;
import java.util.List;

public final class BasicNodeTestUtils {
    private BasicNodeTestUtils() {
    }

    public static List<Node> getNodes(Collection<Collection<Integer>> weights) {
        return weights.stream()
                .map(BasicNode::of)
                .toList();
    }
}
