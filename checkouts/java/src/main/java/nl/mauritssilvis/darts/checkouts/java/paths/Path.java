/*
 * Copyright © 2023 Maurits H. Silvis
 * SPDX-License-Identifier: GPL-3.0-or-later
 */

package nl.mauritssilvis.darts.checkouts.java.paths;

import java.util.List;

/**
 * A path consisting of integer steps.
 * <p>
 * A simple path will consist of ungrouped steps. In such a path, different
 * steps cannot be interchanged without getting a different path. Simple paths,
 * thus, represent only a single path. In contrast, paths with grouped steps
 * represent a group of paths, one for each permutation of the grouped elements.
 */
public interface Path {
    /**
     * Get the size of this path, that is, the number of steps comprising this
     * path.
     *
     * @return the size of this path
     */
    int getSize();

    /**
     * Get the length of this path, that is, the total length covered by the steps
     * of this path.
     *
     * @return the length of this path
     */
    int getLength();

    /**
     * Get the steps of this path, that is, the list of integers representing
     * the step lengths of this path.
     *
     * @return a list of integers representing the steps of this path
     */
    List<Integer> getSteps();

    List<Boolean> getLinks();

    /**
     * Get the multiplicity of this path.
     * <p>
     * Simple paths without grouped elements
     * represent only a single path and have a unit multiplicity. Paths with
     * grouped steps represent multiple paths and, therefore, have a
     * multiplicity larger than one.
     *
     * @return the multiplicity of this path
     */
    int getMultiplicity();
}
