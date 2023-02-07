/*
 * Copyright © 2023 Maurits H. Silvis
 * SPDX-License-Identifier: GPL-3.0-or-later
 */

package nl.mauritssilvis.darts.java.boards.output;

import nl.mauritssilvis.darts.java.boards.Board;
import nl.mauritssilvis.darts.java.boards.factory.BoardFactory;
import nl.mauritssilvis.darts.java.output.Serializer;
import nl.mauritssilvis.darts.java.settings.BoardType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

class MarkdownBoardSerializerTests {
    @Test
    void getAMarkdownBoard() {
        BoardType boardType = BoardType.QUADRO;
        Board board = BoardFactory.create(boardType);
        Serializer<Board> serializer = MarkdownBoardSerializer.create();

        String output = serializer.serialize(board);

        Assertions.assertEquals(
                """
                        |   S |   D |   T |   Q |
                        |----:|----:|----:|----:|
                        |   1 |  D1 |  T1 |  Q1 |
                        |   2 |  D2 |  T2 |  Q2 |
                        |   3 |  D3 |  T3 |  Q3 |
                        |   4 |  D4 |  T4 |  Q4 |
                        |   5 |  D5 |  T5 |  Q5 |
                        |   6 |  D6 |  T6 |  Q6 |
                        |   7 |  D7 |  T7 |  Q7 |
                        |   8 |  D8 |  T8 |  Q8 |
                        |   9 |  D9 |  T9 |  Q9 |
                        |  10 | D10 | T10 | Q10 |
                        |  11 | D11 | T11 | Q11 |
                        |  12 | D12 | T12 | Q12 |
                        |  13 | D13 | T13 | Q13 |
                        |  14 | D14 | T14 | Q14 |
                        |  15 | D15 | T15 | Q15 |
                        |  16 | D16 | T16 | Q16 |
                        |  17 | D17 | T17 | Q17 |
                        |  18 | D18 | T18 | Q18 |
                        |  19 | D19 | T19 | Q19 |
                        |  20 | D20 | T20 | Q20 |
                        |  25 | D25 |   - |   - |
                        """,
                output);
    }

    @ParameterizedTest
    @EnumSource(BoardType.class)
    void getTheTableStart(BoardType boardType) {
        Board board = BoardFactory.create(boardType);
        Serializer<Board> serializer = MarkdownBoardSerializer.create();

        String output = serializer.serialize(board);

        Assertions.assertTrue(output.startsWith("|   "));
    }
}
