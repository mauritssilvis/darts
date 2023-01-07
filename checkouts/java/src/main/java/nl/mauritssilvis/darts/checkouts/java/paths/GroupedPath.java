/*
 * Copyright © 2023 Maurits H. Silvis
 * SPDX-License-Identifier: GPL-3.0-or-later
 */

package nl.mauritssilvis.darts.checkouts.java.paths;

import java.util.*;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * An implementation of the {@code Path} interface that can represent multiple
 * sequences of integer steps, namely, one for each permutation of the grouped
 * elements.
 */
public class GroupedPath implements Path {
    private final List<Integer> steps;
    private final List<Boolean> grouping;

    private GroupedPath(Collection<Integer> steps, Collection<Boolean> grouping) {
        this.steps = List.copyOf(steps);
        this.grouping = processGrouping(grouping, steps.size());
    }

    /**
     * Returns a new {@code GroupedPath} with the supplied integer steps and
     * grouping signature.
     *
     * @param steps    a collection of integer steps
     * @param grouping a collection of booleans representing the grouping signature
     * @return a new {@code SimplePath} with the given steps and grouping signature
     */
    public static Path of(Collection<Integer> steps, Collection<Boolean> grouping) {
        return new GroupedPath(steps, grouping);
    }

    @Override
    public int getSize() {
        return steps.size();
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
    public int getGroupCount() {
        return (int) grouping.stream()
                .filter(Predicate.isEqual(false))
                .count();
    }

    @Override
    public long getMultiplicity() {
        if (steps.isEmpty()) {
            return 0;
        }

        List<List<Integer>> groups = new ArrayList<>();
        List<Integer> group = new ArrayList<>();

        for (int i = 0; i < steps.size(); i++) {
            if (i > 0 && !grouping.get(i)) {
                groups.add(group);
                group = new ArrayList<>();
            }

            group.add(steps.get(i));
        }

        groups.add(group);

        return groups.stream()
                .mapToLong(this::getGroupMultiplicity)
                .reduce(1, (prod, e) -> prod * e);
    }

    private List<Boolean> processGrouping(Collection<Boolean> input, int size) {
        if (size == 0) {
            return List.of();
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

    private long getGroupMultiplicity(Collection<Integer> group) {
        Map<Integer, Long> frequencies = group.stream()
                .collect(
                        Collectors.groupingBy(
                                Function.identity(),
                                HashMap::new,
                                Collectors.counting()
                        )
                );

        long denominator = frequencies.values().stream()
                .map(this::factorial)
                .reduce(1L, (p, e) -> p * e);

        return factorial(group.size()) / denominator;
    }

    private long factorial(long n) {
        long result = 1;

        for (int i = 2; i <= n; i++) {
            result *= i;
        }

        return result;
    }
}
