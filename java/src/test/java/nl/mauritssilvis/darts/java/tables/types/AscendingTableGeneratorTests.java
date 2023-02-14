/*
 * Copyright © 2023 Maurits Silvis
 * SPDX-License-Identifier: GPL-3.0-or-later
 */

package nl.mauritssilvis.darts.java.tables.types;

import nl.mauritssilvis.darts.java.finders.checkouts.Checkout;
import nl.mauritssilvis.darts.java.finders.checkouts.CheckoutTestUtils;
import nl.mauritssilvis.darts.java.settings.*;
import nl.mauritssilvis.darts.java.settings.types.TableSettingsBuilder;
import nl.mauritssilvis.darts.java.tables.Table;
import nl.mauritssilvis.darts.java.tables.TableGenerator;
import nl.mauritssilvis.darts.java.tables.TableTestUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

class AscendingTableGeneratorTests {
    @Test
    void getTheSettings() {
        Settings settings = TableSettingsBuilder.create().build();
        TableGenerator tableGenerator = AscendingTableGenerator.of(settings);

        int minScore = 0;
        int maxScore = 0;

        Table table = tableGenerator.generate(minScore, maxScore);

        Assertions.assertEquals(settings, table.getSettings());
    }

    @ParameterizedTest
    @MethodSource("withInvertedScores")
    void doNotGetCheckoutsWithInvertedScores(int numThrows, ThrowMode throwMode, int minScore, int maxScore) {
        Settings settings = TableSettingsBuilder.create()
                .setNumThrows(numThrows)
                .setThrowMode(throwMode)
                .build();

        TableGenerator tableGenerator = AscendingTableGenerator.of(settings);
        Table table = tableGenerator.generate(minScore, maxScore);

        Map<Integer, List<Checkout>> storedCheckoutMap = table.getCheckoutMap();

        Assertions.assertEquals(0, storedCheckoutMap.size());
    }

    private static Stream<Arguments> withInvertedScores() {
        return Stream.of(
                Arguments.of(-1, ThrowMode.OPTIMAL, 10, 9),
                Arguments.of(-1, ThrowMode.FIXED, 2, 1),
                Arguments.of(1, ThrowMode.OPTIMAL, 10, 5),
                Arguments.of(3, ThrowMode.FIXED, 100, 1)
        );
    }

