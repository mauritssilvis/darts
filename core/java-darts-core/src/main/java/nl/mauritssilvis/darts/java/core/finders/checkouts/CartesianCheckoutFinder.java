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
 * This implementation finds checkouts by considering all possible combinations
 * of the available fields and returns results in the form of a list of {@code
 * SimpleCheckout} objects.
 * <p>
 * This implementation relies on the {@code CartesianPathfinder} class.
 * <p>
 * Relevant design patterns: strategy, immutable object, simple factory.
 *
 * @author Maurits Silvis
 * @since 0.5.0
 */
@EqualsAndHashCode
@ToString
final class CartesianCheckoutFinder implements CheckoutFinder {
    private final Pathfinder pathfinder;
    @ToString.Exclude
    private final List<Map<Integer, List<Field>>> scoreMaps;

    private CartesianCheckoutFinder(Collection<? extends Collection<? extends Field>> fieldsPerThrow) {
        Collection<Node> nodes = fieldsPerThrow.stream()
                .map(CartesianCheckoutFinder::getNode)
                .toList();

        pathfinder = PathfinderFactory.create(FinderType.CARTESIAN, nodes);

        scoreMaps = fieldsPerThrow.stream()
                .map(CartesianCheckoutFinder::getScoreMap)
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
    public static CheckoutFinder of(Collection<? extends Collection<? extends Field>> fieldsPerThrow) {
        return new CartesianCheckoutFinder(fieldsPerThrow);
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

        return new Converter(paths, scoreMaps).convert();
    }

    private static Node getNode(Collection<? extends Field> fields) {
        List<Integer> scores = fields.stream()
                .map(Field::getScore)
                .toList();

        return NodeFactory.create(FinderType.CARTESIAN, scores);
    }

    private static Map<Integer, List<Field>> getScoreMap(Collection<? extends Field> fields) {
        return fields.stream()
                .distinct()
                .collect(Collectors.groupingBy(Field::getScore));
    }

    private static class Converter {
        private final List<? extends Path> paths;
        private final List<? extends Map<Integer, List<Field>>> scoreMaps;
        private final int maxLevel;
        private final List<Checkout> checkouts;

        Converter(List<? extends Path> paths, List<? extends Map<Integer, List<Field>>> scoreMaps) {
            this.paths = paths;
            this.scoreMaps = scoreMaps;

            maxLevel = scoreMaps.size();
            checkouts = new ArrayList<>();
        }

        List<Checkout> convert() {
            for (Path path : paths) {
                List<Integer> steps = path.getSteps();

                List<Field> fields = new ArrayList<>();
                IntStream.range(0, maxLevel)
                        .forEach(i -> fields.add(null));

                convertRecursively(0, steps, fields);
            }

            return Collections.unmodifiableList(checkouts);
        }

        void convertRecursively(int level, List<Integer> steps, List<Field> fields) {
            if (level == maxLevel) {
                checkouts.add(SimpleCheckout.of(fields));
                return;
            }

            int score = steps.get(level);

            Map<Integer, List<Field>> scoreMap = scoreMaps.get(level);
            List<Field> scoreFields = scoreMap.get(score);

            for (Field field : scoreFields) {
                fields.set(level, field);
                convertRecursively(level + 1, steps, fields);
            }
        }
    }
}
