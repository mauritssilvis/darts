/*
 * Copyright © 2023 Maurits Silvis
 * SPDX-License-Identifier: GPL-3.0-or-later
 */

/**
 * The Java module for the Java-based command-line interface for darts.
 *
 * @author Maurits Silvis
 * @since 0.6.0
 */
module nl.mauritssilvis.darts.java.cli {
    requires static lombok;

    requires nl.mauritssilvis.darts.java.api;
    requires nl.mauritssilvis.darts.java.core;
    requires info.picocli;

    opens nl.mauritssilvis.darts.java.cli to info.picocli;

    exports nl.mauritssilvis.darts.java.cli;
}
