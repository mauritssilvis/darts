/*
 * Copyright © 2023 Maurits Silvis
 * SPDX-License-Identifier: GPL-3.0-or-later
 */

package nl.mauritssilvis.darts.java.core.finders.checkouts;

import nl.mauritssilvis.darts.java.api.boards.Field;
import nl.mauritssilvis.darts.java.api.boards.FieldTestUtils;
import nl.mauritssilvis.darts.java.api.finders.checkouts.Throw;
import nl.mauritssilvis.darts.java.core.boards.TypedFieldTestUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

class CompoundThrowTests {
    @Test
    void storeIndependentFields() {
        Collection<String> names = List.of("14", "D7");
        List<Field> fields = new ArrayList<>(TypedFieldTestUtils.getFields(names));
        Throw compoundThrow = CompoundThrow.of(fields);

        fields.remove(1);

        Collection<Field> storedFields = compoundThrow.getFields();
        Collection<String> storedNames = FieldTestUtils.getAllNames(storedFields);

        Assertions.assertEquals(names, storedNames);
    }

    @Test
    void storeUniqueFields() {
        Collection<String> names = List.of("12", "D6", "12");
        Collection<Field> fields = TypedFieldTestUtils.getFields(names);
        Throw compoundThrow = CompoundThrow.of(fields);

        Collection<Field> storedFields = compoundThrow.getFields();
        Collection<String> storedNames = FieldTestUtils.getAllNames(storedFields);

        Collection<String> uniqueNames = List.of("12", "D6");

        Assertions.assertEquals(uniqueNames, storedNames);
    }

    @Test
    void doNotAcceptFieldsWithDifferentScores() {
        Collection<String> names = List.of("8", "T3");
        Collection<Field> fields = TypedFieldTestUtils.getFields(names);

        Assertions.assertThrows(IllegalArgumentException.class, () -> CompoundThrow.of(fields));
    }

    @Test
    void getTheScore() {
        Collection<String> names = List.of("10", "D5");
        Collection<Field> fields = TypedFieldTestUtils.getFields(names);
        Throw compoundThrow = CompoundThrow.of(fields);

        Assertions.assertEquals(10, compoundThrow.getScore());
    }

    @Test
    void getTheScoreWithSingletonInput() {
        Collection<String> names = List.of("D10");
        Collection<Field> fields = TypedFieldTestUtils.getFields(names);
        Throw singleThrow = CompoundThrow.of(fields);

        Assertions.assertEquals(20, singleThrow.getScore());
    }

    @Test
    void getTheScoreWithEmptyInput() {
        Collection<Field> fields = Collections.emptyList();
        Throw emptyThrow = CompoundThrow.of(fields);

        Assertions.assertEquals(0, emptyThrow.getScore());
    }

    @Test
    void getTheFields() {
        Collection<String> names = List.of("Q4", "D8", "16");
        Collection<Field> fields = TypedFieldTestUtils.getFields(names);
        Throw compoundThrow = CompoundThrow.of(fields);

        Collection<Field> storedFields = compoundThrow.getFields();
        Collection<String> storedNames = FieldTestUtils.getAllNames(storedFields);

        Assertions.assertEquals(names, storedNames);
    }

    @Test
    void getTheFieldsWithSingletonInput() {
        Collection<String> names = List.of("T15");
        Collection<Field> fields = TypedFieldTestUtils.getFields(names);
        Throw compoundThrow = CompoundThrow.of(fields);

        Collection<Field> storedFields = compoundThrow.getFields();
        Collection<String> storedNames = FieldTestUtils.getAllNames(storedFields);

        Assertions.assertEquals(names, storedNames);
    }

    @Test
    void getTheFieldsWithEmptyInput() {
        Collection<String> names = Collections.emptyList();
        Collection<Field> fields = TypedFieldTestUtils.getFields(names);
        Throw compoundThrow = CompoundThrow.of(fields);

        Collection<Field> storedFields = compoundThrow.getFields();
        Collection<String> storedNames = FieldTestUtils.getAllNames(storedFields);

        Assertions.assertEquals(names, storedNames);
    }

    @Test
    void getImmutableFields() {
        Collection<String> names = List.of("D14");
        Collection<Field> fields = TypedFieldTestUtils.getFields(names);
        Throw compoundThrow = CompoundThrow.of(fields);

        List<Field> storedFields = compoundThrow.getFields();

        Assertions.assertThrows(UnsupportedOperationException.class, () -> storedFields.remove(0));
    }

    @Test
    void getEqualThrows() {
        Collection<String> names1 = List.of("T6", "D9");
        Collection<Field> fields1 = TypedFieldTestUtils.getFields(names1);
        Throw compoundThrow1 = CompoundThrow.of(fields1);

        Collection<String> names2 = List.of("T6", "D9", "D9", "T6");
        Collection<Field> fields2 = TypedFieldTestUtils.getFields(names2);
        Throw compoundThrow2 = CompoundThrow.of(fields2);

        Assertions.assertAll(
                () -> Assertions.assertEquals(compoundThrow1, compoundThrow2),
                () -> Assertions.assertEquals(compoundThrow1.hashCode(), compoundThrow2.hashCode())
        );
    }

    @Test
    void getUnequalThrows() {
        Collection<String> names1 = List.of("25");
        Collection<Field> fields1 = TypedFieldTestUtils.getFields(names1);
        Throw compoundThrow1 = CompoundThrow.of(fields1);

        Collection<String> names2 = List.of("D25");
        Collection<Field> fields2 = TypedFieldTestUtils.getFields(names2);
        Throw compoundThrow2 = CompoundThrow.of(fields2);

        Assertions.assertNotEquals(compoundThrow1, compoundThrow2);
    }

    @Test
    void convertToAString() {
        Collection<String> names = List.of("T12", "D18", "Q9");
        Collection<Field> fields = TypedFieldTestUtils.getFields(names);
        Throw compoundThrow = CompoundThrow.of(fields);

        String str = compoundThrow.toString();

        Assertions.assertAll(
                () -> Assertions.assertTrue(str.startsWith(compoundThrow.getClass().getSimpleName())),
                () -> Assertions.assertTrue(str.contains("score")),
                () -> Assertions.assertTrue(str.contains("field"))
        );
    }
}
