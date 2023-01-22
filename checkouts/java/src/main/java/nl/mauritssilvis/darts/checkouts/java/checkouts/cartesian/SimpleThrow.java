/*
 * Copyright © 2023 Maurits H. Silvis
 * SPDX-License-Identifier: GPL-3.0-or-later
 */

package nl.mauritssilvis.darts.checkouts.java.checkouts.cartesian;

import nl.mauritssilvis.darts.checkouts.java.boards.Field;
import nl.mauritssilvis.darts.checkouts.java.checkouts.Throw;

import java.util.List;

/**
 * An implementation of the {@code Throw} interface that stores a single field.
 * <p>
 * Relevant design patterns: Immutable object, static factory method.
 */
public final class SimpleThrow implements Throw {
    private SimpleThrow(Field field) {
    }

    /**
     * Returns a new {@code SimpleThrow} with the specified field.
     *
     * @param field a field
     * @return a new {@code SimpleThrow} with the specified field
     */
    public static Throw of(Field field) {
        return new SimpleThrow(field);
    }

    @Override
    public int getScore() {
        return -1;
    }

    @Override
    public int countFields() {
        return -1;
    }

    @Override
    public List<Field> getFields() {
        return null;
    }
}
