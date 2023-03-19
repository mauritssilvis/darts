/*
 * Copyright © 2023 Maurits Silvis
 * SPDX-License-Identifier: GPL-3.0-or-later
 */

package nl.mauritssilvis.darts.java.api.tables;

import nl.mauritssilvis.darts.java.api.finders.checkouts.Checkout;
import nl.mauritssilvis.darts.java.api.finders.checkouts.CheckoutTestUtils;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * A set of testing utilities for processing {@code Table} objects.
 *
 * @author Maurits Silvis
 * @since 0.1.0
 */
public final class TableTestUtils {
    private TableTestUtils() {
    }

    /**
     * Gets the names of the fields of the stored checkouts.
     *
     * @param table a checkout table
     * @return a map of the field names
     */
    public static Map<Integer, List<List<List<String>>>> getAllNames(Table table) {
        Map<Integer, List<Checkout>> checkoutMap = table.getCheckoutMap();

        return checkoutMap.entrySet().stream()
                .collect(Collectors.toMap(Map.Entry::getKey, e -> CheckoutTestUtils.getAllNames(e.getValue())));
    }

    /**
     * Gets the multiplicities of the stored checkouts per score.
     *
     * @param table a checkout table
     * @return a map of the total multiplicities per score
     */
    public static Map<Integer, Long> getAllMultiplicities(Table table) {
        Map<Integer, List<Checkout>> checkoutMap = table.getCheckoutMap();

        return checkoutMap.entrySet().stream()
                .collect(Collectors.toMap(
                                Map.Entry::getKey, e -> CheckoutTestUtils.getTotalMultiplicity(e.getValue())
                        )
                );
    }
}
