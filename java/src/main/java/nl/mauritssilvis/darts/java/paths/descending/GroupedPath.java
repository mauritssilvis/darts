/*
 * Copyright © 2023 Maurits H. Silvis
 * SPDX-License-Identifier: GPL-3.0-or-later
 */

package nl.mauritssilvis.darts.java.paths.descending;

import nl.mauritssilvis.darts.java.paths.Group;
import nl.mauritssilvis.darts.java.paths.Path;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.IntStream;

/**
 * An implementation of the {@code Path} interface that represents multiple
 * sequences of integer steps, namely, one for each permutation of the grouped
 * elements.
 * <p>
 * Relevant design patterns: Immutable object, static factory method.
 */
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
    public int countGroups() {
        return (int) grouping.stream()
                .filter(Predicate.isEqual(false))
                .count();
    }

    @Override
    public List<Group> getGroups() {
        if (steps.isEmpty()) {
            return Collections.emptyList();
        } else if (steps.size() == 1) {
            return List.of(ExtendedGroup.of(steps));
        }

        List<Group> groups = new ArrayList<>();

        Collection<Integer> values = new ArrayList<>();

        for (int i = 0; i < steps.size(); i++) {
            if (i > 0 && Boolean.FALSE.equals(grouping.get(i))) {
                groups.add(ExtendedGroup.of(values));
                values = new ArrayList<>();
            }

            values.add(steps.get(i));
        }

        groups.add(ExtendedGroup.of(values));

        return groups;
    }

    @Override
    public long getMultiplicity() {
        if (steps.isEmpty()) {
            return 0;
        } else if (steps.size() == 1) {
            return 1;
        }

        List<Group> groups = getGroups();

        return groups.stream()
                .mapToLong(Group::countPermutations)
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
}
