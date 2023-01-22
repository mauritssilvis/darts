/*
 * Copyright © 2023 Maurits H. Silvis
 * SPDX-License-Identifier: GPL-3.0-or-later
 */

package nl.mauritssilvis.darts.checkouts.java.paths.common;

import nl.mauritssilvis.darts.checkouts.java.paths.Path;
import nl.mauritssilvis.darts.checkouts.java.paths.PathFinder;
import nl.mauritssilvis.darts.checkouts.java.paths.cartesian.BasicNode;
import nl.mauritssilvis.darts.checkouts.java.paths.cartesian.CartesianPathFinder;
import nl.mauritssilvis.darts.checkouts.java.paths.descending.DescendingPathFinder;
import org.junit.jupiter.api.Assertions;
import org.junitpioneer.jupiter.cartesian.ArgumentSets;
import org.junitpioneer.jupiter.cartesian.CartesianTest;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;

class PathFinderTests {
    @CartesianTest
    @CartesianTest.MethodFactory("withAllPathFinders")
    void storeIndependentNodes(Function<Collection<Node>, PathFinder> pathFinderFactory) {
        Collection<Integer> weights = List.of(3, 5, 4);
        Node node = BasicNode.of(weights);
        Collection<Node> nodes = new ArrayList<>(List.of(node, node));

        int length = 6;

        PathFinder pathFinder = pathFinderFactory.apply(nodes);
        long totalMultiplicity = PathFinderTestUtils.getTotalMultiplicity(pathFinder.find(length));

        nodes.clear();

        long newTotalMultiplicity = PathFinderTestUtils.getTotalMultiplicity(pathFinder.find(length));

        Assertions.assertEquals(totalMultiplicity, newTotalMultiplicity);
    }

    @CartesianTest
    @CartesianTest.MethodFactory("withAllPathFinders")
    void handleAbsentNodes(Function<Collection<Node>, PathFinder> pathFinderFactory) {
        Collection<Node> nodes = new ArrayList<>();
        int length = 0;

        PathFinder pathFinder = pathFinderFactory.apply(nodes);
        List<Path> paths = pathFinder.find(length);

        Assertions.assertEquals(0, paths.size());
    }

    @CartesianTest
    @CartesianTest.MethodFactory("withAllPathFindersAndDisconnectedNodes")
    void handleDisconnectedNodes(
            Function<Collection<Node>, PathFinder> pathFinderFactory,
            Collection<Collection<Integer>> weights
    ) {
        Collection<Node> nodes = PathFinderTestUtils.getNodes(weights);
        int length = 2;

        PathFinder pathFinder = pathFinderFactory.apply(nodes);
        List<Path> paths = pathFinder.find(length);

        Assertions.assertEquals(0, paths.size());
    }

    @CartesianTest
    @CartesianTest.MethodFactory("withAllPathFindersAndUnreachableLengths")
    void handleUnreachableLengths(
            Function<Collection<Node>, PathFinder> pathFinderFactory,
            Collection<Collection<Integer>> weights,
            int length
    ) {
        Collection<Node> nodes = PathFinderTestUtils.getNodes(weights);

        PathFinder pathFinder = pathFinderFactory.apply(nodes);
        List<Path> paths = pathFinder.find(length);

        Assertions.assertEquals(0, paths.size());
    }

    @CartesianTest
    @CartesianTest.MethodFactory("withAllPathFindersAndMultiplePaths")
    void countTheSteps(
            Function<Collection<Node>, PathFinder> pathFinderFactory,
            Collection<Collection<Integer>> weights,
            int length
    ) {
        Collection<Node> nodes = PathFinderTestUtils.getNodes(weights);

        PathFinder pathFinder = pathFinderFactory.apply(nodes);
        List<Path> paths = pathFinder.find(length);

        int totalSize = PathFinderTestUtils.getTotalSize(paths);

        Assertions.assertAll(
                () -> Assertions.assertEquals(nodes.size(), paths.get(0).countSteps()),
                () -> Assertions.assertEquals(nodes.size(), totalSize / paths.size())
        );
    }

    @CartesianTest
    @CartesianTest.MethodFactory("withAllPathFindersAndMultiplePaths")
    void getThePathLength(
            Function<Collection<Node>, PathFinder> pathFinderFactory,
            Collection<Collection<Integer>> weights,
            int length
    ) {
        Collection<Node> nodes = PathFinderTestUtils.getNodes(weights);

        PathFinder pathFinder = pathFinderFactory.apply(nodes);
        List<Path> paths = pathFinder.find(length);

        int totalLength = PathFinderTestUtils.getTotalLength(paths);

        Assertions.assertAll(
                () -> Assertions.assertEquals(length, paths.get(0).getLength()),
                () -> Assertions.assertEquals(length, totalLength / paths.size())
        );
    }

    @CartesianTest
    @CartesianTest.MethodFactory("withAllPathFindersAndOneShortPath")
    void findOneShortPath(
            Function<Collection<Node>, PathFinder> pathFinderFactory,
            Collection<Collection<Integer>> weights,
            int length
    ) {
        Collection<Node> nodes = PathFinderTestUtils.getNodes(weights);

        PathFinder pathFinder = pathFinderFactory.apply(nodes);
        List<Path> paths = pathFinder.find(length);

        long totalMultiplicity = PathFinderTestUtils.getTotalMultiplicity(paths);

        Assertions.assertAll(
                () -> Assertions.assertEquals(1, totalMultiplicity)
        );
    }

