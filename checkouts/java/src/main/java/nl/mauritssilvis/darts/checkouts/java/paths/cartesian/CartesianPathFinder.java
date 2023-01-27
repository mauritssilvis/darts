/*
 * Copyright © 2023 Maurits H. Silvis
 * SPDX-License-Identifier: GPL-3.0-or-later
 */

package nl.mauritssilvis.darts.checkouts.java.paths.cartesian;

import nl.mauritssilvis.darts.checkouts.java.paths.Node;
import nl.mauritssilvis.darts.checkouts.java.paths.Path;
import nl.mauritssilvis.darts.checkouts.java.paths.PathFinder;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.IntStream;

/**
 * An implementation of the {@code PathFinder} interface that finds paths
 * of a specified length between sequences of nodes that are connected by
 * directed edges with integer weights.
 * <p>
 * This implementation finds paths by considering all possible combinations of
 * edges between the nodes and returns results in the form of a list of
 * {@code SimplePath} objects.
 * <p>
 * Relevant design patterns: Strategy, immutable object, static factory method.
 * <p>
 * Relevant terminology: Subset sum problem.
 */
public final class CartesianPathFinder implements PathFinder {
    private final List<? extends Node> nodes;

    private CartesianPathFinder(Collection<? extends Node> nodes) {
        this.nodes = List.copyOf(nodes);
    }

    /**
     * Returns a new {@code CartesianPathFinder} for the specified nodes.
     *
     * @param nodes a list of nodes
     * @return a new {@code CartesianPathFinder} for the specified nodes
     */
    public static PathFinder of(Collection<? extends Node> nodes) {
        return new CartesianPathFinder(nodes);
    }

    @Override
    public List<Path> find(int length) {
        if (nodes.isEmpty()) {
            return Collections.emptyList();
        }

        boolean hasDisconnectedNodes = nodes.stream()
                .anyMatch(Predicate.not(Node::isConnected));

        if (hasDisconnectedNodes) {
            return Collections.emptyList();
        }

        return new Finder(nodes, length).find();
    }

    private static class Finder {
        private final List<? extends Node> searchNodes;
        private final int maxLevel;
        private final int length;
        private final List<Path> paths;

        Finder(List<? extends Node> searchNodes, int length) {
            this.searchNodes = searchNodes;
            maxLevel = searchNodes.size();

            this.length = length;

            paths = new ArrayList<>();
        }

        List<Path> find() {
            int level = 0;
            int distance = 0;

            List<Integer> path = new ArrayList<>();
            IntStream.range(0, maxLevel).
                    forEach(i -> path.add(0));

            findRecursively(level, distance, path);

            return Collections.unmodifiableList(paths);
        }

        void findRecursively(int level, int distance, List<Integer> path) {
            if (level == maxLevel) {
                if (distance == length) {
                    paths.add(SimplePath.of(path));
                }

                return;
            }

            Node node = searchNodes.get(level);
            List<Integer> weights = node.getWeights();

            for (int weight : weights) {
                path.set(level, weight);
                findRecursively(level + 1, distance + weight, path);
            }
        }
    }
}
