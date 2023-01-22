/*
 * Copyright © 2023 Maurits H. Silvis
 * SPDX-License-Identifier: GPL-3.0-or-later
 */

package nl.mauritssilvis.darts.checkouts.java.boards.standard;

import nl.mauritssilvis.darts.checkouts.java.boards.Board;
import nl.mauritssilvis.darts.checkouts.java.boards.Field;
import nl.mauritssilvis.darts.checkouts.java.boards.FieldType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

class StandardBoardTests {
    @Test
    void getImmutableSingleFields() {
        Board board = StandardBoard.create();
        FieldType fieldType = FieldType.SINGLE;

        List<Field> fields = board.getFields(fieldType);

        Assertions.assertThrows(UnsupportedOperationException.class, fields::clear);
    }

    @Test
    void getImmutableDoubleFields() {
        Board board = StandardBoard.create();
        FieldType fieldType = FieldType.DOUBLE;

        List<Field> fields = board.getFields(fieldType);

        Assertions.assertThrows(UnsupportedOperationException.class, fields::clear);
    }

    @Test
    void getImmutableTripleFields() {
        Board board = StandardBoard.create();
        FieldType fieldType = FieldType.TRIPLE;

        List<Field> fields = board.getFields(fieldType);

        Assertions.assertThrows(UnsupportedOperationException.class, fields::clear);
    }

    @Test
    void getImmutableQuadrupleFields() {
        Board board = StandardBoard.create();
        FieldType fieldType = FieldType.QUADRUPLE;

        List<Field> fields = board.getFields(fieldType);
        Field field = TypedField.of(fieldType, 3);

        Assertions.assertThrows(UnsupportedOperationException.class, () -> fields.add(field));
    }

    @Test
    void countTheSingleFields() {
        Board board = StandardBoard.create();
        FieldType fieldType = FieldType.SINGLE;

        Assertions.assertEquals(21, board.getFields(fieldType).size());
    }

    @Test
    void countTheDoubleFields() {
        Board board = StandardBoard.create();
        FieldType fieldType = FieldType.DOUBLE;

        Assertions.assertEquals(21, board.getFields(fieldType).size());
    }

    @Test
    void countTheTripleFields() {
        Board board = StandardBoard.create();
        FieldType fieldType = FieldType.TRIPLE;

        Assertions.assertEquals(20, board.getFields(fieldType).size());
    }

    @Test
    void countTheQuadrupleFields() {
        Board board = StandardBoard.create();
        FieldType fieldType = FieldType.QUADRUPLE;

        Assertions.assertEquals(0, board.getFields(fieldType).size());
    }

    @Test
    void getOnlySingleFields() {
        Board board = StandardBoard.create();
        FieldType fieldType = FieldType.SINGLE;

        List<Field> fields = board.getFields(fieldType);

        List<Field> otherFields = fields.stream()
                .filter(field -> field.getType() != fieldType)
                .toList();

        Assertions.assertEquals(0, otherFields.size());
    }

    @Test
    void getOnlyDoubleFields() {
        Board board = StandardBoard.create();
        FieldType fieldType = FieldType.DOUBLE;

        List<Field> fields = board.getFields(fieldType);

        List<Field> otherFields = fields.stream()
                .filter(field -> field.getType() != fieldType)
                .toList();

        Assertions.assertEquals(0, otherFields.size());
    }

    @Test
    void getOnlyTripleFields() {
        Board board = StandardBoard.create();
        FieldType fieldType = FieldType.TRIPLE;

        List<Field> fields = board.getFields(fieldType);

        List<Field> otherFields = fields.stream()
                .filter(field -> field.getType() != fieldType)
                .toList();

        Assertions.assertEquals(0, otherFields.size());
    }

    @Test
    void getOnlyQuadrupleFields() {
        Board board = StandardBoard.create();
        FieldType fieldType = FieldType.QUADRUPLE;

        List<Field> fields = board.getFields(fieldType);

        List<Field> otherFields = fields.stream()
                .filter(field -> field.getType() != fieldType)
                .toList();

        Assertions.assertEquals(0, otherFields.size());
    }

