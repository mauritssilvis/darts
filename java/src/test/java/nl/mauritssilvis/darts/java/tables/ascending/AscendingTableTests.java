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
import nl.mauritssilvis.darts.java.settings.ThrowMode;
import nl.mauritssilvis.darts.java.tables.Table;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

class AscendingTableTests {
    @Test
    void doNotStoreCheckoutsWithAnotherScore() {
        BoardType boardType = BoardType.LONDON;
        CheckMode checkInMode = CheckMode.ANY;
        CheckMode checkoutMode = CheckMode.DOUBLE;
        int numThrows = -1;
        ThrowMode throwMode = ThrowMode.OPTIMAL;

        int score = 6;

        Collection<Collection<Collection<String>>> namesPerCheckout = List.of(
                List.of(List.of("D3")), List.of(List.of("D4"))
        );

        Collection<Checkout> checkouts = GroupedCheckoutTestUtils.getCheckouts(namesPerCheckout);

        Map<Integer, Collection<Checkout>> checkoutMap = Map.of(score, checkouts);

        Assertions.assertThrows(
                IllegalArgumentException.class,
                () -> AscendingTable.of(boardType, checkInMode, checkoutMode, numThrows, throwMode, checkoutMap)
        );
    }

    @Test
    void storeAnIndependentCheckoutMap() {
        BoardType boardType = BoardType.QUADRO;
        CheckMode checkInMode = CheckMode.ANY;
        CheckMode checkoutMode = CheckMode.ANY;
        int numThrows = -1;
        ThrowMode throwMode = ThrowMode.OPTIMAL;

        int score = 10;

        Collection<Collection<Collection<String>>> namesPerCheckout = List.of(
                List.of(List.of("5"), List.of("3"), List.of("2", "D1"))
        );

        Collection<Checkout> checkouts = GroupedCheckoutTestUtils.getCheckouts(namesPerCheckout);

        Map<Integer, Collection<Checkout>> checkoutMap = new HashMap<>();
        checkoutMap.put(score, checkouts);

        Table table = AscendingTable.of(boardType, checkInMode, checkoutMode, numThrows, throwMode, checkoutMap);

        checkoutMap.clear();

        Map<Integer, List<Checkout>> storedCheckoutMap = table.getCheckoutMap();
        Collection<Checkout> storedCheckouts = storedCheckoutMap.get(score);
        Collection<List<List<String>>> storedNames = CheckoutTestUtils.getAllNames(storedCheckouts);

        Assertions.assertAll(
                () -> Assertions.assertEquals(1, storedCheckoutMap.size()),
                () -> Assertions.assertEquals(namesPerCheckout, storedNames)
        );
    }

    @Test
    void storeIndependentCheckouts() {
        BoardType boardType = BoardType.LONDON;
        CheckMode checkInMode = CheckMode.ANY;
        CheckMode checkoutMode = CheckMode.MASTER;
        int numThrows = -1;
        ThrowMode throwMode = ThrowMode.OPTIMAL;

        int score = 8;

        Collection<Collection<Collection<String>>> namesPerCheckout = List.of(
                List.of(List.of("2"), List.of("T2", "D3"))
        );

        Collection<Checkout> checkouts = new ArrayList<>(GroupedCheckoutTestUtils.getCheckouts(namesPerCheckout));

        Map<Integer, Collection<Checkout>> checkoutMap = new HashMap<>();
        checkoutMap.put(score, checkouts);

        Table table = AscendingTable.of(boardType, checkInMode, checkoutMode, numThrows, throwMode, checkoutMap);

        checkoutMap.get(score).clear();

        Map<Integer, List<Checkout>> storedCheckoutMap = table.getCheckoutMap();
        Collection<Checkout> storedCheckouts = storedCheckoutMap.get(score);
        Collection<List<List<String>>> storedNames = CheckoutTestUtils.getAllNames(storedCheckouts);

        Assertions.assertAll(
                () -> Assertions.assertEquals(1, storedCheckoutMap.size()),
                () -> Assertions.assertEquals(namesPerCheckout, storedNames)
        );
    }

