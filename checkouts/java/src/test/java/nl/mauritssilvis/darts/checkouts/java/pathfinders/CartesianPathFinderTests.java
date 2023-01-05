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

import java.util.Collection;
import java.util.List;

class CartesianPathFinderTests {
    @ParameterizedTest
    @MethodSource("withASpecificLength10Path")
    void findASpecificShortPath(List<List<Integer>> steps) {
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

    @ParameterizedTest
    @MethodSource("withTwoSpecificLength10Paths")
    void findTwoSpecificShortPaths(List<List<Integer>> steps) {
        PathFinder pathFinder = new CartesianPathFinder();
        int target = 10;

        List<Path> paths = pathFinder.find(steps, target);

        int numPaths = paths.stream()
                .mapToInt(Path::getMultiplicity)
                .sum();

        List<List<Integer>> lists = paths.stream()
                .map(Path::getSteps)
                .toList();

        Assertions.assertAll(
                () -> Assertions.assertEquals(2, numPaths),
                () -> Assertions.assertEquals(List.of(List.of(3, 2, 5), List.of(2, 3, 5)), lists)
        );
    }

    @Test
    void findASpecificLongPath() {
        List<Integer> step = List.of(1, 2, 3, 4, 5, 6, 7, 8, 9);
        List<List<Integer>> steps = List.of(step, step, step, step, step, step, step, step, step);
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

    static Collection<List<List<Integer>>> withASpecificLength10Path() {
        return List.of(
                List.of(List.of(3), List.of(2), List.of(5)),
                List.of(List.of(3, 2), List.of(2, 4), List.of(2, 5)),
                List.of(List.of(0, 3, 10), List.of(2, 4), List.of(1, 5))
        );
    }

    static Collection<List<List<Integer>>> withTwoSpecificLength10Paths() {
        return List.of(
                List.of(List.of(3, 2), List.of(3, 2), List.of(5)),
                List.of(List.of(3, 2), List.of(2, 4, 3), List.of(2, 5)),
                List.of(List.of(0, 3, 10, 2), List.of(3, 2, 4), List.of(1, 5))
        );
    }
}
