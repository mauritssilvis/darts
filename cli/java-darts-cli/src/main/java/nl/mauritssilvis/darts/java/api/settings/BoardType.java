/*
 * Copyright © 2023 Maurits Silvis
 * SPDX-License-Identifier: GPL-3.0-or-later
 */

package nl.mauritssilvis.darts.java.api.settings;

import lombok.ToString;

/**
 * The type of dartboard.
 *
 * @author Maurits Silvis
 * @since 0.1.0
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
