/*
 * Copyright © 2023 Maurits H. Silvis
 * SPDX-License-Identifier: GPL-3.0-or-later
 */

package nl.mauritssilvis.darts.checkouts.java.pathfinders;

import nl.mauritssilvis.darts.checkouts.java.nodes.Node;
import nl.mauritssilvis.darts.checkouts.java.paths.Path;
import nl.mauritssilvis.darts.checkouts.java.paths.SimplePath;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;

public final class CartesianPathFinder implements PathFinder {
    private CartesianPathFinder() {
    }

    /**
     * Returns a new {@code CartesianPathFinder}
     *
     * @return a new {@code CartesianPathFinder}
     */
    public static PathFinder of() {
        return new CartesianPathFinder();
    }

    @Override
    public List<Path> find(List<? extends Node> nodes, int length) {
        boolean hasDisconnectedNodes = nodes.stream()
                .anyMatch(Predicate.not(Node::isConnected));

        return hasDisconnectedNodes ? new ArrayList<>() : new Finder(nodes, length).find();
    }

    private static class Finder {
        private final List<? extends Node> nodes;
        private final int length;
        private final List<Integer> path;
        private final List<Path> paths;

        Finder(List<? extends Node> nodes, int length) {
            this.nodes = nodes;
            this.length = length;

            path = new ArrayList<>();
            nodes.forEach(node -> path.add(0));

            paths = new ArrayList<>();
        }

        List<Path> find() {
            paths.clear();

            int level = 0;
            int distance = 0;
            findRecursively(level, distance);

            return Collections.unmodifiableList(paths);
        }

        private void findRecursively(int level, int distance) {
            if (level == nodes.size()) {
                if (level > 0 && distance == length) {
                    paths.add(SimplePath.of(path));
                }

                return;
            }

            Node node = nodes.get(level);
            List<Integer> weights = node.getWeights();

            for (int weight : weights) {
                path.set(level, weight);
                findRecursively(level + 1, distance + weight);
            }
        }
    }
}
