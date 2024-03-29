/*
 * Copyright © 2023 Maurits Silvis
 * SPDX-License-Identifier: GPL-3.0-or-later
 */

package nl.mauritssilvis.darts.java.api.finders.checkouts;

import nl.mauritssilvis.darts.java.api.boards.Field;

import java.util.List;

/**
 * A throw that contains fields of a certain score. All fields have to be
 * unique.
 * <p>
 * Relevant design patterns: immutable interface.
 *
 * @author Maurits Silvis
 * @since 0.5.0
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
