/*
 * Copyright © 2023 Maurits H. Silvis
 * SPDX-License-Identifier: GPL-3.0-or-later
 */

package nl.mauritssilvis.darts.java.tables.map;

import nl.mauritssilvis.darts.java.checkouts.Checkout;
import nl.mauritssilvis.darts.java.settings.BoardType;
import nl.mauritssilvis.darts.java.settings.CheckType;
import nl.mauritssilvis.darts.java.tables.CheckoutTable;
import nl.mauritssilvis.darts.java.tables.utils.GroupedCheckoutTestUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.*;

class MappedCheckoutTableTests {
    @Test
    void doNotStoreCheckoutsWithAnotherScore() {
        BoardType boardType = BoardType.LONDON;
        CheckType checkInType = CheckType.ANY;
        CheckType checkoutType = CheckType.DOUBLE;

        int score = 6;
        Collection<Collection<Collection<String>>> names = List.of(List.of(List.of("D3")), List.of(List.of("D4")));
        List<Checkout> checkouts = GroupedCheckoutTestUtils.getCheckouts(names);
        Map<Integer, List<Checkout>> checkoutMap = Map.of(score, checkouts);

        Assertions.assertThrows(
                IllegalArgumentException.class,
                () -> MappedCheckoutTable.of(boardType, checkInType, checkoutType, checkoutMap)
        );
    }

    @Test
    void storeAnIndependentCheckoutMap() {
        BoardType boardType = BoardType.QUADRO;
        CheckType checkInType = CheckType.ANY;
        CheckType checkoutType = CheckType.ANY;

        int score = 10;

        Collection<Collection<Collection<String>>> names = List.of(
                List.of(List.of("5"), List.of("3"), List.of("2", "D1"))
        );

        List<Checkout> checkouts = GroupedCheckoutTestUtils.getCheckouts(names);

        Map<Integer, List<Checkout>> checkoutMap = new HashMap<>();
        checkoutMap.put(score, checkouts);

        CheckoutTable checkoutTable = MappedCheckoutTable.of(boardType, checkInType, checkoutType, checkoutMap);

        checkoutMap.clear();

        Assertions.assertNotEquals(checkoutMap, checkoutTable.getCheckoutMap());
    }

    @Test
    void storeIndependentCheckouts() {
        BoardType boardType = BoardType.LONDON;
        CheckType checkInType = CheckType.ANY;
        CheckType checkoutType = CheckType.MASTER;

        int score = 8;

        Collection<Collection<Collection<String>>> names = List.of(
                List.of(List.of("2"), List.of("T2", "D3"))
        );

        List<Checkout> checkouts = GroupedCheckoutTestUtils.getCheckouts(names);

        Map<Integer, List<Checkout>> checkoutMap = new HashMap<>();
        checkoutMap.put(score, checkouts);

        CheckoutTable checkoutTable = MappedCheckoutTable.of(boardType, checkInType, checkoutType, checkoutMap);

        checkoutMap.get(score).clear();

        Assertions.assertNotEquals(checkoutMap, checkoutTable.getCheckoutMap());
    }

    @Test
    void getTheBoardType() {
        BoardType boardType = BoardType.LONDON;
        CheckType checkInType = CheckType.ANY;
        CheckType checkoutType = CheckType.MASTER;
        Map<Integer, List<Checkout>> checkoutMap = new HashMap<>();

        CheckoutTable checkoutTable = MappedCheckoutTable.of(boardType, checkInType, checkoutType, checkoutMap);

        Assertions.assertEquals(boardType, checkoutTable.getBoardType());
    }

    @Test
    void getTheCheckInType() {
        BoardType boardType = BoardType.LONDON;
        CheckType checkInType = CheckType.MASTER;
        CheckType checkoutType = CheckType.DOUBLE;
        Map<Integer, List<Checkout>> checkoutMap = new HashMap<>();

        CheckoutTable checkoutTable = MappedCheckoutTable.of(boardType, checkInType, checkoutType, checkoutMap);

        Assertions.assertEquals(checkInType, checkoutTable.getCheckInType());
    }

    @Test
    void getTheCheckoutType() {
        BoardType boardType = BoardType.LONDON;
        CheckType checkInType = CheckType.ANY;
        CheckType checkoutType = CheckType.DOUBLE;
        Map<Integer, List<Checkout>> checkoutMap = new HashMap<>();

        CheckoutTable checkoutTable = MappedCheckoutTable.of(boardType, checkInType, checkoutType, checkoutMap);

        Assertions.assertEquals(checkoutType, checkoutTable.getCheckoutType());
    }

