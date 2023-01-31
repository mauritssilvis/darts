/*
 * Copyright © 2023 Maurits H. Silvis
 * SPDX-License-Identifier: GPL-3.0-or-later
 */

package nl.mauritssilvis.darts.java.tables;

import nl.mauritssilvis.darts.java.checkouts.Checkout;
import nl.mauritssilvis.darts.java.settings.BoardType;
import nl.mauritssilvis.darts.java.settings.CheckType;

import java.util.List;
import java.util.Map;

/**
 * A checkout table that contains checkouts for a specific dartboard type,
 * check-in type and checkout type.
 * <p>
 * Relevant design patterns: Immutable interface.
 */
public interface CheckoutTable {
    /**
     * Gets the dartboard type of this checkout table.
     *
     * @return the dartboard type of this checkout table
     */
    BoardType getBoardType();

    /**
     * Gets the check-in type of this checkout table.
     *
     * @return the check-in type of this checkout table
     */
    CheckType getCheckInType();

    /**
     * Gets the checkout type of this checkout table.
     *
     * @return the checkout type of this checkout table
     */
    CheckType getCheckOutType();

    /**
     * Gets the checkout map of this checkout table.
     *
     * @return the checkout map of this checkout table
     */
    Map<Integer, List<Checkout>> getCheckoutMap();
}
