/*
 * Copyright © 2023 Maurits H. Silvis
 * SPDX-License-Identifier: GPL-3.0-or-later
 */

package nl.mauritssilvis.darts.java.paths.descending;

import lombok.EqualsAndHashCode;
import nl.mauritssilvis.darts.java.paths.Path;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * An implementation of the {@code Path} interface that represents multiple
 * sequences of integer steps, namely, one for each permutation of the grouped
 * elements.
 * <p>
 * Relevant design patterns: immutable object, static factory method.
 */
@EqualsAndHashCode
public final class GroupedPath implements Path {
    private final List<Integer> steps;
    private final List<Boolean> grouping;

    private GroupedPath(Collection<Integer> steps, Collection<Boolean> grouping) {
        this.steps = List.copyOf(steps);
        this.grouping = processGrouping(grouping, steps.size());
    }

    /**
     * Returns a new {@code GroupedPath} with the specified steps and grouping
     * signature.
     *
     * @param steps    a collection of steps
     * @param grouping a collection of booleans representing the grouping
     *                 signature
     * @return a new {@code GroupedPath} with the specified steps and grouping
     * signature
     */
    public static Path of(Collection<Integer> steps, Collection<Boolean> grouping) {
        return new GroupedPath(steps, grouping);
    }

    @Override
    public int getLength() {
        return steps.stream()
                .mapToInt(Integer::intValue)
                .sum();
    }

    @Override
    public List<Integer> getSteps() {
        return steps;
    }

    @Override
    public List<Boolean> getGrouping() {
        return grouping;
    }

    @Override
    public long getMultiplicity() {
        if (steps.isEmpty()) {
            return 0;
        } else if (steps.size() == 1) {
            return 1;
        }

        List<List<Integer>> groups = getGroups();

        return groups.stream()
                .mapToLong(GroupedPath::countPermutations)
                .reduce(1, (prod, e) -> prod * e);
    }

    private static List<Boolean> processGrouping(Collection<Boolean> input, int size) {
        if (size == 0) {
            return Collections.emptyList();
        } else if (size == 1) {
            return List.of(false);
        }

        List<Boolean> output = new ArrayList<>();
        output.add(false);

        input.stream()
                .limit(size)
                .skip(1)
                .forEach(output::add);

        IntStream.range(input.size(), size)
                .forEach(i -> output.add(false));

        return Collections.unmodifiableList(output);
    }

    private List<List<Integer>> getGroups() {
        List<List<Integer>> groups = new ArrayList<>();

        List<Integer> values = new ArrayList<>();

        for (int i = 0; i < steps.size(); i++) {
            if (i > 0 && Boolean.FALSE.equals(grouping.get(i))) {
                groups.add(values);
                values = new ArrayList<>();
            }

            values.add(steps.get(i));
        }

        groups.add(values);

        return groups;
    }

    private static long countPermutations(Collection<Integer> group) {
        if (group.size() == 1) {
            return 1;
        }

        Map<Integer, Long> frequencies = getFrequencies(group);

        long denominator = frequencies.values().stream()
                .mapToLong(GroupedPath::factorial)
                .reduce(1, (prod, e) -> prod * e);

        long numerator = factorial(group.size());

        return numerator / denominator;
    }

    private static Map<Integer, Long> getFrequencies(Collection<Integer> group) {
        return group.stream()
                .collect(
                        Collectors.groupingBy(
                                Function.identity(),
                                HashMap::new,
                                Collectors.counting()
                        )
                );
    }

    private static long factorial(long in) {
        long out = 1;

        for (int i = 2; i <= in; i++) {
            out *= i;
        }

        return out;
    }
}
