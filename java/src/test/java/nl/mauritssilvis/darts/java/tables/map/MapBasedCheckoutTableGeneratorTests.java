/*
 * Copyright © 2023 Maurits H. Silvis
 * SPDX-License-Identifier: GPL-3.0-or-later
 */

package nl.mauritssilvis.darts.java.tables.map;

import nl.mauritssilvis.darts.java.checkouts.Checkout;
import nl.mauritssilvis.darts.java.settings.BoardType;
import nl.mauritssilvis.darts.java.settings.CheckType;
import nl.mauritssilvis.darts.java.settings.FinderType;
import nl.mauritssilvis.darts.java.tables.CheckoutTable;
import nl.mauritssilvis.darts.java.tables.CheckoutTableGenerator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

class MapBasedCheckoutTableGeneratorTests {
    @Test
    void getTheBoardType() {
        BoardType boardType = BoardType.LONDON;
        CheckType checkInType = CheckType.DOUBLE;
        CheckType checkoutType = CheckType.ANY;
        FinderType finderType = FinderType.CARTESIAN;

        CheckoutTableGenerator checkoutTableGenerator = MapBasedCheckoutTableGenerator.of(
                boardType,
                checkInType,
                checkoutType,
                finderType
        );

        int minScore = 0;
        int maxScore = 0;

        CheckoutTable checkoutTable = checkoutTableGenerator.generate(minScore, maxScore);

        Assertions.assertEquals(boardType, checkoutTable.getBoardType());
    }

    @Test
    void getTheCheckInType() {
        BoardType boardType = BoardType.QUADRO;
        CheckType checkInType = CheckType.MASTER;
        CheckType checkoutType = CheckType.DOUBLE;
        FinderType finderType = FinderType.DESCENDING;

        CheckoutTableGenerator checkoutTableGenerator = MapBasedCheckoutTableGenerator.of(
                boardType,
                checkInType,
                checkoutType,
                finderType
        );

        int minScore = 0;
        int maxScore = 0;

        CheckoutTable checkoutTable = checkoutTableGenerator.generate(minScore, maxScore);

        Assertions.assertEquals(checkInType, checkoutTable.getCheckInType());
    }

    @Test
    void getTheCheckoutType() {
        BoardType boardType = BoardType.LONDON;
        CheckType checkInType = CheckType.ANY;
        CheckType checkoutType = CheckType.ANY;
        FinderType finderType = FinderType.CARTESIAN;

        CheckoutTableGenerator checkoutTableGenerator = MapBasedCheckoutTableGenerator.of(
                boardType,
                checkInType,
                checkoutType,
                finderType
        );

        int minScore = 0;
        int maxScore = 0;

        CheckoutTable checkoutTable = checkoutTableGenerator.generate(minScore, maxScore);

        Assertions.assertEquals(checkoutType, checkoutTable.getCheckoutType());
    }

    @ParameterizedTest
    @MethodSource("withLondonBoardCheckoutMultiplicity")
    void countTheLondonBoardCheckoutsWithACartesianFinder(int score, int multiplicity) {
        BoardType boardType = BoardType.LONDON;
        CheckType checkInType = CheckType.ANY;
        CheckType checkoutType = CheckType.ANY;
        FinderType finderType = FinderType.CARTESIAN;

        CheckoutTableGenerator checkoutTableGenerator = MapBasedCheckoutTableGenerator.of(
                boardType,
                checkInType,
                checkoutType,
                finderType
        );

        CheckoutTable checkoutTable = checkoutTableGenerator.generate(score, score);
        Map<Integer, List<Checkout>> checkoutMap = checkoutTable.getCheckoutMap();
        List<Checkout> checkouts = checkoutMap.get(score);

        Assertions.assertAll(
                () -> Assertions.assertEquals(1, checkoutMap.size()),
                () -> Assertions.assertEquals(multiplicity, getMultiplicity(checkouts))
        );
    }

    private static Stream<Arguments> withLondonBoardCheckoutMultiplicity() {
        return Stream.of(
                Arguments.of(1, 1),
                Arguments.of(2, 2),
                Arguments.of(3, 3),
                Arguments.of(4, 2),
                Arguments.of(5, 1),
                Arguments.of(6, 3),
                Arguments.of(7, 1),
                Arguments.of(8, 2),
                Arguments.of(9, 2),
                Arguments.of(10, 2),
                Arguments.of(11, 1),
                Arguments.of(12, 3),
                Arguments.of(13, 1),
                Arguments.of(14, 2),
                Arguments.of(15, 2),
                Arguments.of(16, 2),
                Arguments.of(17, 1),
                Arguments.of(18, 3),
                Arguments.of(19, 1),
                Arguments.of(20, 2),
                Arguments.of(25, 1)
        );
    }

    private static long getMultiplicity(Collection<? extends Checkout> checkouts) {
        return checkouts.stream()
                .mapToLong(Checkout::getMultiplicity)
                .sum();
    }
}
