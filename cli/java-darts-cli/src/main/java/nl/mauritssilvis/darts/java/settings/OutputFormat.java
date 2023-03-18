/*
 * Copyright © 2023 Maurits Silvis
 * SPDX-License-Identifier: GPL-3.0-or-later
 */

package nl.mauritssilvis.darts.java.settings;

import lombok.ToString;

/**
 * The output format.
 *
 * @author Maurits Silvis
 * @since 0.1.0
 */
@ToString
public enum OutputFormat {
    /**
     * Java string representation output.
     */
    STRING,

    /**
     * Markdown output.
     */
    MARKDOWN,

    /**
     * JSON output.
     */
    JSON,

    /**
     * HTML output.
     */
    HTML
}