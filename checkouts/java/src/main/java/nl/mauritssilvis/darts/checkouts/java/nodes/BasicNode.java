/*
 * Copyright © 2023 Maurits H. Silvis
 * SPDX-License-Identifier: GPL-3.0-or-later
 */

package nl.mauritssilvis.darts.checkouts.java.nodes;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class BasicNode implements Node {
    private final List<Integer> edges;

    private BasicNode(Collection<Integer> edges) {
        this.edges = edges.stream()
                .distinct()
                .toList();
    }

    private BasicNode(int... edges) {
        this.edges = Arrays.stream(edges)
                .distinct()
                .boxed()
                .toList();
    }

    public static Node of(Collection<Integer> edges) {
        return new BasicNode(edges);
    }

    public static Node of(int... edges) {
        return new BasicNode(edges);
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
