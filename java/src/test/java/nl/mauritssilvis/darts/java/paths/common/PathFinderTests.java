/*
 * Copyright © 2023 Maurits H. Silvis
 * SPDX-License-Identifier: GPL-3.0-or-later
 */

package nl.mauritssilvis.darts.java.paths.common;

import nl.mauritssilvis.darts.java.paths.Path;
import nl.mauritssilvis.darts.java.paths.PathTestUtils;
import nl.mauritssilvis.darts.java.paths.Pathfinder;
import nl.mauritssilvis.darts.java.paths.cartesian.CartesianPathfinder;
import nl.mauritssilvis.darts.java.paths.descending.DescendingPathfinder;
import nl.mauritssilvis.darts.java.paths.cartesian.BasicNodeTestUtils;
import org.junit.jupiter.api.Assertions;
import org.junitpioneer.jupiter.cartesian.ArgumentSets;
import org.junitpioneer.jupiter.cartesian.CartesianTest;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;

class PathfinderTests {
    @CartesianTest
    @CartesianTest.MethodFactory("withAllPathfinders")
    void storeIndependentNodes(Function<? super Collection<Node>, ? extends Pathfinder> pathfinderFactory) {
        Collection<Integer> weights = List.of(3, 5, 4);

        Collection<Node> nodes = new ArrayList<>(
                BasicNodeTestUtils.getNodes(List.of(weights, weights))
        );

        int length = 6;

        Pathfinder pathfinder = pathfinderFactory.apply(nodes);
        long totalMultiplicity = PathTestUtils.getTotalMultiplicity(pathfinder.find(length));

        nodes.clear();

        long newTotalMultiplicity = PathTestUtils.getTotalMultiplicity(pathfinder.find(length));

        Assertions.assertEquals(totalMultiplicity, newTotalMultiplicity);
    }

    @CartesianTest
    @CartesianTest.MethodFactory("withAllPathfinders")
    void handleAbsentNodes(Function<? super Collection<Node>, ? extends Pathfinder> pathfinderFactory) {
        Collection<Node> nodes = Collections.emptyList();
        int length = 0;

        Pathfinder pathfinder = pathfinderFactory.apply(nodes);
        List<Path> paths = pathfinder.find(length);

        Assertions.assertEquals(0, paths.size());
    }

    @CartesianTest
    @CartesianTest.MethodFactory("withAllPathfindersAndDisconnectedNodes")
    void handleDisconnectedNodes(
            Function<? super Collection<Node>, ? extends Pathfinder> pathfinderFactory,
            Collection<? extends Collection<Integer>> weights
    ) {
        Collection<Node> nodes = BasicNodeTestUtils.getNodes(weights);
        int length = 2;

        Pathfinder pathfinder = pathfinderFactory.apply(nodes);
        List<Path> paths = pathfinder.find(length);

        Assertions.assertEquals(0, paths.size());
    }

    @CartesianTest
    @CartesianTest.MethodFactory("withAllPathfindersAndUnreachableLengths")
    void handleUnreachableLengths(
            Function<? super Collection<Node>, ? extends Pathfinder> pathfinderFactory,
            Collection<? extends Collection<Integer>> weights,
            int length
    ) {
        Collection<Node> nodes = BasicNodeTestUtils.getNodes(weights);

        Pathfinder pathfinder = pathfinderFactory.apply(nodes);
        List<Path> paths = pathfinder.find(length);

        Assertions.assertEquals(0, paths.size());
    }

    @CartesianTest
    @CartesianTest.MethodFactory("withAllPathfindersAndMultiplePaths")
    void getThePathLength(
            Function<? super Collection<Node>, ? extends Pathfinder> pathfinderFactory,
            Collection<? extends Collection<Integer>> weights,
            int length
    ) {
        Collection<Node> nodes = BasicNodeTestUtils.getNodes(weights);

        Pathfinder pathfinder = pathfinderFactory.apply(nodes);
        List<Path> paths = pathfinder.find(length);

        int totalLength = PathTestUtils.getTotalLength(paths);

        Assertions.assertAll(
                () -> Assertions.assertEquals(length, paths.get(0).getLength()),
                () -> Assertions.assertEquals(length, totalLength / paths.size())
        );
    }

