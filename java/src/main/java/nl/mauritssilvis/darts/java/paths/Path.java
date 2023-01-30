/*
 * Copyright © 2023 Maurits H. Silvis
 * SPDX-License-Identifier: GPL-3.0-or-later
 */

package nl.mauritssilvis.darts.java.paths;

import java.util.List;

/**
 * A path that represents zero or more sequences of integer steps of a certain
 * total length.
 * <p>
 * Simple paths represent a single sequence of integer steps. In such paths, the
 * different steps are not grouped. Interchanging the steps of a simple path
 * leads to a sequence of integers that is not represented by that same path. In
 * contrast, paths with grouped steps represent multiple sequences, one for each
 * permutation of the grouped elements.
 * <p>
 * Relevant design pattern: Immutable interface.
 */
public interface Path {
    /**
     * Gets the length of this path, that is, the total length covered by the steps
     * of this path.
     *
     * @return the length of this path
     */
    int getLength();

    /**
     * Gets the steps of this path.
     *
     * @return a list of the steps of this path
     */
    List<Integer> getSteps();

    /**
     * Gets the groups this path consists of.
     *
     * @return a list of the groups this path consists of
     */
    @Deprecated
    List<List<Integer>> getGroups();

    /**
     * Gets the grouping signature of this path.
     * <p>
     * The grouping signature states for each step whether it belongs to a group
     * with the previous step.
     *
     * @return a list of booleans representing the grouping signature of this
     * path
     */
    List<Boolean> getGrouping();

    /**
     * Gets the multiplicity of this path.
     * <p>
     * Simple paths, which represent a single sequence of steps, have a unit
     * multiplicity. Paths with grouped steps represent multiple sequences and,
     * correspondingly, have a multiplicity larger than one.
     *
     * @return the multiplicity of this path
     */
    long getMultiplicity();
}
