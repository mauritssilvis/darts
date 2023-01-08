/*
 * Copyright © 2023 Maurits H. Silvis
 * SPDX-License-Identifier: GPL-3.0-or-later
 */

package nl.mauritssilvis.darts.checkouts.java.pathfinders;

import nl.mauritssilvis.darts.checkouts.java.nodes.Node;
import nl.mauritssilvis.darts.checkouts.java.paths.Path;

import java.util.Collection;
import java.util.List;

public final class DescendingPathFinder implements PathFinder {
    private DescendingPathFinder(Collection<? extends Node> nodes) {
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
