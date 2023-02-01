/*
 * Copyright © 2023 Maurits H. Silvis
 * SPDX-License-Identifier: GPL-3.0-or-later
 */

package nl.mauritssilvis.darts.java.checkouts.descending;

import nl.mauritssilvis.darts.java.boards.Field;
import nl.mauritssilvis.darts.java.checkouts.Throw;

import java.util.Collection;
import java.util.List;

/**
 * An implementation of the {@code Throw} interface that can store multiple,
 * unique fields of a certain score.
 * <p>
 * Relevant design patterns: immutable object, static factory method.
 */
public final class CompoundThrow implements Throw {
    private final int score;
    private final List<Field> fields;

    private CompoundThrow(Collection<Field> fields) {
        score = fields.isEmpty() ? 0 : fields.iterator().next().getScore();

        boolean hasOtherScore = fields.stream()
                .anyMatch(field -> field.getScore() != score);

        if (hasOtherScore) {
            throw new IllegalArgumentException(
                    "All fields should have the same score"
            );
        }

        this.fields = fields.stream()
                .distinct()
                .toList();
    }

    /**
     * Returns a new {@code CompoundThrow} with unique fields of a certain
     * score.
     *
     * @param fields a collection of fields
     * @return a new {@code CompoundThrow} with the specified fields
     */
    public static Throw of(Collection<Field> fields) {
        return new CompoundThrow(fields);
    }

    @Override
    public int getScore() {
        return score;
    }

    @Override
    public List<Field> getFields() {
        return fields;
    }
}
