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

        int numPaths = paths.stream()
                .mapToInt(Path::getMultiplicity)
                .sum();

        Assertions.assertAll(
                () -> Assertions.assertEquals(1, numPaths),
                () -> Assertions.assertEquals(nodes.size(), paths.get(0).getSize())
        );
    }

    @CartesianTest
    @CartesianTest.MethodFactory("withAllPathFindersAndTwoShortPaths")
    void findTwoShortPaths(PathFinder pathFinder, List<Node> nodes, int length) {
        List<Path> paths = pathFinder.find(nodes, length);

        int numPaths = paths.stream()
                .mapToInt(Path::getMultiplicity)
                .sum();

        int totalSize = paths.stream()
                .mapToInt(Path::getSize)
                .sum();

        Assertions.assertAll(
                () -> Assertions.assertEquals(2, numPaths),
                () -> Assertions.assertEquals(nodes.size(), paths.get(0).getSize()),
                () -> Assertions.assertEquals(nodes.size(), totalSize / paths.size())
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
                        List.of(BasicNode.with()),
                        List.of(BasicNode.with(), BasicNode.with()),
                        List.of(BasicNode.with(), BasicNode.with(1, 2)),
                        List.of(BasicNode.with(1, 2), BasicNode.with()),
                        List.of(BasicNode.with(3), BasicNode.with(), BasicNode.with(-1))
                );
    }

    static ArgumentSets withAllPathFindersAndUnreachableLengths() {
        return ArgumentSets
                .argumentsForFirstParameter(
                        getAllPathFinders()
                )
                .argumentsForNextParameter(
                        List.of(BasicNode.with(1)),
                        List.of(BasicNode.with(-3, 1)),
                        List.of(BasicNode.with(1), BasicNode.with(2)),
                        List.of(BasicNode.with(0, 2), BasicNode.with(3, -4)),
                        List.of(BasicNode.with(1), BasicNode.with(1), BasicNode.with(1)),
                        List.of(BasicNode.with(0), BasicNode.with(1, 2), BasicNode.with(2, 3))
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
                        List.of(BasicNode.with(3)),
                        List.of(BasicNode.with(1, 3)),
                        List.of(BasicNode.with(1), BasicNode.with(2)),
                        List.of(BasicNode.with(2, 1), BasicNode.with(2, 3)),
                        List.of(BasicNode.with(1), BasicNode.with(1), BasicNode.with(1)),
                        List.of(BasicNode.with(1, 2), BasicNode.with(3, 1), BasicNode.with(4, 1, 2)),
                        List.of(BasicNode.with(0, 1, 2), BasicNode.with(1), BasicNode.with(1))
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
                        List.of(BasicNode.with(1, 3), BasicNode.with(1, 3)),
                        List.of(BasicNode.with(2, 3), BasicNode.with(2, 1)),
                        List.of(BasicNode.with(1, 4), BasicNode.with(0, 3)),
                        List.of(BasicNode.with(0), BasicNode.with(1, 2), BasicNode.with(2, 3)),
                        List.of(BasicNode.with(1, 2), BasicNode.with(3, 1), BasicNode.with(4, 0, 2)),
                        List.of(BasicNode.with(1, 2), BasicNode.with(1), BasicNode.with(2, 1))
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