    @ParameterizedTest
    @MethodSource("withCartesianLondonBoardMasterInMasterOutCheckouts")
    void getCartesianLondonBoardMasterInMasterOutCheckouts(
            int score,
            Collection<? extends Collection<? extends Collection<String>>> namesPerCheckout
    ) {
        BoardType boardType = BoardType.LONDON;
        CheckMode checkInMode = CheckMode.MASTER;
        CheckMode checkoutMode = CheckMode.MASTER;
        FinderType finderType = FinderType.CARTESIAN;

        Settings settings = TableSettingsBuilder.create()
                .setBoardType(boardType)
                .setCheckInMode(checkInMode)
                .setCheckoutMode(checkoutMode)
                .setFinderType(finderType)
                .build();

        TableGenerator tableGenerator = AscendingTableGenerator.of(settings);

        Table table = tableGenerator.generate(score, score);

        Map<Integer, List<Checkout>> storedCheckoutMap = table.getCheckoutMap();
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
    @MethodSource("withCartesianQuadroBoardAnyInAnyOutCheckouts")
    void getCartesianQuadroBoardAnyInAnyOutCheckouts(
            int score,
            Collection<? extends Collection<? extends Collection<String>>> namesPerCheckout
    ) {
        BoardType boardType = BoardType.QUADRO;
        CheckMode checkInMode = CheckMode.ANY;
        CheckMode checkoutMode = CheckMode.ANY;
        FinderType finderType = FinderType.CARTESIAN;

        Settings settings = TableSettingsBuilder.create()
                .setBoardType(boardType)
                .setCheckInMode(checkInMode)
                .setCheckoutMode(checkoutMode)
                .setFinderType(finderType)
                .build();

        TableGenerator tableGenerator = AscendingTableGenerator.of(settings);

        Table table = tableGenerator.generate(score, score);

        Map<Integer, List<Checkout>> storedCheckoutMap = table.getCheckoutMap();
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
    @MethodSource("withCartesianYorkshireBoardAnyInMasterOutCheckouts")
    void getCartesianYorkshireBoardAnyInMasterOutCheckouts(
            int score,
            Collection<? extends Collection<? extends Collection<String>>> namesPerCheckout
    ) {
        BoardType boardType = BoardType.YORKSHIRE;
        CheckMode checkInMode = CheckMode.MASTER;
        CheckMode checkoutMode = CheckMode.MASTER;
        FinderType finderType = FinderType.CARTESIAN;

        Settings settings = TableSettingsBuilder.create()
                .setBoardType(boardType)
                .setCheckInMode(checkInMode)
                .setCheckoutMode(checkoutMode)
                .setFinderType(finderType)
                .build();

        TableGenerator tableGenerator = AscendingTableGenerator.of(settings);

        Table table = tableGenerator.generate(score, score);

        Map<Integer, List<Checkout>> storedCheckoutMap = table.getCheckoutMap();
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
    void getDescendingLondonBoardAnyInDoubleOutCheckouts(
            int score,
            Collection<? extends Collection<? extends Collection<String>>> namesPerCheckout,
            int totalMultiplicity
    ) {
        BoardType boardType = BoardType.LONDON;
        CheckMode checkInMode = CheckMode.ANY;
        CheckMode checkoutMode = CheckMode.DOUBLE;
        FinderType finderType = FinderType.DESCENDING;

        Settings settings = TableSettingsBuilder.create()
                .setBoardType(boardType)
                .setCheckInMode(checkInMode)
                .setCheckoutMode(checkoutMode)
                .setFinderType(finderType)
                .build();

        TableGenerator tableGenerator = AscendingTableGenerator.of(settings);

        Table table = tableGenerator.generate(score, score);

        Map<Integer, List<Checkout>> storedCheckoutMap = table.getCheckoutMap();
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
    void getDescendingLondonBoardDoubleInDoubleOutCheckouts(
            int score,
            Collection<? extends Collection<? extends Collection<String>>> namesPerCheckout,
            int totalMultiplicity
    ) {
        BoardType boardType = BoardType.LONDON;
        CheckMode checkInMode = CheckMode.DOUBLE;
        CheckMode checkoutMode = CheckMode.DOUBLE;
        FinderType finderType = FinderType.DESCENDING;

        Settings settings = TableSettingsBuilder.create()
                .setBoardType(boardType)
                .setCheckInMode(checkInMode)
                .setCheckoutMode(checkoutMode)
                .setFinderType(finderType)
                .build();

        TableGenerator tableGenerator = AscendingTableGenerator.of(settings);

        Table table = tableGenerator.generate(score, score);

        Map<Integer, List<Checkout>> storedCheckoutMap = table.getCheckoutMap();
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
    void getDescendingQuadroBoardMasterInDoubleOutCheckouts(
            int score,
            Collection<? extends Collection<? extends Collection<String>>> namesPerCheckout,
            int totalMultiplicity
    ) {
        BoardType boardType = BoardType.QUADRO;
        CheckMode checkInMode = CheckMode.MASTER;
        CheckMode checkoutMode = CheckMode.DOUBLE;
        FinderType finderType = FinderType.DESCENDING;

        Settings settings = TableSettingsBuilder.create()
                .setBoardType(boardType)
                .setCheckInMode(checkInMode)
                .setCheckoutMode(checkoutMode)
                .setFinderType(finderType)
                .build();

        TableGenerator tableGenerator = AscendingTableGenerator.of(settings);

        Table table = tableGenerator.generate(score, score);

        Map<Integer, List<Checkout>> storedCheckoutMap = table.getCheckoutMap();
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
    void countDescendingLondonBoardHighScoreCheckouts(
            int score, CheckMode checkInMode, CheckMode checkoutMode, int totalMultiplicity
    ) {
        BoardType boardType = BoardType.LONDON;
        FinderType finderType = FinderType.DESCENDING;

        Settings settings = TableSettingsBuilder.create()
                .setBoardType(boardType)
                .setCheckInMode(checkInMode)
                .setCheckoutMode(checkoutMode)
                .setFinderType(finderType)
                .build();

        TableGenerator tableGenerator = AscendingTableGenerator.of(settings);

        Table table = tableGenerator.generate(score, score);

        Map<Integer, List<Checkout>> storedCheckoutMap = table.getCheckoutMap();
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
                Arguments.of(160, CheckMode.DOUBLE, CheckMode.DOUBLE, 1),
                Arguments.of(170, CheckMode.ANY, CheckMode.DOUBLE, 1),
                Arguments.of(180, CheckMode.ANY, CheckMode.ANY, 1),
                Arguments.of(220, CheckMode.DOUBLE, CheckMode.DOUBLE, 1),
                Arguments.of(230, CheckMode.ANY, CheckMode.DOUBLE, 1),
                Arguments.of(240, CheckMode.ANY, CheckMode.ANY, 1),
                Arguments.of(280, CheckMode.DOUBLE, CheckMode.DOUBLE, 1),
                Arguments.of(290, CheckMode.ANY, CheckMode.DOUBLE, 1),
                Arguments.of(300, CheckMode.ANY, CheckMode.ANY, 1),
                Arguments.of(340, CheckMode.DOUBLE, CheckMode.DOUBLE, 1),
                Arguments.of(350, CheckMode.ANY, CheckMode.DOUBLE, 1),
                Arguments.of(360, CheckMode.ANY, CheckMode.ANY, 1),
                Arguments.of(400, CheckMode.DOUBLE, CheckMode.DOUBLE, 1),
                Arguments.of(410, CheckMode.ANY, CheckMode.DOUBLE, 1),
                Arguments.of(420, CheckMode.ANY, CheckMode.ANY, 1),
                Arguments.of(501, CheckMode.ANY, CheckMode.DOUBLE, 3944),
                Arguments.of(501, CheckMode.DOUBLE, CheckMode.DOUBLE, 574),
                Arguments.of(460, CheckMode.DOUBLE, CheckMode.DOUBLE, 1),
                Arguments.of(470, CheckMode.ANY, CheckMode.DOUBLE, 1),
                Arguments.of(480, CheckMode.ANY, CheckMode.ANY, 1),
                Arguments.of(600, CheckMode.ANY, CheckMode.ANY, 1)
        );
    }

    @ParameterizedTest
    @MethodSource("withoutFewDartCheckouts")
    void doNotReachACheckoutWithFewDarts(
            BoardType boardType, CheckMode checkInMode, CheckMode checkoutMode, int score, int targetNumThrows
    ) {
        Settings settings = TableSettingsBuilder.create()
                .setBoardType(boardType)
                .setCheckInMode(checkInMode)
                .setCheckoutMode(checkoutMode)
                .build();

        TableGenerator tableGenerator = AscendingTableGenerator.of(settings);

        Table table = tableGenerator.generate(score, score);

        Map<Integer, List<Checkout>> storedCheckoutMap = table.getCheckoutMap();
        List<Checkout> storedCheckouts = storedCheckoutMap.get(score);

        Assertions.assertAll(
                () -> Assertions.assertEquals(1, storedCheckoutMap.size()),
                () -> Assertions.assertTrue(
                        storedCheckouts.stream().noneMatch(checkout -> checkout.getThrows().size() == targetNumThrows)
                )
        );
    }

    private static Stream<Arguments> withoutFewDartCheckouts() {
        return Stream.of(
                Arguments.of(BoardType.QUADRO, CheckMode.ANY, CheckMode.ANY, 0, 1),
                Arguments.of(BoardType.QUADRO, CheckMode.ANY, CheckMode.DOUBLE, 1, 1),
                Arguments.of(BoardType.LONDON, CheckMode.MASTER, CheckMode.ANY, 1, 1),
                Arguments.of(BoardType.QUADRO, CheckMode.ANY, CheckMode.DOUBLE, 3, 1),
                Arguments.of(BoardType.YORKSHIRE, CheckMode.ANY, CheckMode.MASTER, 3, 1),
                Arguments.of(BoardType.QUADRO, CheckMode.DOUBLE, CheckMode.DOUBLE, 5, 1),
                Arguments.of(BoardType.LONDON, CheckMode.ANY, CheckMode.DOUBLE, 9, 1),
                Arguments.of(BoardType.YORKSHIRE, CheckMode.ANY, CheckMode.MASTER, 9, 1),
                Arguments.of(BoardType.YORKSHIRE, CheckMode.ANY, CheckMode.ANY, 21, 1),
                Arguments.of(BoardType.QUADRO, CheckMode.ANY, CheckMode.ANY, 23, 1),
                Arguments.of(BoardType.LONDON, CheckMode.ANY, CheckMode.DOUBLE, 25, 1),
                Arguments.of(BoardType.YORKSHIRE, CheckMode.ANY, CheckMode.ANY, 25, 1),
                Arguments.of(BoardType.LONDON, CheckMode.DOUBLE, CheckMode.ANY, 27, 1),
                Arguments.of(BoardType.QUADRO, CheckMode.ANY, CheckMode.ANY, 29, 1),
                Arguments.of(BoardType.QUADRO, CheckMode.ANY, CheckMode.ANY, 31, 1),
                Arguments.of(BoardType.QUADRO, CheckMode.ANY, CheckMode.ANY, 35, 1),
                Arguments.of(BoardType.QUADRO, CheckMode.ANY, CheckMode.ANY, 37, 1),
                Arguments.of(BoardType.QUADRO, CheckMode.ANY, CheckMode.ANY, 41, 1),
                Arguments.of(BoardType.QUADRO, CheckMode.ANY, CheckMode.ANY, 43, 1),
                Arguments.of(BoardType.QUADRO, CheckMode.ANY, CheckMode.MASTER, 44, 1),
                Arguments.of(BoardType.LONDON, CheckMode.ANY, CheckMode.ANY, 44, 1),
                Arguments.of(BoardType.QUADRO, CheckMode.ANY, CheckMode.ANY, 46, 1),
                Arguments.of(BoardType.QUADRO, CheckMode.ANY, CheckMode.ANY, 47, 1),
                Arguments.of(BoardType.QUADRO, CheckMode.ANY, CheckMode.ANY, 49, 1),
                Arguments.of(BoardType.LONDON, CheckMode.DOUBLE, CheckMode.ANY, 51, 1),
                Arguments.of(BoardType.YORKSHIRE, CheckMode.ANY, CheckMode.ANY, 51, 1),
                Arguments.of(BoardType.LONDON, CheckMode.ANY, CheckMode.ANY, 52, 1),
                Arguments.of(BoardType.LONDON, CheckMode.ANY, CheckMode.ANY, 61, 1),
                Arguments.of(BoardType.LONDON, CheckMode.ANY, CheckMode.ANY, 76, 1),
                Arguments.of(BoardType.QUADRO, CheckMode.ANY, CheckMode.ANY, 79, 1),
                Arguments.of(BoardType.QUADRO, CheckMode.ANY, CheckMode.ANY, 81, 1),
                Arguments.of(BoardType.QUADRO, CheckMode.ANY, CheckMode.DOUBLE, 99, 2),
                Arguments.of(BoardType.LONDON, CheckMode.ANY, CheckMode.DOUBLE, 102, 2),
                Arguments.of(BoardType.QUADRO, CheckMode.ANY, CheckMode.DOUBLE, 103, 2),
                Arguments.of(BoardType.QUADRO, CheckMode.DOUBLE, CheckMode.DOUBLE, 104, 2),
                Arguments.of(BoardType.QUADRO, CheckMode.ANY, CheckMode.DOUBLE, 105, 2),
                Arguments.of(BoardType.LONDON, CheckMode.ANY, CheckMode.DOUBLE, 106, 2),
                Arguments.of(BoardType.LONDON, CheckMode.ANY, CheckMode.DOUBLE, 108, 2),
                Arguments.of(BoardType.LONDON, CheckMode.ANY, CheckMode.DOUBLE, 109, 2),
                Arguments.of(BoardType.LONDON, CheckMode.ANY, CheckMode.DOUBLE, 111, 2),
                Arguments.of(BoardType.LONDON, CheckMode.ANY, CheckMode.MASTER, 113, 2),
                Arguments.of(BoardType.LONDON, CheckMode.ANY, CheckMode.ANY, 121, 2),
                Arguments.of(BoardType.QUADRO, CheckMode.ANY, CheckMode.ANY, 161, 2),
                Arguments.of(BoardType.LONDON, CheckMode.ANY, CheckMode.DOUBLE, 159, 3),
                Arguments.of(BoardType.LONDON, CheckMode.ANY, CheckMode.DOUBLE, 162, 3),
                Arguments.of(BoardType.LONDON, CheckMode.ANY, CheckMode.DOUBLE, 163, 3),
                Arguments.of(BoardType.LONDON, CheckMode.ANY, CheckMode.DOUBLE, 165, 3),
                Arguments.of(BoardType.LONDON, CheckMode.ANY, CheckMode.DOUBLE, 166, 3),
                Arguments.of(BoardType.LONDON, CheckMode.ANY, CheckMode.DOUBLE, 168, 3),
                Arguments.of(BoardType.LONDON, CheckMode.ANY, CheckMode.DOUBLE, 169, 3),
                Arguments.of(BoardType.LONDON, CheckMode.ANY, CheckMode.DOUBLE, 171, 3),
                Arguments.of(BoardType.LONDON, CheckMode.ANY, CheckMode.ANY, 181, 3),
                Arguments.of(BoardType.QUADRO, CheckMode.ANY, CheckMode.ANY, 241, 3)
        );
    }

    @ParameterizedTest
    @MethodSource("withMultipleElementCheckoutMaps")
    void getCheckoutMapsWithMultipleScores(
            BoardType boardType,
            CheckMode checkInMode,
            CheckMode checkoutMode,
            FinderType finderType,
            int minScore,
            int maxScore,
            Map<Integer, Collection<? extends Collection<? extends Collection<String>>>> namesPerScore,
            Map<Integer, Long> multiplicityPerScore
    ) {
        Settings settings = TableSettingsBuilder.create()
                .setBoardType(boardType)
                .setCheckInMode(checkInMode)
                .setCheckoutMode(checkoutMode)
                .setFinderType(finderType)
                .build();

        TableGenerator tableGenerator = AscendingTableGenerator.of(settings);

        Table table = tableGenerator.generate(minScore, maxScore);

        Map<Integer, List<List<List<String>>>> storedNamesPerScore = TableTestUtils.getAllNames(table);
        Map<Integer, Long> storedMultiplicitiesPerScore = TableTestUtils.getAllMultiplicities(table);

        Assertions.assertAll(
                () -> Assertions.assertEquals(namesPerScore, storedNamesPerScore),
                () -> Assertions.assertEquals(multiplicityPerScore, storedMultiplicitiesPerScore)
        );
    }

    private static Stream<Arguments> withMultipleElementCheckoutMaps() {
        return Stream.of(
                Arguments.of(
                        BoardType.QUADRO,
                        CheckMode.ANY,
                        CheckMode.ANY,
                        FinderType.CARTESIAN,
                        1,
                        2,
                        Map.of(
                                1, List.of(List.of(List.of("1"))),
                                2, List.of(
                                        List.of(List.of("2")),
                                        List.of(List.of("D1"))
                                )
                        ),
                        Map.of(
                                1, 1L,
                                2, 2L
                        )
                ),
                Arguments.of(
                        BoardType.QUADRO,
                        CheckMode.ANY,
                        CheckMode.ANY,
                        FinderType.DESCENDING,
                        50,
                        52,
                        Map.of(
                                50, List.of(List.of(List.of("D25"))),
                                51, List.of(List.of(List.of("T17"))),
                                52, List.of(List.of(List.of("Q13")))
                        ),
                        Map.of(
                                50, 1L,
                                51, 1L,
                                52, 1L
                        )
                ),
                Arguments.of(
                        BoardType.YORKSHIRE,
                        CheckMode.ANY,
                        CheckMode.MASTER,
                        FinderType.DESCENDING,
                        49,
                        50,
                        Map.of(
                                49, List.of(
                                        List.of(List.of("19"), List.of("D15")),
                                        List.of(List.of("17"), List.of("D16")),
                                        List.of(List.of("15"), List.of("D17")),
                                        List.of(List.of("13"), List.of("D18")),
                                        List.of(List.of("11"), List.of("D19")),
                                        List.of(List.of("9"), List.of("D20"))
                                ),
                                50, List.of(List.of(List.of("D25")))
                        ),
                        Map.of(
                                49, 6L,
                                50, 1L
                        )
                )
        );
    }

    @ParameterizedTest
    @MethodSource("withoutLowScoreOptimalCheckouts")
    void doNotGetLowScoreOptimalCheckouts(
            BoardType boardType, CheckMode checkInMode, CheckMode checkoutMode, FinderType finderType
    ) {
        Settings settings = TableSettingsBuilder.create()
                .setBoardType(boardType)
                .setCheckInMode(checkInMode)
                .setCheckoutMode(checkoutMode)
                .setFinderType(finderType)
                .build();

        TableGenerator tableGenerator = AscendingTableGenerator.of(settings);

        int minScore = -2;
        int maxScore = 1;

        Table table = tableGenerator.generate(minScore, maxScore);

        Map<Integer, List<Checkout>> storedCheckoutMap = table.getCheckoutMap();

        Assertions.assertAll(
                () -> Assertions.assertEquals(4, storedCheckoutMap.size()),
                () -> Assertions.assertEquals(0, storedCheckoutMap.get(-2).size()),
                () -> Assertions.assertEquals(0, storedCheckoutMap.get(-1).size()),
                () -> Assertions.assertEquals(0, storedCheckoutMap.get(0).size()),
                () -> Assertions.assertEquals(0, storedCheckoutMap.get(1).size())
        );
    }

    private static Stream<Arguments> withoutLowScoreOptimalCheckouts() {
        return Stream.of(
                Arguments.of(BoardType.QUADRO, CheckMode.ANY, CheckMode.DOUBLE, FinderType.CARTESIAN),
                Arguments.of(BoardType.QUADRO, CheckMode.DOUBLE, CheckMode.MASTER, FinderType.DESCENDING),
                Arguments.of(BoardType.LONDON, CheckMode.ANY, CheckMode.MASTER, FinderType.CARTESIAN),
                Arguments.of(BoardType.LONDON, CheckMode.MASTER, CheckMode.MASTER, FinderType.DESCENDING),
                Arguments.of(BoardType.YORKSHIRE, CheckMode.MASTER, CheckMode.ANY, FinderType.CARTESIAN),
                Arguments.of(BoardType.YORKSHIRE, CheckMode.DOUBLE, CheckMode.MASTER, FinderType.DESCENDING)
        );
    }

    @ParameterizedTest
    @MethodSource("withOptimalCheckouts")
    void getOptimalCheckouts(int minScore, int maxScore, int minNumThrows, int maxNumThrows) {
        Settings settings = TableSettingsBuilder.create().build();
        TableGenerator tableGenerator = AscendingTableGenerator.of(settings);

        Table table = tableGenerator.generate(minScore, maxScore);
        Map<Integer, List<Checkout>> storedCheckoutMap = table.getCheckoutMap();

        Collection<Checkout> storedCheckouts1 = storedCheckoutMap.get(minScore);
        Collection<Checkout> storedCheckouts2 = storedCheckoutMap.get(maxScore);

        Assertions.assertAll(
                () -> Assertions.assertEquals(2, storedCheckoutMap.size()),
                () -> Assertions.assertTrue(
                        storedCheckouts1.stream().allMatch(checkout -> checkout.getThrows().size() == minNumThrows)
                ),
                () -> Assertions.assertTrue(
                        storedCheckouts2.stream().allMatch(checkout -> checkout.getThrows().size() == maxNumThrows)
                )
        );
    }

    private static Stream<Arguments> withOptimalCheckouts() {
        return Stream.of(
                Arguments.of(22, 23, 1, 2),
                Arguments.of(50, 51, 1, 2),
                Arguments.of(169, 170, 4, 3)
        );
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 1, 2, 23, 180})
    void doNotGetOptimalZeroThrowCheckouts(int score) {
        CheckMode checkoutMode = CheckMode.ANY;
        int numThrows = 0;

        Settings settings = TableSettingsBuilder.create()
                .setCheckoutMode(checkoutMode)
                .setNumThrows(numThrows)
                .build();

        TableGenerator tableGenerator = AscendingTableGenerator.of(settings);

        Table table = tableGenerator.generate(score, score);

        Map<Integer, List<Checkout>> checkoutMap = table.getCheckoutMap();

        Assertions.assertAll(
                () -> Assertions.assertEquals(1, checkoutMap.size()),
                () -> Assertions.assertEquals(0, checkoutMap.get(score).size())
        );
    }

    @ParameterizedTest
    @MethodSource("withoutLowScoreOptimalFixedThrowCheckouts")
    void doNotGetLowScoreOptimalFixedThrowCheckouts(int numThrows, int score) {
        Settings settings = TableSettingsBuilder.create()
                .setNumThrows(numThrows)
                .build();

        TableGenerator tableGenerator = AscendingTableGenerator.of(settings);
        Table table = tableGenerator.generate(score, score);

        Map<Integer, List<Checkout>> checkoutMap = table.getCheckoutMap();

        Assertions.assertAll(
                () -> Assertions.assertEquals(1, checkoutMap.size()),
                () -> Assertions.assertEquals(0, checkoutMap.get(score).size())
        );
    }

    private static Stream<Arguments> withoutLowScoreOptimalFixedThrowCheckouts() {
        return Stream.of(
                Arguments.of(1, 0),
                Arguments.of(1, 1),
                Arguments.of(1, 3),
                Arguments.of(2, 0),
                Arguments.of(2, 1),
                Arguments.of(2, 2)
        );
    }

    @ParameterizedTest
    @MethodSource("withoutLowThrowOptimalCheckouts")
    void doNotGetLowThrowOptimalCheckouts(int numThrows, int minScore, int maxScore) {
        CheckMode checkoutMode = CheckMode.ANY;

        Settings settings = TableSettingsBuilder.create()
                .setCheckoutMode(checkoutMode)
                .setNumThrows(numThrows)
                .build();

        TableGenerator tableGenerator = AscendingTableGenerator.of(settings);
        Table table = tableGenerator.generate(minScore, maxScore);

        Map<Integer, List<Checkout>> checkoutMap = table.getCheckoutMap();

        Assertions.assertAll(
                () -> Assertions.assertEquals(maxScore - minScore + 1, checkoutMap.size()),
                () -> Assertions.assertEquals(0, checkoutMap.get(minScore).size()),
                () -> Assertions.assertEquals(0, checkoutMap.get(maxScore).size())
        );
    }

    private static Stream<Arguments> withoutLowThrowOptimalCheckouts() {
        return Stream.of(
                Arguments.of(2, 0, 1),
                Arguments.of(2, 1, 2),
                Arguments.of(2, 3, 4),
                Arguments.of(2, 21, 22),
                Arguments.of(2, 50, 51),
                Arguments.of(3, 50, 51),
                Arguments.of(3, 120, 120)
        );
    }

    @Test
    void findOptimalFixedThrowCheckouts() {
        CheckMode checkoutMode = CheckMode.ANY;
        int numThrows = 2;

        Settings settings = TableSettingsBuilder.create()
                .setCheckoutMode(checkoutMode)
                .setNumThrows(numThrows)
                .build();

        TableGenerator tableGenerator = AscendingTableGenerator.of(settings);

        int minScore = 22;
        int maxScore = 23;

        Table table = tableGenerator.generate(minScore, maxScore);
        Map<Integer, List<Checkout>> storedCheckoutMap = table.getCheckoutMap();

        Collection<Checkout> storedCheckouts1 = storedCheckoutMap.get(minScore);
        Collection<Checkout> storedCheckouts2 = storedCheckoutMap.get(maxScore);

        Assertions.assertAll(
                () -> Assertions.assertEquals(2, storedCheckoutMap.size()),
                () -> Assertions.assertEquals(0, storedCheckouts1.size()),
                () -> Assertions.assertNotEquals(0, storedCheckouts2.size()),
                () -> Assertions.assertTrue(
                        storedCheckouts2.stream().allMatch(checkout -> checkout.getThrows().size() == numThrows)
                )
        );
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 1, 2, 23, 180})
    void doNotGetZeroThrowCheckouts(int score) {
        CheckMode checkoutMode = CheckMode.ANY;
        int numThrows = 0;
        ThrowMode throwMode = ThrowMode.FIXED;

        Settings settings = TableSettingsBuilder.create()
                .setCheckoutMode(checkoutMode)
                .setNumThrows(numThrows)
                .setThrowMode(throwMode)
                .build();

        TableGenerator tableGenerator = AscendingTableGenerator.of(settings);

        Table table = tableGenerator.generate(score, score);

        Map<Integer, List<Checkout>> checkoutMap = table.getCheckoutMap();

        Assertions.assertAll(
                () -> Assertions.assertEquals(1, checkoutMap.size()),
                () -> Assertions.assertEquals(0, checkoutMap.get(score).size())
        );
    }

    @ParameterizedTest
    @MethodSource("withoutLowScoreFixedThrowCheckouts")
    void doNotGetLowScoreFixedThrowCheckouts(int numThrows, int score) {
        ThrowMode throwMode = ThrowMode.FIXED;

        Settings settings = TableSettingsBuilder.create()
                .setNumThrows(numThrows)
                .setThrowMode(throwMode)
                .build();

        TableGenerator tableGenerator = AscendingTableGenerator.of(settings);
        Table table = tableGenerator.generate(score, score);

        Map<Integer, List<Checkout>> checkoutMap = table.getCheckoutMap();

        Assertions.assertAll(
                () -> Assertions.assertEquals(1, checkoutMap.size()),
                () -> Assertions.assertEquals(0, checkoutMap.get(score).size())
        );
    }

    private static Stream<Arguments> withoutLowScoreFixedThrowCheckouts() {
        return Stream.of(
                Arguments.of(1, 0),
                Arguments.of(1, 1),
                Arguments.of(1, 3),
                Arguments.of(2, 0),
                Arguments.of(2, 1),
                Arguments.of(2, 2),
                Arguments.of(3, 0),
                Arguments.of(3, 1),
                Arguments.of(3, 2),
                Arguments.of(3, 3)
        );
    }

    @ParameterizedTest
    @MethodSource("withFixedThrowCheckouts")
    void findAllFixedThrowCheckouts(int numThrows, int minScore, int maxScore) {
        CheckMode checkoutMode = CheckMode.ANY;
        ThrowMode throwMode = ThrowMode.FIXED;

        Settings settings = TableSettingsBuilder.create()
                .setCheckoutMode(checkoutMode)
                .setNumThrows(numThrows)
                .setThrowMode(throwMode)
                .build();

        TableGenerator tableGenerator = AscendingTableGenerator.of(settings);

        Table table = tableGenerator.generate(minScore, maxScore);
        Map<Integer, List<Checkout>> storedCheckoutMap = table.getCheckoutMap();

        Collection<Checkout> storedCheckouts1 = storedCheckoutMap.get(minScore);
        Collection<Checkout> storedCheckouts2 = storedCheckoutMap.get(maxScore);

        Assertions.assertAll(
                () -> Assertions.assertEquals(2, storedCheckoutMap.size()),
                () -> Assertions.assertNotEquals(0, storedCheckouts1.size()),
                () -> Assertions.assertTrue(
                        storedCheckouts1.stream().allMatch(checkout -> checkout.getThrows().size() == numThrows)
                ),
                () -> Assertions.assertNotEquals(0, storedCheckouts2.size()),
                () -> Assertions.assertTrue(
                        storedCheckouts2.stream().allMatch(checkout -> checkout.getThrows().size() == numThrows)
                )
        );
    }

    private static Stream<Arguments> withFixedThrowCheckouts() {
        return Stream.of(
                Arguments.of(2, 6, 7),
                Arguments.of(2, 22, 23),
                Arguments.of(2, 50, 51),
                Arguments.of(3, 3, 4)
        );
    }

    @Test
    void getEqualTableGenerators() {
        Settings settings = TableSettingsBuilder.create().build();

        TableGenerator tableGenerator1 = AscendingTableGenerator.of(settings);
        TableGenerator tableGenerator2 = AscendingTableGenerator.of(settings);

        Assertions.assertAll(
                () -> Assertions.assertEquals(tableGenerator1, tableGenerator2),
                () -> Assertions.assertEquals(tableGenerator1.hashCode(), tableGenerator2.hashCode())
        );
    }

    @Test
    void getUnequalTableGenerators() {
        BoardType boardType1 = BoardType.QUADRO;

        Settings settings = TableSettingsBuilder.create()
                .setBoardType(boardType1)
                .build();

        TableGenerator tableGenerator1 = AscendingTableGenerator.of(settings);

        BoardType boardType2 = BoardType.LONDON;

        Settings settings2 = TableSettingsBuilder.create()
                .setBoardType(boardType2)
                .build();

        TableGenerator tableGenerator2 = AscendingTableGenerator.of(settings2);

        Assertions.assertNotEquals(tableGenerator1, tableGenerator2);
    }

    @Test
    void convertToAString() {
        Settings settings = TableSettingsBuilder.create().build();
        TableGenerator tableGenerator = AscendingTableGenerator.of(settings);

        String str = tableGenerator.toString();

        Assertions.assertAll(
                () -> Assertions.assertTrue(str.contains(tableGenerator.getClass().getSimpleName())),
                () -> Assertions.assertTrue(str.contains("settings"))
        );
    }
}
