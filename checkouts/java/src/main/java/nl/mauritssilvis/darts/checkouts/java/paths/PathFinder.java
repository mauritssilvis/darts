/*
 * Copyright © 2023 Maurits H. Silvis
 * SPDX-License-Identifier: GPL-3.0-or-later
 */

package nl.mauritssilvis.darts.checkouts.java.paths;

import java.util.List;

/**
 * A tool to find paths of a specified length.
 * <p>
 * This functional interface does not fix the data structure that is searched
 * for paths. Implementations can specify the data structures they apply to.
 * <p>
 * Relevant design pattern: Strategy.
 */
@FunctionalInterface
public interface PathFinder {
    /**
     * Finds paths having a specified length.
     *
     * @param length the target path length
     * @return a list of paths having the specified length
     */
    List<Path> find(int length);
}
