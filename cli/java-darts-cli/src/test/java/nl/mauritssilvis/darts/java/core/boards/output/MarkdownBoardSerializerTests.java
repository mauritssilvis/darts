/*
 * Copyright © 2023 Maurits Silvis
 * SPDX-License-Identifier: GPL-3.0-or-later
 */

package nl.mauritssilvis.darts.java.core.boards.output;

import nl.mauritssilvis.darts.java.api.boards.Board;
import nl.mauritssilvis.darts.java.api.output.Serializer;
import nl.mauritssilvis.darts.java.api.settings.BoardType;
import nl.mauritssilvis.darts.java.core.boards.BoardFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

class MarkdownBoardSerializerTests {
    @Test
    void getAMarkdownBoard() {
        BoardType boardType = BoardType.LONDON;
        Board board = BoardFactory.create(boardType);
        Serializer<Board> serializer = MarkdownBoardSerializer.create();

        String output = serializer.serialize(board);

        Assertions.assertEquals(
                """
                        |   S |   D |   T |
                        |----:|----:|----:|
                        |   1 |  D1 |  T1 |
                        |   2 |  D2 |  T2 |
                        |   3 |  D3 |  T3 |
                        |   4 |  D4 |  T4 |
                        |   5 |  D5 |  T5 |
                        |   6 |  D6 |  T6 |
                        |   7 |  D7 |  T7 |
                        |   8 |  D8 |  T8 |
                        |   9 |  D9 |  T9 |
                        |  10 | D10 | T10 |
                        |  11 | D11 | T11 |
                        |  12 | D12 | T12 |
                        |  13 | D13 | T13 |
                        |  14 | D14 | T14 |
                        |  15 | D15 | T15 |
                        |  16 | D16 | T16 |
                        |  17 | D17 | T17 |
                        |  18 | D18 | T18 |
                        |  19 | D19 | T19 |
                        |  20 | D20 | T20 |
                        |  25 | D25 |   - |
                        """,
                output);
    }

    @ParameterizedTest
    @EnumSource(BoardType.class)
    void getTheBoardStart(BoardType boardType) {
        Board board = BoardFactory.create(boardType);
        Serializer<Board> serializer = MarkdownBoardSerializer.create();

        String output = serializer.serialize(board);

        Assertions.assertTrue(output.startsWith("|   "));
    }
}
