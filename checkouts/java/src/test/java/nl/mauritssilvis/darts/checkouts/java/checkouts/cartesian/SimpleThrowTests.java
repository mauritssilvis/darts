/*
 * Copyright © 2023 Maurits H. Silvis
 * SPDX-License-Identifier: GPL-3.0-or-later
 */

package nl.mauritssilvis.darts.checkouts.java.checkouts.cartesian;

import nl.mauritssilvis.darts.checkouts.java.boards.Field;
import nl.mauritssilvis.darts.checkouts.java.checkouts.Throw;
import nl.mauritssilvis.darts.checkouts.java.checkouts.common.TypedFieldTestUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

class SimpleThrowTests {
    @Test
    void getTheScore() {
        Field field = TypedFieldTestUtils.getField("D7");
        Throw simpleThrow = SimpleThrow.of(field);

        Assertions.assertEquals(14, simpleThrow.getScore());
    }

    @Test
    void countTheFields() {
        Field field = TypedFieldTestUtils.getField("T7");
        Throw simpleThrow = SimpleThrow.of(field);

        Assertions.assertEquals(1, simpleThrow.countFields());
    }

    @Test
    void getTheFields() {
        Field field = TypedFieldTestUtils.getField("Q3");
        Throw simpleThrow = SimpleThrow.of(field);

        Assertions.assertEquals(List.of(field), simpleThrow.getFields());
    }

    @Test
    void storeImmutableFields() {
        Field field = TypedFieldTestUtils.getField("7");
        Throw simpleThrow = SimpleThrow.of(field);

        List<Field> storedFields = simpleThrow.getFields();

        Assertions.assertThrows(
                UnsupportedOperationException.class,
                () -> storedFields.add(field)
        );
    }
}