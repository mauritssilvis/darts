/*
 * Copyright © 2023 Maurits Silvis
 * SPDX-License-Identifier: GPL-3.0-or-later
 */

package nl.mauritssilvis.darts.java.finders.paths;

import nl.mauritssilvis.darts.java.api.finders.paths.Path;
import nl.mauritssilvis.darts.java.api.finders.paths.Pathfinder;
import nl.mauritssilvis.darts.java.api.settings.FinderType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

class PathfinderFactoryTests {
    @Test
    void getACartesianPathfinder() {
        FinderType finderType = FinderType.CARTESIAN;
        Collection<Node> nodes = Collections.emptyList();
        Pathfinder pathfinder = PathfinderFactory.create(finderType, nodes);

        Assertions.assertTrue(pathfinder instanceof CartesianPathfinder);
    }

    @Test
    void getADescendingPathfinder() {
        FinderType finderType = FinderType.DESCENDING;
        Collection<Node> nodes = Collections.emptyList();
        Pathfinder pathfinder = PathfinderFactory.create(finderType, nodes);

        Assertions.assertTrue(pathfinder instanceof DescendingPathfinder);
    }

    @Test
    void passOnTheNodes() {
        FinderType finderType = FinderType.CARTESIAN;

        Collection<Collection<Integer>> weightsPerNode = List.of(List.of(7));
        Collection<Node> nodes = CartesianNodeTestUtils.getNodes(weightsPerNode);

        Pathfinder pathfinder = PathfinderFactory.create(finderType, nodes);

        int length = 7;

        Collection<Path> paths = pathfinder.find(length);

        Assertions.assertFalse(paths.isEmpty());
    }
}
