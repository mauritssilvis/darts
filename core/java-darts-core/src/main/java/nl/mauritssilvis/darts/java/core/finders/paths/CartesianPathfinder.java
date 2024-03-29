/*
 * Copyright © 2023 Maurits Silvis
 * SPDX-License-Identifier: GPL-3.0-or-later
 */

package nl.mauritssilvis.darts.java.core.finders.paths;

import lombok.EqualsAndHashCode;
import lombok.ToString;
import nl.mauritssilvis.darts.java.api.finders.paths.Path;
import nl.mauritssilvis.darts.java.api.finders.paths.Pathfinder;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.IntStream;

/**
 * An implementation of the {@code Pathfinder} interface that finds paths
 * of a specified length between sequences of nodes that are connected by
 * directed edges with integer weights.
 * <p>
 * This implementation finds paths by considering all possible combinations of
 * edges between the nodes and returns results in the form of a list of
 * {@code SimplePath} objects.
 * <p>
 * Relevant design patterns: strategy, immutable object, simple factory.
 * <p>
 * Relevant terminology: Subset sum problem.
 *
 * @author Maurits Silvis
 * @since 0.5.0
 */
@EqualsAndHashCode
@ToString
final class CartesianPathfinder implements Pathfinder {
    private final List<? extends Node> nodes;

    private CartesianPathfinder(Collection<? extends Node> nodes) {
        this.nodes = List.copyOf(nodes);
    }

    /**
     * Returns a new {@code CartesianPathfinder} for the specified nodes.
     *
     * @param nodes a collection of nodes
     * @return a new {@code CartesianPathfinder} for the specified nodes
     */
    public static Pathfinder of(Collection<? extends Node> nodes) {
        return new CartesianPathfinder(nodes);
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
        private final List<? extends Node> nodes;
        private final int maxLevel;
        private final int length;
        private final List<Path> paths;

        Finder(List<? extends Node> nodes, int length) {
            this.nodes = nodes;
            maxLevel = nodes.size();

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

            Node node = nodes.get(level);
            List<Integer> weights = node.getWeights();

            for (int weight : weights) {
                path.set(level, weight);
                findRecursively(level + 1, distance + weight, path);
            }
        }
    }
}
