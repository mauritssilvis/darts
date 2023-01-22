/*
 * Copyright © 2023 Maurits H. Silvis
 * SPDX-License-Identifier: GPL-3.0-or-later
 */

package nl.mauritssilvis.darts.checkouts.java.paths.cartesian;

import nl.mauritssilvis.darts.checkouts.java.paths.common.Node;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

class BasicNodeTests {
    @Test
    void getTheWeights() {
        Collection<Integer> weights = List.of(4, 5, 6);
        Node node = BasicNode.of(weights);

        Assertions.assertEquals(weights, node.getWeights());
    }

    @Test
    void getTheWeightsWithSingletonInput() {
        Collection<Integer> weights = Collections.singletonList(3);
        Node node = BasicNode.of(weights);

        Assertions.assertEquals(weights, node.getWeights());
    }

    @Test
    void getTheWeightsWithEmptyInput() {
        Collection<Integer> weights = Collections.emptyList();
        Node node = BasicNode.of(weights);

        Assertions.assertEquals(weights, node.getWeights());
    }

    @Test
    void storeIndependentWeights() {
        List<Integer> weights = new ArrayList<>(List.of(-1, 0, 2));
        Node node = BasicNode.of(weights);

        weights.set(1, -2);

        Assertions.assertNotEquals(weights, node.getWeights());
    }

    @Test
    void storeImmutableWeights() {
        Collection<Integer> weights = new ArrayList<>(List.of(4, 5, 6));
        Node node = BasicNode.of(weights);

        List<Integer> storedWeights = node.getWeights();

        Assertions.assertThrows(UnsupportedOperationException.class, () -> storedWeights.set(2, 1));
    }

    @Test
    void storeUniqueWeights() {
        Collection<Integer> weights = List.of(7, 7, 8, 8, 9);
        Node node = BasicNode.of(weights);

        List<Integer> uniqueWeights = List.of(7, 8, 9);

        Assertions.assertEquals(uniqueWeights, node.getWeights());
    }

    @Test
    void getAConnectedNode() {
        Collection<Integer> weights = List.of(10, 8);
        Node node = BasicNode.of(weights);

        Assertions.assertTrue(node.isConnected());
    }

    @Test
    void getAConnectedNodeWithSingletonInput() {
        Collection<Integer> weights = Collections.singletonList(3);
        Node node = BasicNode.of(weights);

        Assertions.assertTrue(node.isConnected());
    }

    @Test
    void getADisconnectedNodeWithEmptyInput() {
        Collection<Integer> weights = Collections.emptyList();
        Node node = BasicNode.of(weights);

        Assertions.assertFalse(node.isConnected());
    }
}
