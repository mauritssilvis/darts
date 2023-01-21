/*
 * Copyright © 2023 Maurits H. Silvis
 * SPDX-License-Identifier: GPL-3.0-or-later
 */

package nl.mauritssilvis.darts.checkouts.java.boards.fields;

/**
 * The type of dartboard field.
 */
public enum Type {
    /**
     * A field with a single point value.
     */
    SINGLE,

    /**
     * A field with a double point value.
     */
    DOUBLE,

    /**
     * A field with a triple point value.
     */
    TRIPLE,

    /**
     * A field with a quadruple point value.
     */
    QUADRUPLE
}
