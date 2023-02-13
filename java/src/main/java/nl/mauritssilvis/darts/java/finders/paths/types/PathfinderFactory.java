/*
 * Copyright © 2023 Maurits Silvis
 * SPDX-License-Identifier: GPL-3.0-or-later
 */

package nl.mauritssilvis.darts.java.finders.paths.types;

import nl.mauritssilvis.darts.java.finders.paths.Pathfinder;
import nl.mauritssilvis.darts.java.settings.FinderType;

import java.util.Collection;

/**
 * A pathfinder factory.
 * <p>
 * Relevant design patterns: helper, parameterized static factory method.
 */
public final class PathfinderFactory {
    private PathfinderFactory() {
    }

    /**
     * Returns a new {@code Pathfinder} of the specified type for the specified
     * nodes.
     *
     * @param finderType the finder type
     * @param nodes      a collection of nodes
     * @return a new {@code Pathfinder} of the specified type for the specified
     * nodes
     */
    public static Pathfinder create(FinderType finderType, Collection<? extends Node> nodes) {
        return switch (finderType) {
            case CARTESIAN -> CartesianPathfinder.of(nodes);
            case DESCENDING -> DescendingPathfinder.of(nodes);
        };
    }
}
