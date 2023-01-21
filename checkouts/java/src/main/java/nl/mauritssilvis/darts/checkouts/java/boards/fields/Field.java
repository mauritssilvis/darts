/*
 * Copyright © 2023 Maurits H. Silvis
 * SPDX-License-Identifier: GPL-3.0-or-later
 */

package nl.mauritssilvis.darts.checkouts.java.boards.fields;

/**
 * A dartboard field with a type, a name and a value.
 * <p>
 * Relevant design patterns: Immutable interface.
 */
public interface Field {
    /**
     * Gets the type of this dartboard field.
     *
     * @return the type of this field
     */
    Type getType();

    /**
     * Gets the name of this dartboard field.
     *
     * @return the name of this field
     */
    String getName();

    /**
     * Gets the point value of this dartboard field.
     *
     * @return the integer value of this field
     */
    int getValue();
}
