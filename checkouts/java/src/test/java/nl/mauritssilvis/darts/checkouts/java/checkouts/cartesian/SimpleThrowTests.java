/*
 * Copyright © 2023 Maurits H. Silvis
 * SPDX-License-Identifier: GPL-3.0-or-later
 */

package nl.mauritssilvis.darts.checkouts.java.checkouts.cartesian;

import nl.mauritssilvis.darts.checkouts.java.boards.Field;
import nl.mauritssilvis.darts.checkouts.java.boards.FieldType;
import nl.mauritssilvis.darts.checkouts.java.boards.standard.TypedField;
import nl.mauritssilvis.darts.checkouts.java.checkouts.Throw;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

class SimpleThrowTests {
    @Test
    void getTheScore() {
        Field field = TypedField.of(FieldType.DOUBLE, 7);
        Throw simpleThrow = SimpleThrow.of(field);

        Assertions.assertEquals(14, simpleThrow.getScore());
    }

    @Test
    void countTheFields() {
        Field field = TypedField.of(FieldType.TRIPLE, 7);
        Throw simpleThrow = SimpleThrow.of(field);

        Assertions.assertEquals(1, simpleThrow.countFields());
    }

    @Test
    void getTheFields() {
        Field field = TypedField.of(FieldType.QUADRUPLE, 3);
        Throw simpleThrow = SimpleThrow.of(field);

        Assertions.assertEquals(List.of(field), simpleThrow.getFields());
    }

    @Test
    void storeImmutableFields() {
        Field field = TypedField.of(FieldType.QUADRUPLE, 3);
        Throw simpleThrow = SimpleThrow.of(field);

        List<Field> storedFields = simpleThrow.getFields();

        Assertions.assertThrows(
                UnsupportedOperationException.class,
                () -> storedFields.add(field)
        );
    }
}