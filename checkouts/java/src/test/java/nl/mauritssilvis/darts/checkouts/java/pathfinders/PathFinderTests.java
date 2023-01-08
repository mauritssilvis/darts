/*
 * Copyright © 2023 Maurits H. Silvis
 * SPDX-License-Identifier: GPL-3.0-or-later
 */

package nl.mauritssilvis.darts.checkouts.java.pathfinders;

import nl.mauritssilvis.darts.checkouts.java.nodes.BasicNode;
import nl.mauritssilvis.darts.checkouts.java.nodes.Node;
import nl.mauritssilvis.darts.checkouts.java.paths.Path;
import org.junit.jupiter.api.Assertions;
import org.junitpioneer.jupiter.cartesian.ArgumentSets;
import org.junitpioneer.jupiter.cartesian.CartesianTest;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.Function;

class PathFinderTests {
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
            Collection<Node> nodes
    ) {
        int length = 2;

        PathFinder pathFinder = pathFinderFactory.apply(nodes);
        List<Path> paths = pathFinder.find(length);

        Assertions.assertEquals(0, paths.size());
    }

    @CartesianTest
    @CartesianTest.MethodFactory("withAllPathFindersAndUnreachableLengths")
    void handleUnreachableLengths(
            Function<Collection<Node>, PathFinder> pathFinderFactory,
            Collection<Node> nodes,
            int length
    ) {
        PathFinder pathFinder = pathFinderFactory.apply(nodes);
        List<Path> paths = pathFinder.find(length);

        Assertions.assertEquals(0, paths.size());
    }

    @CartesianTest
    @CartesianTest.MethodFactory("withAllPathFindersAndOneShortPath")
    void findOneShortPath(
            Function<Collection<Node>, PathFinder> pathFinderFactory,
            Collection<Node> nodes,
            int length
    ) {
        PathFinder pathFinder = pathFinderFactory.apply(nodes);
        List<Path> paths = pathFinder.find(length);

        long totalMultiplicity = PathFinderTestUtils.getTotalMultiplicity(paths);

        Assertions.assertAll(
                () -> Assertions.assertEquals(1, totalMultiplicity),
                () -> Assertions.assertEquals(nodes.size(), paths.get(0).getSize()),
                () -> Assertions.assertEquals(length, paths.get(0).getLength())
        );
    }

    @CartesianTest
    @CartesianTest.MethodFactory("withAllPathFindersAndTwoShortPaths")
    void findTwoShortPaths(
            Function<Collection<Node>, PathFinder> pathFinderFactory,
            Collection<Node> nodes,
            int length
    ) {
        PathFinder pathFinder = pathFinderFactory.apply(nodes);
        List<Path> paths = pathFinder.find(length);

        long totalMultiplicity = PathFinderTestUtils.getTotalMultiplicity(paths);
        int totalSize = getTotalSize(paths);
        int totalLength = getTotalLength(paths);

        Assertions.assertAll(
                () -> Assertions.assertEquals(2, totalMultiplicity),
                () -> Assertions.assertEquals(nodes.size(), paths.get(0).getSize()),
                () -> Assertions.assertEquals(nodes.size(), totalSize / paths.size()),
                () -> Assertions.assertEquals(length, paths.get(0).getLength()),
                () -> Assertions.assertEquals(length, totalLength / paths.size())
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
                        List.of(BasicNode.of()),
                        List.of(BasicNode.of(), BasicNode.of()),
                        List.of(BasicNode.of(), BasicNode.of(1, 2)),
                        List.of(BasicNode.of(1, 2), BasicNode.of()),
                        List.of(BasicNode.of(3), BasicNode.of(), BasicNode.of(-1))
                );
    }

    private static ArgumentSets withAllPathFindersAndUnreachableLengths() {
        return ArgumentSets
                .argumentsForFirstParameter(
                        getAllPathFinders()
                )
                .argumentsForNextParameter(
                        List.of(BasicNode.of(1)),
                        List.of(BasicNode.of(-3, 1)),
                        List.of(BasicNode.of(1), BasicNode.of(2)),
                        List.of(BasicNode.of(0, 2), BasicNode.of(3, -4)),
                        List.of(BasicNode.of(1), BasicNode.of(1), BasicNode.of(1)),
                        List.of(BasicNode.of(0), BasicNode.of(1, 2), BasicNode.of(2, 3))
                )
                .argumentsForNextParameter(
                        -1, 0, 2
                );
    }

    private static ArgumentSets withAllPathFindersAndOneShortPath() {
        return ArgumentSets
                .argumentsForFirstParameter(
                        getAllPathFinders()
                )
                .argumentsForNextParameter(
                        List.of(BasicNode.of(3)),
                        List.of(BasicNode.of(1, 3)),
                        List.of(BasicNode.of(1), BasicNode.of(2)),
                        List.of(BasicNode.of(2, 1), BasicNode.of(2, 3)),
                        List.of(BasicNode.of(1), BasicNode.of(1), BasicNode.of(1)),
                        List.of(BasicNode.of(1, 2), BasicNode.of(3, 1), BasicNode.of(4, 1, 2)),
                        List.of(BasicNode.of(0, 1, 2), BasicNode.of(1), BasicNode.of(1))
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
                        List.of(BasicNode.of(1, 3), BasicNode.of(1, 3)),
                        List.of(BasicNode.of(2, 3), BasicNode.of(2, 1)),
                        List.of(BasicNode.of(1, 4), BasicNode.of(0, 3)),
                        List.of(BasicNode.of(0), BasicNode.of(1, 2), BasicNode.of(2, 3)),
                        List.of(BasicNode.of(1, 2), BasicNode.of(3, 1), BasicNode.of(4, 0, 2)),
                        List.of(BasicNode.of(1, 2), BasicNode.of(1), BasicNode.of(2, 1))
                )
                .argumentsForNextParameter(
                        4
                );
    }

    private static List<Function<Collection<Node>, PathFinder>> getAllPathFinders() {
        return List.of(
                CartesianPathFinder::of
        );
    }

    private static int getTotalSize(Collection<? extends Path> paths) {
        return paths.stream()
                .mapToInt(Path::getSize)
                .sum();
    }

    private static int getTotalLength(Collection<? extends Path> paths) {
        return paths.stream()
                .mapToInt(Path::getLength)
                .sum();
    }
}
