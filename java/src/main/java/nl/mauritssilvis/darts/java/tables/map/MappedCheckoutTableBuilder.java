/*
 * Copyright © 2023 Maurits H. Silvis
 * SPDX-License-Identifier: GPL-3.0-or-later
 */

package nl.mauritssilvis.darts.java.tables.map;

import nl.mauritssilvis.darts.java.checkouts.Checkout;
import nl.mauritssilvis.darts.java.settings.BoardType;
import nl.mauritssilvis.darts.java.settings.CheckType;
import nl.mauritssilvis.darts.java.tables.CheckoutTable;
import nl.mauritssilvis.darts.java.tables.CheckoutTableBuilder;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * An implementation of the {@code CheckoutTableBuilder} interface that builds
 * {@code MappedCheckoutTable} objects. Default values are provided for
 * unspecified properties.
 * <p>
 * Relevant design patterns: Builder.
 */
public class MappedCheckoutTableBuilder implements CheckoutTableBuilder {
    private BoardType boardType = BoardType.LONDON;
    private CheckType checkInType = CheckType.ANY;
    private CheckType checkoutType = CheckType.DOUBLE;
    private final Map<Integer, List<Checkout>> checkoutMap = new HashMap<>();

    @Override
    public CheckoutTableBuilder setBoardType(BoardType boardType) {
        this.boardType = boardType;
        return this;
    }

    @Override
    public CheckoutTableBuilder setCheckInType(CheckType checkInType) {
        this.checkInType = checkInType;
        return this;
    }

    @Override
    public CheckoutTableBuilder setCheckoutType(CheckType checkoutType) {
        this.checkoutType = checkoutType;
        return this;
    }

    @Override
    public CheckoutTableBuilder setCheckouts(int score, List<Checkout> checkouts) {
        checkoutMap.put(score, checkouts);
        return this;
    }

    @Override
    public CheckoutTable build() {
        return MappedCheckoutTable.of(boardType, checkInType, checkoutType, checkoutMap);
    }
}
