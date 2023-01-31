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
import nl.mauritssilvis.darts.java.tables.utils.GroupedCheckoutTestUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;

class MappedCheckoutTableBuilderTests {
    @Test
    void getTheBoardType() {
        BoardType boardType = BoardType.LONDON;

        CheckoutTableBuilder checkoutTableBuilder = new MappedCheckoutTableBuilder();

        CheckoutTable checkoutTable = checkoutTableBuilder
                .setBoardType(boardType)
                .build();

        Assertions.assertEquals(boardType, checkoutTable.getBoardType());
    }

    @Test
    void getTheDefaultBoardType() {
        CheckoutTableBuilder checkoutTableBuilder = new MappedCheckoutTableBuilder();
        CheckoutTable checkoutTable = checkoutTableBuilder.build();

        BoardType boardType = BoardType.LONDON;

        Assertions.assertEquals(boardType, checkoutTable.getBoardType());
    }

    @Test
    void getTheCheckInType() {
        CheckType checkInType = CheckType.MASTER;

        CheckoutTableBuilder checkoutTableBuilder = new MappedCheckoutTableBuilder();

        CheckoutTable checkoutTable = checkoutTableBuilder
                .setCheckInType(checkInType)
                .build();

        Assertions.assertEquals(checkInType, checkoutTable.getCheckInType());
    }

    @Test
    void getTheDefaultCheckInType() {
        CheckoutTableBuilder checkoutTableBuilder = new MappedCheckoutTableBuilder();
        CheckoutTable checkoutTable = checkoutTableBuilder.build();

        CheckType checkInType = CheckType.ANY;

        Assertions.assertEquals(checkInType, checkoutTable.getCheckInType());
    }

    @Test
    void getTheCheckoutType() {
        CheckType checkoutType = CheckType.ANY;

        CheckoutTableBuilder checkoutTableBuilder = new MappedCheckoutTableBuilder();

        CheckoutTable checkoutTable = checkoutTableBuilder
                .setCheckoutType(checkoutType)
                .build();

        Assertions.assertEquals(checkoutType, checkoutTable.getCheckoutType());
    }

    @Test
    void getTheDefaultCheckoutType() {
        CheckoutTableBuilder checkoutTableBuilder = new MappedCheckoutTableBuilder();
        CheckoutTable checkoutTable = checkoutTableBuilder.build();

        CheckType checkoutType = CheckType.DOUBLE;

        Assertions.assertEquals(checkoutType, checkoutTable.getCheckoutType());
    }

    @Test
    void getTheCheckoutMap() {
        Collection<Collection<Collection<String>>> names5 = List.of(
                List.of(List.of("5"))
        );

        Collection<Collection<Collection<String>>> names6 = List.of(
                List.of(List.of("2"), List.of("D2", "4"))
        );

        Map<Integer, List<Checkout>> checkoutMap = Map.of(
                5, GroupedCheckoutTestUtils.getCheckouts(names5),
                6, GroupedCheckoutTestUtils.getCheckouts(names6)
        );

        CheckoutTableBuilder checkoutTableBuilder = new MappedCheckoutTableBuilder();

        CheckoutTable checkoutTable = checkoutTableBuilder
                .setCheckouts(5, checkoutMap.get(5))
                .setCheckouts(6, checkoutMap.get(6))
                .build();

        Assertions.assertEquals(checkoutMap, checkoutTable.getCheckoutMap());
    }

    @Test
    void getTheDefaultCheckoutMap() {
        CheckoutTableBuilder checkoutTableBuilder = new MappedCheckoutTableBuilder();
        CheckoutTable checkoutTable = checkoutTableBuilder.build();

        Map<Integer, List<Checkout>> checkoutMap = Collections.emptyMap();

        Assertions.assertEquals(checkoutMap, checkoutTable.getCheckoutMap());
    }
}
