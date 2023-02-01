/*
 * Copyright © 2023 Maurits H. Silvis
 * SPDX-License-Identifier: GPL-3.0-or-later
 */

package nl.mauritssilvis.darts.java.checkouts.factory;

import nl.mauritssilvis.darts.java.boards.Field;
import nl.mauritssilvis.darts.java.checkouts.CheckoutFinder;
import nl.mauritssilvis.darts.java.checkouts.cartesian.CartesianCheckoutFinder;
import nl.mauritssilvis.darts.java.checkouts.descending.DescendingCheckoutFinder;
import nl.mauritssilvis.darts.java.settings.CheckoutFinderType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Collection;
import java.util.Collections;

class CheckoutFinderFactoryTests {
    @Test
    void getACartesianCheckoutFinder() {
        CheckoutFinderType checkoutFinderType = CheckoutFinderType.CARTESIAN;
        Collection<Collection<Field>> fieldsPerThrow = Collections.emptyList();
        CheckoutFinder checkoutFinder = CheckoutFinderFactory.create(checkoutFinderType, fieldsPerThrow);

        Assertions.assertTrue(checkoutFinder instanceof CartesianCheckoutFinder);
    }

    @Test
    void getADescendingCheckoutFinder() {
        CheckoutFinderType checkoutFinderType = CheckoutFinderType.DESCENDING;
        Collection<Collection<Field>> fieldsPerThrow = Collections.emptyList();
        CheckoutFinder checkoutFinder = CheckoutFinderFactory.create(checkoutFinderType, fieldsPerThrow);

        Assertions.assertTrue(checkoutFinder instanceof DescendingCheckoutFinder);
    }
}
