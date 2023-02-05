/*
 * Copyright © 2023 Maurits H. Silvis
 * SPDX-License-Identifier: GPL-3.0-or-later
 */

package nl.mauritssilvis.darts.java.paths.cartesian;

import lombok.EqualsAndHashCode;
import lombok.ToString;
import nl.mauritssilvis.darts.java.paths.Path;

import java.util.Collection;
import java.util.List;

/**
 * An implementation of the {@code Path} interface that represents a single sequence
 * of ungrouped integer steps.
 * <p>
 * Relevant design patterns: immutable object, static factory method.
 */
@EqualsAndHashCode
@ToString
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
    @ToString.Include(name = "length")
    public int getLength() {
        return steps.stream()
                .mapToInt(Integer::intValue)
                .sum();
    }

    @Override
    public List<Integer> getSteps() {
        return steps;
    }

    @Override
    @ToString.Include(name = "grouping")
    public List<Boolean> getGrouping() {
        return steps.stream()
                .map(step -> false)
                .toList();
    }

    @Override
    @ToString.Include(name = "multiplicity")
    public long getMultiplicity() {
        return steps.isEmpty() ? 0 : 1;
    }
}
