/*
 * Copyright © 2023 Maurits H. Silvis
 * SPDX-License-Identifier: GPL-3.0-or-later
 */

package nl.mauritssilvis.darts.checkouts.java.pathfinders;

import nl.mauritssilvis.darts.checkouts.java.paths.Path;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junitpioneer.jupiter.cartesian.ArgumentSets;
import org.junitpioneer.jupiter.cartesian.CartesianTest;

import java.util.List;
import java.util.Set;

class CartesianPathFinderTests {
    @ParameterizedTest
    @MethodSource("withASpecificLength10Path")
    void findASpecificShortPath(List<Set<Integer>> steps) {
        PathFinder pathFinder = new CartesianPathFinder();
        int target = 10;

        List<Path> paths = pathFinder.find(steps, target);

        int numPaths = paths.stream()
                .mapToInt(Path::getMultiplicity)
                .sum();

        Assertions.assertAll(
                () -> Assertions.assertEquals(1, numPaths),
                () -> Assertions.assertEquals(List.of(3, 2, 5), paths.get(0).getSteps())
        );
    }

    @CartesianTest
    @CartesianTest.MethodFactory("withTwoSpecificShortPaths")
    void findTwoSpecificShortPaths(List<Set<Integer>> steps, int target, Set<List<Integer>> lists) {
        PathFinder pathFinder = new CartesianPathFinder();

        List<Path> paths = pathFinder.find(steps, target);

        int numPaths = paths.stream()
                .mapToInt(Path::getMultiplicity)
                .sum();

        Assertions.assertAll(
                () -> Assertions.assertEquals(2, numPaths),
                () -> Assertions.assertTrue(lists.contains(paths.get(0).getSteps())),
                () -> Assertions.assertTrue(lists.contains(paths.get(1).getSteps()))
        );
    }

    @Test
    void findASpecificLongPath() {
        Set<Integer> step = Set.of(1, 2, 3, 4, 5, 6, 7, 8, 9);
        List<Set<Integer>> steps = List.of(step, step, step, step, step, step, step, step, step);
        int target = 9 * 9;

        PathFinder pathFinder = new CartesianPathFinder();
        List<Path> paths = pathFinder.find(steps, target);

        int numPaths = paths.stream()
                .mapToInt(Path::getMultiplicity)
                .sum();

        Assertions.assertAll(
                () -> Assertions.assertEquals(1, numPaths),
                () -> Assertions.assertEquals(List.of(9, 9, 9, 9, 9, 9, 9, 9, 9), paths.get(0).getSteps())
        );
    }

    static Collection<List<Set<Integer>>> withASpecificLength10Path() {
        return List.of(
                List.of(Set.of(3), Set.of(2), Set.of(5)),
                List.of(Set.of(3, 2), Set.of(2, 4), Set.of(2, 5)),
                        List.of(Set.of(3, 2), Set.of(2, 4), Set.of(2, 5)),
                        List.of(Set.of(0, 3, 10), Set.of(2, 4), Set.of(1, 5))
                );
    }

    static ArgumentSets withTwoSpecificShortPaths() {
        return ArgumentSets
                .argumentsForFirstParameter(
                        List.of(Set.of(3, 2), Set.of(3, 2), Set.of(5)),
                        List.of(Set.of(3, 2), Set.of(2, 4, 3), Set.of(2, 5)),
                        List.of(Set.of(0, 3, 10, 2), Set.of(3, 2, 4), Set.of(1, 5))
                )
                .argumentsForNextParameter(
                        10
                )
                .argumentsForNextParameter(
                        (Object) Set.of(List.of(3, 2, 5), List.of(2, 3, 5))
                );
    }
}
