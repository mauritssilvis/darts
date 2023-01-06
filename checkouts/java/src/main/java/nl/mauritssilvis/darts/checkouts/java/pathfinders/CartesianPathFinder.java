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

public class CartesianPathFinder implements PathFinder {
    @Override
    public List<Path> find(List<Node> nodes, int length) {
        boolean hasDisconnectedNodes = nodes.stream()
                .anyMatch(Node::isDisconnected);

        return hasDisconnectedNodes ? new ArrayList<>() : new Finder(nodes, length).find();
    }

    private static class Finder {
        private final List<Node> nodes;
        private final int length;
        private final List<Integer> path;
        private final List<Path> paths;

        Finder(List<Node> nodes, int length) {
            this.nodes = nodes;
            this.length = length;

            path = new ArrayList<>();
            nodes.forEach(step -> path.add(0));

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
                    paths.add(new SimplePath(path));
                }

                return;
            }

            Node node = nodes.get(level);
            List<Integer> edges = node.getEdges();

            for (int step : edges) {
                path.set(level, step);
                findRecursively(level + 1, distance + step);
            }
        }
    }
}
