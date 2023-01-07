/*
 * Copyright © 2023 Maurits H. Silvis
 * SPDX-License-Identifier: GPL-3.0-or-later
 */

package nl.mauritssilvis.darts.checkouts.java.paths;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Stream;

/**
 * An implementation of the {@code Path} interface that represents a single sequence
 * of ungrouped integer steps.
 */
public class SimplePath implements Path {
    private final List<Integer> steps;

    private SimplePath(Stream<Integer> steps) {
        this.steps = steps.toList();
    }

    /**
     * Returns a new {@code SimplePath} with integer steps given by the supplied
     * collection.
     *
     * @param steps a collection of integer steps
     * @return a new {@code SimplePath} with the given steps
     */
    public static Path of(Collection<Integer> steps) {
        return new SimplePath(steps.stream());
    }

    /**
     * Returns a new {@code SimplePath} with integer steps given by the supplied
     * array.
     *
     * @param steps an array of integer steps
     * @return a new {@code SimplePath} with the given steps
     */
    public static Path of(int... steps) {
        return new SimplePath(Arrays.stream(steps).boxed());
    }

    @Override
    public int getSize() {
        return steps.size();
    }

    @Override
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
    public int getGroupCount() {
        return steps.size();
    }

    @Override
    public long getMultiplicity() {
        return steps.isEmpty() ? 0 : 1;
    }
}
