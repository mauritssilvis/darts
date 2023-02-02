/*
 * Copyright © 2023 Maurits H. Silvis
 * SPDX-License-Identifier: GPL-3.0-or-later
 */

package nl.mauritssilvis.darts.java.boards.factory;

import nl.mauritssilvis.darts.java.boards.Board;
import nl.mauritssilvis.darts.java.boards.london.LondonBoard;
import nl.mauritssilvis.darts.java.boards.quadro.QuadroBoard;
import nl.mauritssilvis.darts.java.settings.BoardType;
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
}
