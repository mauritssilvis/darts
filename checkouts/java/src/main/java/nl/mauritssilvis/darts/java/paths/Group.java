/*
 * Copyright © 2023 Maurits H. Silvis
 * SPDX-License-Identifier: GPL-3.0-or-later
 */

package nl.mauritssilvis.darts.java.paths;

import java.util.List;

/**
 * An ordered group of integer values.
 * <p>
 * Relevant design pattern: Immutable interface.
 */
public interface Group {
    /**
     * Gets the values of this group.
     *
     * @return a list of the values of this group
     */
    List<Integer> getValues();

    /**
     * Gets the number of unique permutations of the values of this group.
     *
     * @return the number of unique permutations of this group
     */
    long countPermutations();
}