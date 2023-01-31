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
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;
import java.util.Map;

class MappedCheckoutTableBuilderTests {
    @Test
    void getTheDefaultBoardType() {
        CheckoutTableBuilder checkoutTableBuilder = new MappedCheckoutTableBuilder();
        CheckoutTable checkoutTable = checkoutTableBuilder.build();

        BoardType boardType = BoardType.LONDON;

        Assertions.assertEquals(boardType, checkoutTable.getBoardType());
    }

    @Test
    void getTheDefaultCheckInType() {
        CheckoutTableBuilder checkoutTableBuilder = new MappedCheckoutTableBuilder();
        CheckoutTable checkoutTable = checkoutTableBuilder.build();

        CheckType checkInType = CheckType.ANY;

        Assertions.assertEquals(checkInType, checkoutTable.getCheckInType());
    }

    @Test
    void getTheDefaultCheckoutType() {
        CheckoutTableBuilder checkoutTableBuilder = new MappedCheckoutTableBuilder();
        CheckoutTable checkoutTable = checkoutTableBuilder.build();

        CheckType checkoutType = CheckType.DOUBLE;

        Assertions.assertEquals(checkoutType, checkoutTable.getCheckoutType());
    }

    @Test
    void getTheDefaultCheckoutMap() {
        CheckoutTableBuilder checkoutTableBuilder = new MappedCheckoutTableBuilder();
        CheckoutTable checkoutTable = checkoutTableBuilder.build();

        Map<Integer, List<Checkout>> checkoutMap = Collections.emptyMap();

        Assertions.assertEquals(checkoutMap, checkoutTable.getCheckoutMap());
    }
}