    @Test
    void storeCheckoutsInAscendingOrder() {
        BoardType boardType = BoardType.QUADRO;
        CheckMode checkInMode = CheckMode.DOUBLE;
        CheckMode checkoutMode = CheckMode.DOUBLE;
        int numThrows = -1;
        ThrowMode throwMode = ThrowMode.OPTIMAL;

        List<Integer> scores = List.of(1, 9, 3, 100, 20, 7, 6, 50, 1000);

        Map<Integer, Collection<Checkout>> checkoutMap = scores.stream()
                .collect(Collectors.toMap(Function.identity(), i -> Collections.emptyList()));

        Table table = AscendingTable.of(boardType, checkInMode, checkoutMode, numThrows, throwMode, checkoutMap);

        Map<Integer, List<Checkout>> storedCheckoutMap = table.getCheckoutMap();
        List<Integer> storedScores = new ArrayList<>(storedCheckoutMap.keySet());

        List<Integer> expectedScores = List.of(1, 3, 6, 7, 9, 20, 50, 100, 1000);

        Assertions.assertEquals(expectedScores, storedScores);
    }

    @Test
    void getTheBoardType() {
        BoardType boardType = BoardType.LONDON;
        CheckMode checkInMode = CheckMode.ANY;
        CheckMode checkoutMode = CheckMode.MASTER;
        int numThrows = -1;
        ThrowMode throwMode = ThrowMode.OPTIMAL;
        Map<Integer, Collection<Checkout>> checkoutMap = Collections.emptyMap();

        Table table = AscendingTable.of(boardType, checkInMode, checkoutMode, numThrows, throwMode, checkoutMap);

        Assertions.assertEquals(boardType, table.getBoardType());
    }

    @Test
    void getTheCheckInMode() {
        BoardType boardType = BoardType.LONDON;
        CheckMode checkInMode = CheckMode.MASTER;
        CheckMode checkoutMode = CheckMode.DOUBLE;
        int numThrows = -1;
        ThrowMode throwMode = ThrowMode.OPTIMAL;
        Map<Integer, Collection<Checkout>> checkoutMap = Collections.emptyMap();

        Table table = AscendingTable.of(boardType, checkInMode, checkoutMode, numThrows, throwMode, checkoutMap);

        Assertions.assertEquals(checkInMode, table.getCheckInMode());
    }

    @Test
    void getTheCheckoutMode() {
        BoardType boardType = BoardType.LONDON;
        CheckMode checkInMode = CheckMode.ANY;
        CheckMode checkoutMode = CheckMode.DOUBLE;
        int numThrows = -1;
        ThrowMode throwMode = ThrowMode.OPTIMAL;
        Map<Integer, Collection<Checkout>> checkoutMap = Collections.emptyMap();

        Table table = AscendingTable.of(boardType, checkInMode, checkoutMode, numThrows, throwMode, checkoutMap);

        Assertions.assertEquals(checkoutMode, table.getCheckoutMode());
    }

