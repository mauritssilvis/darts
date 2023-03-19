/*
 * Copyright © 2023 Maurits Silvis
 * SPDX-License-Identifier: GPL-3.0-or-later
 */

package nl.mauritssilvis.darts.java.core.boards;

import nl.mauritssilvis.darts.java.api.boards.Board;
import nl.mauritssilvis.darts.java.api.settings.BoardType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class BoardFactoryTests {
    @Test
    void getALondonBoard() {
        BoardType boardType = BoardType.LONDON;
        Board board = BoardFactory.create(boardType);

        Assertions.assertTrue(board instanceof LondonBoard);
    }

    @Test
    void getAQuadroBoard() {
        BoardType boardType = BoardType.QUADRO;
        Board board = BoardFactory.create(boardType);

        Assertions.assertTrue(board instanceof QuadroBoard);
    }

    @Test
    void getAYorkshireBoard() {
        BoardType boardType = BoardType.YORKSHIRE;
        Board board = BoardFactory.create(boardType);

        Assertions.assertTrue(board instanceof YorkshireBoard);
    }
}
