/*
 * Copyright © 2023 Maurits Silvis
 * SPDX-License-Identifier: GPL-3.0-or-later
 */

package nl.mauritssilvis.darts.java.core.boards;

import lombok.EqualsAndHashCode;
import lombok.ToString;
import nl.mauritssilvis.darts.java.api.boards.Board;
import nl.mauritssilvis.darts.java.api.boards.Field;
import nl.mauritssilvis.darts.java.api.boards.FieldType;

import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;

/**
 * An implementation of the {@code Board} interface that represents the
 * Yorkshire dartboard.
 * <p>
 * Relevant design patterns: immutable object, simple factory.
 *
 * @author Maurits Silvis
 * @since 0.5.0
 */
@EqualsAndHashCode
@ToString
final class YorkshireBoard implements Board {
    private static final int RANGE_MIN = 1;
    private static final int RANGE_MAX = 20;
    private static final int RANGE_EXTENSION = 25;

    private final List<Field> singleFields = getSingleFields();
    private final List<Field> doubleFields = getDoubleFields();
    private final List<Field> tripleFields = Collections.emptyList();
    private final List<Field> quadrupleFields = Collections.emptyList();

    private YorkshireBoard() {
    }

    /**
     * Returns a new {@code YorkshireBoard}.
     *
     * @return a new {@code YorkshireBoard}
     */
    public static Board create() {
        return new YorkshireBoard();
    }

    @Override
    public List<Field> getFields(FieldType fieldType) {
        return switch (fieldType) {
            case SINGLE -> singleFields;
            case DOUBLE -> doubleFields;
            case TRIPLE -> tripleFields;
            case QUADRUPLE -> quadrupleFields;
        };
    }

    private static List<Field> getSingleFields() {
        return getBaseRange()
                .mapToObj(i -> TypedField.of(FieldType.SINGLE, i))
                .toList();
    }

    private static List<Field> getDoubleFields() {
        return getExtendedRange()
                .mapToObj(i -> TypedField.of(FieldType.DOUBLE, i))
                .toList();
    }

    private static IntStream getBaseRange() {
        return IntStream.rangeClosed(RANGE_MIN, RANGE_MAX);
    }

    private static IntStream getExtendedRange() {
        return IntStream.concat(
                getBaseRange(),
                IntStream.of(RANGE_EXTENSION)
        );
    }
}
