/*
 * Copyright © 2023 Maurits H. Silvis
 * SPDX-License-Identifier: GPL-3.0-or-later
 */

package nl.mauritssilvis.darts.checkouts.java.paths;

import java.util.Arrays;
import java.util.List;

public class SimplePath implements Path {
    private final List<Integer> steps;
    private final List<Boolean> links;

    public SimplePath(List<Integer> steps) {
        this.steps = List.copyOf(steps);

        Boolean[] flags = new Boolean[steps.size()];
        Arrays.fill(flags, false);
        links = List.of(flags);
    }

    @Override
    public List<Integer> getSteps() {
        return steps;
    }

    @Override
    public List<Boolean> getLinks() {
        return links;
    }

    @Override
    public int getMultiplicity() {
        return 1;
    }
}
