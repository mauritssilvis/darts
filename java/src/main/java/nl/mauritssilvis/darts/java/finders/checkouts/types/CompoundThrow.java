/*
 * Copyright © 2023 Maurits Silvis
 * SPDX-License-Identifier: GPL-3.0-or-later
 */

package nl.mauritssilvis.darts.java.finders.checkouts.types;

import lombok.EqualsAndHashCode;
import lombok.ToString;
import nl.mauritssilvis.darts.java.boards.Field;
import nl.mauritssilvis.darts.java.finders.checkouts.Throw;

import java.util.Collection;
import java.util.List;

/**
 * An implementation of the {@code Throw} interface that can store multiple,
 * unique fields of a certain score.
 * <p>
 * Relevant design patterns: immutable object, static factory method.
 */
@EqualsAndHashCode
@ToString
final class CompoundThrow implements Throw {
    private final int score;
    private final List<Field> fields;

    private CompoundThrow(Collection<? extends Field> fields) {
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
                .map(Field.class::cast)
                .toList();
    }

    /**
     * Returns a new {@code CompoundThrow} with unique fields of a certain
     * score.
     *
     * @param fields a collection of fields
     * @return a new {@code CompoundThrow} with the specified fields
     * @throws IllegalArgumentException if the fields do not all have the same
     *                                  score
     */
    public static Throw of(Collection<? extends Field> fields) {
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
