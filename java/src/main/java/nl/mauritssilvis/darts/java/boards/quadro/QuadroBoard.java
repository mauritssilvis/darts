/*
 * Copyright © 2023 Maurits H. Silvis
 * SPDX-License-Identifier: GPL-3.0-or-later
 */

package nl.mauritssilvis.darts.java.boards.quadro;

import nl.mauritssilvis.darts.java.boards.Board;
import nl.mauritssilvis.darts.java.boards.Field;
import nl.mauritssilvis.darts.java.boards.FieldType;

import java.util.List;

/**
 * An implementation of the {@code Board} interface that represents a Quadro
 * dartboard.
 * <p>
 * Relevant design patterns: immutable object, static factory method.
 */
public class QuadroBoard implements Board {
    private QuadroBoard() {
    }

    /**
     * Returns a new {@code QuadroBoard}.
     *
     * @return a new {@code QuadroBoard}
     */
    public static Board create() {
        return new QuadroBoard();
    }

    @Override
    public List<Field> getFields(FieldType fieldType) {
        return null;
    }
}
