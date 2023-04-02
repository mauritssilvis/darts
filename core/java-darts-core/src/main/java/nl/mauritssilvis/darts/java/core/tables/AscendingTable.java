/*
 * Copyright © 2023 Maurits Silvis
 * SPDX-License-Identifier: GPL-3.0-or-later
 */

package nl.mauritssilvis.darts.java.core.tables;

import lombok.EqualsAndHashCode;
import lombok.ToString;
import nl.mauritssilvis.darts.java.api.finders.checkouts.Checkout;
import nl.mauritssilvis.darts.java.api.settings.Settings;
import nl.mauritssilvis.darts.java.api.tables.Table;

import java.util.*;
import java.util.stream.Collectors;

/**
 * An implementation of the {@code Table} interface that stores checkouts in
 * ascending order.
 * <p>
 * Relevant design patterns: immutable object, simple factory.
 *
 * @author Maurits Silvis
 * @since 0.5.0
 */
@EqualsAndHashCode
@ToString
final class AscendingTable implements Table {
    private final Settings settings;
    private final Map<Integer, List<Checkout>> checkoutMap;

    private AscendingTable(Settings settings, Map<Integer, ? extends Collection<? extends Checkout>> checkoutMap) {
        boolean hasOtherScore = checkoutMap.entrySet().stream()
                .anyMatch(e -> e.getValue().stream()
                        .anyMatch(checkout -> checkout.getScore() != e.getKey())
                );

        if (hasOtherScore) {
            throw new IllegalArgumentException(
                    "Checkouts should have the same score as their checkout map key"
            );
        }

        this.settings = settings;

        this.checkoutMap = Collections.unmodifiableMap(
                checkoutMap.entrySet().stream()
                        .collect(
                                Collectors.toMap(
                                        Map.Entry::getKey, e -> List.copyOf(e.getValue()), (e1, e2) -> e1, TreeMap::new
                                )
                        )
        );
    }

    /**
     * Returns a new {@code AscendingTable} with the specified settings.
     *
     * @param settings    the table settings
     * @param checkoutMap the checkout map
     * @return a new {@code AscendingTable} with the specified settings
     * @throws IllegalArgumentException if some checkouts in the checkout map do
     *                                  not have the same score as their
     *                                  checkout map key
     */
    public static Table of(Settings settings, Map<Integer, ? extends Collection<? extends Checkout>> checkoutMap) {
        return new AscendingTable(settings, checkoutMap);
    }

    @Override
    public Settings getSettings() {
        return settings;
    }

    @Override
    public Map<Integer, List<Checkout>> getCheckoutMap() {
        return checkoutMap;
    }
}
