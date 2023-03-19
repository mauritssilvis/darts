/*
 * Copyright © 2023 Maurits Silvis
 * SPDX-License-Identifier: GPL-3.0-or-later
 */

/**
 * The Java module for the Java implementation of darts.
 *
 * @author Maurits Silvis
 * @since 0.6.0
 */
module nl.mauritssilvis.darts.java.core {
    requires static lombok;

    requires transitive nl.mauritssilvis.darts.java.api;

    exports nl.mauritssilvis.darts.java.core.boards;
    exports nl.mauritssilvis.darts.java.core.boards.output;
    exports nl.mauritssilvis.darts.java.core.finders.checkouts;
    exports nl.mauritssilvis.darts.java.core.finders.paths;
    exports nl.mauritssilvis.darts.java.core.output;
    exports nl.mauritssilvis.darts.java.core.settings;
    exports nl.mauritssilvis.darts.java.core.tables;
    exports nl.mauritssilvis.darts.java.core.tables.output;
}