    @CartesianTest
    @CartesianTest.MethodFactory("withAllPathfindersAndOneShortPath")
    void findOneShortPath(
            Function<? super Collection<Node>, ? extends Pathfinder> pathfinderFactory,
            Collection<? extends Collection<Integer>> weights,
            int length
    ) {
        Collection<Node> nodes = BasicNodeTestUtils.getNodes(weights);

        Pathfinder pathfinder = pathfinderFactory.apply(nodes);
        List<Path> paths = pathfinder.find(length);

        long totalMultiplicity = PathTestUtils.getTotalMultiplicity(paths);

        Assertions.assertAll(
                () -> Assertions.assertEquals(1, totalMultiplicity)
        );
    }

    @CartesianTest
    @CartesianTest.MethodFactory("withAllPathfindersAndTwoShortPaths")
    void findTwoShortPaths(
            Function<? super Collection<Node>, ? extends Pathfinder> pathfinderFactory,
            Collection<? extends Collection<Integer>> weights,
            int length
    ) {
        Collection<Node> nodes = BasicNodeTestUtils.getNodes(weights);

        Pathfinder pathfinder = pathfinderFactory.apply(nodes);
        List<Path> paths = pathfinder.find(length);

        long totalMultiplicity = PathTestUtils.getTotalMultiplicity(paths);

        Assertions.assertAll(
                () -> Assertions.assertEquals(2, totalMultiplicity)
        );
    }

    @CartesianTest
    @CartesianTest.MethodFactory("withAllPathfindersAndThreeShortPaths")
    void findThreeShortPaths(
            Function<? super Collection<Node>, ? extends Pathfinder> pathfinderFactory,
            Collection<? extends Collection<Integer>> weights,
            int length
    ) {
        Collection<Node> nodes = BasicNodeTestUtils.getNodes(weights);

        Pathfinder pathfinder = pathfinderFactory.apply(nodes);
        List<Path> paths = pathfinder.find(length);

        long totalMultiplicity = PathTestUtils.getTotalMultiplicity(paths);

        Assertions.assertAll(
                () -> Assertions.assertEquals(3, totalMultiplicity)
        );
    }

    @CartesianTest
    @CartesianTest.MethodFactory("withAllPathfindersAndFourShortPaths")
    void findFourShortPaths(
            Function<? super Collection<Node>, ? extends Pathfinder> pathfinderFactory,
            Collection<? extends Collection<Integer>> weights,
            int length
    ) {
        Collection<Node> nodes = BasicNodeTestUtils.getNodes(weights);

        Pathfinder pathfinder = pathfinderFactory.apply(nodes);
        List<Path> paths = pathfinder.find(length);

        long totalMultiplicity = PathTestUtils.getTotalMultiplicity(paths);

        Assertions.assertAll(
                () -> Assertions.assertEquals(4, totalMultiplicity)
        );
    }

    @CartesianTest
    @CartesianTest.MethodFactory("withAllPathfindersAndSixShortPaths")
    void findSixShortPaths(
            Function<? super Collection<Node>, ? extends Pathfinder> pathfinderFactory,
            Collection<? extends Collection<Integer>> weights,
            int length
    ) {
        Collection<Node> nodes = BasicNodeTestUtils.getNodes(weights);

        Pathfinder pathfinder = pathfinderFactory.apply(nodes);
        List<Path> paths = pathfinder.find(length);

        long totalMultiplicity = PathTestUtils.getTotalMultiplicity(paths);

        Assertions.assertAll(
                () -> Assertions.assertEquals(6, totalMultiplicity)
        );
    }

    @CartesianTest
    @CartesianTest.MethodFactory("withAllPathfindersAndTwelveShortPaths")
    void findTwelveShortPaths(
            Function<? super Collection<Node>, ? extends Pathfinder> pathfinderFactory,
            Collection<? extends Collection<Integer>> weights,
            int length
    ) {
        Collection<Node> nodes = BasicNodeTestUtils.getNodes(weights);

        Pathfinder pathfinder = pathfinderFactory.apply(nodes);
        List<Path> paths = pathfinder.find(length);

        long totalMultiplicity = PathTestUtils.getTotalMultiplicity(paths);

        Assertions.assertAll(
                () -> Assertions.assertEquals(12, totalMultiplicity)
        );
    }

