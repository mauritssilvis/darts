/*
 * Copyright © 2023 Maurits H. Silvis
 * SPDX-License-Identifier: GPL-3.0-or-later
 */

package nl.mauritssilvis.darts.checkouts.java.boards;

import nl.mauritssilvis.darts.checkouts.java.boards.fields.Field;
import nl.mauritssilvis.darts.checkouts.java.boards.fields.Type;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

class StandardBoardTests {
    @Test
    void getTheSingleFields() {
        Board board = new StandardBoard();
        Type type = Type.SINGLE;

        Assertions.assertEquals(21, board.getFields(type).size());
    }

    @Test
    void getTheDoubleFields() {
        Board board = new StandardBoard();
        Type type = Type.DOUBLE;

        Assertions.assertEquals(21, board.getFields(type).size());
    }

    @Test
    void getTheTripleFields() {
        Board board = new StandardBoard();
        Type type = Type.TRIPLE;

        Assertions.assertEquals(20, board.getFields(type).size());
    }

    @Test
    void getTheQuadrupleFields() {
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
