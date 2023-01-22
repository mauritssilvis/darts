/*
 * Copyright © 2023 Maurits H. Silvis
 * SPDX-License-Identifier: GPL-3.0-or-later
 */

package nl.mauritssilvis.darts.checkouts.java.boards.standard;

import nl.mauritssilvis.darts.checkouts.java.boards.Field;
import nl.mauritssilvis.darts.checkouts.java.boards.FieldType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class TypedFieldTests {
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
    void getTheTypeOfASingleField() {
        FieldType fieldType = FieldType.SINGLE;
        int baseScore = 19;
        Field field = TypedField.of(fieldType, baseScore);

        Assertions.assertEquals(fieldType, field.getType());
    }

    @Test
    void getTheTypeOfADoubleField() {
        FieldType fieldType = FieldType.DOUBLE;
        int baseScore = 5;
        Field field = TypedField.of(fieldType, baseScore);

        Assertions.assertEquals(fieldType, field.getType());
    }

    @Test
    void getTheTypeOfATripleField() {
        FieldType fieldType = FieldType.TRIPLE;
        int baseScore = 17;
        Field field = TypedField.of(fieldType, baseScore);

        Assertions.assertEquals(fieldType, field.getType());
    }

    @Test
    void getTheTypeOfAQuadrupleField() {
        FieldType fieldType = FieldType.QUADRUPLE;
        int baseScore = 4;
        Field field = TypedField.of(fieldType, baseScore);

        Assertions.assertEquals(fieldType, field.getType());
    }
}
