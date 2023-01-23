/*
 * Copyright © 2023 Maurits H. Silvis
 * SPDX-License-Identifier: GPL-3.0-or-later
 */

package nl.mauritssilvis.darts.checkouts.java.checkouts.cartesian;

import nl.mauritssilvis.darts.checkouts.java.boards.Field;
import nl.mauritssilvis.darts.checkouts.java.checkouts.Checkout;
import nl.mauritssilvis.darts.checkouts.java.checkouts.CheckoutFinder;
import nl.mauritssilvis.darts.checkouts.java.checkouts.common.CheckoutTestUtils;
import nl.mauritssilvis.darts.checkouts.java.checkouts.common.TypedFieldTestUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

class CartesianCheckoutFinderTests {
    @Test
    void storeIndependentFieldsPerThrow() {
        List<List<String>> namesPerThrow = new ArrayList<>(
                List.of(List.of("D2", "D4"), List.of("D4", "D6"))
        );

        List<List<Field>> fieldsPerThrow = TypedFieldTestUtils.getFieldsPerThrow(namesPerThrow);

        CheckoutFinder checkoutFinder = CartesianCheckoutFinder.of(fieldsPerThrow);
        int score = 16;

        long totalMultiplicity = CheckoutTestUtils.getTotalMultiplicity(checkoutFinder.find(score));

        namesPerThrow.clear();

        long newTotalMultiplicity = CheckoutTestUtils.getTotalMultiplicity(checkoutFinder.find(score));

        Assertions.assertEquals(totalMultiplicity, newTotalMultiplicity);
    }

    @ParameterizedTest
    @MethodSource("withEmptyFieldsPerThrow")
    void handleEmptyFieldsPerThrow(List<List<String>> namesPerThrow) {
        List<List<Field>> fieldsPerThrow = TypedFieldTestUtils.getFieldsPerThrow(namesPerThrow);
        CheckoutFinder checkoutFinder = CartesianCheckoutFinder.of(fieldsPerThrow);

        int score = 3;

        List<Checkout> checkouts = checkoutFinder.find(score);

        Assertions.assertEquals(0, checkouts.size());
    }

    @ParameterizedTest
    @MethodSource("withCheckouts")
    void findCheckouts(List<List<String>> namesPerThrow, int score, List<List<String>> namesPerCheckout) {
        List<List<Field>> fieldsPerThrow = TypedFieldTestUtils.getFieldsPerThrow(namesPerThrow);
        CheckoutFinder checkoutFinder = CartesianCheckoutFinder.of(fieldsPerThrow);

        List<Checkout> checkouts = checkoutFinder.find(score);

        Assertions.assertAll(
                () -> Assertions.assertEquals(namesPerCheckout.size(), checkouts.size()),
                () -> Assertions.assertEquals(score, checkouts.get(0).getScore()),
                () -> Assertions.assertEquals(score, CheckoutTestUtils.getTotalScore(checkouts) / checkouts.size()),
                () -> Assertions.assertEquals(namesPerThrow.size(), checkouts.get(0).countThrows()),
                () -> Assertions.assertEquals(namesPerThrow.size(), CheckoutTestUtils.countTotalThrows(checkouts) / checkouts.size()),
                () -> Assertions.assertEquals(1, checkouts.get(0).getMultiplicity()),
                () -> Assertions.assertEquals(namesPerCheckout.size(), CheckoutTestUtils.getTotalMultiplicity(checkouts)),
                () -> Assertions.assertEquals(namesPerCheckout, CheckoutTestUtils.getNamesPerCheckout(checkouts))
        );
    }

    private static Stream<Arguments> withEmptyFieldsPerThrow() {
        return Stream.of(
                Arguments.of(Collections.emptyList()),
                Arguments.of(List.of(Collections.emptyList())),
                Arguments.of(List.of(Collections.emptyList(), Collections.emptyList())),
                Arguments.of(List.of(Collections.emptyList(), List.of("D3"))),
                Arguments.of(List.of(List.of("T8", "D9"), Collections.emptyList())),
                Arguments.of(List.of(List.of("Q2"), Collections.emptyList(), List.of("25")))
        );
    }

    private static Stream<Arguments> withCheckouts() {
        return Stream.of(
                Arguments.of(
                        List.of(List.of("1")),
                        1,
                        List.of(List.of(List.of("1")))
                ),
                Arguments.of(
                        List.of(List.of("1"), List.of("2")),
                        3,
                        List.of(List.of(List.of("1"), List.of("2")))
                ),
                Arguments.of(
                        List.of(List.of("D4"), List.of("D4")),
                        16,
                        List.of(List.of(List.of("D4"), List.of("D4")))
                )
        );
    }
}
