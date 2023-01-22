/*
 * Copyright © 2023 Maurits H. Silvis
 * SPDX-License-Identifier: GPL-3.0-or-later
 */

package nl.mauritssilvis.darts.checkouts.java.checkouts.common;

import nl.mauritssilvis.darts.checkouts.java.boards.Field;
import nl.mauritssilvis.darts.checkouts.java.checkouts.Throw;

import java.util.Collection;
import java.util.List;

/**
 * An implementation of the {@code Throw} interface that stores fields of a
 * certain score.
 * <p>
 * Relevant design patterns: Immutable object, static factory method.
 */
public final class BasicThrow implements Throw {
    private BasicThrow(Collection<Field> fields) {
    }

    /**
     * Returns a new {@code BasicThrow} with the specified score and fields.
     * @param fields a collection of fields
     * @return a new {@code BasicThrow} with the specified score and fields
     */
    public static Throw of(Collection<Field> fields) {
        return new BasicThrow(fields);
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
