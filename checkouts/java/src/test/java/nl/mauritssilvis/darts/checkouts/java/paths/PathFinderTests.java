/*
 * Copyright © 2023 Maurits H. Silvis
 * SPDX-License-Identifier: GPL-3.0-or-later
 */

package nl.mauritssilvis.darts.checkouts.java.paths;

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

        Set<Path> paths = pathFinder.find(steps, target);

        Assertions.assertEquals(0, paths.size());
    }

    @CartesianTest
    @CartesianTest.MethodFactory("withEmptySteps")
    void handleEmptySteps(PathFinder pathFinder, List<Set<Integer>> steps, int target) {
        Set<Path> paths = pathFinder.find(steps, target);

        Assertions.assertEquals(0, paths.size());
    }

    @CartesianTest
    @CartesianTest.MethodFactory("withUnreachableTargets")
    void handleUnreachableTargets(PathFinder pathFinder, List<Set<Integer>> steps, int target) {
        Set<Path> paths = pathFinder.find(steps, target);

        Assertions.assertEquals(0, paths.size());
    }

    @CartesianTest
    @CartesianTest.MethodFactory("withOneShortPath")
    void findOneShortPath(PathFinder pathFinder, List<Set<Integer>> steps, int target) {
        Set<Path> paths = pathFinder.find(steps, target);

        int numPaths = paths.stream()
                .mapToInt(Path::getMultiplicity)
                .sum();

        Iterator<Path> iterator = paths.iterator();

        Assertions.assertAll(
                () -> Assertions.assertEquals(1, numPaths),
                () -> Assertions.assertEquals(steps.size(), iterator.next().getSteps().size())
        );
    }

    @CartesianTest
    @CartesianTest.MethodFactory("withTwoShortPaths")
    void findTwoShortPaths(PathFinder pathFinder, List<Set<Integer>> steps, int target) {
        Set<Path> paths = pathFinder.find(steps, target);

        int numPaths = paths.stream()
                .mapToInt(Path::getMultiplicity)
                .sum();

        Iterator<Path> iterator = paths.iterator();

        Assertions.assertAll(
                () -> Assertions.assertEquals(2, numPaths),
                () -> Assertions.assertEquals(steps.size(), iterator.next().getSteps().size()),
                () -> Assertions.assertEquals(steps.size(), iterator.next().getSteps().size())
        );
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

    static ArgumentSets withOneShortPath() {
        return ArgumentSets
                .argumentsForFirstParameter(
                        getPathFinders()
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

    static ArgumentSets withTwoShortPaths() {
        return ArgumentSets
                .argumentsForFirstParameter(
                        getPathFinders()
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

    static List<PathFinder> getPathFinders() {
        return List.of(
                new NaivePathFinder()
        );
    }
}
