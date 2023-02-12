/*
 * Copyright © 2023 Maurits Silvis
 * SPDX-License-Identifier: GPL-3.0-or-later
 */

package nl.mauritssilvis.darts.java.tables;

import nl.mauritssilvis.darts.java.checkouts.Checkout;
import nl.mauritssilvis.darts.java.settings.BoardType;
import nl.mauritssilvis.darts.java.settings.CheckMode;
import nl.mauritssilvis.darts.java.settings.ThrowMode;

import java.util.Collection;

/**
 * A checkout table builder.
 * <p>
 * Relevant design patterns: builder, fluent interface.
 */
public interface TableBuilder {
    /**
     * Sets the dartboard type.
     *
     * @param boardType the dartboard type
     * @return this checkout table builder
     */
    TableBuilder setBoardType(BoardType boardType);

    /**
     * Sets the check-in mode.
     *
     * @param checkInMode the check-in mode
     * @return this checkout table builder
     */
    TableBuilder setCheckInMode(CheckMode checkInMode);

    /**
     * Sets the number of throws.
     *
     * @param numThrows the number of throws
     * @return this checkout table builder
     */
    TableBuilder setNumThrows(int numThrows);

    /**
     * Sets the throw mode.
     *
     * @param throwMode the throw mode
     * @return this checkout table builder
     */
    TableBuilder setThrowMode(ThrowMode throwMode);

    /**
     * Sets the checkout mode.
     *
     * @param checkoutMode the checkout mode
     * @return this checkout table builder
     */
    TableBuilder setCheckoutMode(CheckMode checkoutMode);

    /**
     * Sets the checkouts for the specified score.
     *
     * @param score     a score
     * @param checkouts a list of checkouts for the specified score
     * @return this checkout table builder
     */
    TableBuilder setCheckouts(int score, Collection<? extends Checkout> checkouts);

    /**
     * Gets a new {@code Table} with the specified dartboard type, check-in
     * mode, checkout mode and checkouts.
     * <p>
     * Non-null, default values have to be set for unspecified properties.
     *
     * @return a new {@code Table} with the set dartboard type, check-in mode,
     * checkout mode and checkouts.
     */
    Table build();
}
