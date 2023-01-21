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
    private final int base;
    private final Type type;

    private TypedField(int base, Type type) {
        this.base = base;
        this.type = type;
    }

    /**
     * Returns a new {@code TypedField} with the supplied base value and type.
     *
     * @param base the integer base value
     * @param type the field type
     * @return a new {@code TypedField} with the given base value and type
     */
    public static Field of(int base, Type type) {
        return new TypedField(base, type);
    }

    @Override
    public String getName() {
        return type.getShorthand() + base;
    }

    @Override
    public int getValue() {
        return base * type.getMultiplier();
    }

    @Override
    public Type getType() {
        return type;
    }
}