    @Test
    void getTheCheckoutMap() {
        BoardType boardType = BoardType.LONDON;
        CheckMode checkInMode = CheckMode.ANY;
        CheckMode checkoutMode = CheckMode.DOUBLE;
        int numThrows = -1;
        ThrowMode throwMode = ThrowMode.OPTIMAL;

        int score1 = 4;
        int score2 = 6;

        Collection<Collection<Collection<String>>> namesPerCheckout1 = List.of(
                List.of(List.of("1"), List.of("1"), List.of("D1")),
                List.of(List.of("D1", "2"), List.of("D1")),
                List.of(List.of("D2"))
        );

        Collection<Collection<Collection<String>>> namesPerCheckout2 = List.of(
                List.of(List.of("2", "D1"), List.of("D2")),
                List.of(List.of("D3"))
        );

        Collection<Checkout> checkouts1 = GroupedCheckoutTestUtils.getCheckouts(namesPerCheckout1);
        Collection<Checkout> checkouts2 = GroupedCheckoutTestUtils.getCheckouts(namesPerCheckout2);

        Map<Integer, Collection<Checkout>> checkoutMap = Map.of(
                score1, checkouts1,
                score2, checkouts2
        );

        Table table = AscendingTable.of(boardType, checkInMode, checkoutMode, numThrows, throwMode, checkoutMap);

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
    void getTheCheckoutMapWithAnEmptyMap() {
        BoardType boardType = BoardType.QUADRO;
        CheckMode checkInMode = CheckMode.DOUBLE;
        CheckMode checkoutMode = CheckMode.DOUBLE;
        int numThrows = -1;
        ThrowMode throwMode = ThrowMode.OPTIMAL;
        Map<Integer, Collection<Checkout>> checkoutMap = Collections.emptyMap();

        Table table = AscendingTable.of(boardType, checkInMode, checkoutMode, numThrows, throwMode, checkoutMap);

        Assertions.assertEquals(checkoutMap, table.getCheckoutMap());
    }

    @Test
    void getTheCheckoutMapWithEmptyCheckouts() {
        BoardType boardType = BoardType.LONDON;
        CheckMode checkInMode = CheckMode.ANY;
        CheckMode checkoutMode = CheckMode.DOUBLE;
        int numThrows = -1;
        ThrowMode throwMode = ThrowMode.OPTIMAL;

        Map<Integer, Collection<Checkout>> checkoutMap = Map.of(1, Collections.emptyList());

        Table table = AscendingTable.of(boardType, checkInMode, checkoutMode, numThrows, throwMode, checkoutMap);

        Assertions.assertEquals(checkoutMap, table.getCheckoutMap());
    }

    @Test
    void getAnImmutableCheckoutMap() {
        BoardType boardType = BoardType.LONDON;
        CheckMode checkInMode = CheckMode.MASTER;
        CheckMode checkoutMode = CheckMode.MASTER;
        int numThrows = -1;
        ThrowMode throwMode = ThrowMode.OPTIMAL;

        int score = 9;

        Collection<Collection<Collection<String>>> namesPerCheckout = List.of(
                List.of(List.of("T3"))
        );

        Collection<Checkout> checkouts = GroupedCheckoutTestUtils.getCheckouts(namesPerCheckout);

        Map<Integer, Collection<Checkout>> checkoutMap = Map.of(
                score, checkouts
        );

        Table table = AscendingTable.of(boardType, checkInMode, checkoutMode, numThrows, throwMode, checkoutMap);

        Map<Integer, List<Checkout>> storedCheckoutMap = table.getCheckoutMap();

        Assertions.assertThrows(UnsupportedOperationException.class, storedCheckoutMap::clear);
    }

    @Test
    void getImmutableCheckouts() {
        BoardType boardType = BoardType.QUADRO;
        CheckMode checkInMode = CheckMode.ANY;
        CheckMode checkoutMode = CheckMode.ANY;
        int numThrows = -1;
        ThrowMode throwMode = ThrowMode.OPTIMAL;

        int score = 10;

        Collection<Collection<Collection<String>>> namesPerCheckout = List.of(
                List.of(List.of("10")),
                List.of(List.of("D5"))
        );

        Collection<Checkout> checkouts = GroupedCheckoutTestUtils.getCheckouts(namesPerCheckout);

        Map<Integer, Collection<Checkout>> checkoutMap = Map.of(
                score, checkouts
        );

        Table table = AscendingTable.of(boardType, checkInMode, checkoutMode, numThrows, throwMode, checkoutMap);

        Map<Integer, List<Checkout>> storedCheckoutMap = table.getCheckoutMap();
        Collection<Checkout> storedCheckouts = storedCheckoutMap.get(score);

        Assertions.assertThrows(UnsupportedOperationException.class, storedCheckouts::clear);
    }

    @Test
    void getEqualTables() {
        BoardType boardType = BoardType.YORKSHIRE;
        CheckMode checkInMode = CheckMode.DOUBLE;
        CheckMode checkoutMode = CheckMode.DOUBLE;
        int numThrows = -1;
        ThrowMode throwMode = ThrowMode.OPTIMAL;

        int score = 20;

        Collection<Collection<Collection<String>>> namesPerCheckout = List.of(
                List.of(List.of("20")),
                List.of(List.of("D10"))
        );

        Collection<Checkout> checkouts1 = GroupedCheckoutTestUtils.getCheckouts(namesPerCheckout);

        Map<Integer, Collection<Checkout>> checkoutMap1 = Map.of(
                score, checkouts1
        );

        Table table1 = AscendingTable.of(boardType, checkInMode, checkoutMode, numThrows, throwMode, checkoutMap1);

        Collection<Checkout> checkouts2 = GroupedCheckoutTestUtils.getCheckouts(namesPerCheckout);

        Map<Integer, Collection<Checkout>> checkoutMap2 = Map.of(
                score, checkouts2
        );

        Table table2 = AscendingTable.of(boardType, checkInMode, checkoutMode, numThrows, throwMode, checkoutMap2);

        Assertions.assertAll(
                () -> Assertions.assertEquals(table1, table2),
                () -> Assertions.assertEquals(table1.hashCode(), table2.hashCode())
        );
    }

    @Test
    void getUnequalTables() {
        BoardType boardType1 = BoardType.LONDON;
        CheckMode checkInMode1 = CheckMode.DOUBLE;
        CheckMode checkoutMode1 = CheckMode.DOUBLE;
        int numThrows = -1;
        ThrowMode throwMode = ThrowMode.OPTIMAL;

        int score1 = 50;

        Collection<Collection<Collection<String>>> namesPerCheckout1 = List.of(List.of(List.of("D25")));
        Collection<Checkout> checkouts1 = GroupedCheckoutTestUtils.getCheckouts(namesPerCheckout1);

        Map<Integer, Collection<Checkout>> checkoutMap1 = Map.of(
                score1, checkouts1
        );

        Table table1 = AscendingTable.of(boardType1, checkInMode1, checkoutMode1, numThrows, throwMode, checkoutMap1);

        BoardType boardType2 = BoardType.QUADRO;
        CheckMode checkInMode2 = CheckMode.ANY;
        CheckMode checkoutMode2 = CheckMode.ANY;

        int score2 = 80;

        Collection<Collection<Collection<String>>> namesPerCheckout2 = List.of(List.of(List.of("Q20")));
        Collection<Checkout> checkouts2 = GroupedCheckoutTestUtils.getCheckouts(namesPerCheckout2);

        Map<Integer, Collection<Checkout>> checkoutMap2 = Map.of(
                score2, checkouts2
        );

        Table table2 = AscendingTable.of(boardType2, checkInMode2, checkoutMode2, numThrows, throwMode, checkoutMap2);

        Assertions.assertNotEquals(table1, table2);
    }

    @Test
    void getUnequalTablesForDifferentBoardTypes() {
        CheckMode checkInMode = CheckMode.DOUBLE;
        CheckMode checkoutMode = CheckMode.DOUBLE;
        int numThrows = -1;
        ThrowMode throwMode = ThrowMode.OPTIMAL;

        int score = 8;

        Collection<Collection<Collection<String>>> namesPerCheckout = List.of(List.of(List.of("D4")));
        Collection<Checkout> checkouts = GroupedCheckoutTestUtils.getCheckouts(namesPerCheckout);

        Map<Integer, Collection<Checkout>> checkoutMap = Map.of(
                score, checkouts
        );

        BoardType boardType1 = BoardType.LONDON;
        Table table1 = AscendingTable.of(boardType1, checkInMode, checkoutMode, numThrows, throwMode, checkoutMap);

        BoardType boardType2 = BoardType.YORKSHIRE;
        Table table2 = AscendingTable.of(boardType2, checkInMode, checkoutMode, numThrows, throwMode, checkoutMap);

        Assertions.assertNotEquals(table1, table2);
    }

    @Test
    void getUnequalTablesForDifferentCheckInModes() {
        BoardType boardType = BoardType.YORKSHIRE;
        CheckMode checkoutMode = CheckMode.DOUBLE;
        int numThrows = -1;
        ThrowMode throwMode = ThrowMode.OPTIMAL;

        int score = 8;

        Collection<Collection<Collection<String>>> namesPerCheckout = List.of(List.of(List.of("D4")));
        Collection<Checkout> checkouts = GroupedCheckoutTestUtils.getCheckouts(namesPerCheckout);

        Map<Integer, Collection<Checkout>> checkoutMap = Map.of(
                score, checkouts
        );

        CheckMode checkInMode1 = CheckMode.DOUBLE;
        Table table1 = AscendingTable.of(boardType, checkInMode1, checkoutMode, numThrows, throwMode, checkoutMap);

        CheckMode checkInMode2 = CheckMode.ANY;
        Table table2 = AscendingTable.of(boardType, checkInMode2, checkoutMode, numThrows, throwMode, checkoutMap);

        Assertions.assertNotEquals(table1, table2);
    }

    @Test
    void getUnequalTablesForDifferentCheckoutModes() {
        BoardType boardType = BoardType.YORKSHIRE;
        CheckMode checkInMode = CheckMode.DOUBLE;
        int numThrows = -1;
        ThrowMode throwMode = ThrowMode.OPTIMAL;

        int score = 8;

        Collection<Collection<Collection<String>>> namesPerCheckout = List.of(List.of(List.of("D4")));
        Collection<Checkout> checkouts = GroupedCheckoutTestUtils.getCheckouts(namesPerCheckout);

        Map<Integer, Collection<Checkout>> checkoutMap = Map.of(
                score, checkouts
        );

        CheckMode checkoutMode1 = CheckMode.MASTER;
        Table table1 = AscendingTable.of(boardType, checkInMode, checkoutMode1, numThrows, throwMode, checkoutMap);

        CheckMode checkoutMode2 = CheckMode.DOUBLE;
        Table table2 = AscendingTable.of(boardType, checkInMode, checkoutMode2, numThrows, throwMode, checkoutMap);

        Assertions.assertNotEquals(table1, table2);
    }

    @Test
    void getUnequalTablesForDifferentCheckoutMaps() {
        BoardType boardType = BoardType.LONDON;
        CheckMode checkInMode = CheckMode.MASTER;
        CheckMode checkoutMode = CheckMode.MASTER;
        int numThrows = -1;
        ThrowMode throwMode = ThrowMode.OPTIMAL;

        int score1 = 15;

        Collection<Collection<Collection<String>>> namesPerCheckout1 = List.of(List.of(List.of("T5")));
        Collection<Checkout> checkouts1 = GroupedCheckoutTestUtils.getCheckouts(namesPerCheckout1);

        Map<Integer, Collection<Checkout>> checkoutMap1 = Map.of(
                score1, checkouts1
        );

        Table table1 = AscendingTable.of(boardType, checkInMode, checkoutMode, numThrows, throwMode, checkoutMap1);

        int score2 = 60;

        Collection<Collection<Collection<String>>> namesPerCheckout2 = List.of(List.of(List.of("T20")));
        Collection<Checkout> checkouts2 = GroupedCheckoutTestUtils.getCheckouts(namesPerCheckout2);

        Map<Integer, Collection<Checkout>> checkoutMap2 = Map.of(
                score2, checkouts2
        );

        Table table2 = AscendingTable.of(boardType, checkInMode, checkoutMode, numThrows, throwMode, checkoutMap2);

        Assertions.assertNotEquals(table1, table2);
    }

    @Test
    void convertToAString() {
        BoardType boardType = BoardType.LONDON;
        CheckMode checkInMode = CheckMode.ANY;
        CheckMode checkoutMode = CheckMode.DOUBLE;
        int numThrows = -1;
        ThrowMode throwMode = ThrowMode.OPTIMAL;

        int score = 3;

        Collection<Collection<Collection<String>>> namesPerCheckout = List.of(
                List.of(List.of("3")),
                List.of(List.of("2", "D1"), List.of("1")),
                List.of(List.of("1"), List.of("1"), List.of("1"))
        );

        Collection<Checkout> checkouts = GroupedCheckoutTestUtils.getCheckouts(namesPerCheckout);

        Map<Integer, Collection<Checkout>> checkoutMap = Map.of(
                score, checkouts
        );

        Table table = AscendingTable.of(boardType, checkInMode, checkoutMode, numThrows, throwMode, checkoutMap);

        String str = table.toString();

        Assertions.assertAll(
                () -> Assertions.assertTrue(str.contains(table.getClass().getSimpleName())),
                () -> Assertions.assertTrue(str.contains("boardType")),
                () -> Assertions.assertTrue(str.contains("checkInMode")),
                () -> Assertions.assertTrue(str.contains("checkoutMode")),
                () -> Assertions.assertTrue(str.contains("checkoutMap"))
        );
    }
}
