/*
 * Copyright © 2023 Maurits H. Silvis
 * SPDX-License-Identifier: GPL-3.0-or-later
 */

package nl.mauritssilvis.darts.checkouts.java.paths;

import org.junit.jupiter.api.Assertions;
import org.junitpioneer.jupiter.cartesian.ArgumentSets;
import org.junitpioneer.jupiter.cartesian.CartesianTest;

import java.util.Collections;
import java.util.List;
import java.util.Set;

class PathFinderTests {
    @CartesianTest
    @CartesianTest.MethodFactory("emptyStepFactory")
    void handleEmptySteps(PathFinder pathFinder, List<Set<Integer>> steps, int target) {
        Set<List<Integer>> paths = pathFinder.find(steps, target);
        Assertions.assertEquals(0, paths.size());
    }

    static ArgumentSets emptyStepFactory() {
        return ArgumentSets
                .argumentsForFirstParameter(
                        new NaivePathFinder()
                )
                .argumentsForNextParameter(
                        List.of(Collections.emptySet()),
                        List.of(Collections.emptySet(), Collections.emptySet()),
                        List.of(Collections.emptySet(), Set.of(1, 2)),
                        List.of(Set.of(1, 2), Collections.emptySet()),
                        List.of(Set.of(3), Collections.emptySet(), Set.of(-1))
                )
                .argumentsForNextParameter(
                        2
                );
    }
}
