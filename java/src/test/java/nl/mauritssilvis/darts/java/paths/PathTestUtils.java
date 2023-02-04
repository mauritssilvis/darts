/*
 * Copyright © 2023 Maurits H. Silvis
 * SPDX-License-Identifier: GPL-3.0-or-later
 */

package nl.mauritssilvis.darts.java.paths;

import java.util.Collection;
import java.util.List;

/**
 * A set of testing utilities that process collections of {@code Path} objects.
 */
public final class PathTestUtils {
    private PathTestUtils() {
    }

    /**
     * Gets the total length of the specified paths.
     *
     * @param paths a collection of paths
     * @return the total length of the specified paths
     */
    public static int getTotalLength(Collection<? extends Path> paths) {
        return paths.stream()
                .mapToInt(Path::getLength)
                .sum();
    }

    /**
     * Gets the steps of the specified paths.
     *
     * @param paths a collection of paths
     * @return a list of the lists of steps of the specified paths
     */
    public static List<List<Integer>> getAllSteps(Collection<? extends Path> paths) {
        return paths.stream()
                .map(Path::getSteps)
                .toList();
    }

    /**
     * Gets the total multiplicity of the specified paths.
     *
     * @param paths a collection of paths
     * @return the total multiplicity of the specified paths
     */
    public static long getTotalMultiplicity(Collection<? extends Path> paths) {
        return paths.stream()
                .mapToLong(Path::getMultiplicity)
                .sum();
    }
}
