/*
 * Copyright © 2023 Maurits Silvis
 * SPDX-License-Identifier: GPL-3.0-or-later
 */

package nl.mauritssilvis.darts.java.boards.types;

import nl.mauritssilvis.darts.java.api.boards.Board;
import nl.mauritssilvis.darts.java.api.boards.Field;
import nl.mauritssilvis.darts.java.api.boards.FieldType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

class QuadroBoardTests {
    @ParameterizedTest
    @EnumSource(FieldType.class)
    void getImmutableFields(FieldType fieldType) {
        Board board = QuadroBoard.create();
        List<Field> fields = board.getFields(fieldType);

        Assertions.assertThrows(UnsupportedOperationException.class, () -> fields.remove(0));
    }

    @ParameterizedTest
    @EnumSource(FieldType.class)
    void getFieldsOfOneType(FieldType fieldType) {
        Board board = QuadroBoard.create();
        List<Field> fields = board.getFields(fieldType);

        List<Field> otherFields = fields.stream()
                .filter(field -> field.getFieldType() != fieldType)
                .toList();

        Assertions.assertEquals(0, otherFields.size());
    }

    @ParameterizedTest
    @MethodSource("withTheFieldCount")
    void countTheFields(FieldType fieldType, int count) {
        Board board = QuadroBoard.create();
        List<Field> fields = board.getFields(fieldType);

        Assertions.assertEquals(count, fields.size());
    }

    private static Stream<Arguments> withTheFieldCount() {
        return Stream.of(
                Arguments.of(FieldType.SINGLE, 21),
                Arguments.of(FieldType.DOUBLE, 21),
                Arguments.of(FieldType.TRIPLE, 20),
                Arguments.of(FieldType.QUADRUPLE, 20)
        );
    }

    @ParameterizedTest
    @MethodSource("withTheMinimumScore")
    void getTheMinimumScore(FieldType fieldType, int expectedMinimum) {
        Board board = QuadroBoard.create();
        List<Field> fields = board.getFields(fieldType);

        int actualMinimum = fields.stream()
                .mapToInt(Field::getScore)
                .min()
                .orElse(Integer.MAX_VALUE);

        Assertions.assertEquals(expectedMinimum, actualMinimum);
    }

    private static Stream<Arguments> withTheMinimumScore() {
        return Stream.of(
                Arguments.of(FieldType.SINGLE, 1),
                Arguments.of(FieldType.DOUBLE, 2),
                Arguments.of(FieldType.TRIPLE, 3),
                Arguments.of(FieldType.QUADRUPLE, 4)
        );
    }

    @ParameterizedTest
    @MethodSource("withTheMaximumScore")
    void getTheMaximumScore(FieldType fieldType, int expectedMaximum) {
        Board board = QuadroBoard.create();
        List<Field> fields = board.getFields(fieldType);

        int actualMaximum = fields.stream()
                .mapToInt(Field::getScore)
                .max()
                .orElse(Integer.MIN_VALUE);

        Assertions.assertEquals(expectedMaximum, actualMaximum);
    }

    private static Stream<Arguments> withTheMaximumScore() {
        return Stream.of(
                Arguments.of(FieldType.SINGLE, 25),
                Arguments.of(FieldType.DOUBLE, 50),
                Arguments.of(FieldType.TRIPLE, 60),
                Arguments.of(FieldType.QUADRUPLE, 80)
        );
    }

    @ParameterizedTest
    @MethodSource("withTheSumOfScores")
    void getTheTotalScore(FieldType fieldType, int expectedSum) {
        Board board = QuadroBoard.create();
        List<Field> fields = board.getFields(fieldType);

        int actualSum = fields.stream()
                .mapToInt(Field::getScore)
                .sum();

        Assertions.assertEquals(expectedSum, actualSum);
    }

    private static Stream<Arguments> withTheSumOfScores() {
        return Stream.of(
                Arguments.of(FieldType.SINGLE, 235),
                Arguments.of(FieldType.DOUBLE, 470),
                Arguments.of(FieldType.TRIPLE, 630),
                Arguments.of(FieldType.QUADRUPLE, 840)
        );
    }

    @Test
    void getEqualBoards() {
        Board board1 = QuadroBoard.create();
        Board board2 = QuadroBoard.create();

        Assertions.assertAll(
                () -> Assertions.assertEquals(board1, board2),
                () -> Assertions.assertEquals(board1.hashCode(), board2.hashCode())
        );
    }

    @Test
    void convertToAString() {
        Board board = QuadroBoard.create();
        String str = board.toString();

        Assertions.assertAll(
                () -> Assertions.assertTrue(str.startsWith(board.getClass().getSimpleName())),
                () -> Assertions.assertTrue(str.contains("Fields")),
                () -> Assertions.assertTrue(str.contains("single")),
                () -> Assertions.assertTrue(str.contains("double")),
                () -> Assertions.assertTrue(str.contains("triple")),
                () -> Assertions.assertTrue(str.contains("quadruple"))
        );
    }
}
