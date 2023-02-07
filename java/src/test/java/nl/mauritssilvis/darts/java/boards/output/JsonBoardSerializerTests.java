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

class JsonBoardSerializerTests {
    @Test
    void getAJsonBoard() {
        BoardType boardType = BoardType.YORKSHIRE;
        Board board = BoardFactory.create(boardType);
        Serializer<Board> serializer = JsonBoardSerializer.create();

        String output = serializer.serialize(board);

        Assertions.assertEquals(
                """
                        {
                            "singles": [
                                "1"
                                "2",
                                "3",
                                "4",
                                "5",
                                "6",
                                "7",
                                "8",
                                "9",
                                "10",
                                "11",
                                "12",
                                "13",
                                "14",
                                "15",
                                "16",
                                "17",
                                "18",
                                "19",
                                "20"
                            ],
                            "doubles": [
                                "D1",
                                "D2",
                                "D3",
                                "D4",
                                "D5",
                                "D6",
                                "D7",
                                "D8",
                                "D9",
                                "D10",
                                "D11",
                                "D12",
                                "D13",
                                "D14",
                                "D15",
                                "D16",
                                "D17",
                                "D18",
                                "D19",
                                "D20",
                                "D25"
                            ]
                        }
                        """,
                output);
    }

    @ParameterizedTest
    @EnumSource(BoardType.class)
    void getTheBoardStart(BoardType boardType) {
        Board board = BoardFactory.create(boardType);
        Serializer<Board> serializer = JsonBoardSerializer.create();

        String output = serializer.serialize(board);

        Assertions.assertTrue(output.startsWith("{\n    \""));
    }
}
