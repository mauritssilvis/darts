/*
 * Copyright © 2023 Maurits Silvis
 * SPDX-License-Identifier: GPL-3.0-or-later
 */

package nl.mauritssilvis.darts.java.checkouts.cartesian;

import nl.mauritssilvis.darts.java.boards.Field;
import nl.mauritssilvis.darts.java.boards.common.TypedFieldTestUtils;
import nl.mauritssilvis.darts.java.checkouts.Checkout;
import nl.mauritssilvis.darts.java.checkouts.Throw;
import nl.mauritssilvis.darts.java.checkouts.ThrowTestUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

class SimpleCheckoutTests {
    @Test
    void storeIndependentFields() {
        Collection<String> names = List.of("D9", "D11", "D13");
        List<Field> fields = new ArrayList<>(TypedFieldTestUtils.getFields(names));
        Checkout checkout = SimpleCheckout.of(fields);

        fields.remove(2);

        Collection<Throw> storedThrows = checkout.getThrows();
        Collection<String> storedNames = getAllNames(storedThrows);

        Assertions.assertEquals(names, storedNames);
    }

    @Test
    void getTheScore() {
        Collection<String> names = List.of("1", "4");
        Collection<Field> fields = TypedFieldTestUtils.getFields(names);
        Checkout checkout = SimpleCheckout.of(fields);

        Assertions.assertEquals(5, checkout.getScore());
    }

    @Test
    void getTheScoreWithSingletonInput() {
        Collection<String> names = List.of("Q20");
        Collection<Field> fields = TypedFieldTestUtils.getFields(names);
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
        Collection<String> names = List.of("Q2", "T7", "D19");
        Collection<Field> fields = TypedFieldTestUtils.getFields(names);
        Checkout checkout = SimpleCheckout.of(fields);

        Collection<Throw> storedThrows = checkout.getThrows();
        Collection<String> storedNames = getAllNames(storedThrows);

        Assertions.assertEquals(names, storedNames);
    }

    @Test
    void getTheThrowsWithSingletonInput() {
        Collection<String> names = List.of("D7");
        Collection<Field> fields = TypedFieldTestUtils.getFields(names);
        Checkout checkout = SimpleCheckout.of(fields);

        Collection<Throw> storedThrows = checkout.getThrows();
        Collection<String> storedNames = getAllNames(storedThrows);

        Assertions.assertEquals(names, storedNames);
    }

    @Test
    void getTheThrowsWithEmptyInput() {
        Collection<String> names = Collections.emptyList();
        Collection<Field> fields = TypedFieldTestUtils.getFields(names);
        Checkout checkout = SimpleCheckout.of(fields);

        Collection<Throw> storedThrows = checkout.getThrows();
        Collection<String> storedNames = getAllNames(storedThrows);

        Assertions.assertEquals(names, storedNames);
    }

    @Test
    void getImmutableThrows() {
        Collection<String> names = List.of("1", "13", "25");
        Collection<Field> fields = TypedFieldTestUtils.getFields(names);
        Checkout checkout = SimpleCheckout.of(fields);

        Collection<Throw> storedThrows = checkout.getThrows();

        Assertions.assertThrows(UnsupportedOperationException.class, storedThrows::clear);
    }

    @Test
    void getTheMultiplicity() {
        Collection<String> names = List.of("T20", "1", "D20");
        Collection<Field> fields = TypedFieldTestUtils.getFields(names);
        Checkout checkout = SimpleCheckout.of(fields);

        Assertions.assertEquals(1, checkout.getMultiplicity());
    }

    @Test
    void getTheMultiplicityWithSingletonInput() {
        Collection<String> names = List.of("16");
        Collection<Field> fields = TypedFieldTestUtils.getFields(names);
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
        Collection<String> names1 = List.of("Q4", "D8");
        Collection<Field> fields1 = TypedFieldTestUtils.getFields(names1);
        Checkout checkout1 = SimpleCheckout.of(fields1);

        Collection<String> names2 = List.of("Q4", "D8");
        Collection<Field> fields2 = TypedFieldTestUtils.getFields(names2);
        Checkout checkout2 = SimpleCheckout.of(fields2);

        Assertions.assertAll(
                () -> Assertions.assertEquals(checkout1, checkout2),
                () -> Assertions.assertEquals(checkout1.hashCode(), checkout2.hashCode())
        );
    }

    @Test
    void getUnequalCheckouts() {
        Collection<String> names1 = List.of("D4");
        Collection<Field> fields1 = TypedFieldTestUtils.getFields(names1);
        Checkout checkout1 = SimpleCheckout.of(fields1);

        Collection<String> names2 = List.of("D5");
        Collection<Field> fields2 = TypedFieldTestUtils.getFields(names2);
        Checkout checkout2 = SimpleCheckout.of(fields2);

        Assertions.assertNotEquals(checkout1, checkout2);
    }

    private static List<String> getAllNames(Collection<? extends Throw> throwCollection) {
        return ThrowTestUtils.getAllNames(throwCollection).stream()
                .flatMap(Collection::stream)
                .toList();
    }

    @Test
    void convertToAString() {
        Collection<String> names = List.of("D1", "D2", "D3");
        Collection<Field> fields = TypedFieldTestUtils.getFields(names);
        Checkout checkout = SimpleCheckout.of(fields);

        String str = checkout.toString();

        Assertions.assertAll(
                () -> Assertions.assertTrue(str.contains(checkout.getClass().getSimpleName())),
                () -> Assertions.assertTrue(str.contains("score")),
                () -> Assertions.assertTrue(str.contains("throw")),
                () -> Assertions.assertTrue(str.contains("multiplicity"))
        );
    }
}
