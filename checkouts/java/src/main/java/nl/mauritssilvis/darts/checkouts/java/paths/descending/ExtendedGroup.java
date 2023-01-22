/*
 * Copyright © 2023 Maurits H. Silvis
 * SPDX-License-Identifier: GPL-3.0-or-later
 */

package nl.mauritssilvis.darts.checkouts.java.paths.descending;

import nl.mauritssilvis.darts.checkouts.java.paths.Group;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * An implementation of the {@code Group} interface that represents an ordered
 * group of integer values.
 * <p>
 * Relevant design patterns: Immutable object, static factory method.
 */
public final class ExtendedGroup implements Group {
    private final List<Integer> values;

    private ExtendedGroup(Collection<Integer> values) {
        this.values = List.copyOf(values);
    }

    /**
     * Returns a new {@code ExtendedGroup} with the supplied integer values.
     *
     * @param values a collection of integer values
     * @return a new {@code ExtendedGroup} with the given values
     */
    public static Group of(Collection<Integer> values) {
        return new ExtendedGroup(values);
    }

    @Override
    public List<Integer> getValues() {
        return values;
    }

    @Override
    public long countPermutations() {
        if (values.isEmpty()) {
            return 0;
        } else if (values.size() == 1) {
            return 1;
        }

        Map<Integer, Long> frequencies = getFrequencies();

        long denominator = frequencies.values().stream()
                .mapToLong(ExtendedGroup::factorial)
                .reduce(1, (p, e) -> p * e);

        long numerator = factorial(values.size());

        return numerator / denominator;
    }

    private static long factorial(long in) {
        long out = 1;

        for (int i = 2; i <= in; i++) {
            out *= i;
        }

        return out;
    }

    private Map<Integer, Long> getFrequencies() {
        return values.stream()
                .collect(
                        Collectors.groupingBy(
                                Function.identity(),
                                HashMap::new,
                                Collectors.counting()
                        )
                );
    }
}
