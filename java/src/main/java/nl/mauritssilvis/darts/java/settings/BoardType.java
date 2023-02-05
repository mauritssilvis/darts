/*
 * Copyright © 2023 Maurits H. Silvis
 * SPDX-License-Identifier: GPL-3.0-or-later
 */

package nl.mauritssilvis.darts.java.settings;

import lombok.ToString;

/**
 * The type of dartboard.
 */
@ToString
public enum BoardType {
    /**
     * A London dartboard.
     */
    LONDON,

    /**
     * A Quadro dartboard.
     */
    QUADRO,

    /**
     * A Yorkshire dartboard.
     */
    YORKSHIRE
}
