/*
 * Copyright © 2023 Maurits H. Silvis
 * SPDX-License-Identifier: GPL-3.0-or-later
 */

package nl.mauritssilvis.darts.checkouts.java.paths.groups;

import java.util.List;

/**
 * An ordered group of integer values.
 */
public interface Group {
    /**
     * Gets the values of this group, that is, the list of integer values of
     * this group.
     *
     * @return a list of integers with the values of this group
     */
    List<Integer> getValues();

    /**
     * Gets the number of unique permutations of the integer values of this
     * group.
     *
     * @return the number of unique permutations of this group
     */
    long countPermutations();
}
