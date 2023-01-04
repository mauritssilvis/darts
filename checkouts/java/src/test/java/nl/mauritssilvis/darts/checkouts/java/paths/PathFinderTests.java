/*
 * Copyright © 2023 Maurits H. Silvis
 * SPDX-License-Identifier: GPL-3.0-or-later
 */

package nl.mauritssilvis.darts.checkouts.java.paths;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Set;

class PathFinderTests {
    @Test
    void findSimplePath() {
        List<Set<Integer>> steps = List.of(
                Set.of(1), Set.of(1)
        );
        int target = 2;

        PathFinder pathFinder = new NaivePathFinder();
        Set<List<Integer>> paths = pathFinder.find(steps, target);

        Assertions.assertEquals(1, paths.size());
    }
}
