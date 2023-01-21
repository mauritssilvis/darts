/*
 * Copyright © 2023 Maurits H. Silvis
 * SPDX-License-Identifier: GPL-3.0-or-later
 */

package nl.mauritssilvis.darts.checkouts.java.boards;

import nl.mauritssilvis.darts.checkouts.java.boards.fields.Field;
import nl.mauritssilvis.darts.checkouts.java.boards.fields.Type;
import nl.mauritssilvis.darts.checkouts.java.boards.fields.TypedField;

import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;

/**
 * An implementation of the {@code Board} interface that represents a standard
 * dartboard.
 * <p>
 * Relevant design patterns: Immutable object, static factory method.
 */
public final class StandardBoard implements Board {
    private static final int RANGE_MIN = 1;
    private static final int RANGE_MAX = 20;
    private static final int RANGE_EXTENSION = 25;

    private final List<Field> singleFields = getSingleFields();
    private final List<Field> doubleFields = getDoubleFields();
    private final List<Field> tripleFields = getTripleFields();
    private final List<Field> quadrupleFields = Collections.emptyList();

    private StandardBoard() {
    }

    /**
     * Return a new {@code StandardBoard}.
     *
     * @return a new {@code StandardBoard}
     */
    public static Board create() {
        return new StandardBoard();
    }

    @Override
    public List<Field> getFields(Type type) {
        return switch (type) {
            case SINGLE -> singleFields;
            case DOUBLE -> doubleFields;
            case TRIPLE -> tripleFields;
            case QUADRUPLE -> quadrupleFields;
        };
    }

    private static List<Field> getSingleFields() {
        return getExtendedRange()
                .mapToObj(i -> TypedField.of(Type.SINGLE, i))
                .toList();
    }

    private static List<Field> getDoubleFields() {
        return getExtendedRange()
                .mapToObj(i -> TypedField.of(Type.DOUBLE, i))
                .toList();
    }

    private static List<Field> getTripleFields() {
        return getBaseRange()
                .mapToObj(i -> TypedField.of(Type.TRIPLE, i))
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
