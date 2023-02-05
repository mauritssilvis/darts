/*
 * Copyright © 2023 Maurits H. Silvis
 * SPDX-License-Identifier: GPL-3.0-or-later
 */

package nl.mauritssilvis.darts.java.tables.map;

import nl.mauritssilvis.darts.java.checkouts.Checkout;
import nl.mauritssilvis.darts.java.checkouts.CheckoutTestUtils;
import nl.mauritssilvis.darts.java.checkouts.descending.GroupedCheckoutTestUtils;
import nl.mauritssilvis.darts.java.settings.BoardType;
import nl.mauritssilvis.darts.java.settings.CheckType;
import nl.mauritssilvis.darts.java.tables.CheckoutTable;
import nl.mauritssilvis.darts.java.tables.CheckoutTableBuilder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.*;

class MapBasedCheckoutTableBuilderTests {
    @Test
    void getTheBoardType() {
        BoardType boardType = BoardType.QUADRO;

        CheckoutTableBuilder checkoutTableBuilder = MapBasedCheckoutTableBuilder.create();

        CheckoutTable checkoutTable = checkoutTableBuilder
                .setBoardType(boardType)
                .build();

        Assertions.assertEquals(boardType, checkoutTable.getBoardType());
    }

    @Test
    void getTheDefaultBoardType() {
        CheckoutTableBuilder checkoutTableBuilder = MapBasedCheckoutTableBuilder.create();
        CheckoutTable checkoutTable = checkoutTableBuilder.build();

        BoardType boardType = BoardType.LONDON;

        Assertions.assertEquals(boardType, checkoutTable.getBoardType());
    }

    @Test
    void overrideTheBoardType() {
        BoardType boardType1 = BoardType.QUADRO;
        BoardType boardType2 = BoardType.YORKSHIRE;

        CheckoutTableBuilder checkoutTableBuilder = MapBasedCheckoutTableBuilder.create();

        CheckoutTable checkoutTable = checkoutTableBuilder
                .setBoardType(boardType1)
                .setBoardType(boardType2)
                .build();

        Assertions.assertEquals(boardType2, checkoutTable.getBoardType());
    }

    @Test
    void getTheCheckInType() {
        CheckType checkInType = CheckType.MASTER;

        CheckoutTableBuilder checkoutTableBuilder = MapBasedCheckoutTableBuilder.create();

        CheckoutTable checkoutTable = checkoutTableBuilder
                .setCheckInType(checkInType)
                .build();

        Assertions.assertEquals(checkInType, checkoutTable.getCheckInType());
    }

    @Test
    void getTheDefaultCheckInType() {
        CheckoutTableBuilder checkoutTableBuilder = MapBasedCheckoutTableBuilder.create();
        CheckoutTable checkoutTable = checkoutTableBuilder.build();

        CheckType checkInType = CheckType.ANY;

        Assertions.assertEquals(checkInType, checkoutTable.getCheckInType());
    }

    @Test
    void overrideTheCheckInType() {
        CheckType checkInType1 = CheckType.DOUBLE;
        CheckType checkInType2 = CheckType.MASTER;

        CheckoutTableBuilder checkoutTableBuilder = MapBasedCheckoutTableBuilder.create();

        CheckoutTable checkoutTable = checkoutTableBuilder
                .setCheckInType(checkInType1)
                .setCheckInType(checkInType2)
                .build();

        Assertions.assertEquals(checkInType2, checkoutTable.getCheckInType());
    }

    @Test
    void getTheCheckoutType() {
        CheckType checkoutType = CheckType.ANY;

        CheckoutTableBuilder checkoutTableBuilder = MapBasedCheckoutTableBuilder.create();

        CheckoutTable checkoutTable = checkoutTableBuilder
                .setCheckoutType(checkoutType)
                .build();

        Assertions.assertEquals(checkoutType, checkoutTable.getCheckoutType());
    }

