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

import java.util.Collection;

public class MappedCheckoutTableBuilder implements CheckoutTableBuilder {
    @Override
    public CheckoutTableBuilder setBoardType(BoardType boardType) {
        return this;
    }

    @Override
    public CheckoutTableBuilder setCheckInType(CheckType checkInType) {
        return this;
    }

    @Override
    public CheckoutTableBuilder setCheckoutType(CheckType checkoutType) {
        return this;
    }

    @Override
    public CheckoutTableBuilder setCheckouts(int score, Collection<Checkout> checkouts) {
        return this;
    }

    @Override
    public CheckoutTable build() {
        return null;
    }
}
