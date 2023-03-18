/*
 * Copyright © 2023 Maurits Silvis
 * SPDX-License-Identifier: GPL-3.0-or-later
 */

package nl.mauritssilvis.darts.java.finders.checkouts;

import nl.mauritssilvis.darts.java.api.boards.Field;
import nl.mauritssilvis.darts.java.api.finders.checkouts.Checkout;
import nl.mauritssilvis.darts.java.api.finders.checkouts.CheckoutFinder;
import nl.mauritssilvis.darts.java.api.settings.FinderType;
import nl.mauritssilvis.darts.java.boards.TypedFieldTestUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

class CheckoutFinderFactoryTests {
    @Test
    void getACartesianCheckoutFinder() {
        FinderType finderType = FinderType.CARTESIAN;
        Collection<Collection<Field>> fieldsPerThrow = Collections.emptyList();
        CheckoutFinder checkoutFinder = CheckoutFinderFactory.create(finderType, fieldsPerThrow);

        Assertions.assertTrue(checkoutFinder instanceof CartesianCheckoutFinder);
    }

    @Test
    void getADescendingCheckoutFinder() {
        FinderType finderType = FinderType.DESCENDING;
        Collection<Collection<Field>> fieldsPerThrow = Collections.emptyList();
        CheckoutFinder checkoutFinder = CheckoutFinderFactory.create(finderType, fieldsPerThrow);

        Assertions.assertTrue(checkoutFinder instanceof DescendingCheckoutFinder);
    }

    @Test
    void passOnTheFieldsPerThrow() {
        FinderType finderType = FinderType.CARTESIAN;

        Collection<Collection<String>> namesPerThrow = List.of(List.of("1"));
        Collection<List<Field>> fieldsPerThrow = TypedFieldTestUtils.getFieldsPerThrow(namesPerThrow);

        CheckoutFinder checkoutFinder = CheckoutFinderFactory.create(finderType, fieldsPerThrow);

        int score = 1;

        Collection<Checkout> checkouts = checkoutFinder.find(score);

        Assertions.assertFalse(checkouts.isEmpty());
    }
}
