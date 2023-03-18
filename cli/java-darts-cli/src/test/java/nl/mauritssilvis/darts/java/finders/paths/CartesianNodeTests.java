/*
 * Copyright © 2023 Maurits Silvis
 * SPDX-License-Identifier: GPL-3.0-or-later
 */

package nl.mauritssilvis.darts.java.finders.paths;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

class CartesianNodeTests {
    @Test
    void storeIndependentWeights() {
        List<Integer> weights = new ArrayList<>(List.of(-1, 0, 2));
        Node node = CartesianNode.of(weights);

        weights.set(1, -2);

        Assertions.assertNotEquals(weights, node.getWeights());
    }

    @Test
    void storeUniqueWeights() {
        Collection<Integer> weights = List.of(7, 7, 8, 8, 9);
        Node node = CartesianNode.of(weights);

        List<Integer> uniqueWeights = List.of(7, 8, 9);

        Assertions.assertEquals(uniqueWeights, node.getWeights());
    }

    @Test
    void getTheWeights() {
        Collection<Integer> weights = List.of(4, 5, 6);
        Node node = CartesianNode.of(weights);

        Assertions.assertEquals(weights, node.getWeights());
    }

    @Test
    void getTheWeightsWithSingletonInput() {
        Collection<Integer> weights = Collections.singletonList(3);
        Node node = CartesianNode.of(weights);

        Assertions.assertEquals(weights, node.getWeights());
    }

    @Test
    void getTheWeightsWithEmptyInput() {
        Collection<Integer> weights = Collections.emptyList();
        Node node = CartesianNode.of(weights);

        Assertions.assertEquals(weights, node.getWeights());
    }

    @Test
    void getImmutableWeights() {
        Collection<Integer> weights = new ArrayList<>(List.of(4, 5, 6));
        Node node = CartesianNode.of(weights);

        List<Integer> storedWeights = node.getWeights();

        Assertions.assertThrows(UnsupportedOperationException.class, () -> storedWeights.set(2, 1));
    }

    @Test
    void getAConnectedNode() {
        Collection<Integer> weights = List.of(10, 8);
        Node node = CartesianNode.of(weights);

        Assertions.assertTrue(node.isConnected());
    }

    @Test
    void getAConnectedNodeWithSingletonInput() {
        Collection<Integer> weights = Collections.singletonList(3);
        Node node = CartesianNode.of(weights);

        Assertions.assertTrue(node.isConnected());
    }

    @Test
    void getADisconnectedNodeWithEmptyInput() {
        Collection<Integer> weights = Collections.emptyList();
        Node node = CartesianNode.of(weights);

        Assertions.assertFalse(node.isConnected());
    }

    @Test
    void getEqualNodes() {
        Collection<Integer> weights1 = List.of(-10, 10, 10);
        Node node1 = CartesianNode.of(weights1);

        Collection<Integer> weights2 = List.of(-10, 10);
        Node node2 = CartesianNode.of(weights2);

        Assertions.assertAll(
                () -> Assertions.assertEquals(node1, node2),
                () -> Assertions.assertEquals(node1.hashCode(), node2.hashCode())
        );
    }

    @Test
    void getUnequalNodes() {
        Collection<Integer> weights1 = List.of(-10, 0, 10);
        Node node1 = CartesianNode.of(weights1);

        Collection<Integer> weights2 = List.of(-5, 0, 5);
        Node node2 = CartesianNode.of(weights2);

        Assertions.assertNotEquals(node1, node2);
    }

    @Test
    void convertToAString() {
        Collection<Integer> weights = List.of(0, 1, 3);
        Node node = CartesianNode.of(weights);

        String str = node.toString();

        Assertions.assertAll(
                () -> Assertions.assertTrue(str.startsWith(node.getClass().getSimpleName())),
                () -> Assertions.assertTrue(str.contains("weights")),
                () -> Assertions.assertTrue(str.contains("connected"))
        );
    }
}