    @Test
    void getTheDefaultCheckoutType() {
        CheckoutTableBuilder checkoutTableBuilder = MapBasedCheckoutTableBuilder.create();
        CheckoutTable checkoutTable = checkoutTableBuilder.build();

        CheckType checkoutType = CheckType.DOUBLE;

        Assertions.assertEquals(checkoutType, checkoutTable.getCheckoutType());
    }

    @Test
    void overrideTheCheckoutType() {
        CheckType checkoutType1 = CheckType.ANY;
        CheckType checkoutType2 = CheckType.MASTER;

        CheckoutTableBuilder checkoutTableBuilder = MapBasedCheckoutTableBuilder.create();

        CheckoutTable checkoutTable = checkoutTableBuilder
                .setCheckoutType(checkoutType1)
                .setCheckoutType(checkoutType2)
                .build();

        Assertions.assertEquals(checkoutType2, checkoutTable.getCheckoutType());
    }

    @Test
    void getTheCheckoutMap() {
        int score1 = 5;
        int score2 = 6;

        Collection<Collection<Collection<String>>> namesPerCheckout1 = List.of(
                List.of(List.of("5"))
        );

        Collection<Collection<Collection<String>>> namesPerCheckout2 = List.of(
                List.of(List.of("2"), List.of("D2", "4"))
        );

        Collection<Checkout> checkouts1 = GroupedCheckoutTestUtils.getCheckouts(namesPerCheckout1);
        Collection<Checkout> checkouts2 = GroupedCheckoutTestUtils.getCheckouts(namesPerCheckout2);

        CheckoutTableBuilder checkoutTableBuilder = MapBasedCheckoutTableBuilder.create();

        CheckoutTable checkoutTable = checkoutTableBuilder
                .setCheckouts(score1, checkouts1)
                .setCheckouts(score2, checkouts2)
                .build();

        Map<Integer, List<Checkout>> storedCheckoutMap = checkoutTable.getCheckoutMap();

        Collection<Checkout> storedCheckouts1 = storedCheckoutMap.get(score1);
        Collection<Checkout> storedCheckouts2 = storedCheckoutMap.get(score2);

        Collection<List<List<String>>> storedNames1 = CheckoutTestUtils.getAllNames(storedCheckouts1);
        Collection<List<List<String>>> storedNames2 = CheckoutTestUtils.getAllNames(storedCheckouts2);

        Assertions.assertAll(
                () -> Assertions.assertEquals(2, storedCheckoutMap.size()),
                () -> Assertions.assertEquals(namesPerCheckout1, storedNames1),
                () -> Assertions.assertEquals(namesPerCheckout2, storedNames2)
        );
    }

    @Test
    void getTheDefaultCheckoutMap() {
        CheckoutTableBuilder checkoutTableBuilder = MapBasedCheckoutTableBuilder.create();
        CheckoutTable checkoutTable = checkoutTableBuilder.build();

        Map<Integer, Collection<Checkout>> checkoutMap = Collections.emptyMap();

        Assertions.assertEquals(checkoutMap, checkoutTable.getCheckoutMap());
    }

    @Test
    void overrideACheckoutList() {
        int score = 7;

        Collection<Collection<Collection<String>>> namesPerCheckout1 = List.of(
                List.of(List.of("7"))
        );

        Collection<Collection<Collection<String>>> namesPerCheckout2 = List.of(
                List.of(List.of("1"), List.of("D3", "6"))
        );

        Collection<Checkout> checkouts1 = GroupedCheckoutTestUtils.getCheckouts(namesPerCheckout1);
        Collection<Checkout> checkouts2 = GroupedCheckoutTestUtils.getCheckouts(namesPerCheckout2);

        CheckoutTableBuilder checkoutTableBuilder = MapBasedCheckoutTableBuilder.create();

        CheckoutTable checkoutTable = checkoutTableBuilder
                .setCheckouts(score, checkouts1)
                .setCheckouts(score, checkouts2)
                .build();

        Map<Integer, List<Checkout>> storedCheckoutMap = checkoutTable.getCheckoutMap();
        Collection<Checkout> storedCheckouts = storedCheckoutMap.get(score);
        Collection<List<List<String>>> storedNames = CheckoutTestUtils.getAllNames(storedCheckouts);

        Assertions.assertAll(
                () -> Assertions.assertEquals(1, storedCheckoutMap.size()),
                () -> Assertions.assertEquals(namesPerCheckout2, storedNames)
        );
    }

