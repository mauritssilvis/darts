/*
 * Copyright © 2023 Maurits H. Silvis
 * SPDX-License-Identifier: GPL-3.0-or-later
 */

package nl.mauritssilvis.darts.checkouts.java.pathfinders;

import nl.mauritssilvis.darts.checkouts.java.paths.Path;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junitpioneer.jupiter.cartesian.ArgumentSets;
import org.junitpioneer.jupiter.cartesian.CartesianTest;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

class CartesianPathFinderTests {
    @CartesianTest
    @CartesianTest.MethodFactory("withASpecificShortPath")
    void findASpecificShortPath(List<Set<Integer>> steps, int target, List<Integer> path) {
        PathFinder pathFinder = new CartesianPathFinder();

        Set<Path> paths = pathFinder.find(steps, target);

        int numPaths = paths.stream()
                .mapToInt(Path::getMultiplicity)
                .sum();

        Iterator<Path> iterator = paths.iterator();

        Assertions.assertAll(
                () -> Assertions.assertEquals(1, numPaths),
                () -> Assertions.assertEquals(path, iterator.next().getSteps())
        );
    }

    @CartesianTest
    @CartesianTest.MethodFactory("withTwoSpecificShortPaths")
    void findTwoSpecificShortPaths(List<Set<Integer>> steps, int target, Set<List<Integer>> lists) {
        PathFinder pathFinder = new CartesianPathFinder();

        Set<Path> paths = pathFinder.find(steps, target);

        int numPaths = paths.stream()
                .mapToInt(Path::getMultiplicity)
                .sum();

        Iterator<Path> iterator = paths.iterator();

        Assertions.assertAll(
                () -> Assertions.assertEquals(2, numPaths),
                () -> Assertions.assertTrue(lists.contains(iterator.next().getSteps())),
                () -> Assertions.assertTrue(lists.contains(iterator.next().getSteps()))
        );
    }

    @Test
    void findASpecificLongPath() {
        Set<Integer> step = Set.of(1, 2, 3, 4, 5, 6, 7, 8, 9);
        List<Set<Integer>> steps = List.of(step, step, step, step, step, step, step, step, step);
        int target = 9 * 9;

        PathFinder pathFinder = new CartesianPathFinder();
        Set<Path> paths = pathFinder.find(steps, target);

        int numPaths = paths.stream()
                .mapToInt(Path::getMultiplicity)
                .sum();

        Iterator<Path> iterator = paths.iterator();

        Assertions.assertAll(
                () -> Assertions.assertEquals(1, numPaths),
                () -> Assertions.assertEquals(List.of(9, 9, 9, 9, 9, 9, 9, 9, 9), iterator.next().getSteps())
        );
    }

    static ArgumentSets withASpecificShortPath() {
        return ArgumentSets
                .argumentsForFirstParameter(
                        List.of(Set.of(3), Set.of(2), Set.of(5)),
                        List.of(Set.of(3, 2), Set.of(2, 4), Set.of(2, 5)),
                        List.of(Set.of(0, 3, 10), Set.of(2, 4), Set.of(1, 5))
                )
                .argumentsForNextParameter(
                        10
                )
                .argumentsForNextParameter(
                        (Object) List.of(3, 2, 5)
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