    @Test
    void getTheCheckoutMap() {
        BoardType boardType = BoardType.LONDON;
        CheckType checkInType = CheckType.ANY;
        CheckType checkoutType = CheckType.DOUBLE;

        Collection<Collection<Collection<String>>> names4 = List.of(
                List.of(List.of("1"), List.of("1"), List.of("D1")),
                List.of(List.of("D1", "2"), List.of("D1")),
                List.of(List.of("D2"))
        );

        Collection<Collection<Collection<String>>> names6 = List.of(
                List.of(List.of("2", "D1"), List.of("D2")),
                List.of(List.of("D3"))
        );

        List<Checkout> checkouts4 = GroupedCheckoutTestUtils.getCheckouts(names4);
        List<Checkout> checkouts6 = GroupedCheckoutTestUtils.getCheckouts(names6);

        Map<Integer, List<Checkout>> checkoutMap = Map.of(
                4, checkouts4,
                6, checkouts6
        );

        CheckoutTable checkoutTable = MappedCheckoutTable.of(boardType, checkInType, checkoutType, checkoutMap);

        Assertions.assertEquals(checkoutMap, checkoutTable.getCheckoutMap());
    }

    @Test
    void getTheCheckoutMapWithAnEmptyMap() {
        BoardType boardType = BoardType.QUADRO;
        CheckType checkInType = CheckType.DOUBLE;
        CheckType checkoutType = CheckType.DOUBLE;
        Map<Integer, List<Checkout>> checkoutMap = Collections.emptyMap();

        CheckoutTable checkoutTable = MappedCheckoutTable.of(boardType, checkInType, checkoutType, checkoutMap);

        Assertions.assertEquals(checkoutMap, checkoutTable.getCheckoutMap());
    }

    @Test
    void getTheCheckoutMapWithEmptyCheckouts() {
        BoardType boardType = BoardType.LONDON;
        CheckType checkInType = CheckType.ANY;
        CheckType checkoutType = CheckType.DOUBLE;

        Map<Integer, List<Checkout>> checkoutMap = Map.of(1, Collections.emptyList());

        CheckoutTable checkoutTable = MappedCheckoutTable.of(boardType, checkInType, checkoutType, checkoutMap);

        Assertions.assertEquals(checkoutMap, checkoutTable.getCheckoutMap());
    }

    @Test
    void getAnImmutableCheckoutMap() {
        BoardType boardType = BoardType.LONDON;
        CheckType checkInType = CheckType.MASTER;
        CheckType checkoutType = CheckType.MASTER;

        int score = 9;

        Collection<Collection<Collection<String>>> names = List.of(
                List.of(List.of("T3"))
        );

        List<Checkout> checkouts = GroupedCheckoutTestUtils.getCheckouts(names);

        Map<Integer, List<Checkout>> checkoutMap = new HashMap<>();
        checkoutMap.put(score, checkouts);

        CheckoutTable checkoutTable = MappedCheckoutTable.of(boardType, checkInType, checkoutType, checkoutMap);
        Map<Integer, List<Checkout>> storedCheckoutMap = checkoutTable.getCheckoutMap();

        Assertions.assertThrows(UnsupportedOperationException.class, storedCheckoutMap::clear);
    }

    @Test
    void getImmutableCheckouts() {
        BoardType boardType = BoardType.QUADRO;
        CheckType checkInType = CheckType.ANY;
        CheckType checkoutType = CheckType.ANY;

        int score = 10;

        Collection<Collection<Collection<String>>> names = List.of(
                List.of(List.of("10")),
                List.of(List.of("D5"))
        );

        List<Checkout> checkouts = GroupedCheckoutTestUtils.getCheckouts(names);

        Map<Integer, List<Checkout>> checkoutMap = new HashMap<>();
        checkoutMap.put(score, checkouts);

        CheckoutTable checkoutTable = MappedCheckoutTable.of(boardType, checkInType, checkoutType, checkoutMap);

        Map<Integer, List<Checkout>> storedCheckoutMap = checkoutTable.getCheckoutMap();
        List<Checkout> storedCheckouts = storedCheckoutMap.get(score);

        Assertions.assertThrows(UnsupportedOperationException.class, storedCheckouts::clear);
    }
}
