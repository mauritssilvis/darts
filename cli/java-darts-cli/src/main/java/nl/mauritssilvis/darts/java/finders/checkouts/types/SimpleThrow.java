/*
 * Copyright © 2023 Maurits Silvis
 * SPDX-License-Identifier: GPL-3.0-or-later
 */

package nl.mauritssilvis.darts.java.finders.checkouts.types;

import lombok.EqualsAndHashCode;
import lombok.ToString;
import nl.mauritssilvis.darts.java.boards.Field;
import nl.mauritssilvis.darts.java.finders.checkouts.Throw;

import java.util.Collections;
import java.util.List;

/**
 * An implementation of the {@code Throw} interface that stores a single field.
 * <p>
 * Relevant design patterns: immutable object, static factory method.
 *
 * @author Maurits Silvis
 * @since 0.1.0
 */
@EqualsAndHashCode
@ToString
final class SimpleThrow implements Throw {
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
    public List<Field> getFields() {
        return Collections.singletonList(field);
    }
}
