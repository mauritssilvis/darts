/*
 * Copyright © 2023 Maurits H. Silvis
 * SPDX-License-Identifier: GPL-3.0-or-later
 */

package nl.mauritssilvis.darts.checkouts.java.nodes;

import nl.mauritssilvis.darts.checkouts.java.paths.Path;
import nl.mauritssilvis.darts.checkouts.java.paths.SimplePath;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

class UnsortedNodeTests {
    @Test
    void getStoredEdges() {
        List<Integer> edges = List.of(4, 5, 6);
        Node node = UnsortedNode.of(edges);

        Assertions.assertEquals(edges, node.getEdges());
    }

    @Test
    void storeAnIndependentCopyOfTheEdges() {
        List<Integer> edges = new ArrayList<>(List.of(-1, 0, 2));
        Node node = UnsortedNode.of(edges);

        edges.set(1, -2);

        Assertions.assertNotEquals(edges, node.getEdges());
    }

    @Test
    void storeImmutableEdges() {
        List<Integer> edges = new ArrayList<>(List.of(4, 5, 6));
        Node node = UnsortedNode.of(edges);

        List<Integer> storedEdges = node.getEdges();

        Assertions.assertThrows(UnsupportedOperationException.class, () -> storedEdges.set(0, 7));
    }

    @Test
    void storeUniqueEdges() {
        List<Integer> edges = List.of(7, 7, 8, 8, 9);
        Node node = UnsortedNode.of(edges);

        List<Integer> uniqueEdges = List.of(7, 8, 9);

        Assertions.assertEquals(uniqueEdges, node.getEdges());
    }
}
