/*
 * Copyright © 2023 Maurits Silvis
 * SPDX-License-Identifier: GPL-3.0-or-later
 */

package nl.mauritssilvis.darts.java.finders.paths.types;

import nl.mauritssilvis.darts.java.finders.paths.Pathfinder;
import nl.mauritssilvis.darts.java.settings.FinderType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Collection;
import java.util.Collections;

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
}
