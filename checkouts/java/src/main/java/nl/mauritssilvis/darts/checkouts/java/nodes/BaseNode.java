/*
 * Copyright © 2023 Maurits H. Silvis
 * SPDX-License-Identifier: GPL-3.0-or-later
 */

package nl.mauritssilvis.darts.checkouts.java.nodes;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public abstract class BaseNode implements Node {
    private final List<Integer> edges;

    BaseNode(Collection<Integer> edges) {
        this.edges = edges.stream()
                .distinct()
                .toList();
    }

    BaseNode(int[] edges) {
        this.edges = Arrays.stream(edges)
                .distinct()
                .boxed()
                .toList();
    }

    @Override
    public List<Integer> getEdges() {
        return edges;
    }

    @Override
    public boolean isDisconnected() {
        return edges.isEmpty();
    }
}
