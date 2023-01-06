/*
 * Copyright © 2023 Maurits H. Silvis
 * SPDX-License-Identifier: GPL-3.0-or-later
 */

package nl.mauritssilvis.darts.checkouts.java.paths;

import java.util.List;

/**
 * A representation of paths consisting of integer steps.
 * <p>
 * A simple path consists of ungrouped steps. In such a path, different steps
 * cannot be interchanged without getting a different path. Simple paths, thus,
 * represent only a single path. In contrast, paths with grouped steps represent
 * a group of paths, one for each permutation of the grouped elements.
 */
public interface Path {
    /**
     * Gets the size of this path, that is, the number of steps comprising this
     * path.
     *
     * @return the size of this path
     */
    int getSize();

    /**
     * Gets the length of this path, that is, the total length covered by the steps
     * of this path.
     *
     * @return the length of this path
     */
    int getLength();

    /**
     * Gets the steps of this path, that is, the list of integers representing
     * the step lengths of this path.
     *
     * @return a list of integers representing the steps of this path
     */
    List<Integer> getSteps();

    /**
     * Gets the number of groups this path consists of.
     * <p>
     * For a simple path, the number of groups equals the number of steps.
     *
     * @return the number of groups this path comprises
     */
    int getGroupCount();

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
