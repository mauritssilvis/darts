/*
 * Copyright © 2023 Maurits Silvis
 * SPDX-License-Identifier: GPL-3.0-or-later
 */

package nl.mauritssilvis.darts.java.checkouts.factory;

import nl.mauritssilvis.darts.java.boards.Field;
import nl.mauritssilvis.darts.java.checkouts.CheckoutFinder;
import nl.mauritssilvis.darts.java.checkouts.cartesian.CartesianCheckoutFinder;
import nl.mauritssilvis.darts.java.checkouts.descending.DescendingCheckoutFinder;
import nl.mauritssilvis.darts.java.settings.FinderType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Collection;
import java.util.Collections;

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
}
