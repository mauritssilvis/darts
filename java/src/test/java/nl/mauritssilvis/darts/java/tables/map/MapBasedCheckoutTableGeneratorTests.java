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

    @ParameterizedTest
    @MethodSource("withDescendingLondonBoardAnyInDoubleOutCheckouts")
    void getTheDescendingLondonBoardAnyInDoubleOutCheckouts(
            int score,
            Collection<? extends Collection<? extends Collection<String>>> namesPerCheckout,
            int totalMultiplicity
    ) {
        BoardType boardType = BoardType.LONDON;
        CheckType checkInType = CheckType.ANY;
        CheckType checkoutType = CheckType.DOUBLE;
        FinderType finderType = FinderType.DESCENDING;

        CheckoutTableGenerator checkoutTableGenerator = MapBasedCheckoutTableGenerator.of(
                boardType, checkInType, checkoutType, finderType
        );

        CheckoutTable checkoutTable = checkoutTableGenerator.generate(score, score);

        Map<Integer, List<Checkout>> storedCheckoutMap = checkoutTable.getCheckoutMap();
        List<Checkout> storedCheckouts = storedCheckoutMap.get(score);
        Collection<List<List<String>>> storedNames = CheckoutTestUtils.getAllNames(storedCheckouts);

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

    private static Stream<Arguments> withDescendingLondonBoardAnyInDoubleOutCheckouts() {
        return Stream.of(
                Arguments.of(2, List.of(List.of(List.of("D1"))), 1),
                Arguments.of(12, List.of(List.of(List.of("D6"))), 1),
                Arguments.of(22, List.of(List.of(List.of("D11"))), 1),
                Arguments.of(34, List.of(List.of(List.of("D17"))), 1),
                Arguments.of(40, List.of(List.of(List.of("D20"))), 1),
                Arguments.of(50, List.of(List.of(List.of("D25"))), 1),
                Arguments.of(
                        100,
                        List.of(
                                List.of(List.of("T20"), List.of("D20")),
                                List.of(List.of("D25"), List.of("D25"))
                        ),
                        2
                ),
                Arguments.of(101, List.of(List.of(List.of("T17"), List.of("D25"))), 1),
                Arguments.of(104, List.of(List.of(List.of("T18"), List.of("D25"))), 1),
                Arguments.of(107, List.of(List.of(List.of("T19"), List.of("D25"))), 1),
                Arguments.of(110, List.of(List.of(List.of("T20"), List.of("D25"))), 1),
                Arguments.of(
                        160,
                        List.of(
                                List.of(List.of("T20"), List.of("T20"), List.of("D20")),
                                List.of(List.of("T20"), List.of("D25"), List.of("D25"))
                        ),
                        1 + 2
                ),
                Arguments.of(
                        161,
                        List.of(
                                List.of(List.of("T20"), List.of("T17"), List.of("D25")),
                                List.of(List.of("T19"), List.of("T18"), List.of("D25"))

                        ),
                        2 + 2
                ),
                Arguments.of(
                        164,
                        List.of(
                                List.of(List.of("T20"), List.of("T18"), List.of("D25")),
                                List.of(List.of("T19"), List.of("T19"), List.of("D25"))

                        ),
                        2 + 1
                ),
                Arguments.of(167, List.of(List.of(List.of("T20"), List.of("T19"), List.of("D25"))), 2),
                Arguments.of(170, List.of(List.of(List.of("T20"), List.of("T20"), List.of("D25"))), 1),
                Arguments.of(220,
                        List.of(
                                List.of(List.of("T20"), List.of("T20"), List.of("T20"), List.of("D20")),
                                List.of(List.of("T20"), List.of("T20"), List.of("D25"), List.of("D25"))
                        ),
                        1 + 3
                ),
                Arguments.of(221,
                        List.of(
                                List.of(List.of("T20"), List.of("T20"), List.of("T17"), List.of("D25")),
                                List.of(List.of("T20"), List.of("T19"), List.of("T18"), List.of("D25")),
                                List.of(List.of("T19"), List.of("T19"), List.of("T19"), List.of("D25"))
                        ),
                        3 + 6 + 1
                ),
                Arguments.of(224,
                        List.of(
                                List.of(List.of("T20"), List.of("T20"), List.of("T18"), List.of("D25")),
                                List.of(List.of("T20"), List.of("T19"), List.of("T19"), List.of("D25"))
                        ),
                        3 + 3
                ),
                Arguments.of(227, List.of(List.of(List.of("T20"), List.of("T20"), List.of("T19"), List.of("D25"))), 3),
                Arguments.of(230, List.of(List.of(List.of("T20"), List.of("T20"), List.of("T20"), List.of("D25"))), 1),
                Arguments.of(
                        280,
                        List.of(
                                List.of(List.of("T20"), List.of("T20"), List.of("T20"), List.of("T20"), List.of("D20")),
                                List.of(List.of("T20"), List.of("T20"), List.of("T20"), List.of("D25"), List.of("D25"))
                        ),
                        1 + 4
                ),
                Arguments.of(
                        281,
                        List.of(
                                List.of(List.of("T20"), List.of("T20"), List.of("T20"), List.of("T17"), List.of("D25")),
                                List.of(List.of("T20"), List.of("T20"), List.of("T19"), List.of("T18"), List.of("D25")),
                                List.of(List.of("T20"), List.of("T19"), List.of("T19"), List.of("T19"), List.of("D25"))
                        ),
                        4 + 12 + 4
                ),
                Arguments.of(
                        284,
                        List.of(
                                List.of(List.of("T20"), List.of("T20"), List.of("T20"), List.of("T18"), List.of("D25")),
                                List.of(List.of("T20"), List.of("T20"), List.of("T19"), List.of("T19"), List.of("D25"))
                        ),
                        4 + 6
                ),
                Arguments.of(
                        287,
                        List.of(
                                List.of(List.of("T20"), List.of("T20"), List.of("T20"), List.of("T19"), List.of("D25"))
                        ),
                        4
                ),
                Arguments.of(
                        290,
                        List.of(
                                List.of(List.of("T20"), List.of("T20"), List.of("T20"), List.of("T20"), List.of("D25"))
                        ),
                        1
                )
        );
    }


    @ParameterizedTest
    @MethodSource("withDescendingLondonBoardDoubleInDoubleOutCheckouts")
    void getTheDescendingLondonBoardDoubleInDoubleOutCheckouts(
            int score,
            Collection<? extends Collection<? extends Collection<String>>> namesPerCheckout,
            int totalMultiplicity
    ) {
        BoardType boardType = BoardType.LONDON;
        CheckType checkInType = CheckType.DOUBLE;
        CheckType checkoutType = CheckType.DOUBLE;
        FinderType finderType = FinderType.DESCENDING;

        CheckoutTableGenerator checkoutTableGenerator = MapBasedCheckoutTableGenerator.of(
                boardType, checkInType, checkoutType, finderType
        );

        CheckoutTable checkoutTable = checkoutTableGenerator.generate(score, score);

        Map<Integer, List<Checkout>> storedCheckoutMap = checkoutTable.getCheckoutMap();
        List<Checkout> storedCheckouts = storedCheckoutMap.get(score);
        Collection<List<List<String>>> storedNames = CheckoutTestUtils.getAllNames(storedCheckouts);

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

    private static Stream<Arguments> withDescendingLondonBoardDoubleInDoubleOutCheckouts() {
        return Stream.of(
                Arguments.of(2, List.of(List.of(List.of("D1"))), 1),
                Arguments.of(10, List.of(List.of(List.of("D5"))), 1),
                Arguments.of(26, List.of(List.of(List.of("D13"))), 1),
                Arguments.of(32, List.of(List.of(List.of("D16"))), 1),
                Arguments.of(40, List.of(List.of(List.of("D20"))), 1),
                Arguments.of(50, List.of(List.of(List.of("D25"))), 1),
                Arguments.of(
                        80,
                        List.of(
                                List.of(List.of("D25"), List.of("D15")),
                                List.of(List.of("D20"), List.of("D20"))
                        ),
                        2 + 1
                ),
                Arguments.of(90, List.of(List.of(List.of("D25"), List.of("D20"))), 2),
                Arguments.of(100, List.of(List.of(List.of("D25"), List.of("D25"))), 1),
                Arguments.of(
                        147,
                        List.of(
                                List.of(List.of("D25"), List.of("T19"), List.of("D20")),
                                List.of(List.of("D20"), List.of("T19"), List.of("D25"))
                        ),
                        1 + 1
                ),
                Arguments.of(
                        150,
                        List.of(
                                List.of(List.of("D25"), List.of("T20"), List.of("D20")),
                                List.of(List.of("D25"), List.of("D25"), List.of("D25")),
                                List.of(List.of("D20"), List.of("T20"), List.of("D25"))
                        ),
                        1 + 1 + 1
                ),
                Arguments.of(151, List.of(List.of(List.of("D25"), List.of("T17"), List.of("D25"))), 1),
                Arguments.of(154, List.of(List.of(List.of("D25"), List.of("T18"), List.of("D25"))), 1),
                Arguments.of(157, List.of(List.of(List.of("D25"), List.of("T19"), List.of("D25"))), 1),
                Arguments.of(160, List.of(List.of(List.of("D25"), List.of("T20"), List.of("D25"))), 1),
                Arguments.of(220, List.of(List.of(List.of("D25"), List.of("T20"), List.of("T20"), List.of("D25"))), 1),
                Arguments.of(
                        280,
                        List.of(
                                List.of(List.of("D25"), List.of("T20"), List.of("T20"), List.of("T20"), List.of("D25"))
                        ),
                        1
                )
        );
    }

    @ParameterizedTest
    @MethodSource("withDescendingQuadroBoardMasterInDoubleOutCheckouts")
    void getTheDescendingQuadroBoardMasterInDoubleOutCheckouts(
            int score,
            Collection<? extends Collection<? extends Collection<String>>> namesPerCheckout,
            int totalMultiplicity
    ) {
        BoardType boardType = BoardType.QUADRO;
        CheckType checkInType = CheckType.MASTER;
        CheckType checkoutType = CheckType.DOUBLE;
        FinderType finderType = FinderType.DESCENDING;

        CheckoutTableGenerator checkoutTableGenerator = MapBasedCheckoutTableGenerator.of(
                boardType, checkInType, checkoutType, finderType
        );

        CheckoutTable checkoutTable = checkoutTableGenerator.generate(score, score);

        Map<Integer, List<Checkout>> storedCheckoutMap = checkoutTable.getCheckoutMap();
        List<Checkout> storedCheckouts = storedCheckoutMap.get(score);
        Collection<List<List<String>>> storedNames = CheckoutTestUtils.getAllNames(storedCheckouts);

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

    private static Stream<Arguments> withDescendingQuadroBoardMasterInDoubleOutCheckouts() {
        return Stream.of(
                Arguments.of(2, List.of(List.of(List.of("D1"))), 1),
                Arguments.of(14, List.of(List.of(List.of("D7"))), 1),
                Arguments.of(40, List.of(List.of(List.of("D20"))), 1),
                Arguments.of(50, List.of(List.of(List.of("D25"))), 1),
                Arguments.of(
                        100,
                        List.of(
                                List.of(List.of("T20"), List.of("D20")),
                                List.of(List.of("D25"), List.of("D25"))
                        ),
                        2
                ),
                Arguments.of(101, List.of(List.of(List.of("T17"), List.of("D25"))), 1),
                Arguments.of(104, List.of(List.of(List.of("T18"), List.of("D25"))), 1),
                Arguments.of(107, List.of(List.of(List.of("T19"), List.of("D25"))), 1),
                Arguments.of(110, List.of(List.of(List.of("T20"), List.of("D25"))), 1),
                Arguments.of(
                        180,
                        List.of(
                                List.of(List.of("T20"), List.of("Q20"), List.of("D20")),
                                List.of(List.of("T18"), List.of("Q19"), List.of("D25")),
                                List.of(List.of("D25"), List.of("Q20"), List.of("D25"))
                        ),
                        1 + 1 + 1
                ),
                Arguments.of(183, List.of(List.of(List.of("T19"), List.of("Q19"), List.of("D25"))), 1),
                Arguments.of(184, List.of(List.of(List.of("T18"), List.of("Q20"), List.of("D25"))), 1),
                Arguments.of(186, List.of(List.of(List.of("T20"), List.of("Q19"), List.of("D25"))), 1),
                Arguments.of(187, List.of(List.of(List.of("T19"), List.of("Q20"), List.of("D25"))), 1),
                Arguments.of(190, List.of(List.of(List.of("T20"), List.of("Q20"), List.of("D25"))), 1),
                Arguments.of(
                        260,
                        List.of(
                                List.of(List.of("T20"), List.of("Q20"), List.of("Q20"), List.of("D20")),
                                List.of(List.of("T18"), List.of("Q20"), List.of("Q19"), List.of("D25")),
                                List.of(List.of("D25"), List.of("Q20"), List.of("Q20"), List.of("D25"))
                        ),
                        1 + 2 + 1
                ),
                Arguments.of(
                        261,
                        List.of(
                                List.of(List.of("T17"), List.of("Q20"), List.of("Q20"), List.of("D25"))
                        ),
                        1
                ),
                Arguments.of(
                        262,
                        List.of(
                                List.of(List.of("T20"), List.of("Q20"), List.of("Q18"), List.of("D25")),
                                List.of(List.of("T20"), List.of("Q19"), List.of("Q19"), List.of("D25"))
                        ),
                        2 + 1
                ),
                Arguments.of(
                        263,
                        List.of(
                                List.of(List.of("T19"), List.of("Q20"), List.of("Q19"), List.of("D25"))
                        ),
                        2
                ),
                Arguments.of(
                        264,
                        List.of(
                                List.of(List.of("T18"), List.of("Q20"), List.of("Q20"), List.of("D25"))
                        ),
                        1
                ),
                Arguments.of(
                        266,
                        List.of(
                                List.of(List.of("T20"), List.of("Q20"), List.of("Q19"), List.of("D25"))
                        ),
                        2
                ),
                Arguments.of(
                        267,
                        List.of(
                                List.of(List.of("T19"), List.of("Q20"), List.of("Q20"), List.of("D25"))
                        ),
                        1
                ),
                Arguments.of(
                        270,
                        List.of(
                                List.of(List.of("T20"), List.of("Q20"), List.of("Q20"), List.of("D25"))
                        ),
                        1
                )
        );
    }

    @ParameterizedTest
    @MethodSource("withDescendingLondonBoardHighScoreCheckouts")
    void countTheDescendingLondonBoardHighScoreCheckouts(
            int score, CheckType checkInType, CheckType checkoutType, int totalMultiplicity
    ) {
        BoardType boardType = BoardType.LONDON;
        FinderType finderType = FinderType.DESCENDING;

        CheckoutTableGenerator checkoutTableGenerator = MapBasedCheckoutTableGenerator.of(
                boardType, checkInType, checkoutType, finderType
        );

        CheckoutTable checkoutTable = checkoutTableGenerator.generate(score, score);

        Map<Integer, List<Checkout>> storedCheckoutMap = checkoutTable.getCheckoutMap();
        List<Checkout> storedCheckouts = storedCheckoutMap.get(score);

        Assertions.assertAll(
                () -> Assertions.assertEquals(1, storedCheckoutMap.size()),
                () -> Assertions.assertEquals(score, CheckoutTestUtils.getAvgScore(storedCheckouts)),
                () -> Assertions.assertEquals(
                        totalMultiplicity,
                        CheckoutTestUtils.getTotalMultiplicity(storedCheckouts)
                )
        );
    }

    private static Stream<Arguments> withDescendingLondonBoardHighScoreCheckouts() {
        return Stream.of(
                Arguments.of(160, CheckType.DOUBLE, CheckType.DOUBLE, 1),
                Arguments.of(170, CheckType.ANY, CheckType.DOUBLE, 1),
                Arguments.of(180, CheckType.ANY, CheckType.ANY, 1),
                Arguments.of(220, CheckType.DOUBLE, CheckType.DOUBLE, 1),
                Arguments.of(230, CheckType.ANY, CheckType.DOUBLE, 1),
                Arguments.of(240, CheckType.ANY, CheckType.ANY, 1),
                Arguments.of(280, CheckType.DOUBLE, CheckType.DOUBLE, 1),
                Arguments.of(290, CheckType.ANY, CheckType.DOUBLE, 1),
                Arguments.of(300, CheckType.ANY, CheckType.ANY, 1),
                Arguments.of(340, CheckType.DOUBLE, CheckType.DOUBLE, 1),
                Arguments.of(350, CheckType.ANY, CheckType.DOUBLE, 1),
                Arguments.of(360, CheckType.ANY, CheckType.ANY, 1),
                Arguments.of(400, CheckType.DOUBLE, CheckType.DOUBLE, 1),
                Arguments.of(410, CheckType.ANY, CheckType.DOUBLE, 1),
                Arguments.of(420, CheckType.ANY, CheckType.ANY, 1),
                Arguments.of(501, CheckType.ANY, CheckType.DOUBLE, 3944),
                Arguments.of(501, CheckType.DOUBLE, CheckType.DOUBLE, 574),
                Arguments.of(460, CheckType.DOUBLE, CheckType.DOUBLE, 1),
                Arguments.of(470, CheckType.ANY, CheckType.DOUBLE, 1),
                Arguments.of(480, CheckType.ANY, CheckType.ANY, 1)
        );
    }

    @ParameterizedTest
    @MethodSource("withoutFewDartCheckouts")
    void doNotReachACheckoutWithFewDarts(
            BoardType boardType, CheckType checkInType, CheckType checkoutType, int score, int numThrows
    ) {
        FinderType finderType = FinderType.DESCENDING;

        CheckoutTableGenerator checkoutTableGenerator = MapBasedCheckoutTableGenerator.of(
                boardType, checkInType, checkoutType, finderType
        );

        CheckoutTable checkoutTable = checkoutTableGenerator.generate(score, score);

        Map<Integer, List<Checkout>> storedCheckoutMap = checkoutTable.getCheckoutMap();
        List<Checkout> storedCheckouts = storedCheckoutMap.get(score);

        Assertions.assertAll(
                () -> Assertions.assertEquals(1, storedCheckoutMap.size()),
                () -> Assertions.assertTrue(
                        storedCheckouts.stream().noneMatch(checkout -> checkout.getThrows().size() == numThrows)
                )
        );
    }

    private static Stream<Arguments> withoutFewDartCheckouts() {
        return Stream.of(
                Arguments.of(BoardType.QUADRO, CheckType.ANY, CheckType.ANY, 0, 1),
                Arguments.of(BoardType.QUADRO, CheckType.ANY, CheckType.DOUBLE, 1, 1),
                Arguments.of(BoardType.LONDON, CheckType.MASTER, CheckType.ANY, 1, 1),
                Arguments.of(BoardType.QUADRO, CheckType.ANY, CheckType.DOUBLE, 3, 1),
                Arguments.of(BoardType.YORKSHIRE, CheckType.ANY, CheckType.MASTER, 3, 1),
                Arguments.of(BoardType.QUADRO, CheckType.DOUBLE, CheckType.DOUBLE, 5, 1),
                Arguments.of(BoardType.LONDON, CheckType.ANY, CheckType.DOUBLE, 9, 1),
                Arguments.of(BoardType.YORKSHIRE, CheckType.ANY, CheckType.MASTER, 9, 1),
                Arguments.of(BoardType.YORKSHIRE, CheckType.ANY, CheckType.ANY, 21, 1),
                Arguments.of(BoardType.QUADRO, CheckType.ANY, CheckType.ANY, 23, 1),
                Arguments.of(BoardType.LONDON, CheckType.ANY, CheckType.DOUBLE, 25, 1),
                Arguments.of(BoardType.YORKSHIRE, CheckType.ANY, CheckType.ANY, 25, 1),
                Arguments.of(BoardType.LONDON, CheckType.DOUBLE, CheckType.ANY, 27, 1),
                Arguments.of(BoardType.QUADRO, CheckType.ANY, CheckType.ANY, 29, 1),
                Arguments.of(BoardType.QUADRO, CheckType.ANY, CheckType.ANY, 31, 1),
                Arguments.of(BoardType.QUADRO, CheckType.ANY, CheckType.ANY, 35, 1),
                Arguments.of(BoardType.QUADRO, CheckType.ANY, CheckType.ANY, 37, 1),
                Arguments.of(BoardType.QUADRO, CheckType.ANY, CheckType.ANY, 41, 1),
                Arguments.of(BoardType.QUADRO, CheckType.ANY, CheckType.ANY, 43, 1),
                Arguments.of(BoardType.QUADRO, CheckType.ANY, CheckType.MASTER, 44, 1),
                Arguments.of(BoardType.LONDON, CheckType.ANY, CheckType.ANY, 44, 1),
                Arguments.of(BoardType.QUADRO, CheckType.ANY, CheckType.ANY, 46, 1),
                Arguments.of(BoardType.QUADRO, CheckType.ANY, CheckType.ANY, 47, 1),
                Arguments.of(BoardType.QUADRO, CheckType.ANY, CheckType.ANY, 49, 1),
                Arguments.of(BoardType.LONDON, CheckType.DOUBLE, CheckType.ANY, 51, 1),
                Arguments.of(BoardType.YORKSHIRE, CheckType.ANY, CheckType.ANY, 51, 1),
                Arguments.of(BoardType.LONDON, CheckType.ANY, CheckType.ANY, 52, 1),
                Arguments.of(BoardType.LONDON, CheckType.ANY, CheckType.ANY, 61, 1),
                Arguments.of(BoardType.LONDON, CheckType.ANY, CheckType.ANY, 76, 1),
                Arguments.of(BoardType.QUADRO, CheckType.ANY, CheckType.ANY, 79, 1),
                Arguments.of(BoardType.QUADRO, CheckType.ANY, CheckType.ANY, 81, 1),
                Arguments.of(BoardType.QUADRO, CheckType.ANY, CheckType.DOUBLE, 99, 2),
                Arguments.of(BoardType.LONDON, CheckType.ANY, CheckType.DOUBLE, 102, 2),
                Arguments.of(BoardType.QUADRO, CheckType.ANY, CheckType.DOUBLE, 103, 2),
                Arguments.of(BoardType.QUADRO, CheckType.DOUBLE, CheckType.DOUBLE, 104, 2),
                Arguments.of(BoardType.QUADRO, CheckType.ANY, CheckType.DOUBLE, 105, 2),
                Arguments.of(BoardType.LONDON, CheckType.ANY, CheckType.DOUBLE, 106, 2),
                Arguments.of(BoardType.LONDON, CheckType.ANY, CheckType.DOUBLE, 108, 2),
                Arguments.of(BoardType.LONDON, CheckType.ANY, CheckType.DOUBLE, 109, 2),
                Arguments.of(BoardType.LONDON, CheckType.ANY, CheckType.DOUBLE, 111, 2),
                Arguments.of(BoardType.LONDON, CheckType.ANY, CheckType.MASTER, 113, 2),
                Arguments.of(BoardType.LONDON, CheckType.ANY, CheckType.ANY, 121, 2),
                Arguments.of(BoardType.QUADRO, CheckType.ANY, CheckType.ANY, 161, 2),
                Arguments.of(BoardType.LONDON, CheckType.ANY, CheckType.DOUBLE, 159, 3),
                Arguments.of(BoardType.LONDON, CheckType.ANY, CheckType.DOUBLE, 162, 3),
                Arguments.of(BoardType.LONDON, CheckType.ANY, CheckType.DOUBLE, 163, 3),
                Arguments.of(BoardType.LONDON, CheckType.ANY, CheckType.DOUBLE, 165, 3),
                Arguments.of(BoardType.LONDON, CheckType.ANY, CheckType.DOUBLE, 166, 3),
                Arguments.of(BoardType.LONDON, CheckType.ANY, CheckType.DOUBLE, 168, 3),
                Arguments.of(BoardType.LONDON, CheckType.ANY, CheckType.DOUBLE, 169, 3),
                Arguments.of(BoardType.LONDON, CheckType.ANY, CheckType.DOUBLE, 171, 3),
                Arguments.of(BoardType.LONDON, CheckType.ANY, CheckType.ANY, 181, 3),
                Arguments.of(BoardType.QUADRO, CheckType.ANY, CheckType.ANY, 241, 3)
        );
    }
}
