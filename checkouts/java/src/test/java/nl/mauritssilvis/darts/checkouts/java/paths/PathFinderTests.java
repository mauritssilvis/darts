/*
 * Copyright © 2023 Maurits H. Silvis
 * SPDX-License-Identifier: GPL-3.0-or-later
 */

package nl.mauritssilvis.darts.checkouts.java.paths;

import org.junit.jupiter.api.Assertions;
import org.junitpioneer.jupiter.cartesian.ArgumentSets;
import org.junitpioneer.jupiter.cartesian.CartesianTest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

class PathFinderTests {
    @CartesianTest
    @CartesianTest.MethodFactory("withAllPathFinders")
    void handleAbsentSteps(PathFinder pathFinder) {
        List<Set<Integer>> steps = new ArrayList<>();
        int target = 0;

        Set<List<Integer>> paths = pathFinder.find(steps, target);

        Assertions.assertEquals(0, paths.size());
    }

    @CartesianTest
    @CartesianTest.MethodFactory("withEmptySteps")
    void handleEmptySteps(PathFinder pathFinder, List<Set<Integer>> steps, int target) {
        Set<List<Integer>> paths = pathFinder.find(steps, target);

        Assertions.assertEquals(0, paths.size());
    }

    @CartesianTest
    @CartesianTest.MethodFactory("withUnreachableTargets")
    void handleUnreachableTargets(PathFinder pathFinder, List<Set<Integer>> steps, int target) {
        Set<List<Integer>> paths = pathFinder.find(steps, target);

        Assertions.assertEquals(0, paths.size());
    }

    private static ArgumentSets withAllPathFinders() {
        return ArgumentSets
                .argumentsForFirstParameter(getPathFinders());
    }

    static ArgumentSets withEmptySteps() {
        return ArgumentSets
                .argumentsForFirstParameter(
                        getPathFinders()
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

    static ArgumentSets withUnreachableTargets() {
        return ArgumentSets
                .argumentsForFirstParameter(
                        getPathFinders()
                )
                .argumentsForNextParameter(
                        List.of(Set.of(1)),
                        List.of(Set.of(-3, 1)),
                        List.of(Set.of(1), Set.of(2)),
                        List.of(Set.of(0, 2), Set.of(3, -4)),
                        List.of(Set.of(1), Set.of(1), Set.of(1)),
                        List.of(Set.of(0), Set.of(1, 2), Set.of(2, 3))
                )
                .argumentsForNextParameter(
                        -1, 0, 2
                );
    }

    static List<PathFinder> getPathFinders() {
        return List.of(
                new NaivePathFinder()
        );
    }
}
