/*
 * Copyright © 2023 Maurits Silvis
 * SPDX-License-Identifier: GPL-3.0-or-later
 */

/**
 * The Java module for the Java API of darts.
 *
 * @author Maurits Silvis
 * @since 0.6.0
 */
module nl.mauritssilvis.darts.java.api {
    requires static lombok;

    exports nl.mauritssilvis.darts.java.api.boards;
    exports nl.mauritssilvis.darts.java.api.finders.checkouts;
    exports nl.mauritssilvis.darts.java.api.finders.paths;
    exports nl.mauritssilvis.darts.java.api.output;
    exports nl.mauritssilvis.darts.java.api.settings;
    exports nl.mauritssilvis.darts.java.api.tables;
}
