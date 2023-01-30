/*
 * Copyright © 2023 Maurits H. Silvis
 * SPDX-License-Identifier: GPL-3.0-or-later
 */

package nl.mauritssilvis.darts.java.checkouts.cartesian;

import nl.mauritssilvis.darts.java.boards.Field;
import nl.mauritssilvis.darts.java.checkouts.Checkout;
import nl.mauritssilvis.darts.java.checkouts.Throw;
import nl.mauritssilvis.darts.java.checkouts.utils.TypedFieldTestUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

class SimpleCheckoutTests {
    @Test
    void storeIndependentFields() {
        List<Field> fields = new ArrayList<>(TypedFieldTestUtils.getFields("D9", "D11", "D13"));
        Checkout checkout = SimpleCheckout.of(fields);

        fields.remove(2);

        Assertions.assertNotEquals(getThrows(fields), checkout.getThrows());
    }

    @Test
    void getTheScore() {
        Collection<Field> fields = TypedFieldTestUtils.getFields("1", "4");
        Checkout checkout = SimpleCheckout.of(fields);

        Assertions.assertEquals(5, checkout.getScore());
    }

    @Test
    void getTheScoreWithSingletonInput() {
        Collection<Field> fields = TypedFieldTestUtils.getFields("Q20");
        Checkout checkout = SimpleCheckout.of(fields);

        Assertions.assertEquals(80, checkout.getScore());
    }

    @Test
    void getTheScoreWithEmptyInput() {
        Collection<Field> fields = Collections.emptyList();
        Checkout checkout = SimpleCheckout.of(fields);

        Assertions.assertEquals(0, checkout.getScore());
    }

    @Test
    void getTheThrows() {
        Collection<Field> fields = TypedFieldTestUtils.getFields("Q2", "T7", "D19");
        Checkout checkout = SimpleCheckout.of(fields);

        Assertions.assertEquals(getThrows(fields), checkout.getThrows());
    }

    @Test
    void getTheThrowsWithSingletonInput() {
        Collection<Field> fields = TypedFieldTestUtils.getFields("D7");
        Checkout checkout = SimpleCheckout.of(fields);

        Assertions.assertEquals(getThrows(fields), checkout.getThrows());
    }

    @Test
    void getTheThrowsWithEmptyInput() {
        Collection<Field> fields = Collections.emptyList();
        Checkout checkout = SimpleCheckout.of(fields);

        Assertions.assertEquals(getThrows(fields), checkout.getThrows());
    }

    @Test
    void getImmutableThrows() {
        Collection<Field> fields = TypedFieldTestUtils.getFields("1", "13", "25");
        Checkout checkout = SimpleCheckout.of(fields);

        List<Throw> storedThrows = checkout.getThrows();

        Assertions.assertThrows(UnsupportedOperationException.class, () -> storedThrows.remove(2));
    }

    @Test
    void getTheMultiplicity() {
        Collection<Field> fields = TypedFieldTestUtils.getFields("T20", "1", "D20");
        Checkout checkout = SimpleCheckout.of(fields);

        Assertions.assertEquals(1, checkout.getMultiplicity());
    }

    @Test
    void getTheMultiplicityWithSingletonInput() {
        Collection<Field> fields = TypedFieldTestUtils.getFields("16");
        Checkout checkout = SimpleCheckout.of(fields);

        Assertions.assertEquals(1, checkout.getMultiplicity());
    }

    @Test
    void getTheMultiplicityWithEmptyInput() {
        Collection<Field> fields = Collections.emptyList();
        Checkout checkout = SimpleCheckout.of(fields);

        Assertions.assertEquals(0, checkout.getMultiplicity());
    }

    private static Collection<Throw> getThrows(Collection<? extends Field> fields) {
        return fields.stream()
                .map(SimpleThrow::of)
                .toList();
    }
}
