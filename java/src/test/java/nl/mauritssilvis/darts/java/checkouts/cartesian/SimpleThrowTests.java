/*
 * Copyright © 2023 Maurits H. Silvis
 * SPDX-License-Identifier: GPL-3.0-or-later
 */

package nl.mauritssilvis.darts.java.checkouts.cartesian;

import nl.mauritssilvis.darts.java.boards.Field;
import nl.mauritssilvis.darts.java.boards.common.TypedFieldTestUtils;
import nl.mauritssilvis.darts.java.checkouts.Throw;
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
    void getTheFields() {
        Field field = TypedFieldTestUtils.getField("Q3");
        Throw simpleThrow = SimpleThrow.of(field);

        Assertions.assertEquals(List.of(field), simpleThrow.getFields());
    }

    @Test
    void getImmutableFields() {
        Field field = TypedFieldTestUtils.getField("7");
        Throw simpleThrow = SimpleThrow.of(field);

        List<Field> storedFields = simpleThrow.getFields();

        Assertions.assertThrows(UnsupportedOperationException.class, () -> storedFields.add(field));
    }

    @Test
    void getEqualThrows() {
        Field field1 = TypedFieldTestUtils.getField("19");
        Throw simpleThrow1 = SimpleThrow.of(field1);

        Field field2 = TypedFieldTestUtils.getField("19");
        Throw simpleThrow2 = SimpleThrow.of(field2);

        Assertions.assertAll(
                () -> Assertions.assertEquals(simpleThrow1, simpleThrow2),
                () -> Assertions.assertEquals(simpleThrow1.hashCode(), simpleThrow2.hashCode())
        );
    }

    @Test
    void getUnequalThrows() {
        Field field1 = TypedFieldTestUtils.getField("25");
        Throw simpleThrow1 = SimpleThrow.of(field1);

        Field field2 = TypedFieldTestUtils.getField("D25");
        Throw simpleThrow2 = SimpleThrow.of(field2);

        Assertions.assertNotEquals(simpleThrow1, simpleThrow2);
    }
}
