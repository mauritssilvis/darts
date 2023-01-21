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
    private TypedField(int base, Type type) {

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
        return null;
    }

    @Override
    public int getValue() {
        return 0;
    }

    @Override
    public Type getType() {
        return null;
    }
}
