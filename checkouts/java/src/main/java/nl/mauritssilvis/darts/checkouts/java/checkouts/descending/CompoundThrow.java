/*
 * Copyright © 2023 Maurits H. Silvis
 * SPDX-License-Identifier: GPL-3.0-or-later
 */

package nl.mauritssilvis.darts.checkouts.java.checkouts.descending;

import nl.mauritssilvis.darts.checkouts.java.boards.Field;
import nl.mauritssilvis.darts.checkouts.java.checkouts.Throw;

import java.util.Collection;
import java.util.List;

/**
 * An implementation of the {@code Throw} interface that can store multiple
 * fields of a certain score.
 * <p>
 * Relevant design patterns: Immutable object, static factory method.
 */
public final class CompoundThrow implements Throw {
    private final int score;
    private final List<Field> fields;

    private CompoundThrow(Collection<Field> fields) {
        score = fields.isEmpty() ? 0 : fields.iterator().next().getScore();

        boolean otherScore = fields.stream()
                .anyMatch(field -> field.getScore() != score);

        if (otherScore) {
            throw new IllegalArgumentException(
                    "All fields should have the same score"
            );
        }

        this.fields = fields.stream()
                .toList();
    }

    /**
     * Returns a new {@code CompoundThrow} with the specified fields.
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
    public int countFields() {
        return fields.size();
    }

    @Override
    public List<Field> getFields() {
        return fields;
    }
}
