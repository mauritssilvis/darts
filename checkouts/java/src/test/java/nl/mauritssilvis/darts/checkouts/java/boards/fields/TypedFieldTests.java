/*
 * Copyright © 2023 Maurits H. Silvis
 * SPDX-License-Identifier: GPL-3.0-or-later
 */

package nl.mauritssilvis.darts.checkouts.java.boards.fields;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class TypedFieldTests {
    @Test
    void getTheNameOfASingleField() {
        FieldType fieldType = FieldType.SINGLE;
        int base = 10;
        Field field = TypedField.of(fieldType, base);

        String name = "10";

        Assertions.assertEquals(name, field.getName());
    }

    @Test
    void getTheNameOfADoubleField() {
        FieldType fieldType = FieldType.DOUBLE;
        int base = 8;
        Field field = TypedField.of(fieldType, base);

        String name = "D8";

        Assertions.assertEquals(name, field.getName());
    }

    @Test
    void getTheNameOfATripleField() {
        FieldType fieldType = FieldType.TRIPLE;
        int base = 12;
        Field field = TypedField.of(fieldType, base);

        String name = "T12";

        Assertions.assertEquals(name, field.getName());
    }

    @Test
    void getTheNameOfAQuadrupleField() {
        FieldType fieldType = FieldType.QUADRUPLE;
        int base = 20;
        Field field = TypedField.of(fieldType, base);

        String name = "Q20";

        Assertions.assertEquals(name, field.getName());
    }

    @Test
    void getTheValueOfASingleField() {
        FieldType fieldType = FieldType.SINGLE;
        int base = 2;
        Field field = TypedField.of(fieldType, base);

        int value = 2;

        Assertions.assertEquals(value, field.getValue());
    }

    @Test
    void getTheValueOfADoubleField() {
        FieldType fieldType = FieldType.DOUBLE;
        int base = 6;
        Field field = TypedField.of(fieldType, base);

        int value = 12;

        Assertions.assertEquals(value, field.getValue());
    }

    @Test
    void getTheValueOfATripleField() {
        FieldType fieldType = FieldType.TRIPLE;
        int base = 20;
        Field field = TypedField.of(fieldType, base);

        int value = 60;

        Assertions.assertEquals(value, field.getValue());
    }

    @Test
    void getTheValueOfAQuadrupleField() {
        FieldType fieldType = FieldType.QUADRUPLE;
        int base = 10;
        Field field = TypedField.of(fieldType, base);

        int value = 40;

        Assertions.assertEquals(value, field.getValue());
    }

    @Test
    void getTheTypeOfASingleField() {
        FieldType fieldType = FieldType.SINGLE;
        int base = 19;
        Field field = TypedField.of(fieldType, base);

        Assertions.assertEquals(fieldType, field.getType());
    }

    @Test
    void getTheTypeOfADoubleField() {
        FieldType fieldType = FieldType.DOUBLE;
        int base = 5;
        Field field = TypedField.of(fieldType, base);

        Assertions.assertEquals(fieldType, field.getType());
    }

    @Test
    void getTheTypeOfATripleField() {
        FieldType fieldType = FieldType.TRIPLE;
        int base = 17;
        Field field = TypedField.of(fieldType, base);

        Assertions.assertEquals(fieldType, field.getType());
    }

    @Test
    void getTheTypeOfAQuadrupleField() {
        FieldType fieldType = FieldType.QUADRUPLE;
        int base = 4;
        Field field = TypedField.of(fieldType, base);

        Assertions.assertEquals(fieldType, field.getType());
    }
}
