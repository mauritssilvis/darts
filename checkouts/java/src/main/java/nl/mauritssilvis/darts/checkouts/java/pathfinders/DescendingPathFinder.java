/*
 * Copyright © 2023 Maurits H. Silvis
 * SPDX-License-Identifier: GPL-3.0-or-later
 */

package nl.mauritssilvis.darts.checkouts.java.pathfinders;

import nl.mauritssilvis.darts.checkouts.java.nodes.DescendingNode;
import nl.mauritssilvis.darts.checkouts.java.nodes.Node;
import nl.mauritssilvis.darts.checkouts.java.paths.Path;

import java.util.Collection;
import java.util.List;

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

    private DescendingPathFinder(Collection<? extends Node> nodes) {
        this.nodes = nodes.stream()
                .map(Node::getWeights)
                .map(DescendingNode::of)
                .toList();
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
        return null;
    }
}
