/*
 * Copyright © 2023 Maurits H. Silvis
 * SPDX-License-Identifier: GPL-3.0-or-later
 */

package nl.mauritssilvis.darts.java.tables;

import nl.mauritssilvis.darts.java.checkouts.Checkout;
import nl.mauritssilvis.darts.java.checkouts.CheckoutTestUtils;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * A set of testing utilities for processing {@code CheckoutTable} objects.
 */
public final class CheckoutTableTestUtils {
    private CheckoutTableTestUtils() {
    }

    /**
     * Gets the names of the fields of the stored checkouts
     *
     * @param checkoutTable a checkout table
     * @return a map of the field names
     */
    public static Map<Integer, List<List<List<String>>>> getAllNames(CheckoutTable checkoutTable) {
        Map<Integer, List<Checkout>> checkoutMap = checkoutTable.getCheckoutMap();

        return checkoutMap.entrySet().stream()
                .collect(Collectors.toMap(Map.Entry::getKey, e -> CheckoutTestUtils.getAllNames(e.getValue())));
    }

    /**
     * Gets the multiplicities of the stored checkouts per score.
     *
     * @param checkoutTable a checkout table
     * @return a map of the total multiplicities per score
     */
    public static Map<Integer, Long> getAllMultiplicities(CheckoutTable checkoutTable) {
        Map<Integer, List<Checkout>> checkoutMap = checkoutTable.getCheckoutMap();

        return checkoutMap.entrySet().stream()
                .collect(Collectors.toMap(
                                Map.Entry::getKey, e -> CheckoutTestUtils.getTotalMultiplicity(e.getValue())
                        )
                );
    }
}
