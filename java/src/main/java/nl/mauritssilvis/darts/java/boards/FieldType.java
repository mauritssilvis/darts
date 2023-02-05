/*
 * Copyright © 2023 Maurits H. Silvis
 * SPDX-License-Identifier: GPL-3.0-or-later
 */

package nl.mauritssilvis.darts.java.boards;

import lombok.ToString;

/**
 * The type of dartboard field.
 */
@ToString
public enum FieldType {
    /**
     * A single field.
     */
    SINGLE,

    /**
     * A double field.
     */
    DOUBLE,

    /**
     * A triple field.
     */
    TRIPLE,

    /**
     * A quadruple field.
     */
    QUADRUPLE
}
