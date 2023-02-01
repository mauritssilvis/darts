/*
 * Copyright © 2023 Maurits H. Silvis
 * SPDX-License-Identifier: GPL-3.0-or-later
 */

package nl.mauritssilvis.darts.java.tables.map;

import nl.mauritssilvis.darts.java.paths.Pathfinder;
import nl.mauritssilvis.darts.java.paths.common.Node;
import nl.mauritssilvis.darts.java.settings.BoardType;
import nl.mauritssilvis.darts.java.settings.CheckType;
import nl.mauritssilvis.darts.java.tables.CheckoutTable;
import nl.mauritssilvis.darts.java.tables.CheckoutTableGenerator;

import java.util.Collection;
import java.util.function.Function;

/**
 * An implementation of the {@code CheckoutTableGenerator} interface that
 * generates {@code MappedCheckoutTable} objects.
 * <p>
 * Relevant design patterns: Immutable object, static factory method.
 */
public class MappedCheckoutTableGenerator implements CheckoutTableGenerator {
    private MappedCheckoutTableGenerator(
            BoardType boardType,
            CheckType checkInType,
            CheckType checkoutType,
            Function<Collection<? extends Node>, Pathfinder> pathfinderFactory
    ) {
    }

    /**
     * Returns a new {@code MappedCheckoutTableGenerator} with the specified
     * dartboard, check-in and checkout types.
     *
     * @param boardType         the dartboard type
     * @param checkInType       the check-in type
     * @param checkoutType      the checkout type
     * @param pathfinderFactory a pathfinder factory
     * @return a new {@code MappedCheckoutTableGenerator} with the specified
     * dartboard, check-in and checkout types.
     */
    public static CheckoutTableGenerator of(
            BoardType boardType,
            CheckType checkInType,
            CheckType checkoutType,
            Function<Collection<? extends Node>, Pathfinder> pathfinderFactory
    ) {
        return new MappedCheckoutTableGenerator(boardType, checkInType, checkoutType, pathfinderFactory);
    }

    @Override
    public CheckoutTable generate(int minScore, int maxScore) {
        return null;
    }
}
