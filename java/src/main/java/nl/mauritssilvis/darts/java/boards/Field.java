/*
 * Copyright © 2023 Maurits H. Silvis
 * SPDX-License-Identifier: GPL-3.0-or-later
 */

package nl.mauritssilvis.darts.java.boards;

/**
 * A dartboard field with a type, a name and a score.
 * <p>
 * Relevant design patterns: immutable interface.
 */
public interface Field {
    /**
     * Gets the type of this field.
     *
     * @return the type of this field
     */
    FieldType getFieldType();

    /**
     * Gets the name of this field.
     *
     * @return the name of this field
     */
    String getName();

    /**
     * Gets the score of this field.
     *
     * @return the score of this field
     */
    int getScore();
}
