/*
 * Copyright © 2023 Maurits H. Silvis
 * SPDX-License-Identifier: GPL-3.0-or-later
 */

package nl.mauritssilvis.darts.java.checkouts.descending;

import nl.mauritssilvis.darts.java.boards.Field;
import nl.mauritssilvis.darts.java.checkouts.Checkout;
import nl.mauritssilvis.darts.java.checkouts.CheckoutFinder;
import nl.mauritssilvis.darts.java.paths.Pathfinder;
import nl.mauritssilvis.darts.java.paths.common.Node;
import nl.mauritssilvis.darts.java.paths.descending.DescendingNode;
import nl.mauritssilvis.darts.java.paths.descending.DescendingPathfinder;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * An implementation of the {@code CheckoutFinder} interface that finds darts
 * checkouts of a specified score by selecting throws from available dartboard
 * fields.
 * <p>
 * This implementation finds checkouts by grouping fields and considering only
 * sequences of grouped fields that have non-increasing scores. Results are
 * returned in the form of a list of {@code GroupedCheckout} objects.
 * <p>
 * Relevant design patterns: Strategy, immutable object, static factory method.
 */
public final class DescendingCheckoutFinder implements CheckoutFinder {
    private final Pathfinder pathFinder;
    private final List<Map<Integer, List<Field>>> scoreMaps;

    private DescendingCheckoutFinder(Collection<? extends Collection<Field>> fieldsPerThrow) {
        List<Node> nodes = fieldsPerThrow.stream()
                .map(DescendingCheckoutFinder::getNode)
                .toList();

        pathFinder = DescendingPathfinder.of(nodes);

        scoreMaps = fieldsPerThrow.stream()
                .map(DescendingCheckoutFinder::getScoreMap)
                .toList();
    }

    /**
     * Returns a new {@code CartesianCheckoutFinder} for the specified fields
     * per throw.
     *
     * @param fieldsPerThrow a collection of available fields per throw
     * @return a new {@code CartesianCheckoutFinder} for the specified fields
     * per throw
     */
    public static CheckoutFinder of(Collection<? extends Collection<Field>> fieldsPerThrow) {
        return new DescendingCheckoutFinder(fieldsPerThrow);
    }

    @Override
    public List<Checkout> find(int score) {
        return null;
    }

    private static Node getNode(Collection<? extends Field> fields) {
        List<Integer> scores = fields.stream()
                .map(Field::getScore)
                .toList();

        return DescendingNode.of(scores);
    }

    private static Map<Integer, List<Field>> getScoreMap(Collection<? extends Field> fields) {
        return fields.stream()
                .distinct()
                .collect(Collectors.groupingBy(Field::getScore));
    }
}
