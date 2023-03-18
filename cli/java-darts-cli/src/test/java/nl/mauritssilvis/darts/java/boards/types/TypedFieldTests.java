/*
 * Copyright © 2023 Maurits Silvis
 * SPDX-License-Identifier: GPL-3.0-or-later
 */

package nl.mauritssilvis.darts.java.boards.types;

import nl.mauritssilvis.darts.java.api.boards.Field;
import nl.mauritssilvis.darts.java.api.boards.FieldType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

class TypedFieldTests {
    @ParameterizedTest
    @MethodSource("withTheFieldType")
    void getTheFieldType(FieldType fieldType, int baseScore) {
        Field field = TypedField.of(fieldType, baseScore);

        Assertions.assertEquals(fieldType, field.getFieldType());
    }

    private static Stream<Arguments> withTheFieldType() {
        return Stream.of(
                Arguments.of(FieldType.SINGLE, 19),
                Arguments.of(FieldType.DOUBLE, 5),
                Arguments.of(FieldType.TRIPLE, 17),
                Arguments.of(FieldType.QUADRUPLE, 4)
        );
    }

    @ParameterizedTest
    @MethodSource("withTheName")
    void getTheName(FieldType fieldType, int baseScore, String name) {
        Field field = TypedField.of(fieldType, baseScore);

        Assertions.assertEquals(name, field.getName());
    }

    private static Stream<Arguments> withTheName() {
        return Stream.of(
                Arguments.of(FieldType.SINGLE, 10, "10"),
                Arguments.of(FieldType.DOUBLE, 8, "D8"),
                Arguments.of(FieldType.TRIPLE, 12, "T12"),
                Arguments.of(FieldType.QUADRUPLE, 20, "Q20")
        );
    }

    @ParameterizedTest
    @MethodSource("withTheScore")
    void getTheScore(FieldType fieldType, int baseScore, int score) {
        Field field = TypedField.of(fieldType, baseScore);

        Assertions.assertEquals(score, field.getScore());
    }

    private static Stream<Arguments> withTheScore() {
        return Stream.of(
                Arguments.of(FieldType.SINGLE, 2, 2),
                Arguments.of(FieldType.DOUBLE, 6, 12),
                Arguments.of(FieldType.TRIPLE, 20, 60),
                Arguments.of(FieldType.QUADRUPLE, 10, 40)
        );
    }

    @Test
    void getEqualFields() {
        FieldType fieldType = FieldType.DOUBLE;
        int baseScore = 6;

        Field field1 = TypedField.of(fieldType, baseScore);
        Field field2 = TypedField.of(fieldType, baseScore);

        Assertions.assertAll(
                () -> Assertions.assertEquals(field1, field2),
                () -> Assertions.assertEquals(field1.hashCode(), field2.hashCode())
        );
    }

    @Test
    void getUnequalFields() {
        FieldType fieldType1 = FieldType.SINGLE;
        int baseScore1 = 9;
        Field field1 = TypedField.of(fieldType1, baseScore1);

        FieldType fieldType2 = FieldType.DOUBLE;
        int baseScore2 = 10;
        Field field2 = TypedField.of(fieldType2, baseScore2);

        Assertions.assertNotEquals(field1, field2);
    }

    @Test
    void getUnequalFieldsForDifferentFieldTypes() {
        int baseScore = 9;

        FieldType fieldType1 = FieldType.TRIPLE;
        Field field1 = TypedField.of(fieldType1, baseScore);

        FieldType fieldType2 = FieldType.QUADRUPLE;
        Field field2 = TypedField.of(fieldType2, baseScore);

        Assertions.assertNotEquals(field1, field2);
    }

    @Test
    void getUnequalFieldsForDifferentBaseScores() {
        FieldType fieldType = FieldType.TRIPLE;

        int baseScore1 = 9;
        Field field1 = TypedField.of(fieldType, baseScore1);

        int baseScore2 = 10;
        Field field2 = TypedField.of(fieldType, baseScore2);

        Assertions.assertNotEquals(field1, field2);
    }

    @ParameterizedTest
    @MethodSource("withTheStringRepresentation")
    void convertToAString(FieldType fieldType, int baseScore, String name) {
        Field field = TypedField.of(fieldType, baseScore);
        String str = field.toString();

        Assertions.assertAll(
                () -> Assertions.assertTrue(str.startsWith(field.getClass().getSimpleName())),
                () -> Assertions.assertTrue(str.contains("fieldType")),
                () -> Assertions.assertTrue(str.contains("name")),
                () -> Assertions.assertTrue(str.contains("score"))
        );
    }

    private static Stream<Arguments> withTheStringRepresentation() {
        return Stream.of(
                Arguments.of(FieldType.SINGLE, 2, "2"),
                Arguments.of(FieldType.DOUBLE, 9, "D9"),
                Arguments.of(FieldType.TRIPLE, 17, "T17"),
                Arguments.of(FieldType.QUADRUPLE, 2, "Q2")
        );
    }
}