    @CartesianTest
    @CartesianTest.MethodFactory("withAllPathFindersAndTwoShortPaths")
    void findTwoShortPaths(
            Function<Collection<Node>, PathFinder> pathFinderFactory,
            Collection<Collection<Integer>> weights,
            int length
    ) {
        Collection<Node> nodes = PathFinderTestUtils.getNodes(weights);

        PathFinder pathFinder = pathFinderFactory.apply(nodes);
        List<Path> paths = pathFinder.find(length);

        long totalMultiplicity = PathFinderTestUtils.getTotalMultiplicity(paths);

        Assertions.assertAll(
                () -> Assertions.assertEquals(2, totalMultiplicity)
        );
    }

    @CartesianTest
    @CartesianTest.MethodFactory("withAllPathFindersAndThreeShortPaths")
    void findThreeShortPaths(
            Function<Collection<Node>, PathFinder> pathFinderFactory,
            Collection<Collection<Integer>> weights,
            int length
    ) {
        Collection<Node> nodes = PathFinderTestUtils.getNodes(weights);

        PathFinder pathFinder = pathFinderFactory.apply(nodes);
        List<Path> paths = pathFinder.find(length);

        long totalMultiplicity = PathFinderTestUtils.getTotalMultiplicity(paths);

        Assertions.assertAll(
                () -> Assertions.assertEquals(3, totalMultiplicity)
        );
    }

    @CartesianTest
    @CartesianTest.MethodFactory("withAllPathFindersAndFourShortPaths")
    void findFourShortPaths(
            Function<Collection<Node>, PathFinder> pathFinderFactory,
            Collection<Collection<Integer>> weights,
            int length
    ) {
        Collection<Node> nodes = PathFinderTestUtils.getNodes(weights);

        PathFinder pathFinder = pathFinderFactory.apply(nodes);
        List<Path> paths = pathFinder.find(length);

        long totalMultiplicity = PathFinderTestUtils.getTotalMultiplicity(paths);

        Assertions.assertAll(
                () -> Assertions.assertEquals(4, totalMultiplicity)
        );
    }

    @CartesianTest
    @CartesianTest.MethodFactory("withAllPathFindersAndSixShortPaths")
    void findSixShortPaths(
            Function<Collection<Node>, PathFinder> pathFinderFactory,
            Collection<Collection<Integer>> weights,
            int length
    ) {
        Collection<Node> nodes = PathFinderTestUtils.getNodes(weights);

        PathFinder pathFinder = pathFinderFactory.apply(nodes);
        List<Path> paths = pathFinder.find(length);

        long totalMultiplicity = PathFinderTestUtils.getTotalMultiplicity(paths);

        Assertions.assertAll(
                () -> Assertions.assertEquals(6, totalMultiplicity)
        );
    }

    @CartesianTest
    @CartesianTest.MethodFactory("withAllPathFindersAndTwelveShortPaths")
    void findTwelveShortPaths(
            Function<Collection<Node>, PathFinder> pathFinderFactory,
            Collection<Collection<Integer>> weights,
            int length
    ) {
        Collection<Node> nodes = PathFinderTestUtils.getNodes(weights);

        PathFinder pathFinder = pathFinderFactory.apply(nodes);
        List<Path> paths = pathFinder.find(length);

        long totalMultiplicity = PathFinderTestUtils.getTotalMultiplicity(paths);

        Assertions.assertAll(
                () -> Assertions.assertEquals(12, totalMultiplicity)
        );
    }

    private static ArgumentSets withAllPathFinders() {
        return ArgumentSets
                .argumentsForFirstParameter(
                        getAllPathFinders()
                );
    }

    private static ArgumentSets withAllPathFindersAndDisconnectedNodes() {
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

    private static ArgumentSets withAllPathFindersAndUnreachableLengths() {
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

    private static ArgumentSets withAllPathFindersAndMultiplePaths() {
        return ArgumentSets
                .argumentsForFirstParameter(
                        getAllPathFinders()
                )
                .argumentsForNextParameter(
                        List.of(List.of(5, 3), List.of(1, 4, 5)),
                        List.of(List.of(3, 4), List.of(4, 3), List.of(1, 2))
                )
                .argumentsForNextParameter(
                        7, 8, 9, 10
                );
    }

    private static ArgumentSets withAllPathFindersAndOneShortPath() {
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

    private static ArgumentSets withAllPathFindersAndTwoShortPaths() {
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

    private static ArgumentSets withAllPathFindersAndThreeShortPaths() {
        return ArgumentSets
                .argumentsForFirstParameter(
                        getAllPathFinders()
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

    private static ArgumentSets withAllPathFindersAndFourShortPaths() {
        return ArgumentSets
                .argumentsForFirstParameter(
                        getAllPathFinders()
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

    private static ArgumentSets withAllPathFindersAndSixShortPaths() {
        return ArgumentSets
                .argumentsForFirstParameter(
                        getAllPathFinders()
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

    private static ArgumentSets withAllPathFindersAndTwelveShortPaths() {
        return ArgumentSets
                .argumentsForFirstParameter(
                        getAllPathFinders()
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

    private static List<Function<Collection<Node>, PathFinder>> getAllPathFinders() {
        return List.of(
                CartesianPathFinder::of,
                DescendingPathFinder::of
        );
    }

}
