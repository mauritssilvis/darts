/*
 * Copyright © 2023 Maurits H. Silvis
 * SPDX-License-Identifier: GPL-3.0-or-later
 */

package nl.mauritssilvis.darts.checkouts.java.pathfinders;

import nl.mauritssilvis.darts.checkouts.java.pathfinders.cartesian.BasicNode;

import java.util.Collection;
import java.util.List;

public final class PathFinderTestUtils {
    private PathFinderTestUtils() {
    }

    public static List<Node> getNodes(Collection<Collection<Integer>> weights) {
        return weights.stream()
                .map(BasicNode::of)
                .toList();
    }

    public static int getTotalSize(Collection<? extends Path> paths) {
        return paths.stream()
                .mapToInt(Path::getSize)
                .sum();
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
