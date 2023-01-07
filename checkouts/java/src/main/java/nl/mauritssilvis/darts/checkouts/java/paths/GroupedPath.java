/*
 * Copyright © 2023 Maurits H. Silvis
 * SPDX-License-Identifier: GPL-3.0-or-later
 */

package nl.mauritssilvis.darts.checkouts.java.paths;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * An implementation of the {@code Path} interface that can represent multiple
 * sequences of integer steps, namely, one for each permutation of the grouped
 * elements.
 */
public class GroupedPath implements Path {
    private final List<Integer> steps;
    private final List<Boolean> grouping;

    private GroupedPath(Stream<Integer> steps, Stream<Boolean> grouping) {
        this.steps = steps.toList();
        this.grouping = grouping.limit(this.steps.size())
                .toList();
    }

    public static GroupedPath of(Collection<Integer> steps, Collection<Boolean> grouping) {
        return new GroupedPath(steps.stream(), grouping.stream());
    }

    public static GroupedPath of(int[] steps, boolean[] grouping) {
        return new GroupedPath(
                Arrays.stream(steps).boxed(),
                IntStream.range(0, grouping.length)
                        .mapToObj(i -> grouping[i])
        );
    }

    @Override
    public int getSize() {
        return -1;
    }

    @Override
    public int getLength() {
        return -1;
    }

    @Override
    public List<Integer> getSteps() {
        return null;
    }

    @Override
    public int getGroupCount() {
        return -1;
    }

    @Override
    public int getMultiplicity() {
        return -1;
    }
}
