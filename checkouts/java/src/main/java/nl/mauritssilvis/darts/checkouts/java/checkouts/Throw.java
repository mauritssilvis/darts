/*
 * Copyright © 2023 Maurits H. Silvis
 * SPDX-License-Identifier: GPL-3.0-or-later
 */

package nl.mauritssilvis.darts.checkouts.java.checkouts;

import nl.mauritssilvis.darts.checkouts.java.boards.Field;

import java.util.List;

/**
 * A throw that contains fields of a certain score.
 */
public interface Throw {
    /**
     * Gets the score of this throw.
     *
     * @return the score of this throw
     */
    int getScore();

    /**
     * Gets the number of fields of this throw.
     *
     * @return the number of fields of this throw
     */
    int countFields();

    /**
     * Gets the fields of this throw. All fields have the same score as this
     * throw.
     *
     * @return a list of the fields of this throw
     */
    List<Field> getFields();
}
