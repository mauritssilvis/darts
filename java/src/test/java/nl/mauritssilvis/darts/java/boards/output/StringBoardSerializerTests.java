/*
 * Copyright © 2023 Maurits Silvis
 * SPDX-License-Identifier: GPL-3.0-or-later
 */

package nl.mauritssilvis.darts.java.boards.output;

import nl.mauritssilvis.darts.java.boards.Board;
import nl.mauritssilvis.darts.java.boards.types.BoardFactory;
import nl.mauritssilvis.darts.java.output.Serializer;
import nl.mauritssilvis.darts.java.settings.BoardType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

class StringBoardSerializerTests {
    @ParameterizedTest
    @EnumSource(BoardType.class)
    void includeTheBoardName(BoardType boardType) {
        Board board = BoardFactory.create(boardType);
        Serializer<Board> serializer = StringBoardSerializer.create();

        String output = serializer.serialize(board);

        String boardName = getBoardName(boardType);

        Assertions.assertTrue(output.startsWith(boardName));
    }

    @ParameterizedTest
    @EnumSource(BoardType.class)
    void useTheStringRepresentation(BoardType boardType) {
        Board board = BoardFactory.create(boardType);
        Serializer<Board> serializer = StringBoardSerializer.create();

        String output = serializer.serialize(board);

        String str = board.toString();
        int index = str.indexOf('(');
        String start = str.substring(0, index + 1);

        Assertions.assertTrue(output.startsWith(start));
    }

    @ParameterizedTest
    @EnumSource(BoardType.class)
    void indentTheOutput(BoardType boardType) {
        Board board = BoardFactory.create(boardType);
        Serializer<Board> serializer = StringBoardSerializer.create();

        String output = serializer.serialize(board);

        String indentation = "(\n  ";

        Assertions.assertTrue(output.contains(indentation));
    }

    private static String getBoardName(BoardType boardType) {
        String fullName = boardType.toString();
        String shortName = fullName.split("\\.")[1];

        char first = shortName.charAt(0);
        String rest = shortName.substring(1);

        return first + rest.toLowerCase();
    }
}
