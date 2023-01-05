/*
 * Copyright © 2023 Maurits H. Silvis
 * SPDX-License-Identifier: GPL-3.0-or-later
 */

package nl.mauritssilvis.darts.checkouts.java.pathfinders;

import nl.mauritssilvis.darts.checkouts.java.paths.Path;
import org.junit.jupiter.api.Assertions;
import org.junitpioneer.jupiter.cartesian.ArgumentSets;
import org.junitpioneer.jupiter.cartesian.CartesianTest;

import java.util.*;

class PathFinderTests {
    @CartesianTest
    @CartesianTest.MethodFactory("withAllPathFinders")
    void handleAbsentSteps(PathFinder pathFinder) {
        List<Set<Integer>> steps = new ArrayList<>();
        int target = 0;

        List<Path> paths = pathFinder.find(steps, target);

        Assertions.assertEquals(0, paths.size());
    }

    @CartesianTest
    @CartesianTest.MethodFactory("withAllPathFindersAndEmptySteps")
    void handleEmptySteps(PathFinder pathFinder, List<Set<Integer>> steps) {
        int target = 2;

        List<Path> paths = pathFinder.find(steps, target);

        Assertions.assertEquals(0, paths.size());
    }

    @CartesianTest
    @CartesianTest.MethodFactory("withAllPathFindersAndUnreachableTargets")
    void handleUnreachableTargets(PathFinder pathFinder, List<Set<Integer>> steps, int target) {
        List<Path> paths = pathFinder.find(steps, target);

        Assertions.assertEquals(0, paths.size());
    }

    @CartesianTest
    @CartesianTest.MethodFactory("withAllPathFindersAndOneShortPath")
    void findOneShortPath(PathFinder pathFinder, List<Set<Integer>> steps, int target) {
        List<Path> paths = pathFinder.find(steps, target);

        int numPaths = paths.stream()
                .mapToInt(Path::getMultiplicity)
                .sum();

        Assertions.assertAll(
                () -> Assertions.assertEquals(1, numPaths),
                () -> Assertions.assertEquals(steps.size(), paths.get(0).getLength())
        );
    }

    @CartesianTest
    @CartesianTest.MethodFactory("withAllPathFindersAndTwoShortPaths")
    void findTwoShortPaths(PathFinder pathFinder, List<Set<Integer>> steps, int target) {
        List<Path> paths = pathFinder.find(steps, target);

        int numPaths = paths.stream()
                .mapToInt(Path::getMultiplicity)
                .sum();

        Assertions.assertAll(
                () -> Assertions.assertEquals(2, numPaths),
                () -> Assertions.assertEquals(steps.size(), paths.get(0).getLength()),
                () -> Assertions.assertEquals(steps.size(), paths.get(1).getLength())
        );
    }

    private static ArgumentSets withAllPathFinders() {
        return ArgumentSets
                .argumentsForFirstParameter(
                        getAllPathFinders()
                );
    }

    static ArgumentSets withAllPathFindersAndEmptySteps() {
        return ArgumentSets
                .argumentsForFirstParameter(
                        getAllPathFinders()
                )
                .argumentsForNextParameter(
                        List.of(Collections.emptySet()),
                        List.of(Collections.emptySet(), Collections.emptySet()),
                        List.of(Collections.emptySet(), Set.of(1, 2)),
                        List.of(Set.of(1, 2), Collections.emptySet()),
                        List.of(Set.of(3), Collections.emptySet(), Set.of(-1))
                );
    }

    static ArgumentSets withAllPathFindersAndUnreachableTargets() {
        return ArgumentSets
                .argumentsForFirstParameter(
                        getAllPathFinders()
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

    static ArgumentSets withAllPathFindersAndOneShortPath() {
        return ArgumentSets
                .argumentsForFirstParameter(
                        getAllPathFinders()
                )
                .argumentsForNextParameter(
                        List.of(Set.of(3)),
                        List.of(Set.of(1, 3)),
                        List.of(Set.of(1), Set.of(2)),
                        List.of(Set.of(2, 1), Set.of(2, 3)),
                        List.of(Set.of(1), Set.of(1), Set.of(1)),
                        List.of(Set.of(1, 2), Set.of(3, 1), Set.of(4, 1, 2)),
                        List.of(Set.of(0, 1, 2), Set.of(1), Set.of(1))
                )
                .argumentsForNextParameter(
                        3
                );
    }

    static ArgumentSets withAllPathFindersAndTwoShortPaths() {
        return ArgumentSets
                .argumentsForFirstParameter(
                        getAllPathFinders()
                )
                .argumentsForNextParameter(
                        List.of(Set.of(1, 3), Set.of(1, 3)),
                        List.of(Set.of(2, 3), Set.of(2, 1)),
                        List.of(Set.of(1, 4), Set.of(0, 3)),
                        List.of(Set.of(0), Set.of(1, 2), Set.of(2, 3)),
                        List.of(Set.of(1, 2), Set.of(3, 1), Set.of(4, 0, 2)),
                        List.of(Set.of(1, 2), Set.of(1), Set.of(2, 1))
                )
                .argumentsForNextParameter(
                        4
                );
    }

    static List<PathFinder> getAllPathFinders() {
        return List.of(
                new CartesianPathFinder()
        );
    }
}