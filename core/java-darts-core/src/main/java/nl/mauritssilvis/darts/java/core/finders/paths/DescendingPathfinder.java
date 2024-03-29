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
import java.util.function.IntBinaryOperator;
import java.util.function.Predicate;
import java.util.stream.IntStream;

/**
 * An implementation of the {@code Pathfinder} interface that finds paths
 * of a specified length between sequences of nodes that are connected by
 * directed edges with integer weights.
 * <p>
 * This implementation finds paths by grouping nodes and considering only
 * sequences of edges between those nodes that have non-increasing weights.
 * Results are returned in the form of a list of {@code GroupedPath} objects.
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
final class DescendingPathfinder implements Pathfinder {
    private final List<? extends Node> nodes;

    private DescendingPathfinder(Collection<? extends Node> nodes) {
        this.nodes = nodes.stream()
                .map(Node::getWeights)
                .map(DescendingNode::of)
                .toList();
    }

    /**
     * Returns a new {@code DescendingPathfinder} for the specified nodes.
     *
     * @param nodes a collection of nodes
     * @return a new {@code DescendingPathfinder} for the specified nodes
     */
    public static Pathfinder of(Collection<? extends Node> nodes) {
        return new DescendingPathfinder(nodes);
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
        private final List<Boolean> grouping;
        private final List<Integer> maxRemaining;
        private final List<Integer> minRemaining;
        private final int maxLevel;
        private final int length;
        private final List<Path> paths;

        Finder(List<? extends Node> nodes, int length) {
            this.nodes = nodes;
            grouping = getGrouping(nodes);
            maxRemaining = getMaxRemaining(nodes);
            minRemaining = getMinRemaining(nodes);
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
                    paths.add(GroupedPath.of(path, grouping));
                }

                return;
            }

            Node node = nodes.get(level);
            List<Integer> weights = node.getWeights();

            int maxRemainder = maxRemaining.get(level);
            int minRemainder = minRemaining.get(level);

            boolean isGrouped = Boolean.TRUE.equals(grouping.get(level));
            int lastWeight = level > 0 ? path.get(level - 1) : Integer.MAX_VALUE;

            for (int weight : weights) {
                if (distance + weight + maxRemainder < length) {
                    return;
                }

                if (distance + weight + minRemainder > length) {
                    continue;
                }

                if (isGrouped && weight > lastWeight) {
                    continue;
                }

                path.set(level, weight);
                findRecursively(level + 1, distance + weight, path);
            }
        }

        private static List<Boolean> getGrouping(List<? extends Node> nodes) {
            if (nodes.size() == 1) {
                return List.of(false);
            }

            List<Boolean> grouping = new ArrayList<>();
            grouping.add(false);

            for (int i = 1; i < nodes.size(); i++) {
                grouping.add(nodes.get(i).equals(nodes.get(i - 1)));
            }

            return grouping;
        }

        private static List<Integer> getMaxRemaining(List<? extends Node> nodes) {
            return getRemaining(nodes, Integer::max);
        }

        private static List<Integer> getMinRemaining(List<? extends Node> nodes) {
            return getRemaining(nodes, Integer::min);
        }

        private static List<Integer> getRemaining(List<? extends Node> nodes, IntBinaryOperator operator) {
            int sum = 0;

            List<Integer> remaining = new ArrayList<>();
            remaining.add(sum);

            for (int i = nodes.size() - 1; i > 0; i--) {
                Node node = nodes.get(i);

                sum += node.getWeights().stream()
                        .mapToInt(Integer::intValue)
                        .reduce(operator)
                        .orElseThrow();

                remaining.add(0, sum);
            }

            return remaining;
        }
    }
}
