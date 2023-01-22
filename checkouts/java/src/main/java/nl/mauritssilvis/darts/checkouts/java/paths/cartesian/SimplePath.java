/*
 * Copyright © 2023 Maurits H. Silvis
 * SPDX-License-Identifier: GPL-3.0-or-later
 */

package nl.mauritssilvis.darts.checkouts.java.paths.cartesian;

import nl.mauritssilvis.darts.checkouts.java.paths.Group;
import nl.mauritssilvis.darts.checkouts.java.paths.Path;

import java.util.Collection;
import java.util.List;

/**
 * An implementation of the {@code Path} interface that represents a single sequence
 * of ungrouped integer steps.
 * <p>
 * Relevant design patterns: Immutable object, static factory method.
 */
public final class SimplePath implements Path {
    private final List<Integer> steps;

    private SimplePath(Collection<Integer> steps) {
        this.steps = List.copyOf(steps);
    }

    /**
     * Returns a new {@code SimplePath} with the specified steps.
     *
     * @param steps a collection of steps
     * @return a new {@code SimplePath} with the specified steps
     */
    public static Path of(Collection<Integer> steps) {
        return new SimplePath(steps);
    }

    @Override
    public int getLength() {
        return steps.stream()
                .mapToInt(Integer::intValue)
                .sum();
    }

    @Override
    public int countSteps() {
        return steps.size();
    }

    @Override
    public List<Integer> getSteps() {
        return steps;
    }

    @Override
    public int countGroups() {
        return steps.size();
    }

    @Override
    public List<Group> getGroups() {
        return steps.stream()
                .map(step -> (Group) SimpleGroup.of(step))
                .toList();
    }

    @Override
    public long getMultiplicity() {
        return steps.isEmpty() ? 0 : 1;
    }
}