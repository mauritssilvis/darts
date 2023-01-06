/*
 * Copyright © 2023 Maurits H. Silvis
 * SPDX-License-Identifier: GPL-3.0-or-later
 */

package nl.mauritssilvis.darts.checkouts.java.nodes;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Stream;

public class BasicNode implements Node {
    private final List<Integer> edges;

    private BasicNode(Stream<Integer> edges) {
        this.edges = edges
                .distinct()
                .toList();
    }

    public static Node of(Collection<Integer> edges) {
        return new BasicNode(edges.stream());
    }

    public static Node of(int... edges) {
        return new BasicNode(Arrays.stream(edges).boxed());
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
