/*
 * Copyright © 2023 Maurits Silvis
 * SPDX-License-Identifier: GPL-3.0-or-later
 */

package nl.mauritssilvis.darts.java.api.tables;

import nl.mauritssilvis.darts.java.api.finders.checkouts.Checkout;
import nl.mauritssilvis.darts.java.api.settings.Settings;

import java.util.List;
import java.util.Map;

/**
 * A checkout table that contains checkouts with specific settings.
 * <p>
 * Relevant design patterns: immutable interface.
 *
 * @author Maurits Silvis
 * @since 0.5.0
 */
public interface Table {
    /**
     * Gets the settings of this checkout table.
     *
     * @return the settings of this checkout table
     */
    Settings getSettings();

    /**
     * Gets the checkout map of this checkout table.
     *
     * @return the checkout map of this checkout table
     */
    Map<Integer, List<Checkout>> getCheckoutMap();
}
