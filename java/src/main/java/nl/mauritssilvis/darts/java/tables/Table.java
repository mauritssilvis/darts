/*
 * Copyright © 2023 Maurits Silvis
 * SPDX-License-Identifier: GPL-3.0-or-later
 */

package nl.mauritssilvis.darts.java.tables;

import nl.mauritssilvis.darts.java.checkouts.Checkout;
import nl.mauritssilvis.darts.java.settings.BoardType;
import nl.mauritssilvis.darts.java.settings.CheckMode;
import nl.mauritssilvis.darts.java.settings.ThrowMode;

import java.util.List;
import java.util.Map;

/**
 * A checkout table that contains checkouts for a specific dartboard type,
 * and check-in and checkout modes.
 * <p>
 * Relevant design patterns: immutable interface.
 */
public interface Table {
    /**
     * Gets the dartboard type of this checkout table.
     *
     * @return the dartboard type of this checkout table
     */
    BoardType getBoardType();

    /**
     * Gets the check-in mode of this checkout table.
     *
     * @return the check-in mode of this checkout table
     */
    CheckMode getCheckInMode();

    /**
     * Gets the checkout mode of this checkout table.
     *
     * @return the checkout mode of this checkout table
     */
    CheckMode getCheckoutMode();

    /**
     * Gets the number of throws of this checkout table if fixed and -1 otherwise.
     *
     * @return the number of throws of this checkout table
     */
    int getNumThrows();

    /**
     * Gets the throw mode of this checkout table.
     *
     * @return the throw mode of this checkout table
     */
    ThrowMode getThrowMode();

    /**
     * Gets the checkout map of this checkout table.
     *
     * @return the checkout map of this checkout table
     */
    Map<Integer, List<Checkout>> getCheckoutMap();
}
