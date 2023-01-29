/*
 * Copyright © 2023 Maurits H. Silvis
 * SPDX-License-Identifier: GPL-3.0-or-later
 */

package nl.mauritssilvis.darts.checkouts.java.checkouts.utils;

import nl.mauritssilvis.darts.checkouts.java.boards.Field;
import nl.mauritssilvis.darts.checkouts.java.checkouts.Checkout;
import nl.mauritssilvis.darts.checkouts.java.checkouts.Throw;

import java.util.Collection;
import java.util.List;

public final class CheckoutTestUtils {
    private CheckoutTestUtils() {
    }

    public static int getTotalScore(Collection<? extends Checkout> checkouts) {
        return checkouts.stream()
                .mapToInt(Checkout::getScore)
                .sum();
    }

    public static int countTotalThrows(Collection<? extends Checkout> checkouts) {
        return checkouts.stream()
                .mapToInt(Checkout::countThrows)
                .sum();
    }

    public static long getTotalMultiplicity(Collection<? extends Checkout> checkouts) {
        return checkouts.stream()
                .mapToLong(Checkout::getMultiplicity)
                .sum();
    }

    public static List<List<List<String>>> getNamesPerCheckout(List<Checkout> checkouts) {
        return checkouts.stream()
                .map(Checkout::getThrows)
                .map(throwList -> throwList.stream()
                        .map(Throw::getFields)
                        .map(fields -> fields.stream()
                                .map(Field::getName)
                                .toList()
                        )
                        .toList()
                )
                .toList();
    }
}
