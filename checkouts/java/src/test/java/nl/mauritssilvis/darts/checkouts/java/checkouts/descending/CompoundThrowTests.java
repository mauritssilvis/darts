/*
 * Copyright © 2023 Maurits H. Silvis
 * SPDX-License-Identifier: GPL-3.0-or-later
 */

package nl.mauritssilvis.darts.checkouts.java.checkouts.descending;

import nl.mauritssilvis.darts.checkouts.java.boards.Field;
import nl.mauritssilvis.darts.checkouts.java.checkouts.Throw;
import nl.mauritssilvis.darts.checkouts.java.checkouts.common.TypedFieldTestUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

class CompoundThrowTests {
    @Test
    void doNotAcceptFieldsWithDifferentScores() {
        Collection<Field> fields = TypedFieldTestUtils.getFields(
                "8", "T3"
        );

        Assertions.assertThrows(
                IllegalArgumentException.class,
                () -> CompoundThrow.of(fields)
        );
    }

    @Test
    void getTheScore() {
        Collection<Field> fields = TypedFieldTestUtils.getFields(
                "10", "D5"
        );

        Throw basicThrow = CompoundThrow.of(fields);

        Assertions.assertEquals(10, basicThrow.getScore());
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
    void countTheFields() {
        Collection<Field> fields = TypedFieldTestUtils.getFields(
                "T2", "D3", "6"
        );

        Throw basicThrow = CompoundThrow.of(fields);

        Assertions.assertEquals(3, basicThrow.countFields());
    }

    @Test
    void countTheFieldsWithSingletonInput() {
        Collection<Field> fields = TypedFieldTestUtils.getFields("T20");
        Throw basicThrow = CompoundThrow.of(fields);

        Assertions.assertEquals(1, basicThrow.countFields());
    }

    @Test
    void countTheFieldsWithEmptyInput() {
        Collection<Field> fields = Collections.emptyList();
        Throw basicThrow = CompoundThrow.of(fields);

        Assertions.assertEquals(0, basicThrow.countFields());
    }

    @Test
    void getTheFields() {
        Collection<Field> fields = TypedFieldTestUtils.getFields(
                "Q4", "D8", "16"
        );

        Throw basicThrow = CompoundThrow.of(fields);

        Assertions.assertEquals(fields, basicThrow.getFields());
    }

    @Test
    void getTheFieldsWithSingletonInput() {
        Collection<Field> fields = TypedFieldTestUtils.getFields("T15");
        Throw basicThrow = CompoundThrow.of(fields);

        Assertions.assertEquals(fields, basicThrow.getFields());
    }

    @Test
    void getTheFieldsWithEmptyInput() {
        Collection<Field> fields = Collections.emptyList();
        Throw basicThrow = CompoundThrow.of(fields);

        Assertions.assertEquals(fields, basicThrow.getFields());
    }

    @Test
    void storeIndependentFields() {
        List<Field> fields = new ArrayList<>(
                TypedFieldTestUtils.getFields("14", "D7")
        );

        Throw basicThrow = CompoundThrow.of(fields);

        fields.remove(1);

        Assertions.assertNotEquals(fields, basicThrow.getFields());
    }

    @Test
    void storeImmutableFields() {
        Collection<Field> fields = TypedFieldTestUtils.getFields("D14");
        Throw basicThrow = CompoundThrow.of(fields);

        List<Field> storedFields = basicThrow.getFields();

        Assertions.assertThrows(
                UnsupportedOperationException.class,
                () -> storedFields.remove(0)
        );
    }
}
