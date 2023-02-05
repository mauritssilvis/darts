/*
 * Copyright © 2023 Maurits H. Silvis
 * SPDX-License-Identifier: GPL-3.0-or-later
 */

package nl.mauritssilvis.darts.java.tables.map;

import nl.mauritssilvis.darts.java.checkouts.Checkout;
import nl.mauritssilvis.darts.java.checkouts.CheckoutTestUtils;
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
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

class MapBasedCheckoutTableGeneratorTests {
    @Test
    void getTheBoardType() {
        BoardType boardType = BoardType.YORKSHIRE;
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
    @MethodSource("withCartesianQuadroBoardAnyInAnyOutCheckouts")
    void getTheCartesianQuadroBoardAnyInAnyOutCheckouts(
            int score,
            Collection<? extends Collection<? extends Collection<String>>> namesPerCheckout
    ) {
        BoardType boardType = BoardType.QUADRO;
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

        Map<Integer, List<Checkout>> storedCheckoutMap = checkoutTable.getCheckoutMap();
        List<Checkout> storedCheckouts = storedCheckoutMap.get(score);
        Collection<List<List<String>>> storedNames = CheckoutTestUtils.getAllNames(storedCheckouts);

        int totalMultiplicity = namesPerCheckout.size();

        Assertions.assertAll(
                () -> Assertions.assertEquals(1, storedCheckoutMap.size()),
                () -> Assertions.assertEquals(score, CheckoutTestUtils.getAvgScore(storedCheckouts)),
                () -> Assertions.assertEquals(namesPerCheckout, storedNames),
                () -> Assertions.assertEquals(
                        totalMultiplicity,
                        CheckoutTestUtils.getTotalMultiplicity(storedCheckouts)
                )
        );
    }

    private static Stream<Arguments> withCartesianQuadroBoardAnyInAnyOutCheckouts() {
        return Stream.of(
                Arguments.of(
                        0,
                        Collections.emptyList()
                ),
                Arguments.of(
                        1,
                        List.of(
                                List.of(List.of("1"))
                        )
                ),
                Arguments.of(
                        2,
                        List.of(
                                List.of(List.of("2")),
                                List.of(List.of("D1"))
                        )
                ),
                Arguments.of(
                        3,
                        List.of(
                                List.of(List.of("3")),
                                List.of(List.of("T1"))
                        )
                ),
                Arguments.of(
                        4,
                        List.of(
                                List.of(List.of("4")),
                                List.of(List.of("D2")),
                                List.of(List.of("Q1"))
                        )
                ),
                Arguments.of(
                        5,
                        List.of(
                                List.of(List.of("5"))
                        )
                ),
                Arguments.of(
                        6,
                        List.of(
                                List.of(List.of("6")),
                                List.of(List.of("D3")),
                                List.of(List.of("T2"))
                        )
                ),
                Arguments.of(
                        7,
                        List.of(
                                List.of(List.of("7"))
                        )
                ),
                Arguments.of(
                        8,
                        List.of(
                                List.of(List.of("8")),
                                List.of(List.of("D4")),
                                List.of(List.of("Q2"))
                        )
                ),
                Arguments.of(
                        9,
                        List.of(
                                List.of(List.of("9")),
                                List.of(List.of("T3"))
                        )
                ),
                Arguments.of(
                        10,
                        List.of(
                                List.of(List.of("10")),
                                List.of(List.of("D5"))
                        )
                ),
                Arguments.of(
                        11,
                        List.of(
                                List.of(List.of("11"))
                        )
                ),
                Arguments.of(
                        12,
                        List.of(
                                List.of(List.of("12")),
                                List.of(List.of("D6")),
                                List.of(List.of("T4")),
                                List.of(List.of("Q3"))
                        )
                ),
                Arguments.of(
                        13,
                        List.of(
                                List.of(List.of("13"))
                        )
                ),
                Arguments.of(
                        14,
                        List.of(
                                List.of(List.of("14")),
                                List.of(List.of("D7"))
                        )
                ),
                Arguments.of(
                        15,
                        List.of(
                                List.of(List.of("15")),
                                List.of(List.of("T5"))
                        )
                ),
                Arguments.of(
                        16,
                        List.of(
                                List.of(List.of("16")),
                                List.of(List.of("D8")),
                                List.of(List.of("Q4"))
                        )
                ),
                Arguments.of(
                        17,
                        List.of(
                                List.of(List.of("17"))
                        )
                ),
                Arguments.of(
                        18,
                        List.of(
                                List.of(List.of("18")),
                                List.of(List.of("D9")),
                                List.of(List.of("T6"))
                        )
                ),
                Arguments.of(
                        19,
                        List.of(
                                List.of(List.of("19"))
                        )
                ),
                Arguments.of(
                        20,
                        List.of(
                                List.of(List.of("20")),
                                List.of(List.of("D10")),
                                List.of(List.of("Q5"))
                        )
                ),
                Arguments.of(
                        25,
                        List.of(
                                List.of(List.of("25"))
                        )
                ),
                Arguments.of(
                        40,
                        List.of(
                                List.of(List.of("D20")),
                                List.of(List.of("Q10"))
                        )
                ),
                Arguments.of(
                        50,
                        List.of(
                                List.of(List.of("D25"))
                        )
                ),
                Arguments.of(
                        60,
                        List.of(
                                List.of(List.of("T20")),
                                List.of(List.of("Q15"))
                        )
                ),
                Arguments.of(
                        80,
                        List.of(
                                List.of(List.of("Q20"))
                        )
                )
        );
    }
}
