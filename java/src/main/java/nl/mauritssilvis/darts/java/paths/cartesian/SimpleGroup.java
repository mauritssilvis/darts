/*
 * Copyright © 2023 Maurits H. Silvis
 * SPDX-License-Identifier: GPL-3.0-or-later
 */

package nl.mauritssilvis.darts.java.paths.cartesian;

import nl.mauritssilvis.darts.java.paths.Group;

import java.util.Collections;
import java.util.List;

/**
 * An implementation of the {@code Group} interface that represents a group
 * consisting of a single integer.
 * <p>
 * Relevant design patterns: Immutable object, static factory method.
 */
public final class SimpleGroup implements Group {
    private final int value;

    private SimpleGroup(int value) {
        this.value = value;
    }

    /**
     * Returns a new {@code SimpleGroup} with the specified value.
     *
     * @param value the value
     * @return a new {@code SimpleGroup} with the specified value
     */
    public static Group of(int value) {
        return new SimpleGroup(value);
    }

    @Override
    public List<Integer> getValues() {
        return Collections.singletonList(value);
    }

    @Override
    public long countPermutations() {
        return 1;
    }
}