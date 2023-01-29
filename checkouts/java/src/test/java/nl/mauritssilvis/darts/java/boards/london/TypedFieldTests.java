/*
 * Copyright © 2023 Maurits H. Silvis
 * SPDX-License-Identifier: GPL-3.0-or-later
 */

package nl.mauritssilvis.darts.java.boards.london;

import nl.mauritssilvis.darts.java.boards.Field;
import nl.mauritssilvis.darts.java.boards.FieldType;
import nl.mauritssilvis.darts.java.boards.common.TypedField;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class TypedFieldTests {
    @Test
    void getTheFieldTypeOfASingleField() {
        FieldType fieldType = FieldType.SINGLE;
        int baseScore = 19;
        Field field = TypedField.of(fieldType, baseScore);

        Assertions.assertEquals(fieldType, field.getFieldType());
    }

    @Test
    void getTheFieldTypeOfADoubleField() {
        FieldType fieldType = FieldType.DOUBLE;
        int baseScore = 5;
        Field field = TypedField.of(fieldType, baseScore);

        Assertions.assertEquals(fieldType, field.getFieldType());
    }

    @Test
    void getTheFieldTypeOfATripleField() {
        FieldType fieldType = FieldType.TRIPLE;
        int baseScore = 17;
        Field field = TypedField.of(fieldType, baseScore);

        Assertions.assertEquals(fieldType, field.getFieldType());
    }

    @Test
    void getTheFieldTypeOfAQuadrupleField() {
        FieldType fieldType = FieldType.QUADRUPLE;
        int baseScore = 4;
        Field field = TypedField.of(fieldType, baseScore);

        Assertions.assertEquals(fieldType, field.getFieldType());
    }

    @Test
    void getTheNameOfASingleField() {
        FieldType fieldType = FieldType.SINGLE;
        int baseScore = 10;
        Field field = TypedField.of(fieldType, baseScore);

        String name = "10";

        Assertions.assertEquals(name, field.getName());
    }

    @Test
    void getTheNameOfADoubleField() {
        FieldType fieldType = FieldType.DOUBLE;
        int baseScore = 8;
        Field field = TypedField.of(fieldType, baseScore);

        String name = "D8";

        Assertions.assertEquals(name, field.getName());
    }

    @Test
    void getTheNameOfATripleField() {
        FieldType fieldType = FieldType.TRIPLE;
        int baseScore = 12;
        Field field = TypedField.of(fieldType, baseScore);

        String name = "T12";

        Assertions.assertEquals(name, field.getName());
    }

    @Test
    void getTheNameOfAQuadrupleField() {
        FieldType fieldType = FieldType.QUADRUPLE;
        int baseScore = 20;
        Field field = TypedField.of(fieldType, baseScore);

        String name = "Q20";

        Assertions.assertEquals(name, field.getName());
    }

    @Test
    void getTheScoreOfASingleField() {
        FieldType fieldType = FieldType.SINGLE;
        int baseScore = 2;
        Field field = TypedField.of(fieldType, baseScore);

        int score = 2;

        Assertions.assertEquals(score, field.getScore());
    }

    @Test
    void getTheScoreOfADoubleField() {
        FieldType fieldType = FieldType.DOUBLE;
        int baseScore = 6;
        Field field = TypedField.of(fieldType, baseScore);

        int score = 12;

        Assertions.assertEquals(score, field.getScore());
    }

    @Test
    void getTheScoreOfATripleField() {
        FieldType fieldType = FieldType.TRIPLE;
        int baseScore = 20;
        Field field = TypedField.of(fieldType, baseScore);

        int score = 60;

        Assertions.assertEquals(score, field.getScore());
    }

    @Test
    void getTheScoreOfAQuadrupleField() {
        FieldType fieldType = FieldType.QUADRUPLE;
        int baseScore = 10;
        Field field = TypedField.of(fieldType, baseScore);

        int score = 40;

        Assertions.assertEquals(score, field.getScore());
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
}