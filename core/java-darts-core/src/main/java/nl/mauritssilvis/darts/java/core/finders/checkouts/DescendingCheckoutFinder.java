/*
 * Copyright © 2023 Maurits Silvis
 * SPDX-License-Identifier: GPL-3.0-or-later
 */

package nl.mauritssilvis.darts.java.core.finders.checkouts;

import lombok.EqualsAndHashCode;
import lombok.ToString;
import nl.mauritssilvis.darts.java.api.boards.Field;
import nl.mauritssilvis.darts.java.api.finders.checkouts.Checkout;
import nl.mauritssilvis.darts.java.api.finders.checkouts.CheckoutFinder;
import nl.mauritssilvis.darts.java.api.finders.checkouts.Throw;
import nl.mauritssilvis.darts.java.api.finders.paths.Path;
import nl.mauritssilvis.darts.java.api.finders.paths.Pathfinder;
import nl.mauritssilvis.darts.java.api.settings.FinderType;
import nl.mauritssilvis.darts.java.core.finders.paths.Node;
import nl.mauritssilvis.darts.java.core.finders.paths.NodeFactory;
import nl.mauritssilvis.darts.java.core.finders.paths.PathfinderFactory;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * An implementation of the {@code CheckoutFinder} interface that finds darts
 * checkouts of a specified score by selecting throws from available dartboard
 * fields.
 * <p>
 * This implementation finds checkouts by grouping fields and considering only
 * sequences of grouped fields that have non-increasing scores. Results are
 * returned in the form of a list of {@code GroupedCheckout} objects.
 * <p>
 * This implementation relies on the {@code DescendingPathfinder} class.
 * <p>
 * Relevant design patterns: strategy, immutable object, simple factory.
 *
 * @author Maurits Silvis
 * @since 0.5.0
 */
@EqualsAndHashCode
@ToString
final class DescendingCheckoutFinder implements CheckoutFinder {
    private final Pathfinder pathfinder;
    @ToString.Exclude
    private final List<Map<Integer, List<Field>>> scoreMaps;

    private DescendingCheckoutFinder(Collection<? extends Collection<? extends Field>> fieldsPerThrow) {
        Collection<Node> nodes = fieldsPerThrow.stream()
                .map(DescendingCheckoutFinder::getNode)
                .toList();

        pathfinder = PathfinderFactory.create(FinderType.DESCENDING, nodes);

        scoreMaps = fieldsPerThrow.stream()
                .map(DescendingCheckoutFinder::getScoreMap)
                .toList();
    }

    /**
     * Returns a new {@code DescendingCheckoutFinder} for the specified fields
     * per throw.
     *
     * @param fieldsPerThrow a collection of available fields per throw
     * @return a new {@code DescendingCheckoutFinder} for the specified fields
     * per throw
     */
    public static CheckoutFinder of(Collection<? extends Collection<? extends Field>> fieldsPerThrow) {
        return new DescendingCheckoutFinder(fieldsPerThrow);
    }

    @Override
    public List<Checkout> find(int score) {
        if (scoreMaps.isEmpty()) {
            return Collections.emptyList();
        }

        List<Path> paths = pathfinder.find(score);

        if (paths.isEmpty()) {
            return Collections.emptyList();
        }

        return paths.stream()
                .map(this::convert)
                .toList();
    }

    private static Node getNode(Collection<? extends Field> fields) {
        List<Integer> scores = fields.stream()
                .map(Field::getScore)
                .toList();

        return NodeFactory.create(FinderType.DESCENDING, scores);
    }

    private static Map<Integer, List<Field>> getScoreMap(Collection<? extends Field> fields) {
        return fields.stream()
                .distinct()
                .sorted(Comparator.comparing(Field::getFieldType))
                .collect(Collectors.groupingBy(Field::getScore));
    }

    private Checkout convert(Path path) {
        List<Integer> steps = path.getSteps();

        Collection<Throw> throwList = IntStream.range(0, steps.size())
                .mapToObj(i -> scoreMaps.get(i).get(steps.get(i)))
                .map(CompoundThrow::of)
                .toList();

        return GroupedCheckout.of(throwList, path.getGrouping());
    }
}
