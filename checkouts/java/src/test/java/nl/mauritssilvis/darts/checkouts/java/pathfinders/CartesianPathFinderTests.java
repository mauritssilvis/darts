/*
 * Copyright © 2023 Maurits H. Silvis
 * SPDX-License-Identifier: GPL-3.0-or-later
 */

package nl.mauritssilvis.darts.checkouts.java.pathfinders;

import nl.mauritssilvis.darts.checkouts.java.nodes.BasicNode;
import nl.mauritssilvis.darts.checkouts.java.nodes.Node;
import nl.mauritssilvis.darts.checkouts.java.paths.Path;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

class CartesianPathFinderTests {
    @Test
    void storeIndependentNodes() {
        Node node = BasicNode.of(3, 5, 4);
        List<Node> nodes = new ArrayList<>(List.of(node, node));
        int length = 6;

        PathFinder pathFinder = CartesianPathFinder.of(nodes);
        long numPaths = PathFinderTests.getTotalMultiplicity(pathFinder.find(length));

        nodes.clear();

        long newNumPaths = PathFinderTests.getTotalMultiplicity(pathFinder.find(length));

        Assertions.assertEquals(numPaths, newNumPaths);
    }

    @ParameterizedTest
    @MethodSource("withASpecificLength10Path")
    void findASpecificShortPath(List<? extends Node> nodes) {
        PathFinder pathFinder = CartesianPathFinder.of(nodes);
        int length = 10;

        List<Path> paths = pathFinder.find(length);

        long numPaths = paths.stream()
                .mapToLong(Path::getMultiplicity)
                .sum();

        Assertions.assertAll(
                () -> Assertions.assertEquals(1, numPaths),
                () -> Assertions.assertEquals(List.of(3, 2, 5), paths.get(0).getSteps())
        );
    }

    @ParameterizedTest
    @MethodSource("withTwoSpecificLength10Paths")
    void findTwoSpecificShortPaths(List<? extends Node> nodes) {
        PathFinder pathFinder = CartesianPathFinder.of(nodes);
        int length = 10;

        List<Path> paths = pathFinder.find(length);

        long numPaths = paths.stream()
                .mapToLong(Path::getMultiplicity)
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
        Node node = BasicNode.of(1, 2, 3, 4, 5, 6, 7, 8, 9);
        List<Node> nodes = List.of(node, node, node, node, node, node, node, node, node);
        int length = 9 * 9;

        PathFinder pathFinder = CartesianPathFinder.of(nodes);
        List<Path> paths = pathFinder.find(length);

        long numPaths = paths.stream()
                .mapToLong(Path::getMultiplicity)
                .sum();

        Assertions.assertAll(
                () -> Assertions.assertEquals(1, numPaths),
                () -> Assertions.assertEquals(List.of(9, 9, 9, 9, 9, 9, 9, 9, 9), paths.get(0).getSteps())
        );
    }

    private static Collection<List<Node>> withASpecificLength10Path() {
        return List.of(
                List.of(BasicNode.of(3), BasicNode.of(2), BasicNode.of(5)),
                List.of(BasicNode.of(3, 2), BasicNode.of(2, 4), BasicNode.of(2, 5)),
                List.of(BasicNode.of(0, 3, 10), BasicNode.of(2, 4), BasicNode.of(1, 5))
        );
    }

    private static Collection<List<Node>> withTwoSpecificLength10Paths() {
        return List.of(
                List.of(BasicNode.of(3, 2), BasicNode.of(3, 2), BasicNode.of(5)),
                List.of(BasicNode.of(3, 2), BasicNode.of(2, 4, 3), BasicNode.of(2, 5)),
                List.of(BasicNode.of(0, 3, 10, 2), BasicNode.of(3, 2, 4), BasicNode.of(1, 5))
        );
    }
}
