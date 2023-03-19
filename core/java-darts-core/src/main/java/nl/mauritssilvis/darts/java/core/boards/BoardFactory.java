/*
 * Copyright © 2023 Maurits Silvis
 * SPDX-License-Identifier: GPL-3.0-or-later
 */

package nl.mauritssilvis.darts.java.core.boards;

import nl.mauritssilvis.darts.java.api.boards.Board;
import nl.mauritssilvis.darts.java.api.settings.BoardType;

/**
 * A dartboard factory.
 * <p>
 * Relevant design patterns: helper, parameterized static factory method.
 *
 * @author Maurits Silvis
 * @since 0.5.0
 */
public final class BoardFactory {
    private BoardFactory() {
    }

    /**
     * Returns a new {@code Board} of the specified type.
     *
     * @param boardType the dartboard type
     * @return a new {@code Board} of the specified type
     */
    public static Board create(BoardType boardType) {
        return switch (boardType) {
            case LONDON -> LondonBoard.create();
            case QUADRO -> QuadroBoard.create();
            case YORKSHIRE -> YorkshireBoard.create();
        };
    }
}
