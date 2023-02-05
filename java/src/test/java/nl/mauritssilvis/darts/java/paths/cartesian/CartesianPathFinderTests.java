/*
 * Copyright © 2023 Maurits H. Silvis
 * SPDX-License-Identifier: GPL-3.0-or-later
 */

package nl.mauritssilvis.darts.java.paths.cartesian;

import nl.mauritssilvis.darts.java.paths.Path;
import nl.mauritssilvis.darts.java.paths.PathTestUtils;
import nl.mauritssilvis.darts.java.paths.Pathfinder;
import nl.mauritssilvis.darts.java.paths.common.Node;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Collection;
import java.util.List;
import java.util.stream.Stream;

class CartesianPathfinderTests {
    @Test
    void getImmutablePaths() {
        Collection<Collection<Integer>> weightsPerNode = List.of(List.of(3, 4), List.of(6, 7));
        Collection<Node> nodes = BasicNodeTestUtils.getNodes(weightsPerNode);
        Pathfinder pathfinder = CartesianPathfinder.of(nodes);

        int length = 10;

        List<Path> paths = pathfinder.find(length);

        Assertions.assertThrows(UnsupportedOperationException.class, paths::clear);
    }

    @ParameterizedTest
    @MethodSource("withPathData")
    void findPaths(
            Collection<? extends Collection<Integer>> weightsPerNode,
            int length,
            Collection<? extends Collection<Integer>> stepsPerPath
    ) {
        Collection<Node> nodes = BasicNodeTestUtils.getNodes(weightsPerNode);
        Pathfinder pathfinder = CartesianPathfinder.of(nodes);
        List<Path> paths = pathfinder.find(length);

        int totalMultiplicity = stepsPerPath.size();

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
                        List.of(List.of(7))
                ),
                Arguments.of(
                        List.of(List.of(3), List.of(6)),
                        9,
                        List.of(List.of(3, 6))
                ),
                Arguments.of(
                        List.of(List.of(5), List.of(5)),
                        10,
                        List.of(List.of(5, 5))
                ),
                Arguments.of(
                        List.of(List.of(2, 4), List.of(6, 3, 7)),
                        8,
                        List.of(List.of(2, 6))
                ),
                Arguments.of(
                        List.of(List.of(1, 2), List.of(1, 2)),
                        4,
                        List.of(List.of(2, 2))
                ),
                Arguments.of(
                        List.of(List.of(1, 2), List.of(2, 1)),
                        3,
                        List.of(List.of(1, 2), List.of(2, 1))
                ),
                Arguments.of(
                        List.of(List.of(1, 2), List.of(1, 2, 3)),
                        3,
                        List.of(List.of(1, 2), List.of(2, 1))
                ),
                Arguments.of(
                        List.of(List.of(3), List.of(2), List.of(5)),
                        10,
                        List.of(List.of(3, 2, 5))
                ),
                Arguments.of(
                        List.of(List.of(3), List.of(2), List.of(2)),
                        7,
                        List.of(List.of(3, 2, 2))
                ),
                Arguments.of(
                        List.of(List.of(3, 2), List.of(2, 4), List.of(2, 5)),
                        10,
                        List.of(List.of(3, 2, 5))
                ),
                Arguments.of(
                        List.of(List.of(3, 2), List.of(2, 3), List.of(5)),
                        10,
                        List.of(List.of(3, 2, 5), List.of(2, 3, 5))
                ),
                Arguments.of(
                        List.of(List.of(2, 3), List.of(2, 3, 4), List.of(2, 5)),
                        10,
                        List.of(List.of(2, 3, 5), List.of(3, 2, 5))
                ),
                Arguments.of(
                        List.of(
                                List.of(1, 2, 4),
                                List.of(1, 2, 4),
                                List.of(1, 2, 4)
                        ),
                        7,
                        List.of(
                                List.of(1, 2, 4),
                                List.of(1, 4, 2),
                                List.of(2, 1, 4),
                                List.of(2, 4, 1),
                                List.of(4, 1, 2),
                                List.of(4, 2, 1)
                        )
                ),
                Arguments.of(
                        List.of(
                                List.of(7, 4, 3),
                                List.of(7, 4, 3),
                                List.of(7, 4, 3)
                        ),
                        14,
                        List.of(
                                List.of(7, 4, 3),
                                List.of(7, 3, 4),
                                List.of(4, 7, 3),
                                List.of(4, 3, 7),
                                List.of(3, 7, 4),
                                List.of(3, 4, 7)
                        )
                ),
                Arguments.of(
                        List.of(List.of(1, 2, 3), List.of(1, 2, 3), List.of(1, 2, 3)),
                        8,
                        List.of(List.of(2, 3, 3), List.of(3, 2, 3), List.of(3, 3, 2))
                ),
                Arguments.of(
                        List.of(List.of(1, 2, 3), List.of(1, 2, 3), List.of(1, 2, 3, 7)),
                        8,
                        List.of(List.of(2, 3, 3), List.of(3, 2, 3), List.of(3, 3, 2))
                ),
                Arguments.of(
                        List.of(
                                List.of(2, 3, 4),
                                List.of(2, 3, 4),
                                List.of(2, 3, 4)
                        ),
                        10,
                        List.of(
                                List.of(2, 4, 4),
                                List.of(3, 3, 4),
                                List.of(3, 4, 3),
                                List.of(4, 2, 4),
                                List.of(4, 3, 3),
                                List.of(4, 4, 2)
                        )
                ),
                Arguments.of(
                        List.of(
                                List.of(2, 3, 4, 7),
                                List.of(2, 3, 4),
                                List.of(2, 3, 4)
                        ),
                        10,
                        List.of(
                                List.of(2, 4, 4),
                                List.of(3, 3, 4),
                                List.of(3, 4, 3),
                                List.of(4, 2, 4),
                                List.of(4, 3, 3),
                                List.of(4, 4, 2)
                        )
                ),
                Arguments.of(
                        List.of(List.of(1, 3), List.of(1, 3), List.of(4, 7), List.of(4, 7)),
                        15,
                        List.of(List.of(1, 3, 4, 7), List.of(1, 3, 7, 4), List.of(3, 1, 4, 7), List.of(3, 1, 7, 4))
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
                        List.of(
                                List.of(1, 2, 4, 3, 6),
                                List.of(1, 2, 4, 6, 3),
                                List.of(2, 1, 4, 3, 6),
                                List.of(2, 1, 4, 6, 3)
                        )
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
                        List.of(
                                List.of(3, 2, 5, 2, 7, 7),
                                List.of(3, 2, 5, 7, 7, 2),
                                List.of(3, 2, 5, 7, 2, 7),
                                List.of(2, 3, 5, 2, 7, 7),
                                List.of(2, 3, 5, 7, 7, 2),
                                List.of(2, 3, 5, 7, 2, 7)
                        )
                )
        );
    }

    @Test
    void getEqualPathfinders() {
        Collection<Collection<Integer>> weightsPerNode1 = List.of(List.of(1, 2, 3), List.of(4, 5, 6));
        Collection<Node> nodes1 = BasicNodeTestUtils.getNodes(weightsPerNode1);
        Pathfinder pathfinder1 = CartesianPathfinder.of(nodes1);

        Collection<Collection<Integer>> weightsPerNode2 = List.of(List.of(1, 2, 3), List.of(4, 5, 6));
        Collection<Node> nodes2 = BasicNodeTestUtils.getNodes(weightsPerNode2);
        Pathfinder pathfinder2 = CartesianPathfinder.of(nodes2);

        Assertions.assertEquals(pathfinder1, pathfinder2);
    }

    @Test
    void getUnequalPathfinders() {
        Collection<Collection<Integer>> weightsPerNode1 = List.of(List.of(8, 7, 6));
        Collection<Node> nodes1 = BasicNodeTestUtils.getNodes(weightsPerNode1);
        Pathfinder pathfinder1 = CartesianPathfinder.of(nodes1);

        Collection<Collection<Integer>> weightsPerNode2 = List.of(List.of(6, 7, 8), List.of(6, 7, 8));
        Collection<Node> nodes2 = BasicNodeTestUtils.getNodes(weightsPerNode2);
        Pathfinder pathfinder2 = CartesianPathfinder.of(nodes2);

        Assertions.assertNotEquals(pathfinder1, pathfinder2);
    }
}
