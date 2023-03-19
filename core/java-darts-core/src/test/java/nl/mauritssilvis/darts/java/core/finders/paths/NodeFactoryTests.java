/*
 * Copyright © 2023 Maurits Silvis
 * SPDX-License-Identifier: GPL-3.0-or-later
 */

package nl.mauritssilvis.darts.java.core.finders.paths;

import nl.mauritssilvis.darts.java.api.settings.FinderType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

class NodeFactoryTests {
    @Test
    void getACartesianNode() {
        FinderType finderType = FinderType.CARTESIAN;
        Collection<Integer> weights = Collections.emptyList();
        Node pathfinder = NodeFactory.create(finderType, weights);

        Assertions.assertTrue(pathfinder instanceof CartesianNode);
    }

    @Test
    void getADescendingNode() {
        FinderType finderType = FinderType.DESCENDING;
        Collection<Integer> weights = Collections.emptyList();
        Node pathfinder = NodeFactory.create(finderType, weights);

        Assertions.assertTrue(pathfinder instanceof DescendingNode);
    }

    @Test
    void passOnTheWeights() {
        FinderType finderType = FinderType.CARTESIAN;
        Collection<Integer> weights = List.of(0, 2, 4);
        Node node = NodeFactory.create(finderType, weights);

        Assertions.assertEquals(weights, node.getWeights());
    }
}