    private static ArgumentSets withAllPathfinders() {
        return ArgumentSets
                .argumentsForFirstParameter(
                        getAllPathfinders()
                );
    }

    private static ArgumentSets withAllPathfindersAndDisconnectedNodes() {
        return ArgumentSets
                .argumentsForFirstParameter(
                        getAllPathfinders()
                )
                .argumentsForNextParameter(
                        List.of(Collections.emptyList()),
                        List.of(Collections.emptyList(), Collections.emptyList()),
                        List.of(Collections.emptyList(), List.of(1, 2)),
                        List.of(List.of(1, 2), Collections.emptyList()),
                        List.of(List.of(3), Collections.emptyList(), List.of(-1))
                );
    }

    private static ArgumentSets withAllPathfindersAndUnreachableLengths() {
        return ArgumentSets
                .argumentsForFirstParameter(
                        getAllPathfinders()
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

    private static ArgumentSets withAllPathfindersAndMultiplePaths() {
        return ArgumentSets
                .argumentsForFirstParameter(
                        getAllPathfinders()
                )
                .argumentsForNextParameter(
                        List.of(List.of(5, 3), List.of(1, 4, 5)),
                        List.of(List.of(3, 4), List.of(4, 3), List.of(1, 2))
                )
                .argumentsForNextParameter(
                        7, 8, 9, 10
                );
    }

    private static ArgumentSets withAllPathfindersAndOneShortPath() {
        return ArgumentSets
                .argumentsForFirstParameter(
                        getAllPathfinders()
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

    private static ArgumentSets withAllPathfindersAndTwoShortPaths() {
        return ArgumentSets
                .argumentsForFirstParameter(
                        getAllPathfinders()
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

    private static ArgumentSets withAllPathfindersAndThreeShortPaths() {
        return ArgumentSets
                .argumentsForFirstParameter(
                        getAllPathfinders()
                )
                .argumentsForNextParameter(
                        List.of(List.of(4, 3), List.of(4, 3), List.of(3, 4)),
                        List.of(List.of(2, 4, 3), List.of(6, 7, 8)),
                        List.of(
                                List.of(3, 4),
                                List.of(4, 3),
                                List.of(0, 2),
                                List.of(2, 0)
                        )
                )
                .argumentsForNextParameter(
                        10
                );
    }

    private static ArgumentSets withAllPathfindersAndFourShortPaths() {
        return ArgumentSets
                .argumentsForFirstParameter(
                        getAllPathfinders()
                )
                .argumentsForNextParameter(
                        (Object) List.of(
                                List.of(1, 2),
                                List.of(1, 2),
                                List.of(1, 2),
                                List.of(2, 1)
                        )
                )
                .argumentsForNextParameter(
                        5
                );
    }

    private static ArgumentSets withAllPathfindersAndSixShortPaths() {
        return ArgumentSets
                .argumentsForFirstParameter(
                        getAllPathfinders()
                )
                .argumentsForNextParameter(
                        (Object) List.of(
                                List.of(1, 3),
                                List.of(1, 3),
                                List.of(3, 1),
                                List.of(3, 1)
                        )
                )
                .argumentsForNextParameter(
                        8
                );
    }

    private static ArgumentSets withAllPathfindersAndTwelveShortPaths() {
        return ArgumentSets
                .argumentsForFirstParameter(
                        getAllPathfinders()
                )
                .argumentsForNextParameter(
                        (Object) List.of(
                                List.of(1, 2, 3, 4),
                                List.of(3, 4, 2, 1),
                                List.of(2, 1, 4, 3)
                        )
                )
                .argumentsForNextParameter(
                        7
                );
    }

    private static List<Function<Collection<Node>, Pathfinder>> getAllPathfinders() {
        return List.of(
                CartesianPathfinder::of,
                DescendingPathfinder::of
        );
    }

}
