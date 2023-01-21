/*
 * Copyright © 2023 Maurits H. Silvis
 * SPDX-License-Identifier: GPL-3.0-or-later
 */

package nl.mauritssilvis.darts.checkouts.java.boards;

import nl.mauritssilvis.darts.checkouts.java.boards.fields.Field;
import nl.mauritssilvis.darts.checkouts.java.boards.fields.Type;
import nl.mauritssilvis.darts.checkouts.java.boards.fields.TypedField;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

class StandardBoardTests {
    @Test
    void getImmutableSingleFields() {
        Board board = new StandardBoard();
        Type type = Type.SINGLE;

        List<Field> fields = board.getFields(type);

        Assertions.assertThrows(UnsupportedOperationException.class, fields::clear);
    }

    @Test
    void getImmutableDoubleFields() {
        Board board = new StandardBoard();
        Type type = Type.DOUBLE;

        List<Field> fields = board.getFields(type);

        Assertions.assertThrows(UnsupportedOperationException.class, fields::clear);
    }

    @Test
    void getImmutableTripleFields() {
        Board board = new StandardBoard();
        Type type = Type.TRIPLE;

        List<Field> fields = board.getFields(type);

        Assertions.assertThrows(UnsupportedOperationException.class, fields::clear);
    }

    @Test
    void getImmutableQuadrupleFields() {
        Board board = new StandardBoard();
        Type type = Type.QUADRUPLE;

        List<Field> fields = board.getFields(type);
        Field field = TypedField.of(type, 3);

        Assertions.assertThrows(UnsupportedOperationException.class, () -> fields.add(field));
    }

    @Test
    void countTheSingleFields() {
        Board board = new StandardBoard();
        Type type = Type.SINGLE;

        Assertions.assertEquals(21, board.getFields(type).size());
    }

    @Test
    void countTheDoubleFields() {
        Board board = new StandardBoard();
        Type type = Type.DOUBLE;

        Assertions.assertEquals(21, board.getFields(type).size());
    }

    @Test
    void countTheTripleFields() {
        Board board = new StandardBoard();
        Type type = Type.TRIPLE;

        Assertions.assertEquals(20, board.getFields(type).size());
    }

    @Test
    void countTheQuadrupleFields() {
        Board board = new StandardBoard();
        Type type = Type.QUADRUPLE;

        Assertions.assertEquals(0, board.getFields(type).size());
    }

    @Test
    void getOnlySingleFields() {
        Board board = new StandardBoard();
        Type type = Type.SINGLE;

        List<Field> fields = board.getFields(type);

        List<Field> otherFields = fields.stream()
                .filter(field -> field.getType() != type)
                .toList();

        Assertions.assertEquals(0, otherFields.size());
    }

    @Test
    void getOnlyDoubleFields() {
        Board board = new StandardBoard();
        Type type = Type.DOUBLE;

        List<Field> fields = board.getFields(type);

        List<Field> otherFields = fields.stream()
                .filter(field -> field.getType() != type)
                .toList();

        Assertions.assertEquals(0, otherFields.size());
    }

    @Test
    void getOnlyTripleFields() {
        Board board = new StandardBoard();
        Type type = Type.TRIPLE;

        List<Field> fields = board.getFields(type);

        List<Field> otherFields = fields.stream()
                .filter(field -> field.getType() != type)
                .toList();

        Assertions.assertEquals(0, otherFields.size());
    }

    @Test
    void getOnlyQuadrupleFields() {
        Board board = new StandardBoard();
        Type type = Type.QUADRUPLE;

        List<Field> fields = board.getFields(type);

        List<Field> otherFields = fields.stream()
                .filter(field -> field.getType() != type)
                .toList();

        Assertions.assertEquals(0, otherFields.size());
    }

    @Test
    void getTheMinimumSingleField() {
        Board board = new StandardBoard();
        Type type = Type.SINGLE;

        List<Field> fields = board.getFields(type);

        int min = fields.stream()
                .mapToInt(Field::getValue)
                .min()
                .orElse(-1);

        Assertions.assertEquals(1, min);
    }

    @Test
    void getTheMinimumDoubleField() {
        Board board = new StandardBoard();
        Type type = Type.DOUBLE;

        List<Field> fields = board.getFields(type);

        int min = fields.stream()
                .mapToInt(Field::getValue)
                .min()
                .orElse(-1);

        Assertions.assertEquals(2, min);
    }

    @Test
    void getTheMinimumTripleField() {
        Board board = new StandardBoard();
        Type type = Type.TRIPLE;

        List<Field> fields = board.getFields(type);

        int min = fields.stream()
                .mapToInt(Field::getValue)
                .min()
                .orElse(-1);

        Assertions.assertEquals(3, min);
    }

    @Test
    void getTheMinimumQuadrupleField() {
        Board board = new StandardBoard();
        Type type = Type.QUADRUPLE;

        List<Field> fields = board.getFields(type);

        int min = fields.stream()
                .mapToInt(Field::getValue)
                .min()
                .orElse(-1);

        Assertions.assertEquals(-1, min);
    }

    @Test
    void getTheMaximumSingleField() {
        Board board = new StandardBoard();
        Type type = Type.SINGLE;

        List<Field> fields = board.getFields(type);

        int max = fields.stream()
                .mapToInt(Field::getValue)
                .max()
                .orElse(-1);

        Assertions.assertEquals(25, max);
    }

    @Test
    void getTheMaximumDoubleField() {
        Board board = new StandardBoard();
        Type type = Type.DOUBLE;

        List<Field> fields = board.getFields(type);

        int max = fields.stream()
                .mapToInt(Field::getValue)
                .max()
                .orElse(-1);

        Assertions.assertEquals(50, max);
    }

    @Test
    void getTheMaximumTripleField() {
        Board board = new StandardBoard();
        Type type = Type.TRIPLE;

        List<Field> fields = board.getFields(type);

        int max = fields.stream()
                .mapToInt(Field::getValue)
                .max()
                .orElse(-1);

        Assertions.assertEquals(60, max);
    }

    @Test
    void getTheMaximumQuadrupleField() {
        Board board = new StandardBoard();
        Type type = Type.QUADRUPLE;

        List<Field> fields = board.getFields(type);

        int max = fields.stream()
                .mapToInt(Field::getValue)
                .max()
                .orElse(-1);

        Assertions.assertEquals(-1, max);
    }

    @Test
    void getTheSingleFieldSum() {
        Board board = new StandardBoard();
        Type type = Type.SINGLE;

        List<Field> fields = board.getFields(type);

        int sum = fields.stream()
                .mapToInt(Field::getValue)
                .sum();

        Assertions.assertEquals(235, sum);
    }

    @Test
    void getTheDoubleFieldSum() {
        Board board = new StandardBoard();
        Type type = Type.DOUBLE;

        List<Field> fields = board.getFields(type);

        int sum = fields.stream()
                .mapToInt(Field::getValue)
                .sum();

        Assertions.assertEquals(470, sum);
    }

    @Test
    void getTheTripleFieldSum() {
        Board board = new StandardBoard();
        Type type = Type.TRIPLE;

        List<Field> fields = board.getFields(type);

        int sum = fields.stream()
                .mapToInt(Field::getValue)
                .sum();

        Assertions.assertEquals(630, sum);
    }

    @Test
    void getTheQuadrupleFieldSum() {
        Board board = new StandardBoard();
        Type type = Type.QUADRUPLE;

        List<Field> fields = board.getFields(type);

        int sum = fields.stream()
                .mapToInt(Field::getValue)
                .sum();

        Assertions.assertEquals(0, sum);
    }
}
