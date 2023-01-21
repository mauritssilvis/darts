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
        int base = 10;
        Type type = Type.SINGLE;
        Field field = TypedField.of(base, type);

        String name = "10";

        Assertions.assertEquals(name, field.getName());
    }

    @Test
    void getTheNameOfADoubleField() {
        int base = 8;
        Type type = Type.DOUBLE;
        Field field = TypedField.of(base, type);

        String name = "D8";

        Assertions.assertEquals(name, field.getName());
    }

    @Test
    void getTheNameOfATripleField() {
        int base = 12;
        Type type = Type.TRIPLE;
        Field field = TypedField.of(base, type);

        String name = "D12";

        Assertions.assertEquals(name, field.getName());
    }

    @Test
    void getTheNameOfAQuadrupleField() {
        int base = 20;
        Type type = Type.QUADRUPLE;
        Field field = TypedField.of(base, type);

        String name = "Q20";

        Assertions.assertEquals(name, field.getName());
    }

    @Test
    void getTheValueOfASingleField() {
        int base = 2;
        Type type = Type.SINGLE;
        Field field = TypedField.of(base, type);

        int value = 2;

        Assertions.assertEquals(value, field.getValue());
    }

    @Test
    void getTheValueOfADoubleField() {
        int base = 6;
        Type type = Type.DOUBLE;
        Field field = TypedField.of(base, type);

        int value = 12;

        Assertions.assertEquals(value, field.getValue());
    }

    @Test
    void getTheValueOfATripleField() {
        int base = 20;
        Type type = Type.TRIPLE;
        Field field = TypedField.of(base, type);

        int value = 60;

        Assertions.assertEquals(value, field.getValue());
    }

    @Test
    void getTheValueOfAQuadrupleField() {
        int base = 10;
        Type type = Type.QUADRUPLE;
        Field field = TypedField.of(base, type);

        int value = 40;

        Assertions.assertEquals(value, field.getValue());
    }

    @Test
    void getTheTypeOfASingleField() {
        int base = 19;
        Type type = Type.SINGLE;
        Field field = TypedField.of(base, type);

        Assertions.assertEquals(type, field.getType());
    }

    @Test
    void getTheTypeOfADoubleField() {
        int base = 5;
        Type type = Type.DOUBLE;
        Field field = TypedField.of(base, type);

        Assertions.assertEquals(type, field.getType());
    }

    @Test
    void getTheTypeOfATripleField() {
        int base = 17;
        Type type = Type.TRIPLE;
        Field field = TypedField.of(base, type);

        Assertions.assertEquals(type, field.getType());
    }

    @Test
    void getTheTypeOfAQuadrupleField() {
        int base = 4;
        Type type = Type.QUADRUPLE;
        Field field = TypedField.of(base, type);

        Assertions.assertEquals(type, field.getType());
    }
}
