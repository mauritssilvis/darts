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

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
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
    private final List<List<Field>> fieldsPerThrow;
    private final PathFinder pathFinder;

    private CartesianCheckoutFinder(Collection<? extends Collection<Field>> fieldsPerThrow) {
        this.fieldsPerThrow = fieldsPerThrow.stream()
                .map(fields -> fields.stream()
                        .toList()
                )
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
        List<Path> paths = pathFinder.find(score);

        List<Map<Integer, List<Field>>> scoreToFields = fieldsPerThrow.stream()
                .map(fields -> fields.stream()
                        .collect(Collectors.groupingBy(Field::getScore))
                )
                .toList();

        List<Checkout> checkouts = new ArrayList<>();

        for (Path path : paths) {
            List<Integer> steps = path.getSteps();

            Collection<Field> fields = new ArrayList<>();

            for (int j = 0; j < steps.size(); j++) {
                int step = steps.get(j);
                fields.add(scoreToFields.get(j).get(step).get(0));
            }

            checkouts.add(SimpleCheckout.of(fields));
        }

        return checkouts;
    }

    private static Node getNode(Collection<? extends Field> fields) {
        List<Integer> scores = fields.stream().
                map(Field::getScore)
                .toList();

        return BasicNode.of(scores);
    }
}
