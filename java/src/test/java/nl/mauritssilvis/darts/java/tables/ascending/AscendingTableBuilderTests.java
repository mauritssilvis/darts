/*
 * Copyright © 2023 Maurits Silvis
 * SPDX-License-Identifier: GPL-3.0-or-later
 */

package nl.mauritssilvis.darts.java.tables.ascending;

import nl.mauritssilvis.darts.java.checkouts.Checkout;
import nl.mauritssilvis.darts.java.checkouts.CheckoutTestUtils;
import nl.mauritssilvis.darts.java.checkouts.descending.GroupedCheckoutTestUtils;
import nl.mauritssilvis.darts.java.settings.BoardType;
import nl.mauritssilvis.darts.java.settings.CheckMode;
import nl.mauritssilvis.darts.java.tables.Table;
import nl.mauritssilvis.darts.java.tables.TableBuilder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.*;

class AscendingTableBuilderTests {
    @Test
    void getTheBoardType() {
        BoardType boardType = BoardType.QUADRO;

        TableBuilder tableBuilder = AscendingTableBuilder.create();

        Table table = tableBuilder
                .setBoardType(boardType)
                .build();

        Assertions.assertEquals(boardType, table.getBoardType());
    }

    @Test
    void getTheDefaultBoardType() {
        TableBuilder tableBuilder = AscendingTableBuilder.create();
        Table table = tableBuilder.build();

        BoardType boardType = BoardType.LONDON;

        Assertions.assertEquals(boardType, table.getBoardType());
    }

    @Test
    void overrideTheBoardType() {
        BoardType boardType1 = BoardType.QUADRO;
        BoardType boardType2 = BoardType.YORKSHIRE;

        TableBuilder tableBuilder = AscendingTableBuilder.create();

        Table table = tableBuilder
                .setBoardType(boardType1)
                .setBoardType(boardType2)
                .build();

        Assertions.assertEquals(boardType2, table.getBoardType());
    }

    @Test
    void getTheCheckInMode() {
        CheckMode checkInMode = CheckMode.MASTER;

        TableBuilder tableBuilder = AscendingTableBuilder.create();

        Table table = tableBuilder
                .setCheckInMode(checkInMode)
                .build();

        Assertions.assertEquals(checkInMode, table.getCheckInMode());
    }

    @Test
    void getTheDefaultCheckInMode() {
        TableBuilder tableBuilder = AscendingTableBuilder.create();
        Table table = tableBuilder.build();

        CheckMode checkInMode = CheckMode.ANY;

        Assertions.assertEquals(checkInMode, table.getCheckInMode());
    }

    @Test
    void overrideTheCheckInMode() {
        CheckMode checkInMode1 = CheckMode.DOUBLE;
        CheckMode checkInMode2 = CheckMode.MASTER;

        TableBuilder tableBuilder = AscendingTableBuilder.create();

        Table table = tableBuilder
                .setCheckInMode(checkInMode1)
                .setCheckInMode(checkInMode2)
                .build();

        Assertions.assertEquals(checkInMode2, table.getCheckInMode());
    }

    @Test
    void getTheCheckoutMode() {
        CheckMode checkoutMode = CheckMode.ANY;

        TableBuilder tableBuilder = AscendingTableBuilder.create();

        Table table = tableBuilder
                .setCheckoutMode(checkoutMode)
                .build();

        Assertions.assertEquals(checkoutMode, table.getCheckoutMode());
    }

    @Test
    void getTheDefaultCheckoutMode() {
        TableBuilder tableBuilder = AscendingTableBuilder.create();
        Table table = tableBuilder.build();

        CheckMode checkoutMode = CheckMode.DOUBLE;

        Assertions.assertEquals(checkoutMode, table.getCheckoutMode());
    }