    @Test
    void storeIndependentCheckouts() {
        int score = 9;

        Collection<Collection<Collection<String>>> namesPerCheckout = List.of(
                List.of(List.of("7"), List.of("2", "D1"))
        );

        Collection<Checkout> checkouts = new ArrayList<>(GroupedCheckoutTestUtils.getCheckouts(namesPerCheckout));

        CheckoutTableBuilder checkoutTableBuilder = MapBasedCheckoutTableBuilder.create();

        checkoutTableBuilder.setCheckouts(score, checkouts);

        checkouts.clear();

        CheckoutTable checkoutTable = checkoutTableBuilder.build();

        Map<Integer, List<Checkout>> storedCheckoutMap = checkoutTable.getCheckoutMap();
        Collection<Checkout> storedCheckouts = storedCheckoutMap.get(score);
        Collection<List<List<String>>> storedNames = CheckoutTestUtils.getAllNames(storedCheckouts);

        Assertions.assertAll(
                () -> Assertions.assertFalse(storedCheckoutMap.isEmpty()),
                () -> Assertions.assertEquals(namesPerCheckout, storedNames)
        );
    }

    @Test
    void getACustomCheckoutTable() {
        BoardType boardType = BoardType.QUADRO;
        CheckType checkInType = CheckType.DOUBLE;
        CheckType checkoutType = CheckType.MASTER;

        int score1 = 4;
        int score2 = 8;

        Collection<Collection<Collection<String>>> namesPerCheckout1 = List.of(
                List.of(List.of("D2"))
        );

        Collection<Collection<Collection<String>>> namesPerCheckout2 = List.of(
                List.of(List.of("2"), List.of("D3", "6"))
        );

        Collection<Checkout> checkouts1 = GroupedCheckoutTestUtils.getCheckouts(namesPerCheckout1);
        Collection<Checkout> checkouts2 = GroupedCheckoutTestUtils.getCheckouts(namesPerCheckout2);

        CheckoutTableBuilder checkoutTableBuilder = MapBasedCheckoutTableBuilder.create();

        CheckoutTable checkoutTable = checkoutTableBuilder
                .setBoardType(boardType)
                .setCheckInType(checkInType)
                .setCheckoutType(checkoutType)
                .setCheckouts(score1, checkouts1)
                .setCheckouts(score2, checkouts2)
                .build();

        Map<Integer, List<Checkout>> storedCheckoutMap = checkoutTable.getCheckoutMap();

        Collection<Checkout> storedCheckouts1 = storedCheckoutMap.get(score1);
        Collection<Checkout> storedCheckouts2 = storedCheckoutMap.get(score2);

        Collection<List<List<String>>> storedNames1 = CheckoutTestUtils.getAllNames(storedCheckouts1);
        Collection<List<List<String>>> storedNames2 = CheckoutTestUtils.getAllNames(storedCheckouts2);

        Assertions.assertAll(
                () -> Assertions.assertEquals(boardType, checkoutTable.getBoardType()),
                () -> Assertions.assertEquals(checkInType, checkoutTable.getCheckInType()),
                () -> Assertions.assertEquals(checkoutType, checkoutTable.getCheckoutType()),
                () -> Assertions.assertEquals(2, storedCheckoutMap.size()),
                () -> Assertions.assertEquals(namesPerCheckout1, storedNames1),
                () -> Assertions.assertEquals(namesPerCheckout2, storedNames2)
        );
    }
}
