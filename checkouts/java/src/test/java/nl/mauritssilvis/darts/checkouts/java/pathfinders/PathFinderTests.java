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
import java.util.List;

class PathFinderTests {
    @CartesianTest
    @CartesianTest.MethodFactory("withAllPathFinders")
    void handleAbsentNodes(PathFinder pathFinder) {
        List<Node> nodes = new ArrayList<>();
        int length = 0;

        List<Path> paths = pathFinder.find(nodes, length);

        Assertions.assertEquals(0, paths.size());
    }

    @CartesianTest
    @CartesianTest.MethodFactory("withAllPathFindersAndDisconnectedNodes")
    void handleDisconnectedNodes(PathFinder pathFinder, List<Node> nodes) {
        int length = 2;

        List<Path> paths = pathFinder.find(nodes, length);

        Assertions.assertEquals(0, paths.size());
    }

    @CartesianTest
    @CartesianTest.MethodFactory("withAllPathFindersAndUnreachableLengths")
    void handleUnreachableLengths(PathFinder pathFinder, List<Node> nodes, int length) {
        List<Path> paths = pathFinder.find(nodes, length);

        Assertions.assertEquals(0, paths.size());
    }

    @CartesianTest
    @CartesianTest.MethodFactory("withAllPathFindersAndOneShortPath")
    void findOneShortPath(PathFinder pathFinder, List<Node> nodes, int length) {
        List<Path> paths = pathFinder.find(nodes, length);

        long numPaths = paths.stream()
                .mapToLong(Path::getMultiplicity)
                .sum();

        Assertions.assertAll(
                () -> Assertions.assertEquals(1, numPaths),
                () -> Assertions.assertEquals(nodes.size(), paths.get(0).getSize()),
                () -> Assertions.assertEquals(length, paths.get(0).getLength())
        );
    }

    @CartesianTest
    @CartesianTest.MethodFactory("withAllPathFindersAndTwoShortPaths")
    void findTwoShortPaths(PathFinder pathFinder, List<Node> nodes, int length) {
        List<Path> paths = pathFinder.find(nodes, length);

        long numPaths = paths.stream()
                .mapToLong(Path::getMultiplicity)
                .sum();

        int totalSize = paths.stream()
                .mapToInt(Path::getSize)
                .sum();

        int totalLength = paths.stream()
                .mapToInt(Path::getLength)
                .sum();

        Assertions.assertAll(
                () -> Assertions.assertEquals(2, numPaths),
                () -> Assertions.assertEquals(nodes.size(), paths.get(0).getSize()),
                () -> Assertions.assertEquals(nodes.size(), totalSize / paths.size()),
                () -> Assertions.assertEquals(length, totalLength / paths.size())
        );
    }

    private static ArgumentSets withAllPathFinders() {
        return ArgumentSets
                .argumentsForFirstParameter(
                        getAllPathFinders()
                );
    }

    static ArgumentSets withAllPathFindersAndDisconnectedNodes() {
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

    static ArgumentSets withAllPathFindersAndUnreachableLengths() {
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

    static ArgumentSets withAllPathFindersAndOneShortPath() {
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

    static ArgumentSets withAllPathFindersAndTwoShortPaths() {
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

    static List<PathFinder> getAllPathFinders() {
        return List.of(
                new CartesianPathFinder()
        );
    }
}
