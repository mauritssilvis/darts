/*
 * Copyright © 2023 Maurits Silvis
 * SPDX-License-Identifier: GPL-3.0-or-later
 */

package nl.mauritssilvis.darts.java.core.finders.paths;

import nl.mauritssilvis.darts.java.api.settings.FinderType;

import java.util.Collection;

/**
 * A node factory.
 * <p>
 * Relevant design patterns: helper, parameterized static factory method.
 *
 * @author Maurits Silvis
 * @since 0.5.0
 */
public final class NodeFactory {
    private NodeFactory() {
    }

    /**
     * Returns a new {@code Node} of the specified type with the specified
     * weights.
     *
     * @param finderType the finder type
     * @param weights    a collection of weights
     * @return a new {@code Node} of the specified type with the specified
     * weights
     */
    public static Node create(FinderType finderType, Collection<Integer> weights) {
        return switch (finderType) {
            case CARTESIAN -> CartesianNode.of(weights);
            case DESCENDING -> DescendingNode.of(weights);
        };
    }
}
