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
    private final Type type;
    private final String name;
    private final int value;

    private TypedField(Type type, int base) {
        this.type = type;

        name = type.getShorthand() + base;
        value = type.getMultiplier() * base;
    }

    /**
     * Returns a new {@code TypedField} with the supplied base value and type.
     *
     * @param type the field type
     * @param base the integer base value
     * @return a new {@code TypedField} with the given base value and type
     */
    public static Field of(Type type, int base) {
        return new TypedField(type, base);
    }

    @Override
    public Type getType() {
        return type;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getValue() {
        return value;
    }
}
