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
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.*;

class MapBasedCheckoutTableTests {
    @Test
    void doNotStoreCheckoutsWithAnotherScore() {
        BoardType boardType = BoardType.LONDON;
        CheckType checkInType = CheckType.ANY;
        CheckType checkoutType = CheckType.DOUBLE;

        int score = 6;

        Collection<Collection<Collection<String>>> namesPerCheckout = List.of(
                List.of(List.of("D3")), List.of(List.of("D4"))
        );

        Collection<Checkout> checkouts = GroupedCheckoutTestUtils.getCheckouts(namesPerCheckout);

        Map<Integer, Collection<Checkout>> checkoutMap = Map.of(score, checkouts);

        Assertions.assertThrows(
                IllegalArgumentException.class,
                () -> MapBasedCheckoutTable.of(boardType, checkInType, checkoutType, checkoutMap)
        );
    }

    @Test
    void storeAnIndependentCheckoutMap() {
        BoardType boardType = BoardType.QUADRO;
        CheckType checkInType = CheckType.ANY;
        CheckType checkoutType = CheckType.ANY;

        int score = 10;

        Collection<Collection<Collection<String>>> namesPerCheckout = List.of(
                List.of(List.of("5"), List.of("3"), List.of("2", "D1"))
        );

        Collection<Checkout> checkouts = GroupedCheckoutTestUtils.getCheckouts(namesPerCheckout);

        Map<Integer, Collection<Checkout>> checkoutMap = new HashMap<>();
        checkoutMap.put(score, checkouts);

        CheckoutTable checkoutTable = MapBasedCheckoutTable.of(boardType, checkInType, checkoutType, checkoutMap);

        checkoutMap.clear();

        Map<Integer, List<Checkout>> storedCheckoutMap = checkoutTable.getCheckoutMap();
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
        CheckType checkInType = CheckType.ANY;
        CheckType checkoutType = CheckType.MASTER;

        int score = 8;

        Collection<Collection<Collection<String>>> namesPerCheckout = List.of(
                List.of(List.of("2"), List.of("T2", "D3"))
        );

        Collection<Checkout> checkouts = new ArrayList<>(GroupedCheckoutTestUtils.getCheckouts(namesPerCheckout));

        Map<Integer, Collection<Checkout>> checkoutMap = new HashMap<>();
        checkoutMap.put(score, checkouts);

        CheckoutTable checkoutTable = MapBasedCheckoutTable.of(boardType, checkInType, checkoutType, checkoutMap);

        checkoutMap.get(score).clear();

        Map<Integer, List<Checkout>> storedCheckoutMap = checkoutTable.getCheckoutMap();
        Collection<Checkout> storedCheckouts = storedCheckoutMap.get(score);
        Collection<List<List<String>>> storedNames = CheckoutTestUtils.getAllNames(storedCheckouts);

        Assertions.assertAll(
                () -> Assertions.assertEquals(1, storedCheckoutMap.size()),
                () -> Assertions.assertEquals(namesPerCheckout, storedNames)
        );
    }

    @Test
    void getTheBoardType() {
        BoardType boardType = BoardType.LONDON;
        CheckType checkInType = CheckType.ANY;
        CheckType checkoutType = CheckType.MASTER;
        Map<Integer, Collection<Checkout>> checkoutMap = Collections.emptyMap();

        CheckoutTable checkoutTable = MapBasedCheckoutTable.of(boardType, checkInType, checkoutType, checkoutMap);

        Assertions.assertEquals(boardType, checkoutTable.getBoardType());
    }

    @Test
    void getTheCheckInType() {
        BoardType boardType = BoardType.LONDON;
        CheckType checkInType = CheckType.MASTER;
        CheckType checkoutType = CheckType.DOUBLE;
        Map<Integer, Collection<Checkout>> checkoutMap = Collections.emptyMap();

        CheckoutTable checkoutTable = MapBasedCheckoutTable.of(boardType, checkInType, checkoutType, checkoutMap);

        Assertions.assertEquals(checkInType, checkoutTable.getCheckInType());
    }

    @Test
    void getTheCheckoutType() {
        BoardType boardType = BoardType.LONDON;
        CheckType checkInType = CheckType.ANY;
        CheckType checkoutType = CheckType.DOUBLE;
        Map<Integer, Collection<Checkout>> checkoutMap = Collections.emptyMap();

        CheckoutTable checkoutTable = MapBasedCheckoutTable.of(boardType, checkInType, checkoutType, checkoutMap);

        Assertions.assertEquals(checkoutType, checkoutTable.getCheckoutType());
    }

    @Test
    void getTheCheckoutMap() {
        BoardType boardType = BoardType.LONDON;
        CheckType checkInType = CheckType.ANY;
        CheckType checkoutType = CheckType.DOUBLE;

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

        CheckoutTable checkoutTable = MapBasedCheckoutTable.of(boardType, checkInType, checkoutType, checkoutMap);

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
    void getTheCheckoutMapWithAnEmptyMap() {
        BoardType boardType = BoardType.QUADRO;
        CheckType checkInType = CheckType.DOUBLE;
        CheckType checkoutType = CheckType.DOUBLE;
        Map<Integer, Collection<Checkout>> checkoutMap = Collections.emptyMap();

        CheckoutTable checkoutTable = MapBasedCheckoutTable.of(boardType, checkInType, checkoutType, checkoutMap);

        Assertions.assertEquals(checkoutMap, checkoutTable.getCheckoutMap());
    }

    @Test
    void getTheCheckoutMapWithEmptyCheckouts() {
        BoardType boardType = BoardType.LONDON;
        CheckType checkInType = CheckType.ANY;
        CheckType checkoutType = CheckType.DOUBLE;

        Map<Integer, Collection<Checkout>> checkoutMap = Map.of(1, Collections.emptyList());

        CheckoutTable checkoutTable = MapBasedCheckoutTable.of(boardType, checkInType, checkoutType, checkoutMap);

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

        Collection<Checkout> checkouts = GroupedCheckoutTestUtils.getCheckouts(names);

        Map<Integer, Collection<Checkout>> checkoutMap = new HashMap<>();
        checkoutMap.put(score, checkouts);

        CheckoutTable checkoutTable = MapBasedCheckoutTable.of(boardType, checkInType, checkoutType, checkoutMap);

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

        Collection<Checkout> checkouts = GroupedCheckoutTestUtils.getCheckouts(names);

        Map<Integer, Collection<Checkout>> checkoutMap = new HashMap<>();
        checkoutMap.put(score, checkouts);

        CheckoutTable checkoutTable = MapBasedCheckoutTable.of(boardType, checkInType, checkoutType, checkoutMap);

        Map<Integer, List<Checkout>> storedCheckoutMap = checkoutTable.getCheckoutMap();
        Collection<Checkout> storedCheckouts = storedCheckoutMap.get(score);

        Assertions.assertThrows(UnsupportedOperationException.class, storedCheckouts::clear);
    }
}
