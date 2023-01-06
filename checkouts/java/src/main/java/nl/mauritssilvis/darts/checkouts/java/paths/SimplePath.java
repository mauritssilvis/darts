/*
 * Copyright © 2023 Maurits H. Silvis
 * SPDX-License-Identifier: GPL-3.0-or-later
 */

package nl.mauritssilvis.darts.checkouts.java.paths;

import java.util.Collection;
import java.util.List;

public class SimplePath implements Path {
    private final List<Integer> steps;

    private SimplePath(Collection<Integer> steps) {
        this.steps = List.copyOf(steps);
    }

    public static Path of(Collection<Integer> steps) {
        return new SimplePath(steps);
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
    public int getMultiplicity() {
        return 1;
    }
}
