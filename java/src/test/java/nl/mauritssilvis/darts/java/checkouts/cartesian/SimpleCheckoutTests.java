/*
 * Copyright © 2023 Maurits H. Silvis
 * SPDX-License-Identifier: GPL-3.0-or-later
 */

package nl.mauritssilvis.darts.java.checkouts.cartesian;

import nl.mauritssilvis.darts.java.boards.Field;
import nl.mauritssilvis.darts.java.boards.common.TypedFieldTestUtils;
import nl.mauritssilvis.darts.java.checkouts.Checkout;
import nl.mauritssilvis.darts.java.checkouts.Throw;
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

        Collection<Throw> throwList = getThrows(fields);

        Assertions.assertNotEquals(throwList, checkout.getThrows());
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

        Collection<Throw> throwList = getThrows(fields);

        Assertions.assertEquals(throwList, checkout.getThrows());
    }

    @Test
    void getTheThrowsWithSingletonInput() {
        Collection<Field> fields = TypedFieldTestUtils.getFields("D7");
        Checkout checkout = SimpleCheckout.of(fields);

        Collection<Throw> throwList = getThrows(fields);

        Assertions.assertEquals(throwList, checkout.getThrows());
    }

    @Test
    void getTheThrowsWithEmptyInput() {
        Collection<Field> fields = Collections.emptyList();
        Checkout checkout = SimpleCheckout.of(fields);

        Collection<Throw> throwList = getThrows(fields);

        Assertions.assertEquals(throwList, checkout.getThrows());
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

    @Test
    void getEqualCheckouts() {
        Collection<Field> fields1 = TypedFieldTestUtils.getFields("Q4", "D8");
        Checkout checkout1 = SimpleCheckout.of(fields1);

        Collection<Field> fields2 = TypedFieldTestUtils.getFields("Q4", "D8");
        Checkout checkout2 = SimpleCheckout.of(fields2);

        Assertions.assertAll(
                () -> Assertions.assertEquals(checkout1, checkout2),
                () -> Assertions.assertEquals(checkout1.hashCode(), checkout2.hashCode())
        );
    }

    @Test
    void getUnequalCheckouts() {
        Collection<Field> fields1 = TypedFieldTestUtils.getFields("D4");
        Checkout checkout1 = SimpleCheckout.of(fields1);

        Collection<Field> fields2 = TypedFieldTestUtils.getFields("D5");
        Checkout checkout2 = SimpleCheckout.of(fields2);

        Assertions.assertNotEquals(checkout1, checkout2);
    }

    private static Collection<Throw> getThrows(Collection<? extends Field> fields) {
        return fields.stream()
                .map(SimpleThrow::of)
                .toList();
    }
}
