/*
 * Copyright © 2023 Maurits H. Silvis
 * SPDX-License-Identifier: GPL-3.0-or-later
 */

package nl.mauritssilvis.darts.checkouts.java.nodes;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

class BasicNodeTests {
    @Test
    void getStoredWeightsWithCollectionInput() {
        List<Integer> weights = List.of(4, 5, 6);
        Node node = BasicNode.with(weights);

        Assertions.assertEquals(weights, node.getWeights());
    }

    @Test
    void getStoredWeightsWithArrayInput() {
        int[] weights = {5, 1, 0};
        Node node = BasicNode.with(weights);

        List<Integer> edgeList = Arrays.stream(weights)
                .boxed()
                .toList();

        Assertions.assertEquals(edgeList, node.getWeights());
    }

    @Test
    void getStoredWeightsWithVarArgsInput() {
        Node node = BasicNode.with(7, 3, 9);

        List<Integer> weights = List.of(7, 3, 9);

        Assertions.assertEquals(weights, node.getWeights());
    }

    @Test
    void storeIndependentWeightsWithCollectionInput() {
        List<Integer> weights = new ArrayList<>(List.of(-1, 0, 2));
        Node node = BasicNode.with(weights);

        weights.set(1, -2);

        Assertions.assertNotEquals(weights, node.getWeights());
    }

    @Test
    void storeIndependentWeightsWithArrayInput() {
        int[] weights = {5, 1, 0};
        Node node = BasicNode.with(weights);

        weights[2] = 10;

        List<Integer> edgeList = Arrays.stream(weights)
                .boxed()
                .toList();

        Assertions.assertNotEquals(edgeList, node.getWeights());
    }

    @Test
    void storeImmutableWeightsWithCollectionInput() {
        Collection<Integer> weights = new ArrayList<>(List.of(4, 5, 6));
        Node node = BasicNode.with(weights);

        List<Integer> storedWeights = node.getWeights();

        Assertions.assertThrows(UnsupportedOperationException.class, () -> storedWeights.set(2, 1));
    }

    @Test
    void storeImmutableWeightsWithArrayInput() {
        int[] weights = {-5, 0, 5};
        Node node = BasicNode.with(weights);

        List<Integer> storedWeights = node.getWeights();

        Assertions.assertThrows(UnsupportedOperationException.class, () -> storedWeights.set(1, 6));
    }

    @Test
    void storeImmutableWeightsWithVarArgsInput() {
        Node node = BasicNode.with(1, 3, 5);

        List<Integer> storedWeights = node.getWeights();

        Assertions.assertThrows(UnsupportedOperationException.class, () -> storedWeights.set(0, 7));
    }

    @Test
    void storeUniqueWeightsWithCollectionInput() {
        List<Integer> weights = List.of(7, 7, 8, 8, 9);
        Node node = BasicNode.with(weights);

        List<Integer> uniqueWeights = List.of(7, 8, 9);

        Assertions.assertEquals(uniqueWeights, node.getWeights());
    }

    @Test
    void storeUniqueWeightsWithArrayInput() {
        int[] weights = {-5, 0, 0, 5, -5};
        Node node = BasicNode.with(weights);

        List<Integer> uniqueWeights = List.of(-5, 0, 5);

        Assertions.assertEquals(uniqueWeights, node.getWeights());
    }

    @Test
    void storeUniqueWeightsWithVarArgsInput() {
        Node node = BasicNode.with(-1, 0, 1, -1, 0, 1, 2);

        List<Integer> uniqueWeights = List.of(-1, 0, 1, 2);

        Assertions.assertEquals(uniqueWeights, node.getWeights());
    }
}
