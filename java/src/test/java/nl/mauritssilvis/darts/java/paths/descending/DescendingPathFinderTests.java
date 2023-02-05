/*
 * Copyright © 2023 Maurits H. Silvis
 * SPDX-License-Identifier: GPL-3.0-or-later
 */

package nl.mauritssilvis.darts.java.paths.descending;

import nl.mauritssilvis.darts.java.paths.Path;
import nl.mauritssilvis.darts.java.paths.PathTestUtils;
import nl.mauritssilvis.darts.java.paths.Pathfinder;
import nl.mauritssilvis.darts.java.paths.cartesian.BasicNodeTestUtils;
import nl.mauritssilvis.darts.java.paths.common.Node;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Collection;
import java.util.List;
import java.util.stream.Stream;

class DescendingPathfinderTests {
    @Test
    void getImmutablePaths() {
        Collection<Collection<Integer>> weightsPerNode = List.of(List.of(4), List.of(4, 5));
        Collection<Node> nodes = BasicNodeTestUtils.getNodes(weightsPerNode);
        Pathfinder pathfinder = DescendingPathfinder.of(nodes);

        int length = 9;

        List<Path> paths = pathfinder.find(length);

        Assertions.assertThrows(UnsupportedOperationException.class, paths::clear);
    }

    @ParameterizedTest
    @MethodSource("withPathData")
    void findPaths(
            Collection<? extends Collection<Integer>> weightsPerNode,
            int length,
            Collection<? extends Collection<Integer>> stepsPerPath,
            int totalMultiplicity
    ) {
        Collection<Node> nodes = BasicNodeTestUtils.getNodes(weightsPerNode);
        Pathfinder pathfinder = DescendingPathfinder.of(nodes);
        List<Path> paths = pathfinder.find(length);

        Assertions.assertAll(
                () -> Assertions.assertEquals(length, paths.get(0).getLength()),
                () -> Assertions.assertEquals(length, PathTestUtils.getTotalLength(paths) / paths.size()),
                () -> Assertions.assertEquals(stepsPerPath.size(), paths.size()),
                () -> Assertions.assertEquals(stepsPerPath, PathTestUtils.getAllSteps(paths)),
                () -> Assertions.assertEquals(totalMultiplicity, PathTestUtils.getTotalMultiplicity(paths))
        );
    }

