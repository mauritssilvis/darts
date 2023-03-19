/*
 * Copyright © 2023 Maurits Silvis
 * SPDX-License-Identifier: GPL-3.0-or-later
 */

package nl.mauritssilvis.darts.java.api.settings;

import lombok.ToString;

/**
 * The throw mode.
 *
 * @author Maurits Silvis
 * @since 0.5.0
 */
@ToString
public enum ThrowMode {
    /**
     * Find checkouts consisting of a minimum number of throws.
     */
    OPTIMAL,

    /**
     * Find checkouts consisting of a fixed number of throws.
     */
    FIXED
}
