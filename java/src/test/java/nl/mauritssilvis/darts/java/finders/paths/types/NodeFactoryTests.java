/*
 * Copyright © 2023 Maurits Silvis
 * SPDX-License-Identifier: GPL-3.0-or-later
 */

package nl.mauritssilvis.darts.java.finders.paths.types;

import nl.mauritssilvis.darts.java.settings.FinderType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Collection;
import java.util.Collections;

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
}
