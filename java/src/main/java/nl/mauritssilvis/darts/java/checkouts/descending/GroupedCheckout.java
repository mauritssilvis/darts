/*
 * Copyright © 2023 Maurits H. Silvis
 * SPDX-License-Identifier: GPL-3.0-or-later
 */

package nl.mauritssilvis.darts.java.checkouts.descending;

import nl.mauritssilvis.darts.java.boards.Field;
import nl.mauritssilvis.darts.java.checkouts.Checkout;
import nl.mauritssilvis.darts.java.checkouts.Throw;
import nl.mauritssilvis.darts.java.paths.descending.GroupedPath;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * An implementation of the {@code Checkout} interface that represents multiple
 * sequence of throws, namely, one for each permutation of the grouped throws.
 * <p>
 * Relevant design patterns: Immutable object, static factory method.
 */
public final class GroupedCheckout implements Checkout {
    private final List<Throw> throwList;
    private final List<Boolean> grouping;
    private final int score;

    private GroupedCheckout(Collection<Throw> throwList, Collection<Boolean> grouping) {
        this.throwList = List.copyOf(throwList);
        this.grouping = processGrouping(grouping, throwList.size());

        score = throwList.stream()
                .mapToInt(Throw::getScore)
                .sum();
    }

    /**
     * Returns a new {@code GroupedCheckout} with the specified throws and
     * grouping signature.
     *
     * @param throwList a collection of throws
     * @param grouping  a collection of booleans representing the grouping
     *                  signature
     * @return a new {@code GroupedCheckout} with the specified throws and
     * grouping signature
     */
    public static Checkout of(Collection<Throw> throwList, Collection<Boolean> grouping) {
        return new GroupedCheckout(throwList, grouping);
    }

    @Override
    public int getScore() {
        return score;
    }

    @Override
    public List<Throw> getThrows() {
        return throwList;
    }

    @Override
    public long getMultiplicity() {
        if (throwList.isEmpty()) {
            return 0;
        } else if (throwList.size() == 1) {
            return throwList.get(0).getFields().size();
        }

        List<List<Throw>> groups = getGroups();

        return groups.stream()
                .mapToLong(GroupedCheckout::countPermutations)
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

    private List<List<Throw>> getGroups() {
        List<List<Throw>> groups = new ArrayList<>();

        List<Throw> values = new ArrayList<>();

        for (int i = 0; i < throwList.size(); i++) {
            if (i > 0 && Boolean.FALSE.equals(grouping.get(i))) {
                groups.add(values);
                values = new ArrayList<>();
            }

            values.add(throwList.get(i));
        }

        groups.add(values);

        return groups;
    }

    private static long countPermutations(List<? extends Throw> group) {
        if (group.size() == 1) {
            return group.get(0).getFields().size();
        }

        List<List<Field>> simpleGroups = getSimpleGroups(group);
        simpleGroups.forEach(list -> list.sort(Comparator.comparing(Field::getName)));

        return simpleGroups.stream()
                .distinct()
                .mapToLong(GroupedCheckout::countFieldPermutations)
                .sum();
    }

    private static List<List<Field>> getSimpleGroups(List<? extends Throw> group) {
        int level = 0;
        int maxLevel = group.size();

        List<List<Field>> simpleGroups = new ArrayList<>();

        List<Field> simpleGroup = new ArrayList<>();

        IntStream.range(0, group.size())
                .forEach(i -> simpleGroup.add(null));

        getSimpleGroupsRecursively(group, level, maxLevel, simpleGroup, simpleGroups);

        return simpleGroups;
    }

    private static void getSimpleGroupsRecursively(
            List<? extends Throw> group,
            int level,
            int maxLevel,
            List<Field> simpleGroup,
            List<List<Field>> simpleGroups
    ) {
        if (level == maxLevel) {
            simpleGroups.add(new ArrayList<>(simpleGroup));
            return;
        }

        List<Field> fields = group.get(level).getFields();

        for (Field field : fields) {
            simpleGroup.set(level, field);
            getSimpleGroupsRecursively(group, level + 1, maxLevel, simpleGroup, simpleGroups);
        }
    }

    private static long countFieldPermutations(Collection<? extends Field> fields) {
        if (fields.isEmpty()) {
            return 0;
        } else if (fields.size() == 1) {
            return 1;
        }

        Map<Field, Long> frequencies = getFrequencies(fields);

        long denominator = frequencies.values().stream()
                .mapToLong(GroupedCheckout::factorial)
                .reduce(1, (p, e) -> p * e);

        long numerator = factorial(fields.size());

        return numerator / denominator;
    }

    private static Map<Field, Long> getFrequencies(Collection<? extends Field> fields) {
        return fields.stream()
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
