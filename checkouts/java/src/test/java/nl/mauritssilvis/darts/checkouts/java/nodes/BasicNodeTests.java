/*
 * Copyright © 2023 Maurits H. Silvis
 * SPDX-License-Identifier: GPL-3.0-or-later
 */

package nl.mauritssilvis.darts.checkouts.java.nodes;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class BasicNodeTests {
    @Test
    void getStoredEdgesWithCollectionInput() {
        List<Integer> edges = List.of(4, 5, 6);
        Node node = BasicNode.of(edges);

        Assertions.assertEquals(edges, node.getEdges());
    }

    @Test
    void getStoredEdgesWithArrayInput() {
        int[] edges = {5, 1, 0};
        Node node = BasicNode.of(edges);

        List<Integer> edgeList = Arrays.stream(edges)
                .boxed()
                .toList();

        Assertions.assertEquals(edgeList, node.getEdges());
    }

    @Test
    void getStoredEdgesWithVarArgsInput() {
        Node node = BasicNode.of(7, 3, 9);

        List<Integer> edges = List.of(7, 3, 9);

        Assertions.assertEquals(edges, node.getEdges());
    }

    @Test
    void storeIndependentEdgesWithCollectionInput() {
        List<Integer> edges = new ArrayList<>(List.of(-1, 0, 2));
        Node node = BasicNode.of(edges);

        edges.set(1, -2);

        Assertions.assertNotEquals(edges, node.getEdges());
    }

    @Test
    void storeIndependentEdgesWithArrayInput() {
        int[] edges = {5, 1, 0};
        Node node = BasicNode.of(edges);

        edges[2] = 10;

        List<Integer> edgeList = Arrays.stream(edges)
                .boxed()
                .toList();

        Assertions.assertNotEquals(edgeList, node.getEdges());
    }

    @Test
    void storeImmutableEdgesWithCollectionInput() {
        List<Integer> edges = new ArrayList<>(List.of(4, 5, 6));
        Node node = BasicNode.of(edges);

        List<Integer> storedEdges = node.getEdges();

        Assertions.assertThrows(UnsupportedOperationException.class, () -> storedEdges.set(2, 1));
    }

    @Test
    void storeImmutableEdgesWithArrayInput() {
        int[] edges = {-5, 0, 5};
        Node node = BasicNode.of(edges);

        List<Integer> storedEdges = node.getEdges();

        Assertions.assertThrows(UnsupportedOperationException.class, () -> storedEdges.set(1, 6));
    }

    @Test
    void storeImmutableEdgesWithVarArgsInput() {
        Node node = BasicNode.of(1, 3, 5);

        List<Integer> storedEdges = node.getEdges();

        Assertions.assertThrows(UnsupportedOperationException.class, () -> storedEdges.set(0, 7));
    }

    @Test
    void storeUniqueEdgesWithCollectionInput() {
        List<Integer> edges = List.of(7, 7, 8, 8, 9);
        Node node = BasicNode.of(edges);

        List<Integer> uniqueEdges = List.of(7, 8, 9);

        Assertions.assertEquals(uniqueEdges, node.getEdges());
    }

    @Test
    void storeUniqueEdgesWithArrayInput() {
        int[] edges = {-5, 0, 0, 5, -5};
        Node node = BasicNode.of(edges);

        List<Integer> uniqueEdges = List.of(-5, 0, 5);

        Assertions.assertEquals(uniqueEdges, node.getEdges());
    }

    @Test
    void storeUniqueEdgesWithVarArgsInput() {
        Node node = BasicNode.of(-1, 0, 1, -1, 0, 1, 2);

        List<Integer> uniqueEdges = List.of(-1, 0, 1, 2);

        Assertions.assertEquals(uniqueEdges, node.getEdges());
    }
}
