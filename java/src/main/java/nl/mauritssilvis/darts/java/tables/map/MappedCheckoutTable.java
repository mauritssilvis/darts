/*
 * Copyright © 2023 Maurits H. Silvis
 * SPDX-License-Identifier: GPL-3.0-or-later
 */

package nl.mauritssilvis.darts.java.tables.map;

import nl.mauritssilvis.darts.java.checkouts.Checkout;
import nl.mauritssilvis.darts.java.settings.BoardType;
import nl.mauritssilvis.darts.java.settings.CheckType;
import nl.mauritssilvis.darts.java.tables.CheckoutTable;

import java.util.List;
import java.util.Map;

/**
 * An implementation of the {@code CheckoutTable} interface that uses a map to
 * store checkouts.
 * <p>
 * Relevant design patterns: Immutable object, static factory method.
 */
public final class MappedCheckoutTable implements CheckoutTable {
    private MappedCheckoutTable(
            BoardType boardType,
            CheckType checkInType,
            CheckType checkoutType,
            Map<Integer, List<Checkout>> checkoutMap
    ) {
    }

    /**
     * Returns a new {@code MappedCheckoutTable} with the specified dartboard
     * type, check-in type, checkout type and checkout map.
     *
     * @param boardType    the dartboard type
     * @param checkInType  the check-in type
     * @param checkoutType the checkout type
     * @param checkoutMap  the checkout map
     * @return a new {@code MappedCheckoutTable} with the specified dartboard
     * type, check-in type, checkout type and checkout map.
     */
    public static MappedCheckoutTable of(
            BoardType boardType,
            CheckType checkInType,
            CheckType checkoutType,
            Map<Integer, List<Checkout>> checkoutMap
    ) {
        return new MappedCheckoutTable(boardType, checkInType, checkoutType, checkoutMap);
    }

    @Override
    public BoardType getBoardType() {
        return null;
    }

    @Override
    public CheckType getCheckInType() {
        return null;
    }

    @Override
    public CheckType getCheckOutType() {
        return null;
    }

    @Override
    public Map<Integer, List<Checkout>> getCheckoutMap() {
        return null;
    }
}
