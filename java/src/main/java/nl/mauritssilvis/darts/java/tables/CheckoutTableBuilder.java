/*
 * Copyright © 2023 Maurits H. Silvis
 * SPDX-License-Identifier: GPL-3.0-or-later
 */

package nl.mauritssilvis.darts.java.tables;

import nl.mauritssilvis.darts.java.checkouts.Checkout;
import nl.mauritssilvis.darts.java.settings.BoardType;
import nl.mauritssilvis.darts.java.settings.CheckType;

import java.util.Collection;

/**
 * A checkout table builder.
 * <p>
 * Relevant design patterns: Builder, fluent interface.
 */
public interface CheckoutTableBuilder {
    /**
     * Sets the dartboard type.
     *
     * @param boardType the dartboard type
     * @return this checkout table builder
     */
    CheckoutTable setBoardType(BoardType boardType);

    /**
     * Sets the check-in type.
     *
     * @param checkInType the check-in type
     * @return this checkout table builder
     */
    CheckoutTable setCheckInType(CheckType checkInType);

    /**
     * Sets the checkout type.
     *
     * @param checkoutType the checkout type
     * @return this checkout table builder
     */
    CheckoutTable setCheckoutType(CheckType checkoutType);

    /**
     * Sets the checkouts for the specified score.
     *
     * @param score     a score
     * @param checkouts a collection of checkouts for the specified score
     * @return this checkout table builder
     */
    CheckoutTable setCheckouts(int score, Collection<Checkout> checkouts);

    /**
     * Gets a new {@code CheckoutTable} with the set dartboard type, check-in
     * type, checkout type and checkouts.
     * <p>
     * Non-null, default values have to be given to unspecified properties.
     *
     * @return a new {@code CheckoutTable} with the set dartboard type, check-in
     * type, checkout type and checkouts.
     */
    CheckoutTable build();
}
