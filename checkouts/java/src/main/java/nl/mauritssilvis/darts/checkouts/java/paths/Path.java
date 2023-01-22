/*
 * Copyright © 2023 Maurits H. Silvis
 * SPDX-License-Identifier: GPL-3.0-or-later
 */

package nl.mauritssilvis.darts.checkouts.java.paths;

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
     * Gets the number of steps of this path.
     *
     * @return the number of steps of this path
     */
    int countSteps();

    /**
     * Gets the steps of this path.
     *
     * @return a list of the steps of this path
     */
    List<Integer> getSteps();

    /**
     * Gets the number of groups this path consists of.
     * <p>
     * For a simple path, the number of groups equals the number of steps. Paths
     * with grouped steps have a smaller number of groups.
     *
     * @return the number of groups this path consists of
     */
    int countGroups();

    /**
     * Gets the groups this path consists of.
     *
     * @return a list of the groups this path consists of
     */
    List<Group> getGroups();

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
