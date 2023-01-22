/*
 * Copyright © 2023 Maurits H. Silvis
 * SPDX-License-Identifier: GPL-3.0-or-later
 */

package nl.mauritssilvis.darts.checkouts.java.checkouts.cartesian;

import lombok.EqualsAndHashCode;
import nl.mauritssilvis.darts.checkouts.java.boards.Field;
import nl.mauritssilvis.darts.checkouts.java.checkouts.Throw;

import java.util.Collections;
import java.util.List;

/**
 * An implementation of the {@code Throw} interface that stores a single field.
 * <p>
 * Relevant design patterns: Immutable object, static factory method.
 */
@EqualsAndHashCode
public final class SimpleThrow implements Throw {
    private final int score;
    private final Field field;

    private SimpleThrow(Field field) {
        score = field.getScore();
        this.field = field;
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
        return score;
    }

    @Override
    public int countFields() {
        return 1;
    }

    @Override
    public List<Field> getFields() {
        return Collections.singletonList(field);
    }
}
