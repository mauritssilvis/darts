/*
 * Copyright © 2023 Maurits H. Silvis
 * SPDX-License-Identifier: GPL-3.0-or-later
 */

package nl.mauritssilvis.darts.checkouts.java.nodes;

import java.util.Collection;
import java.util.List;

public abstract class BaseNode implements Node {
    private final List<Integer> edges;

    BaseNode(Collection<Integer> edges) {
        this.edges = edges.stream()
                .distinct()
                .toList();
    }

    @Override
    public List<Integer> getEdges() {
        return edges;
    }
}
