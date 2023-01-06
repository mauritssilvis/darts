/*
 * Copyright © 2023 Maurits H. Silvis
 * SPDX-License-Identifier: GPL-3.0-or-later
 */

package nl.mauritssilvis.darts.checkouts.java.pathfinders;

import nl.mauritssilvis.darts.checkouts.java.paths.Path;
import org.junit.jupiter.api.Assertions;
import org.junitpioneer.jupiter.cartesian.ArgumentSets;
import org.junitpioneer.jupiter.cartesian.CartesianTest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class PathFinderTests {
    @CartesianTest
    @CartesianTest.MethodFactory("withAllPathFinders")
    void handleAbsentSteps(PathFinder pathFinder) {
        List<List<Integer>> steps = new ArrayList<>();
        int length = 0;

        List<Path> paths = pathFinder.find(steps, length);

        Assertions.assertEquals(0, paths.size());
    }

    @CartesianTest
    @CartesianTest.MethodFactory("withAllPathFindersAndEmptySteps")
    void handleEmptySteps(PathFinder pathFinder, List<List<Integer>> steps) {
        int length = 2;

        List<Path> paths = pathFinder.find(steps, length);

        Assertions.assertEquals(0, paths.size());
    }

    @CartesianTest
    @CartesianTest.MethodFactory("withAllPathFindersAndUnreachableLengths")
    void handleUnreachableLengths(PathFinder pathFinder, List<List<Integer>> steps, int length) {
        List<Path> paths = pathFinder.find(steps, length);

        Assertions.assertEquals(0, paths.size());
    }

    @CartesianTest
    @CartesianTest.MethodFactory("withAllPathFindersAndOneShortPath")
    void findOneShortPath(PathFinder pathFinder, List<List<Integer>> steps, int length) {
        List<Path> paths = pathFinder.find(steps, length);

        int numPaths = paths.stream()
                .mapToInt(Path::getMultiplicity)
                .sum();

        Assertions.assertAll(
                () -> Assertions.assertEquals(1, numPaths),
                () -> Assertions.assertEquals(steps.size(), paths.get(0).getSize())
        );
    }

    @CartesianTest
    @CartesianTest.MethodFactory("withAllPathFindersAndTwoShortPaths")
    void findTwoShortPaths(PathFinder pathFinder, List<List<Integer>> steps, int length) {
        List<Path> paths = pathFinder.find(steps, length);

        int numPaths = paths.stream()
                .mapToInt(Path::getMultiplicity)
                .sum();

        int totalSize = paths.stream()
                .mapToInt(Path::getSize)
                .sum();

        Assertions.assertAll(
                () -> Assertions.assertEquals(2, numPaths),
                () -> Assertions.assertEquals(steps.size(), paths.get(0).getSize()),
                () -> Assertions.assertEquals(steps.size(), totalSize / paths.size())
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
                        List.of(Collections.emptyList()),
                        List.of(Collections.emptyList(), Collections.emptyList()),
                        List.of(Collections.emptyList(), List.of(1, 2)),
                        List.of(List.of(1, 2), Collections.emptyList()),
                        List.of(List.of(3), Collections.emptyList(), List.of(-1))
                );
    }

    static ArgumentSets withAllPathFindersAndUnreachableLengths() {
        return ArgumentSets
                .argumentsForFirstParameter(
                        getAllPathFinders()
                )
                .argumentsForNextParameter(
                        List.of(List.of(1)),
                        List.of(List.of(-3, 1)),
                        List.of(List.of(1), List.of(2)),
                        List.of(List.of(0, 2), List.of(3, -4)),
                        List.of(List.of(1), List.of(1), List.of(1)),
                        List.of(List.of(0), List.of(1, 2), List.of(2, 3))
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
                        List.of(List.of(3)),
                        List.of(List.of(1, 3)),
                        List.of(List.of(1), List.of(2)),
                        List.of(List.of(2, 1), List.of(2, 3)),
                        List.of(List.of(1), List.of(1), List.of(1)),
                        List.of(List.of(1, 2), List.of(3, 1), List.of(4, 1, 2)),
                        List.of(List.of(0, 1, 2), List.of(1), List.of(1))
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
                        List.of(List.of(1, 3), List.of(1, 3)),
                        List.of(List.of(2, 3), List.of(2, 1)),
                        List.of(List.of(1, 4), List.of(0, 3)),
                        List.of(List.of(0), List.of(1, 2), List.of(2, 3)),
                        List.of(List.of(1, 2), List.of(3, 1), List.of(4, 0, 2)),
                        List.of(List.of(1, 2), List.of(1), List.of(2, 1))
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
