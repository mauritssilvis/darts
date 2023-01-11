/*
 * Copyright © 2023 Maurits H. Silvis
 * SPDX-License-Identifier: GPL-3.0-or-later
 */

package nl.mauritssilvis.darts.checkouts.java.pathfinders;

import nl.mauritssilvis.darts.checkouts.java.nodes.DescendingNode;
import nl.mauritssilvis.darts.checkouts.java.nodes.Node;
import nl.mauritssilvis.darts.checkouts.java.paths.GroupedPath;
import nl.mauritssilvis.darts.checkouts.java.paths.Path;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;

/**
 * An implementation of the {@code PathFinder} interface that finds paths
 * of a specified length between sequences of nodes that are connected by
 * directed edges with integer weights.
 * <p>
 * This implementation finds paths by grouping nodes and considering only
 * sequences of edges between those nodes that have non-increasing weights.
 * Results are returned in the form of a list of {@code GroupedPath} objects.
 * <p>
 * Relevant design patterns: Strategy, immutable object, static factory method.
 * <p>
 * Relevant terminology: Subset sum problem.
 */
public final class DescendingPathFinder implements PathFinder {
    private final List<? extends Node> nodes;
    private final List<Boolean> grouping;

    private DescendingPathFinder(Collection<? extends Node> nodes) {
        this.nodes = nodes.stream()
                .map(Node::getWeights)
                .map(DescendingNode::of)
                .toList();

        grouping = getGrouping(this.nodes);
    }

    /**
     * Returns a new {@code DescendingPathFinder} for the supplied nodes.
     *
     * @param nodes a list of nodes
     * @return a new {@code DescendingPathFinder} for the given nodes
     */
    public static PathFinder of(Collection<? extends Node> nodes) {
        return new DescendingPathFinder(nodes);
    }

    @Override
    public List<Path> find(int length) {
        boolean hasDisconnectedNodes = nodes.stream()
                .anyMatch(Predicate.not(Node::isConnected));

        return hasDisconnectedNodes
                ? new ArrayList<>()
                : new Finder(nodes, grouping, length).find();
    }

    private static List<Boolean> getGrouping(List<? extends Node> nodeList) {
        if (nodeList.isEmpty()) {
            return Collections.emptyList();
        } else if (nodeList.size() == 1) {
            return List.of(false);
        }

        List<Boolean> groupingList = new ArrayList<>();
        groupingList.add(false);

        for (int i = 1; i < nodeList.size(); i++) {
            groupingList.add(nodeList.get(i).getWeights().equals(nodeList.get(i - 1).getWeights()));
        }

        return groupingList;
    }

    private static class Finder {
        private final List<? extends Node> searchNodes;
        private final List<Boolean> nodeGrouping;
        private final int length;
        private final List<Integer> path;
        private final List<Path> paths;

        Finder(List<? extends Node> searchNodes, List<Boolean> nodeGrouping, int length) {
            this.searchNodes = searchNodes;
            this.nodeGrouping = nodeGrouping;
            this.length = length;

            path = new ArrayList<>();
            searchNodes.forEach(node -> path.add(0));

            paths = new ArrayList<>();
        }

        List<Path> find() {
            int level = 0;
            int distance = 0;
            findRecursively(level, distance);

            return Collections.unmodifiableList(paths);
        }

        void findRecursively(int level, int distance) {
            if (level == searchNodes.size()) {
                if (level > 0 && distance == length) {
                    paths.add(GroupedPath.of(path, nodeGrouping));
                }

                return;
            }

            Node node = searchNodes.get(level);
            List<Integer> weights = node.getWeights();

            if (level == 0 || Boolean.FALSE.equals(nodeGrouping.get(level))) {
                for (int weight : weights) {
                    path.set(level, weight);
                    findRecursively(level + 1, distance + weight);
                }
            } else {
                for (int weight : weights) {
                    if (weight > path.get(level - 1)) {
                        continue;
                    }

                    path.set(level, weight);
                    findRecursively(level + 1, distance + weight);
                }
            }

        }
    }
}
