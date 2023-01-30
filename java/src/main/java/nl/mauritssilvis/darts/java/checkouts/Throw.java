/*
 * Copyright © 2023 Maurits H. Silvis
 * SPDX-License-Identifier: GPL-3.0-or-later
 */

package nl.mauritssilvis.darts.java.checkouts;

import nl.mauritssilvis.darts.java.boards.Field;

import java.util.List;

/**
 * A throw that contains fields of a certain score. All fields have to be
 * unique.
 * <p>
 * Relevant design patterns: Immutable interface.
 */
public interface Throw {
    /**
     * Gets the score of this throw.
     *
     * @return the score of this throw
     */
    int getScore();

    /**
     * Gets the fields of this throw.
     * <p>
     * All fields should have the same score as this throw and should be unique.
     *
     * @return a list of the fields of this throw
     */
    List<Field> getFields();
}