    @Test
    void overrideTheCheckoutMode() {
        CheckMode checkoutMode1 = CheckMode.ANY;
        CheckMode checkoutMode2 = CheckMode.MASTER;

        TableBuilder tableBuilder = AscendingTableBuilder.create();

        Table table = tableBuilder
                .setCheckoutMode(checkoutMode1)
                .setCheckoutMode(checkoutMode2)
                .build();

        Assertions.assertEquals(checkoutMode2, table.getCheckoutMode());
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

        TableBuilder tableBuilder = AscendingTableBuilder.create();

        Table table = tableBuilder
                .setCheckouts(score1, checkouts1)
                .setCheckouts(score2, checkouts2)
                .build();

        Map<Integer, List<Checkout>> storedCheckoutMap = table.getCheckoutMap();

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
        TableBuilder tableBuilder = AscendingTableBuilder.create();
        Table table = tableBuilder.build();

        Map<Integer, Collection<Checkout>> checkoutMap = Collections.emptyMap();

        Assertions.assertEquals(checkoutMap, table.getCheckoutMap());
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

        TableBuilder tableBuilder = AscendingTableBuilder.create();

        Table table = tableBuilder
                .setCheckouts(score, checkouts1)
                .setCheckouts(score, checkouts2)
                .build();

        Map<Integer, List<Checkout>> storedCheckoutMap = table.getCheckoutMap();
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

        TableBuilder tableBuilder = AscendingTableBuilder.create();

        tableBuilder.setCheckouts(score, checkouts);

        checkouts.clear();

        Table table = tableBuilder.build();

        Map<Integer, List<Checkout>> storedCheckoutMap = table.getCheckoutMap();
        Collection<Checkout> storedCheckouts = storedCheckoutMap.get(score);
        Collection<List<List<String>>> storedNames = CheckoutTestUtils.getAllNames(storedCheckouts);

        Assertions.assertAll(
                () -> Assertions.assertFalse(storedCheckoutMap.isEmpty()),
                () -> Assertions.assertEquals(namesPerCheckout, storedNames)
        );
    }

    @Test
    void getACustomTable() {
        BoardType boardType = BoardType.QUADRO;
        CheckMode checkInMode = CheckMode.DOUBLE;
        CheckMode checkoutMode = CheckMode.MASTER;

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

        TableBuilder tableBuilder = AscendingTableBuilder.create();

        Table table = tableBuilder
                .setBoardType(boardType)
                .setCheckInMode(checkInMode)
                .setCheckoutMode(checkoutMode)
                .setCheckouts(score1, checkouts1)
                .setCheckouts(score2, checkouts2)
                .build();

        Map<Integer, List<Checkout>> storedCheckoutMap = table.getCheckoutMap();

        Collection<Checkout> storedCheckouts1 = storedCheckoutMap.get(score1);
        Collection<Checkout> storedCheckouts2 = storedCheckoutMap.get(score2);

        Collection<List<List<String>>> storedNames1 = CheckoutTestUtils.getAllNames(storedCheckouts1);
        Collection<List<List<String>>> storedNames2 = CheckoutTestUtils.getAllNames(storedCheckouts2);

        Assertions.assertAll(
                () -> Assertions.assertEquals(boardType, table.getBoardType()),
                () -> Assertions.assertEquals(checkInMode, table.getCheckInMode()),
                () -> Assertions.assertEquals(checkoutMode, table.getCheckoutMode()),
                () -> Assertions.assertEquals(2, storedCheckoutMap.size()),
                () -> Assertions.assertEquals(namesPerCheckout1, storedNames1),
                () -> Assertions.assertEquals(namesPerCheckout2, storedNames2)
        );
    }

    @Test
    void getUnequalTableBuilders() {
        TableBuilder tableBuilder1 = AscendingTableBuilder.create();
        TableBuilder tableBuilder2 = AscendingTableBuilder.create();

        Assertions.assertNotEquals(tableBuilder1, tableBuilder2);
    }

    @Test
    void convertToAString() {
        TableBuilder tableBuilder = AscendingTableBuilder.create();
        String str = tableBuilder.toString();

        Assertions.assertAll(
                () -> Assertions.assertTrue(str.contains(tableBuilder.getClass().getSimpleName())),
                () -> Assertions.assertTrue(str.contains("boardType")),
                () -> Assertions.assertTrue(str.contains("checkInMode")),
                () -> Assertions.assertTrue(str.contains("checkoutMode")),
                () -> Assertions.assertTrue(str.contains("checkoutMap"))
        );
    }
}
