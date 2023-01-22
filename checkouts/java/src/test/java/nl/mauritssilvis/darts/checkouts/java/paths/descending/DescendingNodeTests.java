/*
 * Copyright © 2023 Maurits H. Silvis
 * SPDX-License-Identifier: GPL-3.0-or-later
 */

package nl.mauritssilvis.darts.checkouts.java.paths.descending;

import nl.mauritssilvis.darts.checkouts.java.paths.Node;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

class DescendingNodeTests {
    @Test
    void getTheWeights() {
        Collection<Integer> weights = List.of(7, 6, 5);
        Node node = DescendingNode.of(weights);

        Assertions.assertEquals(weights, node.getWeights());
    }

    @Test
    void getTheWeightsWithSingletonInput() {
        Collection<Integer> weights = Collections.singletonList(9);
        Node node = DescendingNode.of(weights);

        Assertions.assertEquals(weights, node.getWeights());
    }

    @Test
    void getTheWeightsWithEmptyInput() {
        Collection<Integer> weights = Collections.emptyList();
        Node node = DescendingNode.of(weights);

        Assertions.assertEquals(weights, node.getWeights());
    }

    @Test
    void storeIndependentWeights() {
        List<Integer> weights = new ArrayList<>(List.of(2, 0, -2));
        Node node = DescendingNode.of(weights);

        weights.set(1, 1);

        Assertions.assertNotEquals(weights, node.getWeights());
    }

    @Test
    void getImmutableWeights() {
        Collection<Integer> weights = new ArrayList<>(List.of(8, 9, 10));
        Node node = DescendingNode.of(weights);

        List<Integer> storedWeights = node.getWeights();

        Assertions.assertThrows(UnsupportedOperationException.class, () -> storedWeights.set(0, 7));
    }

    @Test
    void storeUniqueWeights() {
        Collection<Integer> weights = List.of(5, 5, 6, 8, 6);
        Node node = DescendingNode.of(weights);

        List<Integer> uniqueWeights = List.of(8, 6, 5);

        Assertions.assertEquals(uniqueWeights, node.getWeights());
    }

    @Test
    void storeStrictlyDescendingWeights() {
        Collection<Integer> weights = List.of(1, 4, 5, 5, 4, -1);
        Node node = DescendingNode.of(weights);

        List<Integer> descendingWeights = List.of(5, 4, 1, -1);

        Assertions.assertEquals(descendingWeights, node.getWeights());
    }

    @Test
    void getAConnectedNode() {
        Collection<Integer> weights = List.of(3, 4);
        Node node = DescendingNode.of(weights);

        Assertions.assertTrue(node.isConnected());
    }

    @Test
    void getAConnectedNodeWithSingletonInput() {
        Collection<Integer> weights = Collections.singletonList(8);
        Node node = DescendingNode.of(weights);

        Assertions.assertTrue(node.isConnected());
    }

    @Test
    void getADisconnectedNodeWithEmptyInput() {
        Collection<Integer> weights = Collections.emptyList();
        Node node = DescendingNode.of(weights);

        Assertions.assertFalse(node.isConnected());
    }
}
