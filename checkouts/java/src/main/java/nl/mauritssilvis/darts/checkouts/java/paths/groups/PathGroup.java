/*
 * Copyright © 2023 Maurits H. Silvis
 * SPDX-License-Identifier: GPL-3.0-or-later
 */

package nl.mauritssilvis.darts.checkouts.java.paths.groups;

import java.util.Collection;
import java.util.List;

public class PathGroup implements Group {
    public static Group of(Collection<Integer> values) {
        return new PathGroup();
    }

    @Override
    public List<Integer> getValues() {
        return null;
    }

    @Override
    public long countPermutations() {
        return -1;
    }
}