    @Test
    void getTheMinimumSingleField() {
        Board board = StandardBoard.create();
        FieldType fieldType = FieldType.SINGLE;

        List<Field> fields = board.getFields(fieldType);

        int min = fields.stream()
                .mapToInt(Field::getScore)
                .min()
                .orElse(-1);

        Assertions.assertEquals(1, min);
    }

    @Test
    void getTheMinimumDoubleField() {
        Board board = StandardBoard.create();
        FieldType fieldType = FieldType.DOUBLE;

        List<Field> fields = board.getFields(fieldType);

        int min = fields.stream()
                .mapToInt(Field::getScore)
                .min()
                .orElse(-1);

        Assertions.assertEquals(2, min);
    }

    @Test
    void getTheMinimumTripleField() {
        Board board = StandardBoard.create();
        FieldType fieldType = FieldType.TRIPLE;

        List<Field> fields = board.getFields(fieldType);

        int min = fields.stream()
                .mapToInt(Field::getScore)
                .min()
                .orElse(-1);

        Assertions.assertEquals(3, min);
    }

    @Test
    void getTheMinimumQuadrupleField() {
        Board board = StandardBoard.create();
        FieldType fieldType = FieldType.QUADRUPLE;

        List<Field> fields = board.getFields(fieldType);

        int min = fields.stream()
                .mapToInt(Field::getScore)
                .min()
                .orElse(-1);

        Assertions.assertEquals(-1, min);
    }

    @Test
    void getTheMaximumSingleField() {
        Board board = StandardBoard.create();
        FieldType fieldType = FieldType.SINGLE;

        List<Field> fields = board.getFields(fieldType);

        int max = fields.stream()
                .mapToInt(Field::getScore)
                .max()
                .orElse(-1);

        Assertions.assertEquals(25, max);
    }

    @Test
    void getTheMaximumDoubleField() {
        Board board = StandardBoard.create();
        FieldType fieldType = FieldType.DOUBLE;

        List<Field> fields = board.getFields(fieldType);

        int max = fields.stream()
                .mapToInt(Field::getScore)
                .max()
                .orElse(-1);

        Assertions.assertEquals(50, max);
    }

    @Test
    void getTheMaximumTripleField() {
        Board board = StandardBoard.create();
        FieldType fieldType = FieldType.TRIPLE;

        List<Field> fields = board.getFields(fieldType);

        int max = fields.stream()
                .mapToInt(Field::getScore)
                .max()
                .orElse(-1);

        Assertions.assertEquals(60, max);
    }

    @Test
    void getTheMaximumQuadrupleField() {
        Board board = StandardBoard.create();
        FieldType fieldType = FieldType.QUADRUPLE;

        List<Field> fields = board.getFields(fieldType);

        int max = fields.stream()
                .mapToInt(Field::getScore)
                .max()
                .orElse(-1);

        Assertions.assertEquals(-1, max);
    }

    @Test
    void getTheSingleFieldSum() {
        Board board = StandardBoard.create();
        FieldType fieldType = FieldType.SINGLE;

        List<Field> fields = board.getFields(fieldType);

        int sum = fields.stream()
                .mapToInt(Field::getScore)
                .sum();

        Assertions.assertEquals(235, sum);
    }

    @Test
    void getTheDoubleFieldSum() {
        Board board = StandardBoard.create();
        FieldType fieldType = FieldType.DOUBLE;

        List<Field> fields = board.getFields(fieldType);

        int sum = fields.stream()
                .mapToInt(Field::getScore)
                .sum();

        Assertions.assertEquals(470, sum);
    }

    @Test
    void getTheTripleFieldSum() {
        Board board = StandardBoard.create();
        FieldType fieldType = FieldType.TRIPLE;

        List<Field> fields = board.getFields(fieldType);

        int sum = fields.stream()
                .mapToInt(Field::getScore)
                .sum();

        Assertions.assertEquals(630, sum);
    }

    @Test
    void getTheQuadrupleFieldSum() {
        Board board = StandardBoard.create();
        FieldType fieldType = FieldType.QUADRUPLE;

        List<Field> fields = board.getFields(fieldType);

        int sum = fields.stream()
                .mapToInt(Field::getScore)
                .sum();

        Assertions.assertEquals(0, sum);
    }
}
