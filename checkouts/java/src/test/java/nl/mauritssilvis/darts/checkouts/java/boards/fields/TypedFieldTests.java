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
        Type type = Type.SINGLE;
        int base = 10;
        Field field = TypedField.of(type, base);

        String name = "10";

        Assertions.assertEquals(name, field.getName());
    }

    @Test
    void getTheNameOfADoubleField() {
        Type type = Type.DOUBLE;
        int base = 8;
        Field field = TypedField.of(type, base);

        String name = "D8";

        Assertions.assertEquals(name, field.getName());
    }

    @Test
    void getTheNameOfATripleField() {
        Type type = Type.TRIPLE;
        int base = 12;
        Field field = TypedField.of(type, base);

        String name = "T12";

        Assertions.assertEquals(name, field.getName());
    }

    @Test
    void getTheNameOfAQuadrupleField() {
        Type type = Type.QUADRUPLE;
        int base = 20;
        Field field = TypedField.of(type, base);

        String name = "Q20";

        Assertions.assertEquals(name, field.getName());
    }

    @Test
    void getTheValueOfASingleField() {
        Type type = Type.SINGLE;
        int base = 2;
        Field field = TypedField.of(type, base);

        int value = 2;

        Assertions.assertEquals(value, field.getValue());
    }

    @Test
    void getTheValueOfADoubleField() {
        Type type = Type.DOUBLE;
        int base = 6;
        Field field = TypedField.of(type, base);

        int value = 12;

        Assertions.assertEquals(value, field.getValue());
    }

    @Test
    void getTheValueOfATripleField() {
        Type type = Type.TRIPLE;
        int base = 20;
        Field field = TypedField.of(type, base);

        int value = 60;

        Assertions.assertEquals(value, field.getValue());
    }

    @Test
    void getTheValueOfAQuadrupleField() {
        Type type = Type.QUADRUPLE;
        int base = 10;
        Field field = TypedField.of(type, base);

        int value = 40;

        Assertions.assertEquals(value, field.getValue());
    }

    @Test
    void getTheTypeOfASingleField() {
        Type type = Type.SINGLE;
        int base = 19;
        Field field = TypedField.of(type, base);

        Assertions.assertEquals(type, field.getType());
    }

    @Test
    void getTheTypeOfADoubleField() {
        Type type = Type.DOUBLE;
        int base = 5;
        Field field = TypedField.of(type, base);

        Assertions.assertEquals(type, field.getType());
    }

    @Test
    void getTheTypeOfATripleField() {
        Type type = Type.TRIPLE;
        int base = 17;
        Field field = TypedField.of(type, base);

        Assertions.assertEquals(type, field.getType());
    }

    @Test
    void getTheTypeOfAQuadrupleField() {
        Type type = Type.QUADRUPLE;
        int base = 4;
        Field field = TypedField.of(type, base);

        Assertions.assertEquals(type, field.getType());
    }
}
