/*
 * Copyright © 2023 Maurits Silvis
 * SPDX-License-Identifier: GPL-3.0-or-later
 */

package nl.mauritssilvis.darts.java.core.boards;

import lombok.EqualsAndHashCode;
import lombok.ToString;
import nl.mauritssilvis.darts.java.api.boards.Field;
import nl.mauritssilvis.darts.java.api.boards.FieldType;

/**
 * An implementation of the {@code Field} interface that can represent fields of
 * all predefined types.
 * <p>
 * Relevant design patterns: immutable object, simple factory.
 *
 * @author Maurits Silvis
 * @since 0.5.0
 */
@EqualsAndHashCode
@ToString
final class TypedField implements Field {
    private final FieldType fieldType;
    private final String name;
    private final int score;

    private TypedField(FieldType fieldType, int baseScore) {
        this.fieldType = fieldType;

        name = getShorthand(fieldType) + baseScore;
        score = getMultiplier(fieldType) * baseScore;
    }

    /**
     * Returns a new {@code TypedField} with the specified field type and base
     * score.
     *
     * @param fieldType the field type
     * @param baseScore the base score
     * @return a new {@code TypedField} with the specified field type and base
     * score
     */
    public static Field of(FieldType fieldType, int baseScore) {
        return new TypedField(fieldType, baseScore);
    }

    @Override
    public FieldType getFieldType() {
        return fieldType;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getScore() {
        return score;
    }

    private static String getShorthand(FieldType fieldType) {
        return switch (fieldType) {
            case SINGLE -> "";
            case DOUBLE -> "D";
            case TRIPLE -> "T";
            case QUADRUPLE -> "Q";
        };
    }

    private static int getMultiplier(FieldType fieldType) {
        return switch (fieldType) {
            case SINGLE -> 1;
            case DOUBLE -> 2;
            case TRIPLE -> 3;
            case QUADRUPLE -> 4;
        };
    }
}
