/*
 * Copyright © 2023 Maurits H. Silvis
 * SPDX-License-Identifier: GPL-3.0-or-later
 */

package nl.mauritssilvis.darts.checkouts.java.boards.fields;

/**
 * A field of a dartboard.
 */
public interface Field {
    /**
     * Gets the name of this field.
     *
     * @return the name of this field
     */
    String getName();

    /**
     * Gets the point value of this field.
     *
     * @return the integer value of this field
     */
    int getValue();

    /**
     * Gets the type of this field.
     *
     * @return the type of this field
     */
    Type getType();
}
