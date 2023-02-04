/*
 * Copyright © 2023 Maurits H. Silvis
 * SPDX-License-Identifier: GPL-3.0-or-later
 */

package nl.mauritssilvis.darts.java.checkouts.descending;

import nl.mauritssilvis.darts.java.boards.Field;
import nl.mauritssilvis.darts.java.boards.common.TypedFieldTestUtils;
import nl.mauritssilvis.darts.java.checkouts.Throw;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

class CompoundThrowTests {
    @Test
    void storeIndependentFields() {
        List<Field> fields = new ArrayList<>(TypedFieldTestUtils.getFields("14", "D7"));
        Throw compoundThrow = CompoundThrow.of(fields);

        fields.remove(1);

        Assertions.assertNotEquals(fields, compoundThrow.getFields());
    }

    @Test
    void storeUniqueFields() {
        Collection<Field> fields = TypedFieldTestUtils.getFields("12", "D6", "12");
        Throw compoundThrow = CompoundThrow.of(fields);

        Collection<Field> uniqueFields = TypedFieldTestUtils.getFields("12", "D6");

        Assertions.assertEquals(uniqueFields, compoundThrow.getFields());
    }

    @Test
    void doNotAcceptFieldsWithDifferentScores() {
        Collection<Field> fields = TypedFieldTestUtils.getFields("8", "T3");

        Assertions.assertThrows(
                IllegalArgumentException.class,
                () -> CompoundThrow.of(fields)
        );
    }

    @Test
    void getTheScore() {
        Collection<Field> fields = TypedFieldTestUtils.getFields("10", "D5");
        Throw compoundThrow = CompoundThrow.of(fields);

        Assertions.assertEquals(10, compoundThrow.getScore());
    }

    @Test
    void getTheScoreWithSingletonInput() {
        Collection<Field> fields = TypedFieldTestUtils.getFields("D10");
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
        Collection<Field> fields = TypedFieldTestUtils.getFields("Q4", "D8", "16");
        Throw compoundThrow = CompoundThrow.of(fields);

        Assertions.assertEquals(fields, compoundThrow.getFields());
    }

    @Test
    void getTheFieldsWithSingletonInput() {
        Collection<Field> fields = TypedFieldTestUtils.getFields("T15");
        Throw compoundThrow = CompoundThrow.of(fields);

        Assertions.assertEquals(fields, compoundThrow.getFields());
    }

    @Test
    void getTheFieldsWithEmptyInput() {
        Collection<Field> fields = Collections.emptyList();
        Throw compoundThrow = CompoundThrow.of(fields);

        Assertions.assertEquals(fields, compoundThrow.getFields());
    }

    @Test
    void getImmutableFields() {
        Collection<Field> fields = TypedFieldTestUtils.getFields("D14");
        Throw compoundThrow = CompoundThrow.of(fields);

        List<Field> storedFields = compoundThrow.getFields();

        Assertions.assertThrows(UnsupportedOperationException.class, () -> storedFields.remove(0));
    }

    @Test
    void getEqualThrows() {
        Collection<Field> fields1 = TypedFieldTestUtils.getFields("T6", "D9");
        Throw compoundThrow1 = CompoundThrow.of(fields1);

        Collection<Field> fields2 = TypedFieldTestUtils.getFields("T6", "D9");
        Throw compoundThrow2 = CompoundThrow.of(fields2);

        Assertions.assertAll(
                () -> Assertions.assertEquals(compoundThrow1, compoundThrow2),
                () -> Assertions.assertEquals(compoundThrow1.hashCode(), compoundThrow2.hashCode())
        );
    }

    @Test
    void getUnequalThrows() {
        Collection<Field> fields1 = TypedFieldTestUtils.getFields("25");
        Throw compoundThrow1 = CompoundThrow.of(fields1);

        Collection<Field> fields2 = TypedFieldTestUtils.getFields("D25");
        Throw compoundThrow2 = CompoundThrow.of(fields2);

        Assertions.assertNotEquals(compoundThrow1, compoundThrow2);
    }
}
