/*
 * Copyright © 2023 Maurits Silvis
 * SPDX-License-Identifier: GPL-3.0-or-later
 */

package nl.mauritssilvis.darts.java.finders.checkouts.types;

import nl.mauritssilvis.darts.java.api.boards.Field;
import nl.mauritssilvis.darts.java.api.boards.FieldTestUtils;
import nl.mauritssilvis.darts.java.api.finders.checkouts.Throw;
import nl.mauritssilvis.darts.java.boards.types.TypedFieldTestUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Collection;
import java.util.List;

class SimpleThrowTests {
    @Test
    void getTheScore() {
        String name = "D7";
        Field field = TypedFieldTestUtils.getField(name);
        Throw simpleThrow = SimpleThrow.of(field);

        Assertions.assertEquals(14, simpleThrow.getScore());
    }

    @Test
    void getTheFields() {
        String name = "Q3";
        Field field = TypedFieldTestUtils.getField(name);
        Throw simpleThrow = SimpleThrow.of(field);

        List<Field> storedFields = simpleThrow.getFields();
        List<String> storedNames = FieldTestUtils.getAllNames(storedFields);

        Assertions.assertEquals(List.of(name), storedNames);
    }

    @Test
    void getImmutableFields() {
        String name = "7";
        Field field = TypedFieldTestUtils.getField(name);
        Throw simpleThrow = SimpleThrow.of(field);

        Collection<Field> storedFields = simpleThrow.getFields();

        Assertions.assertThrows(UnsupportedOperationException.class, () -> storedFields.add(field));
    }

    @Test
    void getEqualThrows() {
        String name = "19";

        Field field1 = TypedFieldTestUtils.getField(name);
        Throw simpleThrow1 = SimpleThrow.of(field1);

        Field field2 = TypedFieldTestUtils.getField(name);
        Throw simpleThrow2 = SimpleThrow.of(field2);

        Assertions.assertAll(
                () -> Assertions.assertEquals(simpleThrow1, simpleThrow2),
                () -> Assertions.assertEquals(simpleThrow1.hashCode(), simpleThrow2.hashCode())
        );
    }

    @Test
    void getUnequalThrows() {
        String name1 = "25";
        Field field1 = TypedFieldTestUtils.getField(name1);
        Throw simpleThrow1 = SimpleThrow.of(field1);

        String name2 = "D25";
        Field field2 = TypedFieldTestUtils.getField(name2);
        Throw simpleThrow2 = SimpleThrow.of(field2);

        Assertions.assertNotEquals(simpleThrow1, simpleThrow2);
    }

    @Test
    void convertToAString() {
        String name = "T20";
        Field field = TypedFieldTestUtils.getField(name);
        Throw simpleThrow = SimpleThrow.of(field);

        String str = simpleThrow.toString();

        Assertions.assertAll(
                () -> Assertions.assertTrue(str.startsWith(simpleThrow.getClass().getSimpleName())),
                () -> Assertions.assertTrue(str.contains("score")),
                () -> Assertions.assertTrue(str.contains("field"))
        );
    }
}
