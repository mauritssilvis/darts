/*
 * Copyright © 2023 Maurits H. Silvis
 * SPDX-License-Identifier: GPL-3.0-or-later
 */

package nl.mauritssilvis.darts.checkouts.java.checkouts.cartesian;

import nl.mauritssilvis.darts.checkouts.java.boards.Field;
import nl.mauritssilvis.darts.checkouts.java.checkouts.Checkout;
import nl.mauritssilvis.darts.checkouts.java.checkouts.CheckoutFinder;
import nl.mauritssilvis.darts.checkouts.java.paths.Node;
import nl.mauritssilvis.darts.checkouts.java.paths.Path;
import nl.mauritssilvis.darts.checkouts.java.paths.PathFinder;
import nl.mauritssilvis.darts.checkouts.java.paths.cartesian.BasicNode;
import nl.mauritssilvis.darts.checkouts.java.paths.cartesian.CartesianPathFinder;

import java.util.*;
import java.util.stream.Collectors;

/**
 * An implementation of the {@code CheckoutFinder} interface that finds
 * checkouts of a specified score by selecting throws from available fields.
 * <p>
 * This implementation finds checkouts by considering all possible combinations
 * of the available fields and returns results in the form of a list of {@code
 * SimpleCheckout} objects.
 * <p>
 * Relevant design patterns: Strategy, immutable object, static factory method.
 */
public final class CartesianCheckoutFinder implements CheckoutFinder {
    private final int numThrows;
    private final PathFinder pathFinder;
    private final List<Map<Integer, List<Field>>> scoreToFieldsPerThrow;

    private CartesianCheckoutFinder(Collection<? extends Collection<Field>> fieldsPerThrow) {
        numThrows = fieldsPerThrow.size();

        scoreToFieldsPerThrow = fieldsPerThrow.stream()
                .map(CartesianCheckoutFinder::mapScoresToFields)
                .toList();

        List<Node> nodes = fieldsPerThrow.stream()
                .map(CartesianCheckoutFinder::getNode)
                .toList();

        pathFinder = CartesianPathFinder.of(nodes);
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
        return new CartesianCheckoutFinder(fieldsPerThrow);
    }

    @Override
    public List<Checkout> find(int score) {
        if (numThrows == 0) {
            return Collections.emptyList();
        }

        List<Path> paths = pathFinder.find(score);

        List<Field> fields = new ArrayList<>();
        scoreToFieldsPerThrow.forEach(e -> fields.add(null));

        List<Checkout> checkouts = new ArrayList<>();

        for (Path path : paths) {
            List<Integer> steps = path.getSteps();
            findRecursively(0, steps, fields, checkouts);
        }

        return checkouts;
    }

    private static Node getNode(Collection<? extends Field> fields) {
        List<Integer> scores = fields.stream()
                .map(Field::getScore)
                .distinct()
                .toList();

        return BasicNode.of(scores);
    }

    private static Map<Integer, List<Field>> mapScoresToFields(Collection<? extends Field> fields) {
        return fields.stream()
                .distinct()
                .collect(Collectors.groupingBy(Field::getScore));
    }

    private void findRecursively(int level, List<Integer> steps, List<Field> fields, List<Checkout> checkouts) {
        if (level == numThrows) {
            checkouts.add(SimpleCheckout.of(fields));
            return;
        }

        int score = steps.get(level);

        for (Field field : scoreToFieldsPerThrow.get(level).get(score)) {
            fields.set(level, field);
            findRecursively(level + 1, steps, fields, checkouts);
        }
    }
}
