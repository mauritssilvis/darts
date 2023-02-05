/*
 * Copyright © 2023 Maurits H. Silvis
 * SPDX-License-Identifier: GPL-3.0-or-later
 */

package nl.mauritssilvis.darts.java.settings;

import lombok.ToString;

/**
 * The type of checkout finder.
 */
@ToString
public enum FinderType {
    /**
     * A Cartesian checkout finder.
     */
    CARTESIAN,

    /**
     * A descending checkout finder.
     */
    DESCENDING
}
