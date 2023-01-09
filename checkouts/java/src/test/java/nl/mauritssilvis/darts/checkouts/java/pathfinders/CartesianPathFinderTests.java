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
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Stream;

class CartesianPathFinderTests {
    @Test
    void storeIndependentNodes() {
        Node node = BasicNode.of(3, 5, 4);
        Collection<Node> nodes = new ArrayList<>(List.of(node, node));
        int length = 6;

        PathFinder pathFinder = CartesianPathFinder.of(nodes);
        long totalMultiplicity = PathFinderTestUtils.getTotalMultiplicity(pathFinder.find(length));

        nodes.clear();

        long newTotalMultiplicity = PathFinderTestUtils.getTotalMultiplicity(pathFinder.find(length));

        Assertions.assertEquals(totalMultiplicity, newTotalMultiplicity);
    }

    @ParameterizedTest
    @MethodSource("withPathData")
    void findPaths(Collection<? extends Node> nodes, int length, Collection<Collection<Integer>> steps) {
        PathFinder pathFinder = CartesianPathFinder.of(nodes);
        List<Path> paths = pathFinder.find(length);

        Assertions.assertAll(
                () -> Assertions.assertEquals(nodes.size(), PathFinderTestUtils.getTotalSize(paths) / paths.size()),
                () -> Assertions.assertEquals(length, PathFinderTestUtils.getTotalLength(paths) / paths.size()),
                () -> Assertions.assertEquals(steps.size(), paths.size()),
                () -> Assertions.assertEquals(steps.size(), PathFinderTestUtils.getTotalMultiplicity(paths)),
                () -> Assertions.assertEquals(steps, PathFinderTestUtils.getAllSteps(paths))
        );
    }

    @Test
    void findASpecificLongPath() {
        Node node = BasicNode.of(1, 2, 3, 4, 5, 6, 7, 8, 9);
        Collection<Node> nodes = List.of(node, node, node, node, node, node, node, node, node);
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

    private static Stream<Arguments> withPathData() {
        return Stream.of(
                // Collection<? extends Node> nodes,
                // int length,
                // Collection<Collection<Integer>> steps

                Arguments.of(
                        List.of(BasicNode.of(1)),
                        1,
                        List.of(List.of(1))
                ),
                Arguments.of(
                        List.of(BasicNode.of(1, 2), BasicNode.of(2, 3)),
                        5,
                        List.of(List.of(2, 3))
                ),
                Arguments.of(
                        List.of(BasicNode.of(1, 2), BasicNode.of(1, 2)),
                        4,
                        List.of(List.of(2, 2))
                ),
                Arguments.of(
                        List.of(BasicNode.of(3), BasicNode.of(2), BasicNode.of(5)),
                        10,
                        List.of(List.of(3, 2, 5))
                ),
                Arguments.of(
                        List.of(BasicNode.of(3, 2), BasicNode.of(2, 4), BasicNode.of(2, 5)),
                        10,
                        List.of(List.of(3, 2, 5))
                ),
                Arguments.of(
                        List.of(BasicNode.of(0, 3, 10), BasicNode.of(2, 4), BasicNode.of(1, 5)),
                        10,
                        List.of(List.of(3, 2, 5))
                ),
                Arguments.of(
                        List.of(BasicNode.of(1, 2), BasicNode.of(2, 1)),
                        3,
                        List.of(List.of(1, 2), List.of(2, 1))
                ),
                Arguments.of(
                        List.of(BasicNode.of(3, 2), BasicNode.of(3, 2), BasicNode.of(5)),
                        10,
                        List.of(List.of(3, 2, 5), List.of(2, 3, 5))
                ),
                Arguments.of(
                        List.of(BasicNode.of(3, 2), BasicNode.of(2, 4, 3), BasicNode.of(2, 5)),
                        10,
                        List.of(List.of(3, 2, 5), List.of(2, 3, 5))
                ),
                Arguments.of(
                        List.of(BasicNode.of(0, 3, 10, 2), BasicNode.of(3, 2, 4), BasicNode.of(1, 5)),
                        10,
                        List.of(List.of(3, 2, 5), List.of(2, 3, 5))
                ),
                Arguments.of(
                        List.of(BasicNode.of(1, 2, 3), BasicNode.of(1, 2, 3), BasicNode.of(1, 2, 3)),
                        8,
                        List.of(List.of(2, 3, 3), List.of(3, 2, 3), List.of(3, 3, 2))
                ),
                Arguments.of(
                        List.of(BasicNode.of(2, 3, 4), BasicNode.of(2, 3, 4), BasicNode.of(2, 3, 4)),
                        10,
                        List.of(List.of(2, 4, 4), List.of(3, 3, 4), List.of(3, 4, 3), List.of(4, 2, 4), List.of(4, 3, 3), List.of(4, 4, 2))
                )
        );
    }
}
