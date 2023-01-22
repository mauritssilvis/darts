/*
 * Copyright © 2023 Maurits H. Silvis
 * SPDX-License-Identifier: GPL-3.0-or-later
 */

package nl.mauritssilvis.darts.checkouts.java.checkouts.common;

import nl.mauritssilvis.darts.checkouts.java.boards.Field;
import nl.mauritssilvis.darts.checkouts.java.boards.FieldType;
import nl.mauritssilvis.darts.checkouts.java.boards.standard.TypedField;
import nl.mauritssilvis.darts.checkouts.java.checkouts.Throw;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

class BasicThrowTests {
    @Test
    void doNotAcceptFieldsWithDifferentScores() {
        Collection<Field> fields = List.of(
                TypedField.of(FieldType.SINGLE, 8),
                TypedField.of(FieldType.TRIPLE, 3)
        );

        Assertions.assertThrows(
                IllegalArgumentException.class,
                () -> BasicThrow.of(fields)
        );
    }

    @Test
    void getTheScore() {
        Collection<Field> fields = List.of(
                TypedField.of(FieldType.SINGLE, 10),
                TypedField.of(FieldType.DOUBLE, 5)
        );

        Throw basicThrow = BasicThrow.of(fields);

        Assertions.assertEquals(10, basicThrow.getScore());
    }

    @Test
    void getTheScoreWithSingletonInput() {
        Collection<Field> fields = Collections.singletonList(
                TypedField.of(FieldType.DOUBLE, 10)
        );

        Throw singleThrow = BasicThrow.of(fields);

        Assertions.assertEquals(20, singleThrow.getScore());
    }

    @Test
    void getTheScoreWithEmptyInput() {
        Collection<Field> fields = Collections.emptyList();
        Throw emptyThrow = BasicThrow.of(fields);

        Assertions.assertEquals(0, emptyThrow.getScore());
    }

    @Test
    void countTheFields() {
        Collection<Field> fields = List.of(
                TypedField.of(FieldType.TRIPLE, 2),
                TypedField.of(FieldType.DOUBLE, 3),
                TypedField.of(FieldType.SINGLE, 6)
        );

        Throw basicThrow = BasicThrow.of(fields);

        Assertions.assertEquals(3, basicThrow.countFields());
    }

    @Test
    void countTheFieldsWithSingletonInput() {
        Collection<Field> fields = Collections.singletonList(
                TypedField.of(FieldType.TRIPLE, 20)
        );

        Throw basicThrow = BasicThrow.of(fields);

        Assertions.assertEquals(1, basicThrow.countFields());
    }

    @Test
    void countTheFieldsWithEmptyInput() {
        Collection<Field> fields = Collections.emptyList();

        Throw basicThrow = BasicThrow.of(fields);

        Assertions.assertEquals(0, basicThrow.countFields());
    }

    @Test
    void getTheFields() {
        Collection<Field> fields = List.of(
                TypedField.of(FieldType.QUADRUPLE, 4),
                TypedField.of(FieldType.DOUBLE, 8),
                TypedField.of(FieldType.SINGLE, 16)
        );

        Throw basicThrow = BasicThrow.of(fields);

        Assertions.assertEquals(fields, basicThrow.getFields());
    }

    @Test
    void getTheFieldsWithSingletonInput() {
        Collection<Field> fields = Collections.singletonList(
                TypedField.of(FieldType.TRIPLE, 15)
        );

        Throw basicThrow = BasicThrow.of(fields);

        Assertions.assertEquals(fields, basicThrow.getFields());
    }

    @Test
    void getTheFieldsWithEmptyInput() {
        Collection<Field> fields = Collections.emptyList();

        Throw basicThrow = BasicThrow.of(fields);

        Assertions.assertEquals(fields, basicThrow.getFields());
    }

    @Test
    void storeIndependentFields() {
        List<Field> fields = new ArrayList<>(List.of(
                TypedField.of(FieldType.SINGLE, 14),
                TypedField.of(FieldType.DOUBLE, 7)
        ));

        Throw basicThrow = BasicThrow.of(fields);

        fields.remove(1);

        Assertions.assertNotEquals(fields, basicThrow.getFields());
    }

    @Test
    void storeImmutableFields() {
        Collection<Field> fields = Collections.singletonList(
                TypedField.of(FieldType.SINGLE, 14)
        );

        Throw basicThrow = BasicThrow.of(fields);

        List<Field> storedFields = basicThrow.getFields();

        Assertions.assertThrows(
                UnsupportedOperationException.class,
                () -> storedFields.remove(0)
        );
    }
}
