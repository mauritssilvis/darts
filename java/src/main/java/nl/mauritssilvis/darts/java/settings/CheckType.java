/*
 * Copyright © 2023 Maurits Silvis
 * SPDX-License-Identifier: GPL-3.0-or-later
 */

package nl.mauritssilvis.darts.java.settings;

import lombok.ToString;

/**
 * The type of check-in or checkout.
 */
@ToString
public enum CheckType {
    /**
     * A check of any field.
     */
    ANY,

    /**
     * A check of a double or triple field.
     */
    MASTER,

    /**
     * A check of a double field.
     */
    DOUBLE
}
