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
                boardType, checkInType, checkoutType, finderType
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
                boardType, checkInType, checkoutType, finderType
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
                boardType, checkInType, checkoutType, finderType
        );

        int minScore = 0;
        int maxScore = 0;

        CheckoutTable checkoutTable = checkoutTableGenerator.generate(minScore, maxScore);

        Assertions.assertEquals(checkoutType, checkoutTable.getCheckoutType());
    }

    @ParameterizedTest
    @MethodSource("withoutCheckouts")
    void doNotGetCheckoutsWithTooLowScores(
            BoardType boardType, CheckType checkInType, CheckType checkoutType, FinderType finderType
    ) {
        CheckoutTableGenerator checkoutTableGenerator = MapBasedCheckoutTableGenerator.of(
                boardType, checkInType, checkoutType, finderType
        );

        int minScore = -2;
        int maxScore = 1;

        CheckoutTable checkoutTable = checkoutTableGenerator.generate(minScore, maxScore);

        Map<Integer, List<Checkout>> checkoutMap = checkoutTable.getCheckoutMap();

        Assertions.assertAll(
                () -> Assertions.assertEquals(4, checkoutMap.size()),
                () -> Assertions.assertTrue(checkoutMap.get(-2).isEmpty()),
                () -> Assertions.assertTrue(checkoutMap.get(-1).isEmpty()),
                () -> Assertions.assertTrue(checkoutMap.get(0).isEmpty()),
                () -> Assertions.assertTrue(checkoutMap.get(1).isEmpty())
        );
    }

    private static Stream<Arguments> withoutCheckouts() {
        return Stream.of(
                Arguments.of(BoardType.QUADRO, CheckType.ANY, CheckType.DOUBLE, FinderType.CARTESIAN),
                Arguments.of(BoardType.QUADRO, CheckType.DOUBLE, CheckType.MASTER, FinderType.DESCENDING),
                Arguments.of(BoardType.LONDON, CheckType.ANY, CheckType.MASTER, FinderType.CARTESIAN),
                Arguments.of(BoardType.LONDON, CheckType.MASTER, CheckType.MASTER, FinderType.DESCENDING),
                Arguments.of(BoardType.YORKSHIRE, CheckType.MASTER, CheckType.ANY, FinderType.CARTESIAN),
                Arguments.of(BoardType.YORKSHIRE, CheckType.DOUBLE, CheckType.MASTER, FinderType.DESCENDING)
        );
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
                boardType, checkInType, checkoutType, finderType
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
                Arguments.of(1, List.of(List.of(List.of("1")))),
                Arguments.of(
                        2,
                        List.of(
                                List.of(List.of("2")),
                                List.of(List.of("D1"))
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
                        12,
                        List.of(
                                List.of(List.of("12")),
                                List.of(List.of("D6")),
                                List.of(List.of("T4")),
                                List.of(List.of("Q3"))
                        )
                ),
                Arguments.of(76, List.of(List.of(List.of("Q19")))),
                Arguments.of(80, List.of(List.of(List.of("Q20")))),
                Arguments.of(
                        156,
                        List.of(
                                List.of(List.of("Q19"), List.of("Q20")),
                                List.of(List.of("Q20"), List.of("Q19"))
                        )
                ),
                Arguments.of(160, List.of(List.of(List.of("Q20"), List.of("Q20")))),
                Arguments.of(
                        236,
                        List.of(
                                List.of(List.of("Q19"), List.of("Q20"), List.of("Q20")),
                                List.of(List.of("Q20"), List.of("Q19"), List.of("Q20")),
                                List.of(List.of("Q20"), List.of("Q20"), List.of("Q19"))
                        )
                ),
                Arguments.of(240, List.of(List.of(List.of("Q20"), List.of("Q20"), List.of("Q20"))))
        );
    }

    @ParameterizedTest
    @MethodSource("withCartesianLondonBoardMasterInMasterOutCheckouts")
    void getTheCartesianLondonBoardMasterInMasterOutCheckouts(
            int score,
            Collection<? extends Collection<? extends Collection<String>>> namesPerCheckout
    ) {
        BoardType boardType = BoardType.LONDON;
        CheckType checkInType = CheckType.MASTER;
        CheckType checkoutType = CheckType.MASTER;
        FinderType finderType = FinderType.CARTESIAN;

        CheckoutTableGenerator checkoutTableGenerator = MapBasedCheckoutTableGenerator.of(
                boardType, checkInType, checkoutType, finderType
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

    private static Stream<Arguments> withCartesianLondonBoardMasterInMasterOutCheckouts() {
        return Stream.of(
                Arguments.of(2, List.of(List.of(List.of("D1")))),
                Arguments.of(
                        6,
                        List.of(
                                List.of(List.of("D3")),
                                List.of(List.of("T2"))
                        )
                ),
                Arguments.of(
                        18,
                        List.of(
                                List.of(List.of("D9")),
                                List.of(List.of("T6"))
                        )
                ),
                Arguments.of(48, List.of(List.of(List.of("T16")))),
                Arguments.of(57, List.of(List.of(List.of("T19")))),
                Arguments.of(60, List.of(List.of(List.of("T20")))),
                Arguments.of(
                        117,
                        List.of(
                                List.of(List.of("T19"), List.of("T20")),
                                List.of(List.of("T20"), List.of("T19"))
                        )
                ),
                Arguments.of(120, List.of(List.of(List.of("T20"), List.of("T20")))),
                Arguments.of(
                        177,
                        List.of(
                                List.of(List.of("T19"), List.of("T20"), List.of("T20")),
                                List.of(List.of("T20"), List.of("T19"), List.of("T20")),
                                List.of(List.of("T20"), List.of("T20"), List.of("T19"))
                        )
                ),
                Arguments.of(180, List.of(List.of(List.of("T20"), List.of("T20"), List.of("T20"))))
        );
    }

    @ParameterizedTest
    @MethodSource("withCartesianYorkshireBoardAnyInMasterOutCheckouts")
    void getTheCartesianYorkshireBoardAnyInMasterOutCheckouts(
            int score,
            Collection<? extends Collection<? extends Collection<String>>> namesPerCheckout
    ) {
        BoardType boardType = BoardType.YORKSHIRE;
        CheckType checkInType = CheckType.MASTER;
        CheckType checkoutType = CheckType.MASTER;
        FinderType finderType = FinderType.CARTESIAN;

        CheckoutTableGenerator checkoutTableGenerator = MapBasedCheckoutTableGenerator.of(
                boardType, checkInType, checkoutType, finderType
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

    private static Stream<Arguments> withCartesianYorkshireBoardAnyInMasterOutCheckouts() {
        return Stream.of(
                Arguments.of(2, List.of(List.of(List.of("D1")))),
                Arguments.of(16, List.of(List.of(List.of("D8")))),
                Arguments.of(30, List.of(List.of(List.of("D15")))),
                Arguments.of(38, List.of(List.of(List.of("D19")))),
                Arguments.of(40, List.of(List.of(List.of("D20")))),
                Arguments.of(50, List.of(List.of(List.of("D25")))),
                Arguments.of(
                        90,
                        List.of(
                                List.of(List.of("D20"), List.of("D25")),
                                List.of(List.of("D25"), List.of("D20"))
                        )
                ),
                Arguments.of(100, List.of(List.of(List.of("D25"), List.of("D25")))),
                Arguments.of(
                        140,
                        List.of(
                                List.of(List.of("D20"), List.of("D25"), List.of("D25")),
                                List.of(List.of("D25"), List.of("D20"), List.of("D25")),
                                List.of(List.of("D25"), List.of("D25"), List.of("D20"))
                        )
                ),
                Arguments.of(150, List.of(List.of(List.of("D25"), List.of("D25"), List.of("D25"))))
        );
    }
}
