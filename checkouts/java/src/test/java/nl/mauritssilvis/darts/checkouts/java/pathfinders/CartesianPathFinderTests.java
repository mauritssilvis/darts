/*
 * Copyright © 2023 Maurits H. Silvis
 * SPDX-License-Identifier: GPL-3.0-or-later
 */

package nl.mauritssilvis.darts.checkouts.java.pathfinders;

import nl.mauritssilvis.darts.checkouts.java.nodes.Node;
import nl.mauritssilvis.darts.checkouts.java.nodes.UnsortedNode;
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
    void findASpecificShortPath(List<Node> steps) {
        PathFinder pathFinder = new CartesianPathFinder();
        int length = 10;

        List<Path> paths = pathFinder.find(steps, length);

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
    void findTwoSpecificShortPaths(List<Node> steps) {
        PathFinder pathFinder = new CartesianPathFinder();
        int length = 10;

        List<Path> paths = pathFinder.find(steps, length);

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
        Node step = UnsortedNode.of(1, 2, 3, 4, 5, 6, 7, 8, 9);
        List<Node> steps = List.of(step, step, step, step, step, step, step, step, step);
        int length = 9 * 9;

        PathFinder pathFinder = new CartesianPathFinder();
        List<Path> paths = pathFinder.find(steps, length);

        int numPaths = paths.stream()
                .mapToInt(Path::getMultiplicity)
                .sum();

        Assertions.assertAll(
                () -> Assertions.assertEquals(1, numPaths),
                () -> Assertions.assertEquals(List.of(9, 9, 9, 9, 9, 9, 9, 9, 9), paths.get(0).getSteps())
        );
    }

    static Collection<List<Node>> withASpecificLength10Path() {
        return List.of(
                List.of(UnsortedNode.of(3), UnsortedNode.of(2), UnsortedNode.of(5)),
                List.of(UnsortedNode.of(3, 2), UnsortedNode.of(2, 4), UnsortedNode.of(2, 5)),
                List.of(UnsortedNode.of(0, 3, 10), UnsortedNode.of(2, 4), UnsortedNode.of(1, 5))
        );
    }

    static Collection<List<Node>> withTwoSpecificLength10Paths() {
        return List.of(
                List.of(UnsortedNode.of(3, 2), UnsortedNode.of(3, 2), UnsortedNode.of(5)),
                List.of(UnsortedNode.of(3, 2), UnsortedNode.of(2, 4, 3), UnsortedNode.of(2, 5)),
                List.of(UnsortedNode.of(0, 3, 10, 2), UnsortedNode.of(3, 2, 4), UnsortedNode.of(1, 5))
        );
    }
}
