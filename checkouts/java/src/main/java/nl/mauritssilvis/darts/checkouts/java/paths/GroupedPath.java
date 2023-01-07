/*
 * Copyright © 2023 Maurits H. Silvis
 * SPDX-License-Identifier: GPL-3.0-or-later
 */

package nl.mauritssilvis.darts.checkouts.java.paths;

import java.util.Collection;
import java.util.List;

/**
 * An implementation of the {@code Path} interface that can represent multiple
 * sequences of integer steps, namely, one for each permutation of the grouped
 * elements.
 */
public class GroupedPath implements Path {
    private final List<Integer> steps;
    private final List<Boolean> grouping;

    private GroupedPath(Collection<Integer> steps, Collection<Boolean> grouping) {
        this.steps = List.copyOf(steps);
        this.grouping = grouping.stream()
                .limit(this.steps.size())
                .toList();
    }

    public static Path of(Collection<Integer> steps, Collection<Boolean> grouping) {
        return new GroupedPath(steps, grouping);
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
    public long getMultiplicity() {
        return -1;
    }
}
