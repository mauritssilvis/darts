/*
 * Copyright © 2023 Maurits H. Silvis
 * SPDX-License-Identifier: GPL-3.0-or-later
 */

package nl.mauritssilvis.darts.java.boards.factory;

import nl.mauritssilvis.darts.java.boards.Board;
import nl.mauritssilvis.darts.java.boards.london.LondonBoard;
import nl.mauritssilvis.darts.java.settings.BoardType;

/**
 * A dartboard factory.
 * <p>
 * Relevant design patterns: parameterized static factory method.
 */
public final class BoardFactory {
    private BoardFactory() {
    }

    /**
     * Returns a new {@code Board} of the specified dartboard type.
     *
     * @param boardType the dartboard type
     * @return a new {@code Board} of the specified dartboard type
     */
    public static Board create(BoardType boardType) {
        return switch (boardType) {
            case LONDON -> LondonBoard.create();
        };
    }
}
