/*
 * Copyright © 2023 Maurits H. Silvis
 * SPDX-License-Identifier: GPL-3.0-or-later
 */

package nl.mauritssilvis.darts.java.boards.quadro;

import nl.mauritssilvis.darts.java.boards.Board;
import nl.mauritssilvis.darts.java.boards.Field;
import nl.mauritssilvis.darts.java.boards.FieldType;
import nl.mauritssilvis.darts.java.boards.common.TypedField;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

class QuadroBoardTests {
    @Test
    void getImmutableSingleFields() {
        Board board = QuadroBoard.create();
        FieldType fieldType = FieldType.SINGLE;

        List<Field> fields = board.getFields(fieldType);

        Assertions.assertThrows(UnsupportedOperationException.class, fields::clear);
    }

    @Test
    void getImmutableDoubleFields() {
        Board board = QuadroBoard.create();
        FieldType fieldType = FieldType.DOUBLE;

        List<Field> fields = board.getFields(fieldType);

        Assertions.assertThrows(UnsupportedOperationException.class, fields::clear);
    }

    @Test
    void getImmutableTripleFields() {
        Board board = QuadroBoard.create();
        FieldType fieldType = FieldType.TRIPLE;

        List<Field> fields = board.getFields(fieldType);

        Assertions.assertThrows(UnsupportedOperationException.class, fields::clear);
    }

    @Test
    void getImmutableQuadrupleFields() {
        Board board = QuadroBoard.create();
        FieldType fieldType = FieldType.QUADRUPLE;

        List<Field> fields = board.getFields(fieldType);
        Field field = TypedField.of(fieldType, 3);

        Assertions.assertThrows(UnsupportedOperationException.class, () -> fields.add(field));
    }

    @Test
    void countTheSingleFields() {
        Board board = QuadroBoard.create();
        FieldType fieldType = FieldType.SINGLE;

        Assertions.assertEquals(21, board.getFields(fieldType).size());
    }

    @Test
    void countTheDoubleFields() {
        Board board = QuadroBoard.create();
        FieldType fieldType = FieldType.DOUBLE;

        Assertions.assertEquals(21, board.getFields(fieldType).size());
    }

    @Test
    void countTheTripleFields() {
        Board board = QuadroBoard.create();
        FieldType fieldType = FieldType.TRIPLE;

        Assertions.assertEquals(20, board.getFields(fieldType).size());
    }

    @Test
    void countTheQuadrupleFields() {
        Board board = QuadroBoard.create();
        FieldType fieldType = FieldType.QUADRUPLE;

        Assertions.assertEquals(20, board.getFields(fieldType).size());
    }

    @Test
    void getOnlySingleFields() {
        Board board = QuadroBoard.create();
        FieldType fieldType = FieldType.SINGLE;

        List<Field> fields = board.getFields(fieldType);

        List<Field> otherFields = fields.stream()
                .filter(field -> field.getFieldType() != fieldType)
                .toList();

        Assertions.assertEquals(0, otherFields.size());
    }

    @Test
    void getOnlyDoubleFields() {
        Board board = QuadroBoard.create();
        FieldType fieldType = FieldType.DOUBLE;

        List<Field> fields = board.getFields(fieldType);

        List<Field> otherFields = fields.stream()
                .filter(field -> field.getFieldType() != fieldType)
                .toList();

        Assertions.assertEquals(0, otherFields.size());
    }

    @Test
    void getOnlyTripleFields() {
        Board board = QuadroBoard.create();
        FieldType fieldType = FieldType.TRIPLE;

        List<Field> fields = board.getFields(fieldType);

        List<Field> otherFields = fields.stream()
                .filter(field -> field.getFieldType() != fieldType)
                .toList();

        Assertions.assertEquals(0, otherFields.size());
    }

    @Test
    void getOnlyQuadrupleFields() {
        Board board = QuadroBoard.create();
        FieldType fieldType = FieldType.QUADRUPLE;

        List<Field> fields = board.getFields(fieldType);

        List<Field> otherFields = fields.stream()
                .filter(field -> field.getFieldType() != fieldType)
                .toList();

        Assertions.assertEquals(0, otherFields.size());
    }

    @Test
    void getTheMinimumSingleField() {
        Board board = QuadroBoard.create();
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
        Board board = QuadroBoard.create();
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
        Board board = QuadroBoard.create();
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
        Board board = QuadroBoard.create();
        FieldType fieldType = FieldType.QUADRUPLE;

        List<Field> fields = board.getFields(fieldType);

        int min = fields.stream()
                .mapToInt(Field::getScore)
                .min()
                .orElse(-1);

        Assertions.assertEquals(4, min);
    }

    @Test
    void getTheMaximumSingleField() {
        Board board = QuadroBoard.create();
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
        Board board = QuadroBoard.create();
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
        Board board = QuadroBoard.create();
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
        Board board = QuadroBoard.create();
        FieldType fieldType = FieldType.QUADRUPLE;

        List<Field> fields = board.getFields(fieldType);

        int max = fields.stream()
                .mapToInt(Field::getScore)
                .max()
                .orElse(-1);

        Assertions.assertEquals(80, max);
    }

    @Test
    void getTheSingleFieldSum() {
        Board board = QuadroBoard.create();
        FieldType fieldType = FieldType.SINGLE;

        List<Field> fields = board.getFields(fieldType);

        int sum = fields.stream()
                .mapToInt(Field::getScore)
                .sum();

        Assertions.assertEquals(235, sum);
    }

    @Test
    void getTheDoubleFieldSum() {
        Board board = QuadroBoard.create();
        FieldType fieldType = FieldType.DOUBLE;

        List<Field> fields = board.getFields(fieldType);

        int sum = fields.stream()
                .mapToInt(Field::getScore)
                .sum();

        Assertions.assertEquals(470, sum);
    }

    @Test
    void getTheTripleFieldSum() {
        Board board = QuadroBoard.create();
        FieldType fieldType = FieldType.TRIPLE;

        List<Field> fields = board.getFields(fieldType);

        int sum = fields.stream()
                .mapToInt(Field::getScore)
                .sum();

        Assertions.assertEquals(630, sum);
    }

    @Test
    void getTheQuadrupleFieldSum() {
        Board board = QuadroBoard.create();
        FieldType fieldType = FieldType.QUADRUPLE;

        List<Field> fields = board.getFields(fieldType);

        int sum = fields.stream()
                .mapToInt(Field::getScore)
                .sum();

        Assertions.assertEquals(840, sum);
    }
}
