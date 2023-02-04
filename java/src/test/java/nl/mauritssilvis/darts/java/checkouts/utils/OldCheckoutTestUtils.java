/*
 * Copyright © 2023 Maurits H. Silvis
 * SPDX-License-Identifier: GPL-3.0-or-later
 */

package nl.mauritssilvis.darts.java.checkouts.utils;

import nl.mauritssilvis.darts.java.boards.Field;
import nl.mauritssilvis.darts.java.checkouts.Checkout;
import nl.mauritssilvis.darts.java.checkouts.Throw;

import java.util.Collection;
import java.util.List;

public final class OldCheckoutTestUtils {
    private OldCheckoutTestUtils() {
    }

    public static List<List<List<String>>> getNamesPerCheckout(Collection<? extends Checkout> checkouts) {
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
