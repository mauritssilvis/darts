/*
 * Copyright © 2023 Maurits H. Silvis
 * SPDX-License-Identifier: GPL-3.0-or-later
 */

package nl.mauritssilvis.darts.checkouts.java.paths.cartesian;

import nl.mauritssilvis.darts.checkouts.java.paths.Node;
import nl.mauritssilvis.darts.checkouts.java.paths.Path;
import nl.mauritssilvis.darts.checkouts.java.paths.PathFinder;
import nl.mauritssilvis.darts.checkouts.java.paths.common.BasicNodeTestUtils;
import nl.mauritssilvis.darts.checkouts.java.paths.common.PathTestUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Collection;
import java.util.List;
import java.util.stream.Stream;

class CartesianPathFinderTests {
    @ParameterizedTest
    @MethodSource("withPathData")
    void findPaths(Collection<Collection<Integer>> weights, int length, Collection<Collection<Integer>> steps) {
        Collection<Node> nodes = BasicNodeTestUtils.getNodes(weights);
        PathFinder pathFinder = CartesianPathFinder.of(nodes);
        List<Path> paths = pathFinder.find(length);

        Assertions.assertAll(
                () -> Assertions.assertEquals(nodes.size(), PathTestUtils.getTotalSize(paths) / paths.size()),
                () -> Assertions.assertEquals(length, PathTestUtils.getTotalLength(paths) / paths.size()),
                () -> Assertions.assertEquals(steps.size(), paths.size()),
                () -> Assertions.assertEquals(steps.size(), PathTestUtils.getTotalMultiplicity(paths)),
                () -> Assertions.assertEquals(steps, PathTestUtils.getAllSteps(paths))
        );
    }

    private static Stream<Arguments> withPathData() {
        return Stream.of(
                // Collection<Collection<Integer>> nodes,
                // int length,
                // Collection<Collection<Integer>> steps

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
}
