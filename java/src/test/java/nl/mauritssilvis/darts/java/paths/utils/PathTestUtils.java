/*
 * Copyright © 2023 Maurits H. Silvis
 * SPDX-License-Identifier: GPL-3.0-or-later
 */

package nl.mauritssilvis.darts.java.paths.utils;

import nl.mauritssilvis.darts.java.paths.Path;

import java.util.Collection;
import java.util.List;

public final class PathTestUtils {
    private PathTestUtils() {
    }

    public static int getTotalLength(Collection<? extends Path> paths) {
        return paths.stream()
                .mapToInt(Path::getLength)
                .sum();
    }

    public static List<List<Integer>> getAllSteps(Collection<? extends Path> paths) {
        return paths.stream()
                .map(Path::getSteps)
                .toList();
    }

    public static long getTotalMultiplicity(Collection<? extends Path> paths) {
        return paths.stream()
                .mapToLong(Path::getMultiplicity)
                .sum();
    }
}
