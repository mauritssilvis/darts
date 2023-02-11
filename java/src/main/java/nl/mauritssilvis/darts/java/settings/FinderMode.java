/*
 * Copyright © 2023 Maurits Silvis
 * SPDX-License-Identifier: GPL-3.0-or-later
 */

package nl.mauritssilvis.darts.java.settings;

import lombok.ToString;

/**
 * The checkout finder mode.
 */
@ToString
public enum FinderMode {
    /**
     * Find checkouts consisting of a minimum number of throws.
     */
    MINIMUM,

    /**
     * Find all checkouts for a given number of throws.
     */
    ALL
}
