/*
 * Copyright © 2023 Maurits Silvis
 * SPDX-License-Identifier: GPL-3.0-or-later
 */

package nl.mauritssilvis.darts.java.api.settings;

import lombok.ToString;

/**
 * The check-in or checkout mode.
 *
 * @author Maurits Silvis
 * @since 0.5.0
 */
@ToString
public enum CheckMode {
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
