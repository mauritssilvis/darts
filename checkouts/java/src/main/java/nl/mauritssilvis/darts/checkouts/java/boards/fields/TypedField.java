/*
 * Copyright © 2023 Maurits H. Silvis
 * SPDX-License-Identifier: GPL-3.0-or-later
 */

package nl.mauritssilvis.darts.checkouts.java.boards.fields;

/**
 * An implementation of the {@code Field} interface that can represent fields of
 * all predefined types.
 * <p>
 * Relevant design patterns: Immutable object, static factory method.
 */
public final class TypedField implements Field {
    private final FieldType fieldType;
    private final String name;
    private final int value;

    private TypedField(FieldType fieldType, int base) {
        this.fieldType = fieldType;

        name = getShorthand(fieldType) + base;
        value = getMultiplier(fieldType) * base;
    }

    /**
     * Returns a new {@code TypedField} with the supplied base value and type.
     *
     * @param fieldType the field type
     * @param base the integer base value
     * @return a new {@code TypedField} with the given base value and type
     */
    public static Field of(FieldType fieldType, int base) {
        return new TypedField(fieldType, base);
    }

    @Override
    public FieldType getType() {
        return fieldType;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getValue() {
        return value;
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