    private static Stream<Arguments> withPathData() {
        return Stream.of(
                Arguments.of(
                        List.of(List.of(7)),
                        7,
                        List.of(List.of(7)),
                        1
                ),
                Arguments.of(
                        List.of(List.of(3), List.of(6)),
                        9,
                        List.of(List.of(3, 6)),
                        1
                ),
                Arguments.of(
                        List.of(List.of(5), List.of(5)),
                        10,
                        List.of(List.of(5, 5)),
                        1
                ),
                Arguments.of(
                        List.of(List.of(2, 4), List.of(6, 3, 7)),
                        8,
                        List.of(List.of(2, 6)),
                        1
                ),
                Arguments.of(
                        List.of(List.of(1, 2), List.of(1, 2)),
                        4,
                        List.of(List.of(2, 2)),
                        1
                ),
                Arguments.of(
                        List.of(List.of(1, 2), List.of(2, 1)),
                        3,
                        List.of(List.of(2, 1)),
                        2
                ),
                Arguments.of(
                        List.of(List.of(1, 2), List.of(1, 2, 3)),
                        3,
                        List.of(List.of(2, 1), List.of(1, 2)),
                        2
                ),
                Arguments.of(
                        List.of(List.of(3), List.of(2), List.of(5)),
                        10,
                        List.of(List.of(3, 2, 5)),
                        1
                ),
                Arguments.of(
                        List.of(List.of(3), List.of(2), List.of(2)),
                        7,
                        List.of(List.of(3, 2, 2)),
                        1
                ),
                Arguments.of(
                        List.of(List.of(3, 2), List.of(2, 4), List.of(2, 5)),
                        10,
                        List.of(List.of(3, 2, 5)),
                        1
                ),
                Arguments.of(
                        List.of(List.of(3, 2), List.of(2, 3), List.of(5)),
                        10,
                        List.of(List.of(3, 2, 5)),
                        2
                ),
                Arguments.of(
                        List.of(List.of(2, 3), List.of(2, 3, 4), List.of(2, 5)),
                        10,
                        List.of(List.of(3, 2, 5), List.of(2, 3, 5)),
                        2
                ),
                Arguments.of(
                        List.of(
                                List.of(1, 2, 4),
                                List.of(1, 2, 4),
                                List.of(1, 2, 4)
                        ),
                        7,
                        List.of(List.of(4, 2, 1)),
                        6
                ),
                Arguments.of(
                        List.of(
                                List.of(7, 4, 3),
                                List.of(7, 4, 3),
                                List.of(7, 4, 3)
                        ),
                        14,
                        List.of(List.of(7, 4, 3)),
                        6
                ),
                Arguments.of(
                        List.of(List.of(1, 2, 3), List.of(1, 2, 3), List.of(1, 2, 3)),
                        8,
                        List.of(List.of(3, 3, 2)),
                        3
                ),
                Arguments.of(
                        List.of(List.of(1, 2, 3), List.of(1, 2, 3), List.of(1, 2, 3, 7)),
                        8,
                        List.of(List.of(3, 3, 2), List.of(3, 2, 3)),
                        3
                ),
                Arguments.of(
                        List.of(
                                List.of(2, 3, 4),
                                List.of(2, 3, 4),
                                List.of(2, 3, 4)
                        ),
                        10,
                        List.of(List.of(4, 4, 2), List.of(4, 3, 3)),
                        6
                ),
                Arguments.of(
                        List.of(
                                List.of(2, 3, 4, 7),
                                List.of(2, 3, 4),
                                List.of(2, 3, 4)
                        ),
                        10,
                        List.of(
                                List.of(4, 4, 2),
                                List.of(4, 3, 3),
                                List.of(3, 4, 3),
                                List.of(2, 4, 4)
                        ),
                        6
                ),
                Arguments.of(
                        List.of(
                                List.of(1, 3, 6, 10),
                                List.of(1, 3, 6, 10),
                                List.of(1, 3, 6, 10),
                                List.of(1, 3, 6, 10)
                        ),
                        20,
                        List.of(List.of(10, 6, 3, 1)),
                        24
                ),
                Arguments.of(
                        List.of(List.of(1, 3), List.of(1, 3), List.of(4, 7), List.of(4, 7)),
                        15,
                        List.of(List.of(3, 1, 7, 4)),
                        4
                ),
                Arguments.of(
                        List.of(
                                List.of(3, 17, 61, 7, 29),
                                List.of(3, 17, 61, 7, 29),
                                List.of(3, 17, 61, 7, 29),
                                List.of(3, 17, 61, 7, 29),
                                List.of(3, 17, 61, 7, 29)
                        ),
                        117,
                        List.of(List.of(61, 29, 17, 7, 3)),
                        120
                ),
                Arguments.of(
                        List.of(
                                List.of(1, 2),
                                List.of(1, 2),
                                List.of(4),
                                List.of(3, 6),
                                List.of(6, 3)
                        ),
                        16,
                        List.of(List.of(2, 1, 4, 6, 3)),
                        4
                ),
                Arguments.of(
                        List.of(
                                List.of(3, 2),
                                List.of(2, 3),
                                List.of(5),
                                List.of(2, 7),
                                List.of(7, 2),
                                List.of(7, 2)
                        ),
                        26,
                        List.of(List.of(3, 2, 5, 7, 7, 2)),
                        6
                ),
                Arguments.of(
                        List.of(
                                List.of(1, 2, 3, 4, 5, 6, 7, 8, 9),
                                List.of(1, 2, 3, 4, 5, 6, 7, 8, 9),
                                List.of(1, 2, 3, 4, 5, 6, 7, 8, 9),
                                List.of(1, 2, 3, 4, 5, 6, 7, 8, 9),
                                List.of(1, 2, 3, 4, 5, 6, 7, 8, 9),
                                List.of(1, 2, 3, 4, 5, 6, 7, 8, 9),
                                List.of(1, 2, 3, 4, 5, 6, 7, 8, 9),
                                List.of(1, 2, 3, 4, 5, 6, 7, 8, 9),
                                List.of(1, 2, 3, 4, 5, 6, 7, 8, 9)
                        ),
                        9,
                        List.of(List.of(1, 1, 1, 1, 1, 1, 1, 1, 1)),
                        1
                ),
                Arguments.of(
                        List.of(
                                List.of(1, 2, 3, 4, 5, 6, 7, 8, 9),
                                List.of(1, 2, 3, 4, 5, 6, 7, 8, 9),
                                List.of(1, 2, 3, 4, 5, 6, 7, 8, 9),
                                List.of(1, 2, 3, 4, 5, 6, 7, 8, 9),
                                List.of(1, 2, 3, 4, 5, 6, 7, 8, 9),
                                List.of(1, 2, 3, 4, 5, 6, 7, 8, 9),
                                List.of(1, 2, 3, 4, 5, 6, 7, 8, 9),
                                List.of(1, 2, 3, 4, 5, 6, 7, 8, 9),
                                List.of(1, 2, 3, 4, 5, 6, 7, 8, 9)
                        ),
                        81,
                        List.of(List.of(9, 9, 9, 9, 9, 9, 9, 9, 9)),
                        1
                )
        );
    }

    @Test
    void getEqualPathfinders() {
        Collection<Collection<Integer>> weightsPerNode1 = List.of(List.of(3, 6));
        Collection<Node> nodes1 = BasicNodeTestUtils.getNodes(weightsPerNode1);
        Pathfinder pathfinder1 = DescendingPathfinder.of(nodes1);

        Collection<Collection<Integer>> weightsPerNode2 = List.of(List.of(3, 6));
        Collection<Node> nodes2 = BasicNodeTestUtils.getNodes(weightsPerNode2);
        Pathfinder pathfinder2 = DescendingPathfinder.of(nodes2);

        Assertions.assertAll(
                () -> Assertions.assertEquals(pathfinder1, pathfinder2),
                () -> Assertions.assertEquals(pathfinder1.hashCode(), pathfinder2.hashCode())
        );
    }

    @Test
    void getUnequalPathfinders() {
        Collection<Collection<Integer>> weightsPerNode1 = List.of(List.of(4), List.of(8, 9));
        Collection<Node> nodes1 = BasicNodeTestUtils.getNodes(weightsPerNode1);
        Pathfinder pathfinder1 = DescendingPathfinder.of(nodes1);

        Collection<Collection<Integer>> weightsPerNode2 = List.of(List.of(5, 8), List.of(10));
        Collection<Node> nodes2 = BasicNodeTestUtils.getNodes(weightsPerNode2);
        Pathfinder pathfinder2 = DescendingPathfinder.of(nodes2);

        Assertions.assertNotEquals(pathfinder1, pathfinder2);
    }
}
