/*
 * Copyright © 2023 Maurits H. Silvis
 * SPDX-License-Identifier: GPL-3.0-or-later
 */

package nl.mauritssilvis.darts.checkouts.java.pathfinders.cartesian;

import nl.mauritssilvis.darts.checkouts.java.pathfinders.descending.Group;

import java.util.List;

/**
 * An implementation of the {@code Group} interface that represents a group
 * consisting of a single integer.
 * <p>
 * Relevant design patterns: Immutable object, static factory method.
 */
public class SimpleGroup implements Group {
    private SimpleGroup(int value) {
    }

    /**
     * Returns a new {@code SimpleGroup} with the supplied integer values.
     *
     * @param value an integer value
     * @return a new {@code SimpleGroup} with the given value
     */
    public static SimpleGroup of(int value) {
        return new SimpleGroup(value);
    }

    @Override
    public List<Integer> getValues() {
        return null;
    }

    @Override
    public long countPermutations() {
        return 0;
    }
}
