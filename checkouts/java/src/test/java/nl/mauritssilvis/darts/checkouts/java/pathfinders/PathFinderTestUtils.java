/*
 * Copyright © 2023 Maurits H. Silvis
 * SPDX-License-Identifier: GPL-3.0-or-later
 */

package nl.mauritssilvis.darts.checkouts.java.pathfinders;

import nl.mauritssilvis.darts.checkouts.java.paths.Path;

import java.util.Collection;
import java.util.List;

class PathFinderTestUtils {
    static int getTotalSize(Collection<? extends Path> paths) {
        return paths.stream()
                .mapToInt(Path::getSize)
                .sum();
    }

    static int getTotalLength(Collection<? extends Path> paths) {
        return paths.stream()
                .mapToInt(Path::getLength)
                .sum();
    }

    static List<List<Integer>> getAllSteps(Collection<? extends Path> paths) {
        return paths.stream()
                .map(Path::getSteps)
                .toList();
    }

    static long getTotalMultiplicity(Collection<? extends Path> paths) {
        return paths.stream()
                .mapToLong(Path::getMultiplicity)
                .sum();
    }
}
