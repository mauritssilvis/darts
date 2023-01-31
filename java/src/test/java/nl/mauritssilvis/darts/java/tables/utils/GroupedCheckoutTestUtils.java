/*
 * Copyright © 2023 Maurits H. Silvis
 * SPDX-License-Identifier: GPL-3.0-or-later
 */

package nl.mauritssilvis.darts.java.tables.utils;

import nl.mauritssilvis.darts.java.boards.Field;
import nl.mauritssilvis.darts.java.checkouts.Checkout;
import nl.mauritssilvis.darts.java.checkouts.Throw;
import nl.mauritssilvis.darts.java.checkouts.descending.CompoundThrow;
import nl.mauritssilvis.darts.java.checkouts.descending.GroupedCheckout;
import nl.mauritssilvis.darts.java.checkouts.utils.TypedFieldTestUtils;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class GroupedCheckoutTestUtils {
    public static List<Checkout> getCheckouts(Collection<? extends Collection<Collection<String>>> names) {
        return names.stream()
                .map(GroupedCheckoutTestUtils::getCheckout)
                .collect(Collectors.toList());
    }

    private static Checkout getCheckout(Collection<? extends Collection<String>> names) {
        List<Throw> throwList = names.stream()
                .map(GroupedCheckoutTestUtils::getThrow)
                .toList();

        List<Boolean> grouping = throwList.stream()
                .map(t -> true)
                .toList();

        return GroupedCheckout.of(throwList, grouping);
    }

    private static Throw getThrow(Collection<String> names) {
        List<Field> fields = names.stream()
                .map(TypedFieldTestUtils::getField)
                .toList();

        return CompoundThrow.of(fields);
    }
}
